package pl.seb.czech.ilegal.front.domain.judgment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import pl.seb.czech.ilegal.front.domain.BaseEntity;
import pl.seb.czech.ilegal.front.domain.judgment.deserializer.CaseNumberDeserializer;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JudgmentSynopsis implements BaseEntity<Long> {
    
    @JsonProperty(value = "id")
    private Long saosId;
    private CourtType courtType;

    @JsonProperty(value = "courtCases")
    @JsonDeserialize(using = CaseNumberDeserializer.class)
    private List<String> caseNumbers;

    private JudgmentType judgmentType;
    
    @Setter
    private String customName;
    
    @JsonProperty(value = "textContent")
    private String synopsis;
    private List<String> keywords;
    
    @Setter
    private JudgmentDetails judgmentDetails;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate judgmentDate;

    public String getCustomName() {
        if(customName == null) {
            StringBuilder sb = new StringBuilder(caseNumbers.get(0));
            if(keywords != null && keywords.size() > 0) {
                sb.append("\n");
                sb.append(keywords);
            }
            return sb.toString();
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

    @Override
    public Long getApiId() {
        return saosId;
    }
}
