package pl.seb.czech.ilegal.front.domain.judgment;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JudgmentDetails {
    private Long id;
    private Long saosId;
    private String textContent;
    private Set<ReferencedRegulation> referencedRegulations;
    private Set<String> legalBases;
    private Set<String> keywords;
}
