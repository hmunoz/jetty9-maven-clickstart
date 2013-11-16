package unrn.isiii.model.tdd;


public class Cuenta {
	private Double balance;
	
	
	
	public Cuenta(Double balance) {
		super();
		this.balance = balance;
	}

	public void retirar(Double monto)
	{
		validarRetirar(monto);		
		this.balance -=monto;
	}

	private void validarRetirar(Double monto) {
		if (this.balance-monto< 0)
			throw new RetirarException("La cuenta no puede quedar en negativo.");
	}
	
	public void depositar(Double monto){
		this.balance += monto;
	}	
	public Double getBalance()
	{
		return balance;
	}
}
