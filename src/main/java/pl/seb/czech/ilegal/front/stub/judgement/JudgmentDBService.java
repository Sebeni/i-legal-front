package pl.seb.czech.ilegal.front.stub.judgement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.stub.StubDBService;

@Service
public class JudgmentDBService extends StubDBService<JudgmentSynopsis, Long> {
    
    @Autowired
    public JudgmentDBService(JudgmentSynopsisDataProvider judgmentSynopsisDataProvider) {
        super(judgmentSynopsisDataProvider.getConvertedElements());
    }
}
