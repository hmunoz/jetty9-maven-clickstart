package unrn.isiii.dao.impl;

import org.springframework.stereotype.Component;

import unrn.isiii.dao.ICuotaDao;
import unrn.isiii.dao.generic.DataAccessService;
import unrn.isiii.model.state.*;;

@Component
public class CuotaServiceImpl extends DataAccessService<Cuota> implements
		ICuotaDao {

	public CuotaServiceImpl() {
		super(Cuota.class);
	}

}
