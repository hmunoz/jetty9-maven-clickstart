package unrn.isiii.model.state;

public class PendienteRso implements CuotaEstadoOperacion{

	
	public void pagar(Cuota cuota) {
		cuota.setEstado(CuotaEstado.PAGADA_TOTAL);
	}

	@Override
	public String nombre() {
		return "Pendiente";
	}

}
