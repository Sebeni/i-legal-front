package pl.seb.czech.ilegal.front.client.judgment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.front.client.Client;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentDetails;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsisSearchResult;

import java.net.URI;

@Component
public class SaosClient implements Client<JudgmentSynopsis> {
    private RestTemplate restTemplate;
    private SaosURIGenerator saosURIGenerator;

    @Autowired
    public SaosClient(RestTemplate restTemplate, SaosURIGenerator saosURIGenerator) {
        this.restTemplate = restTemplate;
        this.saosURIGenerator = saosURIGenerator;
    }
    
    @Override
    public JudgmentSynopsisSearchResult performSearchQuery(SearchQuery searchQuery) {
        URI searchUri = saosURIGenerator.generateSearchQueryUri(searchQuery);
        return restTemplate.getForObject(searchUri, JudgmentSynopsisSearchResult.class);
    }
    
    public JudgmentDetails getJudgmentDetails(JudgmentSynopsis judgmentSynopsis){ 
        String searchURL = saosURIGenerator.getApiUrl() + "/judgments/" + judgmentSynopsis.getApiId();
        return restTemplate.getForObject(searchURL, JudgmentDetails.class);
    }
}
