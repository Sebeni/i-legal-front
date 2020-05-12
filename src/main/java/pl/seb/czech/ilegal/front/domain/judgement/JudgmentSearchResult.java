package pl.seb.czech.ilegal.front.domain.judgement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.front.domain.SearchResult;
import pl.seb.czech.ilegal.front.domain.judgement.deserializer.TotalResultsDeserializer;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JudgmentSearchResult implements SearchResult {
    
    @JsonProperty(value = "items")
    private JudgmentSynopsis[] results;

    
    @JsonProperty(value = "info")
    @JsonDeserialize(using = TotalResultsDeserializer.class)
    private Integer numOfResults;

    @Override
    public Integer getNumOfResults() {
        return numOfResults;
    }
}
