package pl.seb.czech.ilegal.front.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Act {
    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Act act = (Act) o;
        return id.equals(act.id) &&
                publisher.equals(act.publisher) &&
                year.equals(act.year) &&
                Objects.equals(volume, act.volume) &&
                position.equals(act.position) &&
                title.equals(act.title) &&
                status.equals(act.status) &&
                promulgation.equals(act.promulgation) &&
                changeDate.equals(act.changeDate) &&
                entryIntoForce.equals(act.entryIntoForce);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publisher, year, volume, position, title, status, promulgation, changeDate, entryIntoForce);
    }
}
