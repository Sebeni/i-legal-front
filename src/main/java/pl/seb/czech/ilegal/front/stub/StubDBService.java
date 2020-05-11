package pl.seb.czech.ilegal.front.stub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.seb.czech.ilegal.front.domain.DummyEntity;

import java.util.*;

public abstract class StubDBService<E extends DummyEntity<K>, K> {
    protected final Set<E> repository = new HashSet<>();
    private final Class<E> typeParameter;
   
    private ObjectMapper objectMapper;

    protected StubDBService(ObjectMapper objectMapper, List<String> jsonElements, Class<E> typeParameter) {
        this.objectMapper = objectMapper;
        this.typeParameter = typeParameter;
        convertInitJsonElements(jsonElements);
    }


    public List<E> getAll() {
        return new ArrayList<E>(repository);
    }

    public void addElement(E elementToAdd) {
        repository.add(elementToAdd);
    }

    public void deleteById(K id) {
        repository.removeIf(e -> e.getId().equals(id));
    }

    public boolean findIfExists(K id) {
        return repository.stream().anyMatch(e -> e.getId().equals(id));
    }


    private void convertInitJsonElements(List<String> jsonElements) {
        jsonElements.forEach(s -> {
            try {
                repository.add(objectMapper.readValue(s, typeParameter));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
    
}
