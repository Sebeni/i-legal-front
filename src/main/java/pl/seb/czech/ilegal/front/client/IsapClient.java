package pl.seb.czech.ilegal.front.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.front.domain.Act;
import pl.seb.czech.ilegal.front.domain.ActSearchQuery;
import pl.seb.czech.ilegal.front.domain.ActSearchResult;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class IsapClient {
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
    
    public boolean checkIfActTxtFileExists(URI uri) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        HttpStatus responseStatus = null;
        try {
            ClientHttpRequest request = factory.createRequest(uri, HttpMethod.GET);
            responseStatus = request.execute().getStatusCode();
        } catch (IOException e) {
            LOGGER.error("Exception in button checking\n" + e + "\n" + Arrays.toString(e.getStackTrace()));
        }
        return responseStatus == HttpStatus.OK;
    }
    
//    TODO ideally get this only periodically (no need for fetching each time the form is created) 
//     because there are 2k+ keywords; 
//     Create entity to store it?
    public List<String> getAllKeywordsAndNames() {
        String endpointURL = "/keywords";
        String[] result = restTemplate.getForObject(IsapURIGenerator.ISAP_API_URL + endpointURL, String[].class);
        return result != null ? Arrays.asList(result) : new ArrayList<>();
    }
    
    
    public ActSearchResult performActSearchQuery(ActSearchQuery query) {
        URI requestUri = isapURIGenerator.generateSearchActQueryUri(query);
        return restTemplate.getForObject(requestUri, ActSearchResult.class);
    }
    
    
    
}
