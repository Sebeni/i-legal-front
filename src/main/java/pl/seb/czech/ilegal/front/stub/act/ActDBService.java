package pl.seb.czech.ilegal.front.stub.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.stub.StubDBService;

@Service
public class ActDBService extends StubDBService<Act, String> {
    
    @Autowired
    public ActDBService(ActDataProvider actDataProvider) {
       super(actDataProvider.getConvertedElements());
    }
}
