package pl.seb.czech.ilegal.front.client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.front.domain.Act;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IsapClientTest {
    @Autowired
    IsapClient isapClient;
    
    private static Act actToDownload;
    
    @BeforeAll
    static void initAct(){
        actToDownload = new Act();
        actToDownload.setId("WDU19910090031");
        actToDownload.setPublisher("WDU");
        actToDownload.setYear(1991);
        actToDownload.setPosition(31);
    }

    @Test
    void shouldGenerateURIToPublishedAct() {
        String uri = isapClient.generateDownloadActURI(actToDownload, ActTextType.PUBLISHED).toString();
        assertEquals("http://isap.sejm.gov.pl/api/isap/deeds/WDU/1991/31/text/O/D19910031.pdf", uri);
    }

    @Test
    void shouldGenerateURIToUnifiedAct() {
        String uri = isapClient.generateDownloadActURI(actToDownload, ActTextType.UNIFIED).toString();
        assertEquals("http://isap.sejm.gov.pl/api/isap/deeds/WDU/1991/31/text/U/D19910031Lj.pdf", uri);
    }
}