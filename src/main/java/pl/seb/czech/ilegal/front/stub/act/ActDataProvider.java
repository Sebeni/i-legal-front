package pl.seb.czech.ilegal.front.stub.act;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.stub.InitDataProvider;

@Service
public class ActDataProvider extends InitDataProvider<Act> {
    private static String[] fileNames = {"epidemy", "kc", "kk", "kpc", "kpk"};
    
    @Autowired
    public ActDataProvider(ObjectMapper objectMapper) {
        super(objectMapper, Act.class, "acts", fileNames);
    }
}
