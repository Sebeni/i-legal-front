package pl.seb.czech.ilegal.front.stub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsisSearchResult;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JudgmentSynopsisSearchResultDeserializationTest {

    @Autowired
    ObjectMapper objectMapper;
    
    @Test
    void shouldReturnMappedResult() {
        
        String jsonResult = "{\n" +
                "    \"links\": [\n" +
                "        {\n" +
                "            \"rel\": \"self\",\n" +
                "            \"href\": \"https://www.saos.org.pl/api/search/judgments?pageSize=20&pageNumber=0&caseNumber=IX%20P%20692/18&sortingField=JUDGMENT_DATE&sortingDirection=DESC\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"id\": 408336,\n" +
                "            \"href\": \"https://www.saos.org.pl/api/judgments/408336\",\n" +
                "            \"courtType\": \"COMMON\",\n" +
                "            \"courtCases\": [\n" +
                "                {\n" +
                "                    \"caseNumber\": \"IX P 692/18\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"judgmentType\": \"REASONS\",\n" +
                "            \"judges\": [],\n" +
                "            \"textContent\": \"Sygn. akt P 692/18 UZASADNIENIE Powódka (...) spółka z ograniczoną odpowiedzialnością w S. wniosła o zasądzenie od pozwanego R. S. kwoty 3.266,62 zł wraz z ustawowymi odsetkami za opóźnienie od dnia 17 lipca 2018 r. do dnia zapłaty tytułem odszkodowania za szkodę powstałą wobec umyślnie zawinionego działania pracownika, który nie stawił się do pracy, co skutkowało rezygnacją przez spółkę z wcześniej przyjętego zlecenia transportowego. Pozwana spółka wskazała, że utraciła korzyści, jakie mogłaby uzyskać, gdyby pozwany nie porzucił pracy. Utracone korzyści wyliczyła w taki sposób, że przyjęła, iż ciągnik o numerze rejestracyjnym (...) pozostawał bez obsady od 23 czerwca 2018 r. do 7 lipca 2018 r., a w okresie od stycznia 2018 r. do maja 2018 r. wygenerował zysk w kwocie 35.232,79 zł, co w pr\",\n" +
                "            \"keywords\": [\n" +
                "                \"odszkodowanie\"\n" +
                "            ],\n" +
                "            \"division\": {\n" +
                "                \"href\": \"https://www.saos.org.pl/api/ccDivisions/1668\",\n" +
                "                \"id\": 1668,\n" +
                "                \"name\": \"IX Wydział Pracy i Ubezpieczeń Społecznych\",\n" +
                "                \"code\": \"0004521\",\n" +
                "                \"court\": {\n" +
                "                    \"href\": \"https://www.saos.org.pl/api/commonCourts/272\",\n" +
                "                    \"id\": 272,\n" +
                "                    \"code\": \"15551530\",\n" +
                "                    \"name\": \"Sąd Rejonowy Szczecin-Centrum w Szczecinie\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"judgmentDate\": \"2020-04-30\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"queryTemplate\": {\n" +
                "        \"pageNumber\": {\n" +
                "            \"value\": 0,\n" +
                "            \"description\": \"Represents current page number\",\n" +
                "            \"allowedValues\": \"Not negative integer\"\n" +
                "        },\n" +
                "        \"pageSize\": {\n" +
                "            \"value\": 20,\n" +
                "            \"description\": \"Represents maximum number of items on the page\",\n" +
                "            \"allowedValues\": \"Any integer greater or equal to 1 and less or equal to 100\"\n" +
                "        },\n" +
                "        \"sortingField\": {\n" +
                "            \"value\": \"JUDGMENT_DATE\",\n" +
                "            \"description\": \"Represents the field by which you want to sort a list of items\",\n" +
                "            \"allowedValues\": [\n" +
                "                \"DATABASE_ID\",\n" +
                "                \"JUDGMENT_DATE\",\n" +
                "                \"REFERENCING_JUDGMENTS_COUNT\",\n" +
                "                \"MAXIMUM_MONEY_AMOUNT\",\n" +
                "                \"CC_COURT_TYPE\",\n" +
                "                \"CC_COURT_ID\",\n" +
                "                \"CC_COURT_CODE\",\n" +
                "                \"CC_COURT_NAME\",\n" +
                "                \"CC_COURT_DIVISION_ID\",\n" +
                "                \"CC_COURT_DIVISION_CODE\",\n" +
                "                \"CC_COURT_DIVISION_NAME\",\n" +
                "                \"SC_JUDGMENT_FORM_ID\",\n" +
                "                \"SC_PERSONNEL_TYPE\",\n" +
                "                \"SC_COURT_DIVISION_ID\",\n" +
                "                \"SC_COURT_DIVISION_NAME\",\n" +
                "                \"SC_COURT_DIVISIONS_CHAMBER_ID\",\n" +
                "                \"SC_COURT_DIVISIONS_CHAMBER_NAME\"\n" +
                "            ]\n" +
                "        },\n" +
                "        \"sortingDirection\": {\n" +
                "            \"value\": \"DESC\",\n" +
                "            \"description\": \"Represents the direction in which to sort a list of items\",\n" +
                "            \"allowedValues\": [\n" +
                "                \"ASC\",\n" +
                "                \"DESC\"\n" +
                "            ]\n" +
                "        },\n" +
                "        \"all\": null,\n" +
                "        \"legalBase\": null,\n" +
                "        \"referencedRegulation\": null,\n" +
                "        \"lawJournalEntryCode\": {\n" +
                "            \"value\": null,\n" +
                "            \"description\": \"Represents polish law journal entry (pl. pozycja dziennika ustaw)\",\n" +
                "            \"allowedValues\": \"String in format : 'year/entry_number'\"\n" +
                "        },\n" +
                "        \"judgeName\": null,\n" +
                "        \"caseNumber\": \"IX P 692/18\",\n" +
                "        \"courtType\": {\n" +
                "            \"value\": null,\n" +
                "            \"description\": \"Represents judgment's court type\",\n" +
                "            \"allowedValues\": [\n" +
                "                \"COMMON\",\n" +
                "                \"SUPREME\",\n" +
                "                \"CONSTITUTIONAL_TRIBUNAL\",\n" +
                "                \"NATIONAL_APPEAL_CHAMBER\",\n" +
                "                \"ADMINISTRATIVE\"\n" +
                "            ]\n" +
                "        },\n" +
                "        \"ccCourtType\": {\n" +
                "            \"value\": null,\n" +
                "            \"description\": \"Represents common court type\",\n" +
                "            \"allowedValues\": [\n" +
                "                \"APPEAL\",\n" +
                "                \"REGIONAL\",\n" +
                "                \"DISTRICT\"\n" +
                "            ]\n" +
                "        },\n" +
                "        \"ccCourtId\": null,\n" +
                "        \"ccCourtCode\": null,\n" +
                "        \"ccCourtName\": null,\n" +
                "        \"ccDivisionId\": null,\n" +
                "        \"ccDivisionCode\": null,\n" +
                "        \"ccDivisionName\": null,\n" +
                "        \"ccIncludeDependentCourtJudgments\": null,\n" +
                "        \"scPersonnelType\": {\n" +
                "            \"value\": null,\n" +
                "            \"description\": \"Represents supreme court judgment's personnel type\",\n" +
                "            \"allowedValues\": [\n" +
                "                \"ONE_PERSON\",\n" +
                "                \"THREE_PERSON\",\n" +
                "                \"FIVE_PERSON\",\n" +
                "                \"SEVEN_PERSON\",\n" +
                "                \"ALL_COURT\",\n" +
                "                \"ALL_CHAMBER\",\n" +
                "                \"JOINED_CHAMBERS\"\n" +
                "            ]\n" +
                "        },\n" +
                "        \"scJudgmentForm\": null,\n" +
                "        \"scChamberId\": null,\n" +
                "        \"scChamberName\": null,\n" +
                "        \"scDivisionId\": null,\n" +
                "        \"scDivisionName\": null,\n" +
                "        \"judgmentTypes\": {\n" +
                "            \"value\": [],\n" +
                "            \"description\": \"Represents list of judgments types\",\n" +
                "            \"allowedValues\": [\n" +
                "                \"DECISION\",\n" +
                "                \"RESOLUTION\",\n" +
                "                \"SENTENCE\",\n" +
                "                \"REGULATION\",\n" +
                "                \"REASONS\"\n" +
                "            ]\n" +
                "        },\n" +
                "        \"keywords\": [],\n" +
                "        \"judgmentDateFrom\": {\n" +
                "            \"value\": \"\",\n" +
                "            \"description\": \"Represents the earliest allowed judgment's date on the list of items\",\n" +
                "            \"allowedValues\": \"Date in format : 'yyyy-MM-dd'\"\n" +
                "        },\n" +
                "        \"judgmentDateTo\": {\n" +
                "            \"value\": \"\",\n" +
                "            \"description\": \"Represents the latest of allowed judgment's date on the list of items\",\n" +
                "            \"allowedValues\": \"Date in format : 'yyyy-MM-dd'\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"info\": {\n" +
                "        \"totalResults\": 1\n" +
                "    }\n" +
                "}";

        JudgmentSynopsisSearchResult searchResult = new JudgmentSynopsisSearchResult();

        try {
            searchResult = objectMapper.readValue(jsonResult, JudgmentSynopsisSearchResult.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        JudgmentSynopsisSearchResult finalSearchResult = searchResult;
        assertAll(
                () -> assertEquals(1, finalSearchResult.getNumOfResults()),
                () -> assertEquals(0, finalSearchResult.getPageNumber()),
                () -> assertEquals(1, finalSearchResult.getResultsList().size())
        );
        

    }
}
