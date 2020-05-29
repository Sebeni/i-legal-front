package pl.seb.czech.ilegal.front.domain.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DeleteLog {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;
    private String sourceName;
    private Long beforeDeleteElementCount;
    private DeleteType deleteType;
}
