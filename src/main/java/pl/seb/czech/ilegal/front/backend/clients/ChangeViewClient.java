package pl.seb.czech.ilegal.front.backend.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.front.domain.ChangeViewLog;

@Service
public class ChangeViewClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.base.history}")
    private String dbEndpoint;

    @Value("${url.history.changeview}")
    private String saveEndpoint;
    
    public void save(ChangeViewLog log){
        restTemplate.postForObject(dbEndpoint + saveEndpoint, log, ChangeViewLog.class);
    }
}
