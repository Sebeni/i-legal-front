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
public class ActSearchQuery implements SearchQuery {
    private String onlyActInForce;
    private String actName;
    private String keyWord;
    private String properName;
    private String publisher;
    private String year;
    private String position;
    private MultiValueMap<String, String> queryParams;
    
    private Integer resultLimitPerPage = 20;
    private Integer offset = 0; 

    public MultiValueMap<String, String> getQueryParams() {
        queryParams = new LinkedMultiValueMap<>();
        if (onlyActInForce != null) {
            if (onlyActInForce.equals(ActSearchForm.IN_FORCE_ACTS_ITEM)) {
                queryParams.add("inForce", "1");
            }
        }

        addToQueryParamsNotNull("title", actName);
        addToQueryParamsNotNull("keyword", keyWord);
        addToQueryParamsNotNull("keywordName", properName);

        if (publisher != null) {
            if (!publisher.equals(ActSearchForm.ALL_PUBLISHERS)) {
                if (publisher.equals(ActSearchForm.DZ_U)) {
                    queryParams.add("publisher", "WDU");
                } else if (publisher.equals(ActSearchForm.M_P)) {
                    queryParams.add("publisher", "WMP");
                }
            }
        }

        addToQueryParamsNotNull("year", year);
        addToQueryParamsNotNull("position", position);
        addToQueryParamsNotNull("limit", String.valueOf(resultLimitPerPage));
        addToQueryParamsNotNull("offset", String.valueOf(offset));
        return queryParams;
    }

    private void addToQueryParamsNotNull(String queryAPIParamName, String fieldValue) {
        if (fieldValue != null && !fieldValue.isEmpty() && !fieldValue.equals("null")) {
            queryParams.add(queryAPIParamName, fieldValue);
        }
    }

    public void setPageNumber(int pageNumber) {
        if(pageNumber > 0) {
            offset = ((pageNumber - 1) * resultLimitPerPage);
        } 
    }
    
    public int getCurrentPageNumber() {
        return (offset / resultLimitPerPage) + 1;
    }

    @Override
    public Integer getResultLimitPerPage() {
        return resultLimitPerPage;
    }
}
