package pl.seb.czech.ilegal.front.backend.clients.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.front.domain.act.ActDifference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ActDifferenceClient {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${url.base}")
    private String dbEndpoint;
    
    @Value("${url.acts.difference.update}")
    private String differenceUpdateEndpoint;
    
    public List<ActDifference> updateActs(){
        ActDifference[] result = restTemplate.getForObject(dbEndpoint + differenceUpdateEndpoint, ActDifference[].class);
        return result != null ? new ArrayList<>(Arrays.asList(result)) : new ArrayList<>();
    }
}
