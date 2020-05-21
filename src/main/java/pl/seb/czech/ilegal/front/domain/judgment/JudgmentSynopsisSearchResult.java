package pl.seb.czech.ilegal.front.domain.judgment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.front.domain.SearchResult;
import pl.seb.czech.ilegal.front.domain.judgment.deserializer.PageNumberDeserializer;
import pl.seb.czech.ilegal.front.domain.judgment.deserializer.TotalResultsDeserializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JudgmentSynopsisSearchResult implements SearchResult<JudgmentSynopsis> {
    
    @JsonProperty(value = "items")
    private JudgmentSynopsis[] results;
    
    @JsonProperty(value = "info")
    @JsonDeserialize(using = TotalResultsDeserializer.class)
    private Integer numOfResults;
    
    @JsonProperty(value = "queryTemplate")
    @JsonDeserialize(using = PageNumberDeserializer.class)
    private Integer pageNumber;

    @Override
    public Integer getNumOfResults() {
        return numOfResults;
    }

    @Override
    public List<JudgmentSynopsis> getResultsList() {
        return results != null ? Arrays.asList(results) : new ArrayList<>();
    }
}
