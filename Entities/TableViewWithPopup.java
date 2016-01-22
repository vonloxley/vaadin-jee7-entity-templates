package ${package};

import ${package}.ejb.${object}Facade;
import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component.Listener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.layouts.MVerticalLayout;

@CDIView("${object?lower_case}")
public class ${object}TableViewWithPopup extends MVerticalLayout implements View, Listener {

    @Inject
    ${object}Facade cf;
    @Inject
    ${object}MaddonForm form;

    MTable<${object}> table = new MTable<>(${object}.class);

    private Window popup;

    @PostConstruct
    public void initComponent() {
        popup = new Window("${object} editieren", form);
        popup.setModal(true);

        form.setResetHandler(this::reset);
        form.setSavedHandler(this::save);
        popup.addListener(this);

        table.addMValueChangeListener(e -> {
            if (e.getValue() != null) {
                form.setEntity(e.getValue());
                getUI().addWindow(popup);
            }
        });
        table.setWidth("100%");
        listEntities();

        Button addButton = new Button(FontAwesome.PLUS);
        addButton.addClickListener(e -> {
            form.setEntity(new ${object}());
            getUI().addWindow(popup);
        });

        addComponents(
                new Header("${object}-Liste"),
                table, 
                addButton
        );
    }

    private void listEntities() {
        table.setBeans(cf.findAll());
    }

    public void save(${object} entity) {
        if (entity.get${idfield.simpleName?cap_first}() == null) {
            cf.create(entity);
        } else {
            cf.edit(entity);
        }
        popup.close();
        Notification.show("Gespeichert!");
    }

    public void reset(${object} entity) {
        popup.close();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @Override
    public void componentEvent(Event event) {
        if (event.getClass() == com.vaadin.ui.Window.CloseEvent.class) {
                listEntities();
        }
    }
}
