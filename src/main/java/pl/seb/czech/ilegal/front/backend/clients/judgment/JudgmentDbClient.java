package pl.seb.czech.ilegal.front.backend.clients.judgment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.front.backend.clients.DbClient;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JudgmentDbClient implements DbClient<JudgmentSynopsis> {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${url.base.db}")
    private String dbEndpoint;
    
    @Value("${url.judgments}")
    private String judgmentEndpoint;
    
    @Value("${url.judgments.bySaosId}")
    private String searchByApiIdEndpoint;
    
    @Override
    public List<JudgmentSynopsis> getAll() {
        JudgmentSynopsis[] result = restTemplate.getForObject(dbEndpoint + judgmentEndpoint, JudgmentSynopsis[].class);
        return result != null ? new ArrayList<>(Arrays.asList(result)) : new ArrayList<>();
    }

    @Override
    public void saveElement(JudgmentSynopsis elementToAdd) {
        restTemplate.postForObject(dbEndpoint + judgmentEndpoint, elementToAdd, JudgmentSynopsis.class);
    }

    @Override
    public void deleteById(Long id) {
        restTemplate.delete(dbEndpoint + judgmentEndpoint + "/" + id);
    }

    @Override
    public Boolean findIfExistsByApiId(JudgmentSynopsis currentElement) {
        return restTemplate.getForObject(dbEndpoint + searchByApiIdEndpoint + "/" + currentElement.getSaosId(), Boolean.class);
    }

    @Override
    public JudgmentSynopsis findById(Long id) {
        return restTemplate.getForObject(dbEndpoint + judgmentEndpoint + "/" + id, JudgmentSynopsis.class);
    }

    @Override
    public void updateElement(JudgmentSynopsis elementToUpdate) {
        restTemplate.put(dbEndpoint + judgmentEndpoint, elementToUpdate);
    }

    @Override
    public void deleteAll() {
        restTemplate.delete(dbEndpoint + judgmentEndpoint);
    }
}
