package unrn.isiii.dao.impl;

import org.springframework.stereotype.Component;

import unrn.isiii.dao.IPropietarioDao;
import unrn.isiii.dao.generic.DataAccessService;
import unrn.isiii.model.Propietario;

@Component
public class PropietarioServiceImpl extends DataAccessService<Propietario>
		implements IPropietarioDao {

	public PropietarioServiceImpl() {
		super(Propietario.class);
	}

}
