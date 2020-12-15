package life.majiang.community.dto;

import life.majiang.community.model.Question;
import lombok.Data;

import java.util.List;

@Data
public class PaginationDTO
{
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;
    private List<Integer> pages;
}
