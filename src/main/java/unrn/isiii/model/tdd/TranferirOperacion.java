package unrn.isiii.model.tdd;


public class TranferirOperacion {
	private Cuenta desde;
	private Cuenta hacia;
	private Double monto;
	
	
	
	public TranferirOperacion() {
		super();
	}
	
	public TranferirOperacion desde( Cuenta cuenta)
	{
		this.desde = cuenta;
		return this;
	}
	public TranferirOperacion hacia( Cuenta cuenta)
	{
		this.hacia = cuenta;
		return this;
	}
	public TranferirOperacion monto( Double monto)
	{
		this.monto = monto;
		return this;
	}
	
	public Cuenta getDesde() {
		return desde;
	}
	public void setDesde(Cuenta desde) {
		this.desde = desde;
	}
	public Cuenta getHacia() {
		return hacia;
	}
	public void setHacia(Cuenta hacia) {
		this.hacia = hacia;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	
}
