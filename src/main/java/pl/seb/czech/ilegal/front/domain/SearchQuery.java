package pl.seb.czech.ilegal.front.domain;


public abstract class SearchQuery {
    protected Integer resultLimitPerPage = 20;

    public abstract void setPageNumber(int pageNumber);

    public abstract int getCurrentPageNumber();

    public Integer getResultLimitPerPage() {
        return resultLimitPerPage;
    }
    
}
