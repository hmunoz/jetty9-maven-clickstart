package unrn.isiii.model.state;

public enum CuotaEstado implements CuotaEstadoOperacion {

	PENDEINTE(new PendienteRso()),
	PAGADA_TOTAL(new PagadaTotalRso());

	private final CuotaEstadoOperacion operations;

	CuotaEstado(CuotaEstadoOperacion operations) {
		this.operations = operations;
	}

	public void pagar(Cuota cuota) {
		operations.pagar(cuota);
	}

	@Override
	public String nombre() {
		return operations.nombre();
	}

}
