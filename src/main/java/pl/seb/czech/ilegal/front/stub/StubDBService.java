package pl.seb.czech.ilegal.front.stub;

import pl.seb.czech.ilegal.front.domain.DummyEntity;

import java.util.*;

public abstract class StubDBService<E extends DummyEntity<K>, K> {
    protected final Set<E> repository = new HashSet<>();
    
    protected StubDBService(Set<E> initialElements) {
        repository.addAll(initialElements);
    }
    
    public List<E> getAll() {
        return new ArrayList<>(repository);
    }

    public void saveElement(E elementToAdd) {
        repository.add(elementToAdd);
    }

    public void deleteById(K id) {
        repository.removeIf(e -> e.getId().equals(id));
    }

    public boolean findIfExists(K id) {
        return repository.stream().anyMatch(e -> e.getId().equals(id));
    }
    
    public E findById(K id) { 
        return repository.stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null);
    }
    
}
