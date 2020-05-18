package pl.seb.czech.ilegal.front.ui.components.act;

import com.vaadin.flow.component.textfield.TextField;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.ui.components.CustomGrid;
import pl.seb.czech.ilegal.front.ui.components.FilterTextField;

import java.util.List;

public class ActGrid extends CustomGrid<Act> {
    private Column<Act> yearColumn;
    private Column<Act> positionColumn;
    private Column<Act> titleColumn;
    private Column<Act> statusColumn;
    private Column<Act> lastChangeColumn;
    

    public ActGrid(List<Act> gridContent) {
        super(Act.class, gridContent);
        setClassName("act-grid");
    }

    @Override
    protected void configureGridColumns() {
        setColumns("year", "position", "title", "status", "changeDate");
        yearColumn = getColumnByKey("year").setHeader("Rok").setFlexGrow(3).setWidth("20px");
        positionColumn = getColumnByKey("position").setHeader("Poz.").setFlexGrow(2).setWidth("15px");
        titleColumn = getColumnByKey("title").setHeader("Tytuł").setFlexGrow(8);
        statusColumn = getColumnByKey("status").setFlexGrow(6).setWidth("30px");
        lastChangeColumn = getColumnByKey("changeDate")
                .setHeader("Ost. zmiana")
                .setFlexGrow(4)
                .setWidth("25px");
    }

    @Override
    protected void configureGridFilterRow() {
        TextField yearFieldFilter = new FilterTextField();
        addFilter(yearColumn, yearFieldFilter, act -> compareFieldWithFilter(act.getYear(), yearFieldFilter));
        
        TextField posFieldFilter = new FilterTextField();
        addFilter(positionColumn, posFieldFilter, act -> compareFieldWithFilter(act.getPosition(), posFieldFilter));
       
        
        TextField titleFieldFilter = new FilterTextField();
        addFilter(titleColumn, titleFieldFilter, act -> compareFieldWithFilter(act.getTitle(), titleFieldFilter));
        
        TextField statusFieldFilter = new FilterTextField();
        addFilter(statusColumn, statusFieldFilter, act -> compareFieldWithFilter(act.getStatus(), statusFieldFilter));
        
        createClearButton(lastChangeColumn);
    }

  
}

