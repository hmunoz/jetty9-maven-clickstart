package unrn.isiii.dao.impl;

import org.springframework.stereotype.Component;

import unrn.isiii.dao.ISitioDao;
import unrn.isiii.dao.generic.DataAccessService;
import unrn.isiii.model.Sitio;

@Component
public class SitioServiceImpl extends DataAccessService<Sitio> implements
		ISitioDao {

	public SitioServiceImpl() {
		super(Sitio.class);
	}

}
