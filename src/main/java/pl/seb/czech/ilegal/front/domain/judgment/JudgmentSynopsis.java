package pl.seb.czech.ilegal.front.domain.judgment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import pl.seb.czech.ilegal.front.domain.BaseEntity;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JudgmentSynopsis implements BaseEntity {
    private Long id;
    private Long saosId;
    private CourtType courtType;
    private Set<String> caseNumbers;
    private JudgmentType judgmentType;
    @Setter
    private String customName;
    private String synopsis;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate judgmentDate;
    @Setter
    private JudgmentDetails judgmentDetails;

    public String getCustomName() {
        if(customName == null) {
            return caseNumbers.toString();
        } else {
            return customName;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JudgmentSynopsis judgmentSynopsis = (JudgmentSynopsis) o;
        return saosId.equals(judgmentSynopsis.saosId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saosId);
    }
    
}
