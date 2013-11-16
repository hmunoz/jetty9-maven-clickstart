package unrn.isiii.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Departamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6039947968358670026L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 48)
	@NotNull(message = "El campo descripción no puede quedar vacio")
	@Size(max = 48, min = 2, message = "El campo descripción debe contener entre 2 y 48 caracteres")
	private String descripcion;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {
			CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "provincia_id")
	@NotNull(message = "El campo provincia no puede quedar vacio")
	private Provincia provincia;

	@OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY, cascade = {
			CascadeType.MERGE, CascadeType.PERSIST })
	private List<Sitio> sitios = new ArrayList<Sitio>(0);

	@NotNull(message = "El campo codigo postal no puede quedar vacio")
	@Min(value = 1000, message = "El campo codigo postal debe tener valor mayor a 1000")
	@Max(value = 9999, message = "El campo codigo postal debe tener valor menor a 9999")
	private int cp;

	@Embedded
	private Coordenada coordenada = new Coordenada().lat(-40.8206348).lng(-63.0003861);

	public Departamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Departamento(String descripcion, Provincia provincia, int cp) {
		super();
		this.descripcion = descripcion;
		this.provincia = provincia;
		this.cp = cp;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

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

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public List<Sitio> getSitios() {
		return sitios;
	}

	public void setSitios(List<Sitio> sitios) {
		this.sitios = sitios;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
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
		Departamento other = (Departamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
