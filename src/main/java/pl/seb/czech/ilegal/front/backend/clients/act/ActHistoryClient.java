package pl.seb.czech.ilegal.front.backend.clients.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.front.domain.log.DeleteLog;
import pl.seb.czech.ilegal.front.domain.log.SearchLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ActHistoryClient {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${url.base.history}")
    private String historyEndpoint;
    @Value("${url.acts.history.log.search}")
    private String searchLogEndpoint;
    @Value("${url.acts.history.log.delete}")
    private String deleteLogEndpoint;
    
    public List<SearchLog> getSearchLogs() {
        SearchLog[] result = restTemplate.getForObject(historyEndpoint + searchLogEndpoint, SearchLog[].class);
        return result != null ? new ArrayList<>(Arrays.asList(result)) : new ArrayList<>();
    }
    
    public void deleteSearchLogs() {
        restTemplate.delete(historyEndpoint + searchLogEndpoint);
    }
    
    public List<DeleteLog> getDeleteLogs() {
        DeleteLog[] result = restTemplate.getForObject(historyEndpoint + deleteLogEndpoint, DeleteLog[].class);
        return result != null ? new ArrayList<>(Arrays.asList(result)) : new ArrayList<>();
    }
    
}
