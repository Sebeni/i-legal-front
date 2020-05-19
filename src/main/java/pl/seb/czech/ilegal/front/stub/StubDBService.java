package pl.seb.czech.ilegal.front.stub;

import pl.seb.czech.ilegal.front.domain.BaseEntity;

import java.util.*;

public abstract class StubDBService<E extends BaseEntity<K>, K> {
    protected final Map<K, E> repository = new HashMap<>();
    
    protected StubDBService(Set<E> initialElements) {
        initialElements.forEach(this::saveElement);
    }
    
    public List<E> getAll() {
        return new ArrayList<>(repository.values());
    }

    public void saveElement(E elementToAdd) {
        repository.put(elementToAdd.getId(), elementToAdd);
    }

    public void deleteById(K id) {
        repository.remove(id);
    }

    public boolean findIfExists(K id) {
        return repository.containsKey(id);
    }
    
    public E findById(K id) { 
        return repository.get(id);
    }
    
}
