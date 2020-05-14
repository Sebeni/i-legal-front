package pl.seb.czech.ilegal.front.stub.act;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.stub.InitDataProvider;

@Service
public class ActDataProvider extends InitDataProvider<Act> {
    private final static String[] FILE_NAMES = {"epidemy", "kc", "kk", "kpc", "kpk"};
    
    @Autowired
    public ActDataProvider(ObjectMapper objectMapper) {
        super(objectMapper, Act.class, "acts", FILE_NAMES);
    }
}
