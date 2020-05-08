package pl.seb.czech.ilegal.front.ui.view;


import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.SearchResult;

public interface SearchView {
    void performSearchAndSetGrid(int pageNumber);
    
    SearchResult getSearchResult();
    SearchQuery getSearchQuery();
    
}
