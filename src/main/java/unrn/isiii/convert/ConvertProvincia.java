package unrn.isiii.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unrn.isiii.dao.IProvinciaDao;
import unrn.isiii.model.Provincia;

@Component("convertProvincia")
public class ConvertProvincia implements Converter {

	@Autowired
	private IProvinciaDao iProvinciaDao;

	public IProvinciaDao getiProvinciaDao() {
		return iProvinciaDao;
	}

	public void setiProvinciaDao(IProvinciaDao iProvinciaDao) {
		this.iProvinciaDao = iProvinciaDao;
	}

	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String value) {
		if (value == null || value.equals("")) {
			return null;
		}

		return getiProvinciaDao().find(Long.parseLong(value));

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		return value instanceof Provincia ? ((Provincia) value).getId()
				.toString() : "";
	}
}