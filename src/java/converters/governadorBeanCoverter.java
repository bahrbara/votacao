package converters;

import entidades.Governador;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="governadorBeanCoverter")

public class governadorBeanCoverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Governador) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Governador) {
            Governador entity = (Governador) value;
            if (entity != null && entity instanceof Governador && entity.getCpf() != null) {
                uiComponent.getAttributes().put(entity.getCpf().toString(), entity);
                return entity.getCpf().toString();
            }
        }
        return "";
    }

}
