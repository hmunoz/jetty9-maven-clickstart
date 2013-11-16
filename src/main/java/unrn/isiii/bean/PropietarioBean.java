package unrn.isiii.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateJdbcException;
import org.springframework.stereotype.Component;

import unrn.isiii.dao.IPropietarioDao;
import unrn.isiii.model.Propietario;

@Component("propietarioBean")
public class PropietarioBean {

	private static final Logger logger = LoggerFactory
			.getLogger(PropietarioBean.class);

	private Propietario propietario = new Propietario();

	private List<Propietario> propietarios;

	@Autowired
	private IPropietarioDao iPropietarioDao;

	public IPropietarioDao getiPropietarioDao() {
		return iPropietarioDao;
	}

	public void setiPropietarioDao(IPropietarioDao iPropietarioDao) {
		this.iPropietarioDao = iPropietarioDao;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public List<Propietario> getPropietarios() {
		if (propietarios == null)
			propietarios = iPropietarioDao.findAll();
		return propietarios;
	}

	public void setPropietarios(List<Propietario> propietarios) {
		this.propietarios = propietarios;
	}

	public void save() {
		if (propietario.getId() == null)
			getiPropietarioDao().create(propietario);
		else
			getiPropietarioDao().update(propietario);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta",
				"Operación realizada con éxito.");
		if (FacesContext.getCurrentInstance() != null)
			FacesContext.getCurrentInstance().addMessage(null, msg);
		invalidatePropietarios();
		resetPropietario();
	}

	public void eliminar(Long id) {
		try {
			getiPropietarioDao().delete(id);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Borrar", "Operación realizada con éxito.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			resetPropietario();
		} catch (HibernateJdbcException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Borrar", "El Productor tiente Sitios activos. Elimine primero los sitios.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Borrar", "Error al eliminar."+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} finally {
			resetPropietario();
		}

	}

	private void invalidatePropietarios() {
		propietarios = null;
	}

	public void resetPropietario() {
		propietario = new Propietario();
	}

	public void seleccionar(Propietario propietario) {
		setPropietario(propietario);
	}

}
