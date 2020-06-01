package pl.seb.czech.ilegal.front.domain.judgment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.front.domain.SearchResult;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class JudgmentSynopsisSearchResult implements SearchResult<JudgmentSynopsis> {
    
    private List<JudgmentSynopsis> results;
    private Integer numOfResults;
    private Integer pageNumber;

    @Override
    public Integer getNumOfResults() {
        return numOfResults;
    }

    @Override
    public List<JudgmentSynopsis> getResultsList() {
        return results != null ? results : new ArrayList<>();
    }
}
