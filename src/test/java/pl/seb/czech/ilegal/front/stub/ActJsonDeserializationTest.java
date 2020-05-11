package pl.seb.czech.ilegal.front.stub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.front.domain.act.Act;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActJsonDeserializationTest {

    @Autowired
    ObjectMapper objectMapper;
    
    @Test
    void shouldDeserializeJsonToAct() {
        String upiolActJson = "{\n" +
                "  \"address\": \"WDU19910090031\",\n" +
                "  \"publisher\": \"WDU\",\n" +
                "  \"year\": 1991,\n" +
                "  \"volume\": 9,\n" +
                "  \"pos\": 31,\n" +
                "  \"type\": \"Ustawa\",\n" +
                "  \"title\": \"Ustawa z dnia 12 stycznia 1991 r. o podatkach i opłatach lokalnych.\",\n" +
                "  \"status\": \"akt posiada tekst jednolity\",\n" +
                "  \"displayAddress\": \"Dz.U. 1991 nr 9 poz. 31\",\n" +
                "  \"promulgation\": \"1991-01-30\",\n" +
                "  \"announcementDate\": \"1991-01-12\",\n" +
                "  \"changeDate\": \"2019-06-28 13:39:43\",\n" +
                "  \"entryIntoForce\": \"1991-01-30\",\n" +
                "  \"validFrom\": \"1991-01-30\",\n" +
                "  \"repealDate\": null}";
        
        
        Act actFromJson = new Act();
        try {
            actFromJson = objectMapper.readValue(upiolActJson, Act.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        String idFromJson = actFromJson.getId();
        LocalDate promulgationDate = actFromJson.getPromulgation();
        LocalDateTime lastChange = actFromJson.getChangeDate();
        
        assertAll(
                () -> assertEquals("WDU19910090031", idFromJson),
                () -> assertEquals(LocalDate.of(1991, 1, 30), promulgationDate),
                () -> assertEquals(LocalDateTime.of(2019, 6, 28, 13, 39, 43), lastChange)
        );
    }
}