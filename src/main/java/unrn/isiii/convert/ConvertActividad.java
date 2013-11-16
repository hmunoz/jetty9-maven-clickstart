package unrn.isiii.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unrn.isiii.dao.IActividadDao;
import unrn.isiii.model.Actividad;

@Component("convertActividad")
public class ConvertActividad implements Converter {

	@Autowired
	private IActividadDao iActividadDao;

	public IActividadDao getiActividadDao() {
		return iActividadDao;
	}

	public void setiActividadDao(IActividadDao iActividadDao) {
		this.iActividadDao = iActividadDao;
	}

	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String value) {
		if (value == null || value.equals("")) {
			return null;
		}

		return getiActividadDao().find(Long.parseLong(value));

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		return value instanceof Actividad ? ((Actividad) value).getId()
				.toString() : "";
	}
}