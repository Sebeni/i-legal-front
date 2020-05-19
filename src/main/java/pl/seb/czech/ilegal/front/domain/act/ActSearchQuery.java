package pl.seb.czech.ilegal.front.domain.act;

import lombok.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.ui.components.act.ActSearchForm;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ActSearchQuery extends SearchQuery {
    private String onlyActInForce;
    private String title;
    private String keyWord;
    private String properName;
    private ActPublisher publisher;
    private Integer year;
    private Integer position;

    private Integer offset = 0;

    @Override
    public MultiValueMap<String, String> getQueryParams() {
        queryParams = new LinkedMultiValueMap<>();
        if (onlyActInForce != null) {
            if (onlyActInForce.equals(ActSearchForm.IN_FORCE_ACTS_ITEM)) {
                queryParams.add("inForce", "1");
            }
        }

        addToQueryParamsNotNull("title", title);
        addToQueryParamsNotNull("keyword", keyWord);
        addToQueryParamsNotNull("keywordName", properName);

        if (publisher != null) {
            if (!publisher.equals(ActPublisher.ALL)) {
                queryParams.add("publisher", publisher.name());
            }
        }

        addToQueryParamsNotNull("year", year);
        addToQueryParamsNotNull("position", position);
        addToQueryParamsNotNull("limit", resultLimitPerPage);
        addToQueryParamsNotNull("offset", offset);
        return queryParams;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber > 0) {
            offset = ((pageNumber - 1) * resultLimitPerPage);
        }
    }

    public int getCurrentPageNumber() {
        return (offset / resultLimitPerPage) + 1;
    }

}
