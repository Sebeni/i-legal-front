package pl.seb.czech.ilegal.front.stub.act;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.stub.StubDBService;


@Service
public class ActDBService extends StubDBService<Act, String> {
    
    @Autowired
    public ActDBService(ObjectMapper objectMapper, ActExampleData actExampleData) {
       super(objectMapper, actExampleData.getJsonActs(), Act.class);
    }
}
