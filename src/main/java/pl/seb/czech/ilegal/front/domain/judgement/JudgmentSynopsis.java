package pl.seb.czech.ilegal.front.domain.judgement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import pl.seb.czech.ilegal.front.domain.DummyEntity;
import pl.seb.czech.ilegal.front.domain.judgement.deserializer.CaseNumberDeserializer;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JudgmentSynopsis implements DummyEntity<Long> {
    private Long id;
    private CourtType courtType;

    @JsonProperty(value = "courtCases")
    @JsonDeserialize(using = CaseNumberDeserializer.class)
    private List<String> caseNumbers;

    private JudgmentType judgmentType;
    
    @Setter
    private String customName;
    
    @JsonProperty(value = "textContent")
    private String synopsis;
    private String[] keywords;
    
    @Setter
    private JudgmentDetails judgmentDetails;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate judgmentDate;


    public String getCustomName() {
        if(customName == null) {
            return caseNumbers.get(0);
        } else {
            return customName;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JudgmentSynopsis judgmentSynopsis = (JudgmentSynopsis) o;
        return id.equals(judgmentSynopsis.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
