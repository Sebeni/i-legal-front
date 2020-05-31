package pl.seb.czech.ilegal.front.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChangeViewLog {
    private Long id;
    private String viewName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = NowTime.generate();

    public ChangeViewLog(String viewName) {
        this.viewName = viewName;
    }
}
