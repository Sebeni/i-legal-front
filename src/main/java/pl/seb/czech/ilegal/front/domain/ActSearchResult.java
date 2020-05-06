package pl.seb.czech.ilegal.front.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActSearchResult {
    
    @JsonProperty("items")
    private Act[] foundActs;
    
    private Integer count;
    private Integer totalCount;
    
    public List<Act> getFoundActsList() {
        return foundActs != null ? Arrays.asList(foundActs) : new ArrayList<>();
    }
    
}
