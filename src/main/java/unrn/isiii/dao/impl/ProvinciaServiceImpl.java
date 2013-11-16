package unrn.isiii.dao.impl;

import org.springframework.stereotype.Component;

import unrn.isiii.dao.IProvinciaDao;
import unrn.isiii.dao.generic.DataAccessService;
import unrn.isiii.model.Provincia;

@Component
public class ProvinciaServiceImpl extends DataAccessService<Provincia> implements
		IProvinciaDao {

	public ProvinciaServiceImpl() {
		super(Provincia.class);
	}

}
