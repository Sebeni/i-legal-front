package pl.seb.czech.ilegal.front.stub.judgement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.stub.StubDBService;

@Service
public class JudgmentDBService extends StubDBService<JudgmentSynopsis, Long> {
    
    @Autowired
    public JudgmentDBService(ObjectMapper objectMapper, JudgmentSynopsisExampleData judgmentSynopsisExampleData) {
        super(objectMapper, judgmentSynopsisExampleData.getJsonActs(), JudgmentSynopsis.class);
    }
}
