package unrn.isiii.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unrn.isiii.dao.IPropietarioDao;
import unrn.isiii.model.Propietario;

@Component("convertPropietario")
public class ConvertPropietario implements Converter {

	@Autowired
	private IPropietarioDao iPropietarioDao;

	public IPropietarioDao getiPropietarioDao() {
		return iPropietarioDao;
	}

	public void setiPropietarioDao(IPropietarioDao iPropietarioDao) {
		this.iPropietarioDao = iPropietarioDao;
	}

	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String value) {
		if (value == null || value.equals("")) {
			return null;
		}

		return getiPropietarioDao().find(Long.parseLong(value));

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		return value instanceof Propietario ? ((Propietario) value).getId()
				.toString() : "";
	}
}