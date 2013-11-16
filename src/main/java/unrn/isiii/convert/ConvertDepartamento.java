package unrn.isiii.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unrn.isiii.dao.IDepartamentoDao;
import unrn.isiii.model.Departamento;

@Component("convertDepartamento")
public class ConvertDepartamento implements Converter {

	@Autowired
	private IDepartamentoDao iDepartamentoDao;

	public IDepartamentoDao getiDepartamentoDao() {
		return iDepartamentoDao;
	}

	public void setiDepartamentoDao(IDepartamentoDao iDepartamentoDao) {
		this.iDepartamentoDao = iDepartamentoDao;
	}

	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String value) {
		if (value == null || value.equals("")) {
			return null;
		}

		return getiDepartamentoDao().find(Long.parseLong(value));

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		return value instanceof Departamento ? ((Departamento) value).getId()
				.toString() : "";
	}
}