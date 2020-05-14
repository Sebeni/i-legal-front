package pl.seb.czech.ilegal.front.stub.judgement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentDetails;
import pl.seb.czech.ilegal.front.stub.InitDataProvider;

@Service
public class JudgmentDetailsDataProvider extends InitDataProvider<JudgmentDetails> {
    private final static String[] FILE_NAMES = {"commonDetails", "commonDetails2", "KIODetails", "SNDetails", "TKDetails"};

    @Autowired
    public JudgmentDetailsDataProvider(ObjectMapper objectMapper) {
        super(objectMapper, JudgmentDetails.class, "judgments/details",  FILE_NAMES);
    }
}
