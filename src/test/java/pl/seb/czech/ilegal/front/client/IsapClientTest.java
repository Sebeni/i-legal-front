package pl.seb.czech.ilegal.front.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.front.client.act.IsapClient;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.domain.act.ActPublisher;
import pl.seb.czech.ilegal.front.domain.act.ActSearchQuery;
import pl.seb.czech.ilegal.front.domain.act.ActSearchResult;
import pl.seb.czech.ilegal.front.domain.judgement.CourtType;
import pl.seb.czech.ilegal.front.ui.components.act.ActSearchForm;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IsapClientTest {
    @Autowired
    IsapClient isapClient;

    @Test
    void shouldReturnAllKeywords() {
        List<String> resultKeywords = isapClient.getAllKeywordsAndNames();

        assertAll(
                () -> assertTrue(resultKeywords.contains("absolwenci")),
                () -> assertTrue(resultKeywords.contains("Australia")),
                () -> assertTrue(resultKeywords.contains("fundusze emerytalne")),
                () -> assertTrue(resultKeywords.contains("leśnictwo"))
        );
    }

    @Test
    void shouldReturnOnlyOneAct() {
        ActSearchQuery query = new ActSearchQuery();
        query.setPublisher(ActPublisher.WDU);
        query.setYear(1991);
        query.setPosition(31);

        ActSearchResult result = (ActSearchResult) isapClient.performSearchQuery(query);
        Act upiolAct = result.getFoundActs()[0];


        assertAll(
                () -> assertEquals("Ustawa z dnia 12 stycznia 1991 r. o podatkach i opłatach lokalnych.", upiolAct.getTitle()),
                () -> assertEquals("WDU", upiolAct.getPublisher()),
                () -> assertEquals("1991", upiolAct.getYear().toString()),
                () -> assertEquals("31", upiolAct.getPosition().toString()),
                () -> assertEquals("1", result.getCount().toString()),
                () -> assertEquals("1", result.getNumOfResults().toString())
        );
    }

    @Test
    void shouldReturnZeroResult() {
        ActSearchQuery query = new ActSearchQuery();
        query.setYear(1);

        ActSearchResult result = (ActSearchResult) isapClient.performSearchQuery(query);
        assertAll(
                () -> assertEquals("0", result.getCount().toString()),
                () -> assertEquals("0", result.getNumOfResults().toString()),
                () -> assertEquals(0, result.getFoundActs().length)
        );

    }

   
}