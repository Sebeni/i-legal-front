package pl.seb.czech.ilegal.front.domain.judgment;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.front.domain.judgment.deserializer.JudgmentDetailsDeserializer;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = JudgmentDetailsDeserializer.class)
public class JudgmentDetails {
    private Long id;
    private String textContent;
    private ReferencedRegulation[] referencedRegulations;
    private String[] legalBases;
    private String[] keywords;
}
