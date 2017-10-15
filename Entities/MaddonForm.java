package ${package};

import com.vaadin.data.Binder;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * 
 * Generated form that allows editing of ${object}-entities.
 * 
 * Instantiate this class, set Reset- and SavedHandler and fill with setEntity(…).
 */
public class ${object}MaddonForm extends AbstractForm<${object}> {
    private static final long serialVersionUID = 1L;
    
  <#list fieldElems as field>
    <#if field.asType() = "java.lang.Boolean" || field.asType().kind = "BOOLEAN">
    CheckBox ${field.simpleName} = new CheckBox("${field.simpleName}");
    <#elseif
        field.asType().kind.primitive ||
        field.asType() = "java.lang.String" ||
        field.asType() = "java.lang.Double" ||
        field.asType() = "java.lang.Number" ||
        field.asType() = "java.lang.Integer" ||
        field.asType() = "java.lang.Long" ||
        field.asType() = "java.lang.BigDecimal" ||
        field.asType() = "java.lang.Float"
        >
    TextField ${field.simpleName} = new MTextField("${field.simpleName}");
    <#elseif field.asType() == "java.util.Date" || field.asType() == "java.time.LocalDate">
    DateField ${field.simpleName} = new DateField("${field.simpleName}");
    <#else>
    /* Fixme: 
     * Unknown type: ${field.asType()}, generating a Select.
     * Please populate it:
     *
     * @Inject ${field.simpleName}Facade ${field.simpleName}Beans;
     * ${field.simpleName}.setBeans(${field.simpleName}Beans.findAll());
     */
    ComboBox<${field.asType()}> ${field.simpleName} = new ComboBox<>("${field.simpleName}");
    </#if>
  </#list>

    @Override
    protected Component createContent() {

        return new MVerticalLayout(
                new FormLayout(
                  <#list fields as field>
                    ${field}<#if field_has_next>,</#if>
                  </#list>
                ),
                getToolbar()
        );
    }

    @Override
    protected void bind() {
        Binder<${object}> binder = getBinder();

        <#list fieldElems as field>
        <#if
            field.asType() = "java.lang.Double" ||
            field.asType() = "java.lang.Integer" ||
            field.asType() = "java.lang.Long" ||
            field.asType() = "java.lang.Float"
        >
        binder
                .forField(${field.simpleName})
                .withConverter(${field.asType()}::valueOf, String::valueOf)
                .bind("${field.simpleName}");
        </#if>
        </#list>

        binder.bindInstanceFields(this);
    }

    public ${object}MaddonForm(){
        super(${object}.class);

        setCancelCaption("Abbrechen");
        setSaveCaption("Speichern");
        setModalWindowTitle("${object} bearbeiten");
        setDeleteCaption("Löschen");
    }
}

