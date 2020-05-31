package pl.seb.czech.ilegal.front.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class NowTime {
    public static LocalDateTime generate() {
        return LocalDateTime.now(ZoneId.of("GMT+2")).truncatedTo(ChronoUnit.MILLIS);
    }
}
