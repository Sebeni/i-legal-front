package pl.seb.czech.ilegal.front.client;

import org.springframework.web.util.UriComponentsBuilder;
import pl.seb.czech.ilegal.front.domain.SearchQuery;

import java.net.URI;

public abstract class URIGenerator {
    protected final String apiUrl;
    
    protected URIGenerator(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    protected URI generateSearchQueryUri(SearchQuery query, String searchEndpointUrl) {
        return UriComponentsBuilder.fromHttpUrl(apiUrl + searchEndpointUrl).queryParams(query.getQueryParams()).build().toUri();
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
