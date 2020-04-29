package pl.seb.czech.ilegal.front.stub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.front.domain.Act;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActJsonDeserializationTest {

    @Autowired
    ObjectMapper objectMapper;
    
    @Test
    void serializationTest() {
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

        System.out.println(actFromJson.getChangeDate());
        System.out.println(actFromJson.getPromulgation());
    }
}