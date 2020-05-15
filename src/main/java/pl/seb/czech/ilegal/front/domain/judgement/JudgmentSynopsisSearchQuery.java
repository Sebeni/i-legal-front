package pl.seb.czech.ilegal.front.domain.judgement;

import org.springframework.util.MultiValueMap;
import pl.seb.czech.ilegal.front.domain.SearchQuery;

public class JudgmentSynopsisSearchQuery extends SearchQuery {
    
    @Override
    public void setPageNumber(int pageNumber) {
        
    }

    @Override
    public int getCurrentPageNumber() {
        return 0;
    }

    @Override
    public MultiValueMap<String, String> getQueryParams() {
        return null;
    }
}
