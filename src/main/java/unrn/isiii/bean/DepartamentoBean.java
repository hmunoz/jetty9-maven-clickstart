package unrn.isiii.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateJdbcException;
import org.springframework.stereotype.Component;

import unrn.isiii.dao.IDepartamentoDao;
import unrn.isiii.model.Coordenada;
import unrn.isiii.model.Departamento;

@Component("departamentoBean")
public class DepartamentoBean {

	private static final Logger logger = LoggerFactory
			.getLogger(DepartamentoBean.class);

	private Departamento departamento = new Departamento();

	private List<Departamento> departamentos;

	@Autowired
	private IDepartamentoDao iDepartamentoDao;

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Departamento> getDepartamentos() {
		if (departamentos == null) {
			departamentos = getiDepartamentoDao().findAll();
		}
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {

		this.departamentos = departamentos;
	}

	public IDepartamentoDao getiDepartamentoDao() {
		return iDepartamentoDao;
	}

	public void setiDepartamentoDao(IDepartamentoDao iDepartamentoDao) {
		this.iDepartamentoDao = iDepartamentoDao;
	}

	public void save() {
		//TODO falta en la Pagina
		getDepartamento().setCoordenada(new Coordenada().lat(-40.8206348).lng(-63.0003861));
		if (departamento.getId() == null)
			getiDepartamentoDao().create(departamento);
		else
			getiDepartamentoDao().update(departamento);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta",
				"Operación realizada con éxito.");
		if (FacesContext.getCurrentInstance() != null)
			FacesContext.getCurrentInstance().addMessage(null, msg);
		invalidateDepartamentos();
		resetDepartamento();
	}

	public void eliminar(Long id) {
		try {
			getiDepartamentoDao().delete(id);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Borrar", "Operación realizada con éxito.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (HibernateJdbcException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Borrar",
					"El departamento tiente relaciones activas. Elimine primero los sitios.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Borrar", "Error al eliminar." + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} finally {
			resetDepartamento();
			invalidateDepartamentos();
		}

	}

	private void invalidateDepartamentos() {
		departamentos = null;
	}

	public void resetDepartamento() {
		departamento = new Departamento();
	}

	public void seleccionar(Departamento departamento) {
		setDepartamento(departamento);
	}

}
