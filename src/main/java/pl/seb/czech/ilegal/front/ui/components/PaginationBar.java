package pl.seb.czech.ilegal.front.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.SearchResult;
import pl.seb.czech.ilegal.front.ui.view.SearchView;

public class PaginationBar extends HorizontalLayout {
    private SearchResult userSearchResult;
    private SearchQuery userQuery;
    private Span pageInfo;

    public PaginationBar(SearchView searchView) {
        userQuery = searchView.getSearchQuery();
        userSearchResult = searchView.getSearchResult();

        Button firstPageButton = new Button("Pierwsza", new Icon(VaadinIcon.ANGLE_DOUBLE_LEFT));
        firstPageButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        firstPageButton.addClickListener(event -> {
            if (userQuery != null && userSearchResult != null) {
                searchView.performSearchAndSetGrid(1);
                updatePageInfo();
            }
        });

        Button previousPageButton = new Button(new Icon(VaadinIcon.ANGLE_LEFT));
        previousPageButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        previousPageButton.addClickListener(event -> {
            if (userQuery != null && userSearchResult != null) {
                int currentPage = userQuery.getCurrentPageNumber();
                if (currentPage > 1) {
                    searchView.performSearchAndSetGrid(currentPage - 1);
                    updatePageInfo();
                }
            }
        });

        pageInfo = new Span();

        Button nextPageButton = new Button(new Icon(VaadinIcon.ANGLE_RIGHT));
        nextPageButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        nextPageButton.addClickListener(event -> {
            if (userQuery != null && userSearchResult != null) {
                int currentPage = userQuery.getCurrentPageNumber();
                if (currentPage < calculateMaxPageNumber()) {
                    searchView.performSearchAndSetGrid(currentPage + 1);
                    updatePageInfo();
                }
            }
        });

        Button lastPageButton = new Button("Ostatnia", new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT));
        lastPageButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        lastPageButton.addClickListener(event -> {
            if (userQuery != null && userSearchResult != null) {
                searchView.performSearchAndSetGrid(calculateMaxPageNumber());
                updatePageInfo();
            }
        });

        this.add(firstPageButton, previousPageButton, pageInfo, nextPageButton, lastPageButton);
        this.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
    }

    public void updatePageInfo(){
        if(userSearchResult.getNumOfResults() != 0){
            pageInfo.setText("Strona " + (userQuery.getCurrentPageNumber()) + " z " + calculateMaxPageNumber());
        } else {
            pageInfo.setText("");
        }
    }


    private int calculateMaxPageNumber(){
        if(userQuery != null && userSearchResult != null) {
            Integer resultLimit = userQuery.getResultLimitPerPage();
            if (resultLimit.equals(0)) {
                resultLimit = 1;
            }
            return (userSearchResult.getNumOfResults() + resultLimit - 1) / resultLimit;
        } else {
            return 0;
        }
    }

    public void updateQueryAndResult(SearchQuery userQuery, SearchResult userSearchResult){
        this.userSearchResult = userSearchResult;
        this.userQuery = userQuery;
        updatePageInfo();
    }
    
   
}