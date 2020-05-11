package pl.seb.czech.ilegal.front.stub.act;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.front.domain.act.Act;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ActExampleData {
    private List<String> jsonActs = new ArrayList<>();

    public ActExampleData() {
        String piolActJson = "{\n" +
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

        String pitActJson = "{\n" +
                "  \"address\": \"WDU19910800350\",\n" +
                "  \"publisher\": \"WDU\",\n" +
                "  \"year\": 1991,\n" +
                "  \"volume\": 80,\n" +
                "  \"pos\": 350,\n" +
                "  \"type\": \"Ustawa\",\n" +
                "  \"title\": \"Ustawa z dnia 26 lipca 1991 r. o podatku dochodowym od osób fizycznych.\",\n" +
                "  \"status\": \"akt posiada tekst jednolity\",\n" +
                "  \"displayAddress\": \"Dz.U. 1991 nr 80 poz. 350\",\n" +
                "  \"promulgation\": \"1991-09-10\",\n" +
                "  \"announcementDate\": \"1991-07-26\",\n" +
                "  \"changeDate\": \"2020-04-27 18:14:33\",\n" +
                "  \"entryIntoForce\": \"1992-01-01\",\n" +
                "  \"validFrom\": \"1992-01-01\",\n" +
                "  \"repealDate\": null,\n" +
                "  \"expirationDate\": null,\n" +
                "  \"legalStatusDate\": null}";
        
        String citActJson = "{\n" +
                "  \"address\": \"WDU19920210086\",\n" +
                "  \"publisher\": \"WDU\",\n" +
                "  \"year\": 1992,\n" +
                "  \"volume\": 21,\n" +
                "  \"pos\": 86,\n" +
                "  \"type\": \"Ustawa\",\n" +
                "  \"title\": \"Ustawa z dnia 15 lutego 1992 r. o podatku dochodowym od osób prawnych\",\n" +
                "  \"status\": \"akt posiada tekst jednolity\",\n" +
                "  \"displayAddress\": \"Dz.U. 1992 nr 21 poz. 86\",\n" +
                "  \"promulgation\": \"1992-03-10\",\n" +
                "  \"announcementDate\": \"1992-02-15\",\n" +
                "  \"changeDate\": \"2020-04-27 17:42:31\",\n" +
                "  \"entryIntoForce\": \"1992-03-10\",\n" +
                "  \"validFrom\": \"1992-01-01\",\n" +
                "  \"repealDate\": null}";
        
        String pccActJson = "{\n" +
                "  \"address\": \"WDU20000860959\",\n" +
                "  \"publisher\": \"WDU\",\n" +
                "  \"year\": 2000,\n" +
                "  \"volume\": 86,\n" +
                "  \"pos\": 959,\n" +
                "  \"type\": \"Ustawa\",\n" +
                "  \"title\": \"Ustawa z dnia 9 września 2000 r. o podatku od czynności cywilnoprawnych.\",\n" +
                "  \"status\": \"akt posiada tekst jednolity\",\n" +
                "  \"displayAddress\": \"Dz.U. 2000 nr 86 poz. 959\",\n" +
                "  \"promulgation\": \"2000-10-17\",\n" +
                "  \"announcementDate\": \"2000-09-09\",\n" +
                "  \"changeDate\": \"2020-04-28 13:45:46\",\n" +
                "  \"entryIntoForce\": \"2001-01-01\",\n" +
                "  \"validFrom\": \"2001-01-01\",\n" +
                "  \"repealDate\": null,\n" +
                "  \"expirationDate\": null}";
        
        String vatActJson = "{\n" +
                "  \"address\": \"WDU20040540535\",\n" +
                "  \"publisher\": \"WDU\",\n" +
                "  \"year\": 2004,\n" +
                "  \"volume\": 54,\n" +
                "  \"pos\": 535,\n" +
                "  \"type\": \"Ustawa\",\n" +
                "  \"title\": \"Ustawa z dnia 11 marca 2004 r. o podatku od towarów i usług\",\n" +
                "  \"status\": \"akt posiada tekst jednolity\",\n" +
                "  \"displayAddress\": \"Dz.U. 2004 nr 54 poz. 535\",\n" +
                "  \"promulgation\": \"2004-04-05\",\n" +
                "  \"announcementDate\": \"2004-03-11\",\n" +
                "  \"changeDate\": \"2020-04-14 13:31:40\",\n" +
                "  \"entryIntoForce\": \"2004-04-20\",\n" +
                "  \"validFrom\": \"2004-04-20\",\n" +
                "  \"repealDate\": null,\n" +
                "  \"expirationDate\": null,\n" +
                "  \"legalStatusDate\": null}";
        
        String upiolRates2020NoticeJson = "{\n" +
                "  \"address\": \"WMP20190000738\",\n" +
                "  \"publisher\": \"WMP\",\n" +
                "  \"year\": 2019,\n" +
                "  \"volume\": 0,\n" +
                "  \"pos\": 738,\n" +
                "  \"type\": \"Obwieszczenie\",\n" +
                "  \"title\": \"Obwieszczenie Ministra Finansów z dnia 24 lipca 2019 r. w sprawie górnych granic stawek kwotowych podatków i opłat lokalnych na rok 2020\",\n" +
                "  \"status\": \"bez statusu\",\n" +
                "  \"displayAddress\": \"M.P. 2019 poz. 738\",\n" +
                "  \"promulgation\": \"2019-08-06\",\n" +
                "  \"announcementDate\": \"2019-07-24\",\n" +
                "  \"changeDate\": \"2019-09-04 12:10:11\",\n" +
                "  \"entryIntoForce\": null,\n" +
                "  \"validFrom\": null,\n" +
                "  \"repealDate\": null}";
        
        jsonActs.addAll(Arrays.asList(piolActJson, pitActJson, citActJson, pccActJson, vatActJson, upiolRates2020NoticeJson));
    }

    public List<String> getJsonActs() {
        return jsonActs;
    }
}
