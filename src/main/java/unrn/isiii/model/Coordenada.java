package unrn.isiii.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Access(AccessType.PROPERTY)
public class Coordenada implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -569839958776305463L;

	@NotNull(message = "El campo latitud no puede quedar vacio")
	private Double latitud;

	@NotNull(message = "El campo longitud no puede quedar vacio")
	private Double longitud;

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Coordenada lat(Double latitud) {
		this.latitud = latitud;
		return this;
	}
	
	public Coordenada lng(Double longitud) {
		this.longitud = longitud;
		return this;
	}

}
