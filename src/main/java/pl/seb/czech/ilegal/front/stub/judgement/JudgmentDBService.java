package pl.seb.czech.ilegal.front.stub.judgement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.front.domain.judgement.Judgment;
import pl.seb.czech.ilegal.front.stub.StubDBService;

@Service
public class JudgmentDBService extends StubDBService<Judgment, Long> {
    
    @Autowired
    public JudgmentDBService(ObjectMapper objectMapper, JudgmentExampleData judgmentExampleData) {
        super(objectMapper, judgmentExampleData.getJsonActs(), Judgment.class);
    }
}
