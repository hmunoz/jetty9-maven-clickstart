package unrn.isiii.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Registro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3173605277513848380L;

	public enum Causa {
		LA("Loque Americana"), ZG("Zanganera"), HM("Hombre"), OTRA("Otra");

		private String causa;

		private Causa(String causa) {
			this.causa = causa;
		}

		@Override
		public String toString() {
			return causa;
		}
	}

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "El campo causa no puede quedar vacio")
	private Causa causaColmenaMuerta;

	@NotNull(message = "El campo cantidad de colmenas muerta no puede quedar vacio")
	private Integer cantColmenaMuerta = 0;

	@NotNull(message = "El campo proteico de colmenas muerta no puede quedar vacio")
	private Double kgProteico = 0D;

	@NotNull(message = "El campo azucar de colmenas muerta no puede quedar vacio")
	private Double kgAzucar = 0D;

	private Integer colmenasTraslado = 0;

	private Integer nucleosTraslado = 0;

	private Integer cantVenta = 0;

	private Integer cantAmpliacion = 0;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@NotNull(message = "El campo Actividad no puede quedar vacio")
	@JoinColumn(name = "depto_id", nullable = false, referencedColumnName = "id")
	private Actividad actividad;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL,
			CascadeType.MERGE })
	@NotNull(message = "El campo productor no puede quedar vacio")
	@JoinColumn(name = "propietario_id", nullable = false, referencedColumnName = "id")
	private Propietario propietario;

	@Temporal(TemporalType.DATE)
	@NotNull(message = "El campo fecha no puede quedar vacio")
	private Date fecha;

	public Registro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Causa getCausaColmenaMuerta() {
		return causaColmenaMuerta;
	}

	public void setCausaColmenaMuerta(Causa causa) {
		this.causaColmenaMuerta = causa;
	}

	public Integer getCantColmenaMuerta() {
		return cantColmenaMuerta;
	}

	public void setCantColmenaMuerta(Integer cantColmenaMuerta) {
		this.cantColmenaMuerta = cantColmenaMuerta;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Double getKgProteico() {
		return kgProteico;
	}

	public void setKgProteico(Double kgProteico) {
		this.kgProteico = kgProteico;
	}

	public Double getKgAzucar() {
		return kgAzucar;
	}

	public void setKgAzucar(Double kgAzucar) {
		this.kgAzucar = kgAzucar;
	}

	public Integer getColmenasTraslado() {
		return colmenasTraslado;
	}

	public void setColmenasTraslado(Integer colmenasTraslado) {
		this.colmenasTraslado = colmenasTraslado;
	}

	public Integer getNucleosTraslado() {
		return nucleosTraslado;
	}

	public void setNucleosTraslado(Integer nucleosTraslado) {
		this.nucleosTraslado = nucleosTraslado;
	}

	public Integer getCantVenta() {
		return cantVenta;
	}

	public void setCantVenta(Integer cantVenta) {
		this.cantVenta = cantVenta;
	}

	public Integer getCantAmpliacion() {
		return cantAmpliacion;
	}

	public void setCantAmpliacion(Integer cantAmpliacion) {
		this.cantAmpliacion = cantAmpliacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		Registro other = (Registro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
