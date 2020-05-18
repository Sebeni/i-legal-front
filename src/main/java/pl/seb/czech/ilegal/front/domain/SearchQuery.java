package pl.seb.czech.ilegal.front.domain;

import org.springframework.util.MultiValueMap;

public abstract class SearchQuery {
    protected MultiValueMap<String, String> queryParams;
    protected Integer resultLimitPerPage = 20;
    
    public abstract MultiValueMap<String, String> getQueryParams();

    public abstract void setPageNumber(int pageNumber);

    public abstract int getCurrentPageNumber();

    public Integer getResultLimitPerPage() {
        return resultLimitPerPage;
    }

    protected void addToQueryParamsNotNull(String queryAPIParamName, String fieldValue) {
        if (fieldValue != null && !fieldValue.isEmpty() && !fieldValue.equals("null")) {
            queryParams.add(queryAPIParamName, fieldValue);
        }
    }
}
