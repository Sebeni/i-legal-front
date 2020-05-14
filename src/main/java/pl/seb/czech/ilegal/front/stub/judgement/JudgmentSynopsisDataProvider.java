package pl.seb.czech.ilegal.front.stub.judgement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.stub.InitDataProvider;

@Service
public class JudgmentSynopsisDataProvider extends InitDataProvider<JudgmentSynopsis> {
    private static final String[] FILE_NAMES = { "commonSynopsis", 
             "KIOSynopsis", 
             "SNSynopsis", 
             "TKSynopsis"};
    
    
    @Autowired
    public JudgmentSynopsisDataProvider(ObjectMapper objectMapper) {
        super(objectMapper, JudgmentSynopsis.class, "judgments/synopsis", FILE_NAMES);
    }
}
