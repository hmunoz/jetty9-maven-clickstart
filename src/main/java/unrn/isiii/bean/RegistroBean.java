package unrn.isiii.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import unrn.isiii.dao.IPropietarioDao;
import unrn.isiii.dao.IRegistroDao;
import unrn.isiii.model.Propietario;
import unrn.isiii.model.Registro;
import unrn.isiii.model.Registro.Causa;

@Component("registroBean")
public class RegistroBean {

	private static final Logger logger = LoggerFactory
			.getLogger(RegistroBean.class);

	private Registro registro;

	private List<Registro> registros;

	@Autowired
	private IRegistroDao iRegistroDao;

	@Autowired
	private IPropietarioDao iPropietarioDao;

	public RegistroBean() {
	}

	@PostConstruct
	public void init() {
		setRegistro(new Registro());

	}

	public IPropietarioDao getiPropietarioDao() {
		return iPropietarioDao;
	}

	public void setiPropietarioDao(IPropietarioDao iPropietarioDao) {
		this.iPropietarioDao = iPropietarioDao;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}

	public IRegistroDao getiRegistroDao() {
		return iRegistroDao;
	}

	public void setiRegistroDao(IRegistroDao iRegistroDao) {
		this.iRegistroDao = iRegistroDao;
	}

	public void save(Propietario productor) {
		productor = getiPropietarioDao().find(productor.getId());
		if (registro.getId() == null) {
			getRegistro().setPropietario(productor);
			getiRegistroDao().create(registro);
		} else
			getiRegistroDao().update(registro);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta",
				"Operación realizada con éxito.");
		if (FacesContext.getCurrentInstance() != null)
			FacesContext.getCurrentInstance().addMessage(null, msg);

		resetRegistro();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminar(Registro registro) {
		getiRegistroDao().delete(registro.getId());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Borrar", "Operación realizada con éxito.");
		if (FacesContext.getCurrentInstance() != null)
			FacesContext.getCurrentInstance().addMessage(null, msg);

		resetRegistro();
	}

	public List<Registro> getRegistros() {
		return getiRegistroDao().findAll();
	}

	public void resetRegistro() {
		init();
	}

	public void seleccionar(Registro registro) {
		setRegistro(registro);
	}

	public SelectItem[] getCausas() {
		SelectItem[] resultado = new SelectItem[Causa.values().length];
		int index = 0;
		for (Causa av : Causa.values()) {
			resultado[index++] = new SelectItem(av, av.toString());
		}
		return resultado;
	}

}
