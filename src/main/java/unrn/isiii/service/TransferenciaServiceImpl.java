package unrn.isiii.service;

import unrn.isiii.model.tdd.TranferirOperacion;

public class TransferenciaServiceImpl implements TransferenciaService {
	
	private InteresService interesService;	

	public InteresService getInteresService() {
		return interesService;
	}

	public void setInteresService(InteresService interesService) {
		this.interesService = interesService;
	}

	public void transferir(TranferirOperacion operacion) {
		operacion.getDesde().retirar(operacion.getMonto());		
		operacion.getHacia().depositar(getInteresService().interesPorMonto(operacion.getMonto()));
	}

}
