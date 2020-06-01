package pl.seb.czech.ilegal.front.domain.act;

import lombok.*;
import pl.seb.czech.ilegal.front.domain.SearchQuery;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ActSearchQuery extends SearchQuery {
    public static final String IN_FORCE_ACTS = "Obowiązujące";
    public static final String ALL_ACTS = "Wszystkie";
    private String onlyActInForce;
    private String title;
    private String keyWord;
    private String properName;
    private ActPublisher publisher;
    private Integer year;
    private Integer position;

    private Integer offset = 0;
    
    public void setPageNumber(int pageNumber) {
        if (pageNumber > 0) {
            offset = ((pageNumber - 1) * resultLimitPerPage);
        }
    }

    public int getCurrentPageNumber() {
        return (offset / resultLimitPerPage) + 1;
    }
}
