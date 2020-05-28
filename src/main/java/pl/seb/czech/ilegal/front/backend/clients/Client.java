package pl.seb.czech.ilegal.front.backend.clients;

import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.SearchResult;

public interface Client<T> {
    
    SearchResult<T> performSearchQuery(SearchQuery searchQuery);
}
