package unrn.isiii.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Propietario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6681449386755798107L;

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty(message = "El campo nombre no puede quedar vacio")
	@Column(length = 48)
	private String nombre;

	@NotEmpty(message = "El campo apellido no puede quedar vacio")
	@Column(length = 48)
	private String apellido;

	@NotEmpty(message = "El campo email no puede quedar vacio")
	@Email(message = "El formato del email es invalido")
	@Column(length = 48)
	private String email;

	@NotEmpty(message = "El campo telefono no puede quedar vacio")
	//@Pattern(regexp = "[0-9]{10}")
	private String telefono;

	@OneToMany(mappedBy = "propietario", fetch = FetchType.EAGER, cascade = {
			CascadeType.MERGE, CascadeType.PERSIST })
	private List<Sitio> sitios = new ArrayList<Sitio>(0);

	public Propietario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Propietario(String nombre, String apellido, String email,
			String telefono) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public List<Sitio> getSitios() {
		return sitios;
	}

	public void setSitios(List<Sitio> sitios) {
		this.sitios = sitios;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void agregarSitio(Sitio sitio) {
		sitio.setPropietario(this);
		getSitios().add(sitio);
	}

	public void eliminarSitio(Sitio sitio) {
		sitio.setPropietario(null);
		getSitios().remove(sitio);
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
		Propietario other = (Propietario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
