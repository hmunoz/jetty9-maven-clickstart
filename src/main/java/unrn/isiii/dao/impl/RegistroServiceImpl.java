package unrn.isiii.dao.impl;

import org.springframework.stereotype.Component;

import unrn.isiii.dao.IRegistroDao;
import unrn.isiii.dao.generic.DataAccessService;
import unrn.isiii.model.Registro;

@Component
public class RegistroServiceImpl extends DataAccessService<Registro> implements
		IRegistroDao {

	public RegistroServiceImpl() {
		super(Registro.class);
	}

}
