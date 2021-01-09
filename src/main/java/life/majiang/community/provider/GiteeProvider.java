package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GitHubUser;
import okhttp3.*;

import java.io.IOException;


public class GiteeProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder().url("https://gitee.com/oauth/token").post(body).build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split(",")[0].split(":")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUser getUser(String token) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://gitee.com/api/v5/user?access_token=" + token.replace("\"", "");
        System.out.println(url);
        Request request = new Request.Builder().url(url).build();
        System.out.println(token);
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            GitHubUser gitUser = JSON.parseObject(string, GitHubUser.class);
            return gitUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
