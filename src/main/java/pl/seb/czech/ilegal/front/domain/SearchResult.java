package pl.seb.czech.ilegal.front.domain;


import java.util.List;

public interface SearchResult<E> {
    Integer getNumOfResults();
    
    List<E> getResultsList();
    
}
