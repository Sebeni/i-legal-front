package pl.seb.czech.ilegal.front.client;

import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.SearchResult;

public interface Client<T> {
    
    SearchResult<T> performSearchQuery(SearchQuery searchQuery);
}
