package ${package};

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.button.PrimaryButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class ${object}MaddonForm extends AbstractForm<${object}> {
    
  <#list fieldElems as field>

    <#if field.asType() = "java.lang.Boolean" || field.asType().kind = "BOOLEAN">
        CheckBox ${field.simpleName} = new CheckBox("${field.simpleName}");
    <#elseif
        field.asType().kind.primitive || 
        field.asType() = "java.lang.String" || 
        field.asType() = "java.lang.Double" || 
        field.asType() = "java.lang.Number" || 
        field.asType() = "java.lang.Integer" || 
        field.asType() = "java.lang.BigDecimal" || 
        field.asType() = "java.lang.Float" 
        >
        TextField ${field.simpleName} = new MTextField("${field.simpleName}");
    <#elseif field.asType() == "java.util.Date">
        DateField ${field.simpleName} = new DateField("${field.simpleName}");
    <#else>
        /*Fixme: Select to another entity, options must be populated!
         * @Inject ${field.simpleName}Facade ${field.simpleName}Beans
         * ${field.simpleName}.setBeans(${field.simpleName}Beans.findAll());
         */
        TypedSelect<${field.asType()}> ${field.simpleName} = new TypedSelect().withCaption("${field.simpleName}");
    </#if>
  </#list>

    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new FormLayout(
                  <#list fields as field>
                     ${field} <#if field_has_next>,</#if>
                  </#list>
                ),
                getToolbar()
        );
    }

    @Override
    protected Button createCancelButton() {
        return new Button("Abbrechen", (Button.ClickEvent event) -> {
            reset(event);
        });
    }

    @Override
    protected Button createSaveButton() {
        return new PrimaryButton("Speichern", (Button.ClickEvent event) -> {
            save(event);
        });
    }
    
}
