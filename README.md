# Vaadin-jee7-entitiy-templates

Vaadin-templates for the great [Entity Expander](https://blogs.oracle.com/geertjan/entry/entity_expander_for_netbeans_ide)-[plugin](http://plugins.netbeans.org/plugin/53874/entityexpander) by Geertjan Wielenga.

Perfectly usable with the [vaadin-jee7-webapp-archetype](https://github.com/vonloxley/vaadin-jee7-webapp-archetype).

## Usage
1. Generate entity classes (from database).
2. Generate session beans from entity classes. Make sure they are put into the subfolder `controller`.
3. Generate a MaddonForm.
4. Look for `fixme`-lines in the generated code. If there are any the comments will tell you what todo.
5. Generate a TableViewWithPopup. If you are using the archetype mentioned aabove, the view will be availabe through the standard navigator automatically.

