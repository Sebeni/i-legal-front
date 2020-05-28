package pl.seb.czech.ilegal.front.domain.judgment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReferencedRegulation {
    private String title;
    private Integer year;
    private Integer volume;
    private Integer position;
    private String text;
}
