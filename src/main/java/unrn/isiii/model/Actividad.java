package unrn.isiii.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Actividad implements  Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 49247170650931023L;

	public Actividad(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public Actividad() {
		super();
	}
	
	@Id
	@GeneratedValue
	private Long id;	
	
	@Column(length = 48)
	@NotNull(message = "El campo descripción no puede quedar vacio")
	@Size(max = 48, min = 2, message = "El nombre debe tener entre 2 y 48 caracteres")
	private String descripcion;
	
	
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actividad other = (Actividad) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	

}
