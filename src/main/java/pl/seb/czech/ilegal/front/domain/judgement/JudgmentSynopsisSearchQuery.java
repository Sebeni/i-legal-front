package pl.seb.czech.ilegal.front.domain.judgement;

import lombok.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pl.seb.czech.ilegal.front.domain.SearchQuery;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JudgmentSynopsisSearchQuery extends SearchQuery {
    private CourtType courtType;
    private String signature;
    private Integer articleNumber;
    private String referencedRegulationYearPos;
    private String searchPhrase;
    
    private Integer pageNumber = 0;
    private String sortingField = "JUDGMENT_DATE";
    private String sortingDirection = "DESC";
    
    @Override
    public void setPageNumber(int pageNumber) {
        if(pageNumber > 0){
            this.pageNumber = pageNumber - 1;
        }
    }

    @Override
    public int getCurrentPageNumber() {
        return pageNumber + 1;
    }

    @Override
    public MultiValueMap<String, String> getQueryParams() {
        queryParams = new LinkedMultiValueMap<>();
        if(courtType != null && courtType != CourtType.ALL) {
            queryParams.add("courtType", courtType.name());
        }
        
        addToQueryParamsNotNull("caseNumber", signature);
        
        String articleNumberString = null;
        if(articleNumber != null && articleNumber > 0){
            articleNumberString = "\"art. " + articleNumber + "\"";
        }
     
        addToQueryParamsNotNull("referencedRegulation", articleNumberString);
        
        if(referencedRegulationYearPos != null){
            if(referencedRegulationYearPos.matches("^\\d{4}/\\d+$")){
                addToQueryParamsNotNull("lawJournalEntryCode", referencedRegulationYearPos);
            }
        }
        
        addToQueryParamsNotNull("all", searchPhrase);
        addToQueryParamsNotNull("pageSize", resultLimitPerPage);
        addToQueryParamsNotNull("pageNumber", pageNumber);
        addToQueryParamsNotNull("sortingField", sortingField);
        addToQueryParamsNotNull("sortingDirection", sortingDirection);
       
        return queryParams;
    }
}
