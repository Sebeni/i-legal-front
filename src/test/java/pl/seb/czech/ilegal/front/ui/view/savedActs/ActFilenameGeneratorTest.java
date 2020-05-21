package pl.seb.czech.ilegal.front.ui.view.savedActs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.front.client.act.ActFilenameGenerator;
import pl.seb.czech.ilegal.front.domain.act.Act;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActFilenameGeneratorTest {
    private static Act upiol;
    private static Act taxRates;
    
    @Autowired
    private ActFilenameGenerator generator;
    
    @BeforeAll
    static void initAct() {
        upiol = new Act();
        upiol.setIsapId("WDU19910090031");
        taxRates = new Act();
        taxRates.setIsapId("WMP20190000738");
    }
    
    @Test
    void shouldReturnGeneratedUnifiedTxtFilename() {
        assertEquals("D19910031Lj.pdf", generator.generateUnifiedTxtFilename(upiol));
    }

    @Test
    void shouldReturnGeneratedPublishedTxtFileName() {
        assertAll(
                () -> assertEquals("D19910031.pdf", generator.generatePublishedTxtFileName(upiol)),
                () -> assertEquals("M20190738.pdf", generator.generatePublishedTxtFileName(taxRates))
        );
        
    }
}