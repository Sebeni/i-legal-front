package pl.seb.czech.ilegal.front.client.act;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.front.client.Client;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.SearchResult;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.domain.act.ActSearchResult;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class IsapClient implements Client<Act> {
    private final IsapURIGenerator isapURIGenerator;
    private RestTemplate restTemplate;
    private final static Logger LOGGER = LoggerFactory.getLogger(IsapClient.class);

    @Autowired
    public IsapClient(IsapURIGenerator isapURIGenerator, RestTemplate restTemplate) {
        this.isapURIGenerator = isapURIGenerator;
        this.restTemplate = restTemplate;
    }

    public URI generateDownloadActURI(Act actToDownload, ActTextType textType){
        return isapURIGenerator.generateDownloadActURI(actToDownload, textType);
    }
    
    public boolean validateTxtExists(URI uri) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        HttpStatus responseStatus = null;
        try {
            ClientHttpRequest request = factory.createRequest(uri, HttpMethod.GET);
            responseStatus = request.execute().getStatusCode();
        } catch (IOException e) {
            LOGGER.error("Exception in button checking", e);
        }
        return responseStatus == HttpStatus.OK;
    }
    
//    TODO ideally get this only periodically (no need for fetching each time the form is created) 
//     because there are 2k+ keywords; 
//     Create entity or cache to store it?
    public List<String> getAllKeywordsAndNames() {
        String endpointURL = "/keywords";
        String[] result = restTemplate.getForObject(isapURIGenerator.getApiUrl() + endpointURL, String[].class);
        return result != null ? Arrays.asList(result) : new ArrayList<>();
    }
    
    @Override
    public SearchResult<Act> performSearchQuery(SearchQuery searchQuery) {
        URI requestUri = isapURIGenerator.generateSearchQueryUri(searchQuery);
        return restTemplate.getForObject(requestUri, ActSearchResult.class);
    }
}
