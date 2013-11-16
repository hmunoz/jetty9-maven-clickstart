package unrn.isiii.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateJdbcException;
import org.springframework.stereotype.Component;

import unrn.isiii.dao.IProvinciaDao;
import unrn.isiii.model.Provincia;

@Component("provinciaBean")
public class ProvinciaBean {

	private static final Logger logger = LoggerFactory
			.getLogger(ProvinciaBean.class);

	private Provincia provincia = new Provincia();

	private List<Provincia> provincias;

	@Autowired
	private IProvinciaDao iProvinciaDao;

	public IProvinciaDao getiProvinciaDao() {
		return iProvinciaDao;
	}

	public void setiProvinciaDao(IProvinciaDao iProvinciaDao) {
		this.iProvinciaDao = iProvinciaDao;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

	public void save() {
		if (provincia.getId() == null)
			getiProvinciaDao().create(provincia);
		else
			getiProvinciaDao().update(provincia);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta",
				"Operación realizada con éxito.");
		if (FacesContext.getCurrentInstance() != null)
			FacesContext.getCurrentInstance().addMessage(null, msg);
		invalidateProvincias();
		resetProvincia();
	}

	public void eliminar(Long id) {
		try {
			getiProvinciaDao().delete(id);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Borrar", "Operación realizada con éxito.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (HibernateJdbcException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Borrar", "La provincia tiente relaciones activas. Elimine primero las ciudades.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Borrar", "Error al eliminar."+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}finally
		{
			resetProvincia();			
		}
		
		
	}

	private void invalidateProvincias() {
		provincias = null;
	}

	public void resetProvincia() {
		provincia = new Provincia();
	}

	public List<Provincia> getProvincias() {
		if (provincias == null) {
			provincias = getiProvinciaDao().findAll();
		}
		return provincias;
	}

	public void seleccionar(Provincia provincia) {
		setProvincia(provincia);
	}

}
