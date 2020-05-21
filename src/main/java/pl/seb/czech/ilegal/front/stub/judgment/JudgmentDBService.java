package pl.seb.czech.ilegal.front.stub.judgment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentDetails;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.stub.StubDBService;

import java.util.Set;

@Service
public class JudgmentDBService extends StubDBService<JudgmentSynopsis, Long> {
    
    @Autowired
    public JudgmentDBService(JudgmentSynopsisDataProvider synopsisDP, JudgmentDetailsDataProvider detailsDP) {
        super(synopsisDP.getConvertedElements());
        
        Set<JudgmentDetails> detailsSet = detailsDP.getConvertedElements();
        
        detailsSet.forEach(currentDetails -> {
            JudgmentSynopsis synopsis = findById(currentDetails.getId());
            if(synopsis != null){
                synopsis.setJudgmentDetails(currentDetails);
            }
        });
        
    }
}
