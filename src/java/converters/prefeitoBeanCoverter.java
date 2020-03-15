package converters;

import entidades.Prefeito;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="prefeitoBeanCoverter")

public class prefeitoBeanCoverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Prefeito) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Prefeito) {
            Prefeito entity = (Prefeito) value;
            if (entity != null && entity instanceof Prefeito && entity.getCpf() != null) {
                uiComponent.getAttributes().put(entity.getCpf().toString(), entity);
                return entity.getCpf().toString();
            }
        }
        return "";
    }

}
