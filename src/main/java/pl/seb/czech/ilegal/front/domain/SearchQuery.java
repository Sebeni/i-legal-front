package pl.seb.czech.ilegal.front.domain;

import org.springframework.util.MultiValueMap;

public interface SearchQuery {
    MultiValueMap<String, String> getQueryParams();
    void setPageNumber(int pageNumber);
    int getCurrentPageNumber();
    Integer getResultLimitPerPage();
}
