package pl.seb.czech.ilegal.front.client.judgment;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.front.client.Client;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.SearchResult;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;

@Component
public class SaosClient implements Client<JudgmentSynopsis> {

    @Override
    public SearchResult<JudgmentSynopsis> performSearchQuery(SearchQuery searchQuery) {
        return null;
    }
}
