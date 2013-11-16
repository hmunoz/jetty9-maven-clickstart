package unrn.isiii.dao.impl;

import org.springframework.stereotype.Component;

import unrn.isiii.dao.IActividadDao;
import unrn.isiii.dao.generic.DataAccessService;
import unrn.isiii.model.Actividad;


@Component
public class ActividadServiceImpl extends DataAccessService<Actividad> implements
		IActividadDao {

	public ActividadServiceImpl() {
		super(Actividad.class);
	}

}
