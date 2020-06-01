package pl.seb.czech.ilegal.front.backend.clients.judgment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.front.backend.clients.Client;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentDetails;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsisSearchResult;

@Component
public class JudgmentSaosClient implements Client<JudgmentSynopsis> {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${url.base.search}")
    private String searchEndpoint;
    
    @Value("${url.judgments.saos}")
    private String performSearch;
    
    @Value("${url.judgments.saos.details}")
    private String getDetails;
    
    @Override
    public JudgmentSynopsisSearchResult performSearchQuery(SearchQuery searchQuery) {
        return restTemplate.postForObject(searchEndpoint + performSearch, searchQuery, JudgmentSynopsisSearchResult.class);
    }
    
    public JudgmentDetails getJudgmentDetails(Long saosId){
        return restTemplate.getForObject(searchEndpoint + getDetails + "/" + saosId, JudgmentDetails.class);
    }
}
