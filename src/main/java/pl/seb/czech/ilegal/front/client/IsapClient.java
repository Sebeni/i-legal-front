package pl.seb.czech.ilegal.front.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.seb.czech.ilegal.front.domain.Act;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IsapClient {
    private RestTemplate restTemplate;
    private ActFilenameGenerator generator;
    
    private final static String ISAP_API_URL = "http://isap.sejm.gov.pl/api/isap";

    @Autowired
    public IsapClient(RestTemplate restTemplate, ActFilenameGenerator generator) {
        this.restTemplate = restTemplate;
        this.generator = generator;
    }


    public URI generateDownloadActURI(Act actToDownload, ActTextType textType){
        String downloadEndpointURL = ISAP_API_URL + "/deeds/{publisher}/{year}/{position}/text/{type}/{fileName}";
        Map<String, String> params = new HashMap<>();
        params.put("publisher", actToDownload.getPublisher());
        params.put("year", actToDownload.getYear().toString());
        params.put("position", actToDownload.getPosition().toString());
        params.put("type", textType == ActTextType.UNIFIED ? "U" : "O");
        String fileName = textType == ActTextType.UNIFIED ? 
                generator.generateUnifiedTxtFilename(actToDownload) 
                : generator.generatePublishedTxtFileName(actToDownload);
        params.put("fileName", fileName);
        
        return UriComponentsBuilder.fromUriString(downloadEndpointURL).buildAndExpand(params).toUri();
    }
    
    public boolean checkIfActTxtFileExists(URI uri) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        HttpStatus responseStatus = null;
        try {
            ClientHttpRequest request = factory.createRequest(uri, HttpMethod.GET);
            responseStatus = request.execute().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseStatus == HttpStatus.OK;
    }
    
//    TODO ideally get this only periodically (no need for fetching each time the form is created) 
//     because there are 2k+ keywords; 
//     Create entity to store it?
    public List<String> getAllKeywordsAndNames() {
        String endpointURL = "/keywords";
        @SuppressWarnings("unchecked")
        List<String> result = restTemplate.getForEntity(ISAP_API_URL + endpointURL, List.class).getBody();
        return result;
    }
    
}
