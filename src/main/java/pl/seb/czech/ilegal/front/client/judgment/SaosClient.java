package pl.seb.czech.ilegal.front.client.judgment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.front.client.Client;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.SearchResult;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentDetails;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsisSearchResult;

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
    public SearchResult<JudgmentSynopsis> performSearchQuery(SearchQuery searchQuery) {
        URI searchUri = saosURIGenerator.generateSearchQueryUri(searchQuery);
        System.out.println(searchUri);
        return restTemplate.getForObject(searchUri, JudgmentSynopsisSearchResult.class);
    }
    
    public JudgmentDetails getJudgmentDetails(JudgmentSynopsis judgmentSynopsis){ 
        String searchURL = saosURIGenerator.getApiUrl() + "/judgments/" + judgmentSynopsis.getId();
        return restTemplate.getForObject(searchURL, JudgmentDetails.class);
    }
}
