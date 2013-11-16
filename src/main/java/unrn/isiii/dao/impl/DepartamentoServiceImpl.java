package unrn.isiii.dao.impl;

import org.springframework.stereotype.Component;

import unrn.isiii.dao.IDepartamentoDao;
import unrn.isiii.dao.generic.DataAccessService;
import unrn.isiii.model.Departamento;

@Component
public class DepartamentoServiceImpl extends DataAccessService<Departamento>
		implements IDepartamentoDao {

	public DepartamentoServiceImpl() {
		super(Departamento.class);
	}

}
