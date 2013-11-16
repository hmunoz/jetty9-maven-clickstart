package unrn.isiii.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateJdbcException;
import org.springframework.stereotype.Component;

import unrn.isiii.dao.IActividadDao;
import unrn.isiii.model.Actividad;

@Component("actividadBean")
public class ActividadBean {

	private static final Logger logger = LoggerFactory
			.getLogger(ActividadBean.class);

	private Actividad actividad = new Actividad();

	private List<Actividad> actividads;

	@Autowired
	private IActividadDao iActividadDao;

	public IActividadDao getiActividadDao() {
		return iActividadDao;
	}

	public void setiActividadDao(IActividadDao iActividadDao) {
		this.iActividadDao = iActividadDao;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public void setActividads(List<Actividad> actividads) {
		this.actividads = actividads;
	}

	public void save() {
		if (actividad.getId() == null)
			getiActividadDao().create(actividad);
		else
			getiActividadDao().update(actividad);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta",
				"Operación realizada con éxito.");
		if (FacesContext.getCurrentInstance() != null)
			FacesContext.getCurrentInstance().addMessage(null, msg);
		invalidateActividads();
		resetActividad();
	}

	public void eliminar(Long id) {
		try {
			getiActividadDao().delete(id);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Borrar", "Operación realizada con éxito.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (HibernateJdbcException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Borrar", "La actividad tiente relaciones activas. Elimine primero las ciudades.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Borrar", "Error al eliminar."+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}finally
		{
			resetActividad();			
		}
		
		
	}

	private void invalidateActividads() {
		actividads = null;
	}

	public void resetActividad() {
		actividad = new Actividad();
	}

	public List<Actividad> getActividads() {
		if (actividads == null) {
			actividads = getiActividadDao().findAll();
		}
		return actividads;
	}

	public void seleccionar(Actividad actividad) {
		setActividad(actividad);
	}

}
