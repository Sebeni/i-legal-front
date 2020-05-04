package pl.seb.czech.ilegal.front.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.seb.czech.ilegal.front.domain.Act;
import pl.seb.czech.ilegal.front.ui.view.savedActs.ActFilenameGenerator;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
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
    
    public boolean checkIfFileExists(URI uri) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        ClientHttpRequest request = null;
        HttpStatus responseStatus = null;
        try {
            request = factory.createRequest(uri, HttpMethod.GET);
            responseStatus = request.execute().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return responseStatus == HttpStatus.OK;


    }
}
