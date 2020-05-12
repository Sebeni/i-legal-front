package pl.seb.czech.ilegal.front.stub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JudgmentSynopsisDeserializationTest {
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Disabled
    @Test
    void shouldDeserializeSupremeCourtJudgement() {
        String supremeCourt = "{\n" +
                "            \"id\": 76256,\n" +
                "            \"href\": \"https://www.saos.org.pl/api/judgments/76256\",\n" +
                "            \"courtType\": \"SUPREME\",\n" +
                "            \"courtCases\": [\n" +
                "                {\n" +
                "                    \"caseNumber\": \"SNO 10/10\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"judgmentType\": \"SENTENCE\",\n" +
                "            \"judges\": [\n" +
                "                {\n" +
                "                    \"name\": \"Marian Buliński\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Piotr Hofmański\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": [\n" +
                "                        \"PRESIDING_JUDGE\"\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Przemysław Kalinowski\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": [\n" +
                "                        \"REASONS_FOR_JUDGMENT_AUTHOR\",\n" +
                "                        \"REPORTING_JUDGE\"\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"textContent\": \"WYROK Z DNIA 15 MARCA 2010 R. SNO 10/10 Przewodniczący: sędzia SN Piotr Hofmański. Sędziowie SN: Przemysław Kalinowski (sprawozdawca), Marian Buliński. S ą d N a j w y ż s z y – S ą d D y s c y p l i n a rny z udziałem sędziego – Zastępcy Rzecznika Dyscyplinarnego Sądu Okręgowego oraz protokolanta po rozpoznaniu w dniu 15 marca 2010 r. sprawy sędziego Sądu Rejonowego w związku z odwołaniem Ministra Sprawiedliwości od wyroku Sądu Apelacyjnego – Sądu Dyscyplinarnego z dnia 20 listopada 2009 r., sygn. ASD (...) 1) z m i e n i ł zaskarżony w y r o k w ten sposób, że w miejsce orzeczonej wobec sędziego Sądu Rejonowego kary dyscyplinarnej nagany – na podstawie art. 109 § 1 pkt 4 u.s.p. wymierzył mu karę dyscyplinarną przeniesienia na inne miejsce służbowe w okręgu Sądu Apelacyjnego w A., 2) kosz\",\n" +
                "            \"keywords\": [],\n" +
                "            \"personnelType\": \"THREE_PERSON\",\n" +
                "            \"judgmentForm\": \"wyrok SN SD\",\n" +
                "            \"division\": {\n" +
                "                \"href\": \"https://www.saos.org.pl/api/scDivisions/1\",\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"Wydział VI\",\n" +
                "                \"chambers\": [\n" +
                "                    {\n" +
                "                        \"href\": \"https://www.saos.org.pl/api/scChambers/1\",\n" +
                "                        \"id\": 1,\n" +
                "                        \"name\": \"Izba Karna\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"judgmentDate\": \"2010-03-15\"\n" +
                "        }";


        JudgmentSynopsis judgementFromJson = new JudgmentSynopsis();
        try {
            judgementFromJson = objectMapper.readValue(supremeCourt, JudgmentSynopsis.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Long id = judgementFromJson.getId();
        LocalDate judgDate = judgementFromJson.getJudgmentDate();
        String caseNumber = judgementFromJson.getCaseNumbers().get(0);

        assertAll(
                () -> assertEquals(76256, id),
                () -> assertEquals(LocalDate.of(2010, 3, 15), judgDate),
                () -> assertEquals("SNO 10/10", caseNumber)
        );
    }
    
}
