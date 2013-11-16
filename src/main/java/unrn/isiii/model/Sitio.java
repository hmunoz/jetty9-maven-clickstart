package unrn.isiii.model;

import java.io.Serializable;

import javax.jdo.annotations.Embedded;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sitio")
public class Sitio implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4474851629392283304L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 48)
	@NotNull(message = "El campo nombre no puede quedar vacio")
	@Size(max = 48, min = 2)
	private String nombre;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@NotNull(message = "El campo departamento no puede quedar vacio")
	@JoinColumn(name = "depto_id", nullable = false, referencedColumnName = "id")
	private Departamento departamento;

	@Embedded
	private Coordenada coordenada = new Coordenada();

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL,CascadeType.MERGE })
	@NotNull(message = "El campo propietario no puede quedar vacio")
	@JoinColumn(name = "propietario_id", nullable = false, referencedColumnName = "id")
	private Propietario propietario;

	public Sitio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sitio(String nombre, Departamento departamento,
			Coordenada coordenada, Propietario propietario) {
		super();
		this.nombre = nombre;
		this.departamento = departamento;
		this.coordenada = coordenada;
		this.propietario = propietario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
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
		Sitio other = (Sitio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean marcada() {
		if (this.getCoordenada() != null) {
			return true;
		}
		return false;
	}
}
