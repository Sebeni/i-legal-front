package pl.seb.czech.ilegal.front.backend.clients;

import pl.seb.czech.ilegal.front.domain.BaseEntity;

import java.util.*;

public interface DbClient<E extends BaseEntity> {
    List<E> getAll();
    void saveElement(E elementToAdd);
    void deleteById(Long id);
    Boolean findIfExistsByApiId(E currentElement);
    E findById(Long id);
    void updateElement(E elementToUpdate);
    void deleteAll();
}
