package pl.seb.czech.ilegal.front.backend.clients.act;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.front.backend.clients.Client;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.domain.act.ActSearchResult;

import java.io.IOException;
import java.net.URI;
import java.util.*;

@Component
public class ActIsapClient implements Client<Act> {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;
    
    @Value("${url.base.search}")
    private String searchEndpoint;
    @Value("${url.acts.isap}")
    private String performSearchEndpoint;
    @Value("${url.acts.isap.text.link}")
    private String getTxtDownloadUri;
    @Value("${url.acts.isap.text.link.check}")
    private String validateTxt;


    @Override
    public ActSearchResult performSearchQuery(SearchQuery searchQuery) {
        return restTemplate.postForObject(searchEndpoint + performSearchEndpoint, searchQuery, ActSearchResult.class);
    }

    public URI getTxtDownloadUri(String isapId, ActTextType textType){
        return restTemplate.getForObject(searchEndpoint + getTxtDownloadUri + "/" + isapId + "/" + textType, URI.class);
    }
    
    public Boolean validateTxtExists(URI uri) {
        return restTemplate.getForObject(searchEndpoint + validateTxt + "?uri=" + uri.toString(), Boolean.class);
    }
}
