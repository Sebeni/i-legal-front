package pl.seb.czech.ilegal.front.stub;

import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.front.domain.Act;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActService {
    private ExampleData exampleData;
    
    private final Map<String, Act> repository = new HashMap<>();

    public ActService(ExampleData exampleData) {
        this.exampleData = exampleData;
    }


    public List<Act> getAllActs() {
        if(repository.isEmpty()) {
            populateRepository();
        }
        return new ArrayList<>(repository.values());
    }
    
    public void addAct(Act actToAdd) {
        repository.put(actToAdd.getId(), actToAdd);
    }
    
    public void deleteActById(String id) {
        repository.remove(id);
    }
    
    public Act getActById(String id) {
        return repository.get(id);
    }
    
    public boolean findIfExists(String id) {
        return repository.containsKey(id);
    }
    
    public List<Act> findByTitle(String title){
        if(title == null || title.isEmpty()) {
            return getAllActs();
        } else {
            return getAllActs().stream().filter(act -> act.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
        }
    }
    
    public void populateRepository() {
        exampleData.getDummyActDataList().forEach(this::addAct);

    }
    
    
}
