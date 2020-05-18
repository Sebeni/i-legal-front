package pl.seb.czech.ilegal.front.ui.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class DetailBox<T> extends VerticalLayout {
    public abstract void setDetailsAndUpdateBox(T currentElement);
}
