package converters;

import entidades.Presidente;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="presidenteBeanCoverter")

public class presidenteBeanCoverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Presidente) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Presidente) {
            Presidente entity = (Presidente) value;
            if (entity != null && entity instanceof Presidente && entity.getCpf() != null) {
                uiComponent.getAttributes().put(entity.getCpf().toString(), entity);
                return entity.getCpf().toString();
            }
        }
        return "";
    }

}
