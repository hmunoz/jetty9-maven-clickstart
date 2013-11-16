package unrn.isiii.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import unrn.isiii.dao.IPropietarioDao;
import unrn.isiii.model.Coordenada;
import unrn.isiii.model.Departamento;
import unrn.isiii.model.Propietario;
import unrn.isiii.model.Provincia;
import unrn.isiii.model.Sitio;

@Component("loginBean")
@Scope("session")
public class LoginBean {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginBean.class);

	@Autowired
	private IPropietarioDao iPropietarioDao;

	private Propietario productor;

	public IPropietarioDao getiPropietarioDao() {
		return iPropietarioDao;
	}

	public void setiPropietarioDao(IPropietarioDao iPropietarioDao) {
		this.iPropietarioDao = iPropietarioDao;
	}

	public Propietario getProductor() {
		return productor;
	}

	public void setProductor(Propietario productor) {
		this.productor = productor;
	}

	public void login() {
		if (getProductor() == null) {
			if (iPropietarioDao.findAll().size() > 0) {
				productor = iPropietarioDao.findAll().get(0);
			} else {
				Provincia provincia = new Provincia("Rio Negro");
				Departamento departamento = new Departamento("Viedma",
						provincia, 8500);
				Propietario p = new Propietario("Pedro", "Alvarez",
						"munozhoracio@gmail.com", "434567");

				Sitio sitio = new Sitio("Nuevo", departamento, new Coordenada()
						.lat(-40.8206348).lng(-63.0003861), p);
				p.getSitios().add(sitio);
				getiPropietarioDao().create(p);

				setProductor(p);
			}
		}
	}

}
