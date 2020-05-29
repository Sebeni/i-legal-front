package pl.seb.czech.ilegal.front.backend.clients.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.front.domain.act.ActDifference;
import pl.seb.czech.ilegal.front.domain.act.ScheduledMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ActDifferenceClient {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${url.base}")
    private String baseEndpoint;
    
    @Value("${url.acts.difference.update}")
    private String differenceUpdateEndpoint;
    
    @Value("${url.acts.difference.scheduled}")
    private String scheduledMessageEndpoint;
    
    public List<ActDifference> updateActs(){
        ActDifference[] result = restTemplate.getForObject(baseEndpoint + differenceUpdateEndpoint, ActDifference[].class);
        return result != null ? new ArrayList<>(Arrays.asList(result)) : new ArrayList<>();
    }
    
    public ScheduledMessage getScheduledMessage(){
        return restTemplate.getForObject(baseEndpoint + scheduledMessageEndpoint, ScheduledMessage.class);
    }
}
