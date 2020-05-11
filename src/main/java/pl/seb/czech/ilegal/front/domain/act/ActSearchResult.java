package pl.seb.czech.ilegal.front.domain.act;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.seb.czech.ilegal.front.domain.SearchResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActSearchResult implements SearchResult {
    
    @JsonProperty("items")
    private Act[] foundActs;
    
    private Integer offset;
    private Integer count;
    
    @JsonProperty("totalCount")
    private Integer numOfResults;
    
    public List<Act> getFoundActsList() {
        return foundActs != null ? Arrays.asList(foundActs) : new ArrayList<>();
    }
    
    
    
}
