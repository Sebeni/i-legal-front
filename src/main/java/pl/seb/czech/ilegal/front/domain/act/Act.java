package pl.seb.czech.ilegal.front.domain.act;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.seb.czech.ilegal.front.domain.DummyEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Act implements DummyEntity<String> {
    @JsonProperty(value = "address")
    private String id;
    private String publisher;
    private Integer year;
    private Integer volume;
    @JsonProperty(value = "pos")
    private Integer position;
    private String title;
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate promulgation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime changeDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate entryIntoForce;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate repealDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Act act = (Act) o;
        return id.equals(act.id) &&
                Objects.equals(publisher, act.publisher) &&
                Objects.equals(year, act.year) &&
                Objects.equals(volume, act.volume) &&
                Objects.equals(position, act.position) &&
                Objects.equals(title, act.title) &&
                Objects.equals(status, act.status) &&
                Objects.equals(promulgation, act.promulgation) &&
                Objects.equals(changeDate, act.changeDate) &&
                Objects.equals(entryIntoForce, act.entryIntoForce) &&
                Objects.equals(repealDate, act.repealDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publisher, year, volume, position, title, status, promulgation, changeDate, entryIntoForce, repealDate);
    }
}
