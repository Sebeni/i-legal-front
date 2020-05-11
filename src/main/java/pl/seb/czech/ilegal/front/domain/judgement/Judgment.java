package pl.seb.czech.ilegal.front.domain.judgement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.seb.czech.ilegal.front.domain.DummyEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Judgment implements DummyEntity<Long> {
    private Long id;
    private String courtType;
    
    @JsonProperty(value = "courtCases")
    @JsonDeserialize(using = CaseNumberDeserializer.class)
    private List<String> caseNumbers;
    
    private String judgmentType;
    private String textContent;
    private String[] keywords;
    private Division division;
    private Court court;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate judgmentDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Judgment judgment = (Judgment) o;
        return id.equals(judgment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
