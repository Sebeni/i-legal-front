package pl.seb.czech.ilegal.front.backend.clients.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.front.backend.clients.Client;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.domain.act.ActSearchResult;

import java.net.URI;

@Component
public class ActIsapClient implements Client<Act> {
    @Autowired
    private RestTemplate restTemplate;
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
