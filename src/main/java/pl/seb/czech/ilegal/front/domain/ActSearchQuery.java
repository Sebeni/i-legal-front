package pl.seb.czech.ilegal.front.domain;

import lombok.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pl.seb.czech.ilegal.front.ui.components.ActsSearchForm;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ActSearchQuery {
    private String onlyActInForce;
    private String actName;
    private String keyWord;
    private String properName;
    private String publisher;
    private String year;
    private String position;
    private MultiValueMap<String, String> queryParams;

    public MultiValueMap<String, String> getQueryParams() {
        queryParams = new LinkedMultiValueMap<>();
        if (onlyActInForce != null) {
            if (onlyActInForce.equals(ActsSearchForm.IN_FORCE_ACTS_ITEM)) {
                queryParams.add("inForce", "1");
            }
        }

        addToQueryParamsNotNull("title", actName);
        addToQueryParamsNotNull("keyword", keyWord);
        addToQueryParamsNotNull("keywordName", properName);

        if (publisher != null) {
            if (!publisher.equals(ActsSearchForm.ALL_PUBLISHERS)) {
                if (publisher.equals(ActsSearchForm.DZ_U)) {
                    queryParams.add("publisher", "WDU");
                } else if (publisher.equals(ActsSearchForm.M_P)) {
                    queryParams.add("publisher", "WMP");
                }
            }
        }

        addToQueryParamsNotNull("year", year);
        addToQueryParamsNotNull("position", position);
        return queryParams;
    }

    private void addToQueryParamsNotNull(String queryAPIParamName, String fieldValue) {
        if (fieldValue != null) {
            queryParams.add(queryAPIParamName, fieldValue);
        }
    }


}
