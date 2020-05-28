package pl.seb.czech.ilegal.front.backend.clients.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.front.backend.clients.DbClient;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.domain.act.ActDifference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ActDbClient implements DbClient<Act> {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.base.db}")
    private String dbEndpoint;
    
    @Value("${url.acts}")
    private String actsEndpoint;
    
    @Value("${url.acts.byIsapId}")
    private String findIfExistsByApiIdEndpoint;
    
    @Value("${url.acts.deleteAll}")
    private String deleteAllEndpoint;
    
    @Value("${url.acts.keywords}")
    private String getKeywordsEndpoint;
    
    @Override
    public List<Act> getAll() {
        Act[] result = restTemplate.getForObject(dbEndpoint + actsEndpoint, Act[].class);
        return result != null ? new ArrayList<>(Arrays.asList(result)) : new ArrayList<>();
    }

    @Override
    public void saveElement(Act elementToAdd) {
        restTemplate.postForObject(dbEndpoint + actsEndpoint, elementToAdd, Act.class);
    }

    @Override
    public void deleteById(Long id) {
        restTemplate.delete(dbEndpoint + actsEndpoint + "/" + id);
    }

    @Override
    public Boolean findIfExistsByApiId(Act currentElement) {
        return restTemplate.getForObject(dbEndpoint + findIfExistsByApiIdEndpoint + "/" + currentElement.getIsapId(), Boolean.class);
    }

    @Override
    public Act findById(Long id) {
        return restTemplate.getForObject(dbEndpoint + actsEndpoint + "/" + id, Act.class);
    }

    @Override
    public void updateElement(Act elementToUpdate) {
        restTemplate.put(dbEndpoint + actsEndpoint, elementToUpdate);
    }

    @Override
    public void deleteAll() {
        restTemplate.delete(dbEndpoint + deleteAllEndpoint);
    }
    
    
    String[] getAllKeywordsAndNames(){
        return restTemplate.getForObject(dbEndpoint + getKeywordsEndpoint, String[].class);
    }
    
}
