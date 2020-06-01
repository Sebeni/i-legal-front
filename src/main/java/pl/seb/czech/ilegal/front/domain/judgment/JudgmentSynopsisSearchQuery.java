package pl.seb.czech.ilegal.front.domain.judgment;

import lombok.*;
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
}
