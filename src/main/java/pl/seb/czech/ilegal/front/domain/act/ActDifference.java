package pl.seb.czech.ilegal.front.domain.act;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActDifference {
    private final String beforeUpdate = "PRZED AKTUALIZACJĄ";
    private final String afterUpdate = "PO AKTUALIZACJI";
    
    private Long id;
    private String title;
    private String statusBefore;
    private String statusAfter;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastChangeBefore;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastChangeAfter;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
    private boolean differentAfter;
    
    public String getBeforeChangesText(){
        return generateText(beforeUpdate);
    }
    
    public String getAfterChangesText(){
        return generateText(afterUpdate);
    }
    
    private String generateText(String beforeOrAfter) {
        StringBuilder sb = new StringBuilder(beforeOrAfter)
                .append(" ost. zmiana: ")
                .append((beforeOrAfter.equals(beforeUpdate) ? lastChangeBefore : lastChangeAfter));

        if(statusesAreNotEqual()) {
            sb.append(" status: ").append((beforeOrAfter.equals(beforeUpdate) ? statusBefore : statusAfter));
        }
        return sb.toString();
    }
    
    private boolean statusesAreNotEqual(){
        return !statusBefore.equals(statusAfter);
    }
}
