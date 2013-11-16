package unrn.isiii.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import unrn.isiii.dao.IPropietarioDao;
import unrn.isiii.dao.ISitioDao;
import unrn.isiii.model.Coordenada;
import unrn.isiii.model.Propietario;
import unrn.isiii.model.Sitio;

@Component("sitioBean")
public class SitioBean {

	private static final Logger logger = LoggerFactory
			.getLogger(SitioBean.class);

	private Sitio sitio;

	private List<Sitio> sitios;

	// Centro de Viedma
	private Coordenada coordenada = new Coordenada().lat(-40.8206348).lng(-63.0003861);

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	@Autowired
	private ISitioDao iSitioDao;

	@Autowired
	private IPropietarioDao iPropietarioDao;

	static String icon = "resources/img/colmena.png";

	static String shadow = "resources/img/shadow.png";

	private MapModel simpleModel = new DefaultMapModel();

	private Marker marker;

	private MapModel draggableModel = new DefaultMapModel();

	public void setDraggableModel(MapModel draggableModel) {
		this.draggableModel = draggableModel;
	}

	public SitioBean() {
	}

	@PostConstruct
	public void init() {
		setSitio(new Sitio());
		getSitio().setCoordenada(getCoordenada());
		setDraggableModel(new DefaultMapModel());
	}

	public MapModel getSimpleModel() {
		LatLng coord;
		for (Sitio s : getSitios()) {
			// Shared coordinates
			coord = new LatLng(s.getCoordenada().getLatitud(), s
					.getCoordenada().getLongitud());
			// Basic marker
			simpleModel.addOverlay(new Marker(coord, s.getNombre(), s
					.getNombre(), icon, shadow));
		}
		return simpleModel;
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public MapModel getDraggableModel() {
		return draggableModel;
	}

	public IPropietarioDao getiPropietarioDao() {
		return iPropietarioDao;
	}

	public void setiPropietarioDao(IPropietarioDao iPropietarioDao) {
		this.iPropietarioDao = iPropietarioDao;
	}

	public Sitio getSitio() {
		return sitio;
	}

	public void setSitio(Sitio sitio) {
		this.sitio = sitio;
	}

	public void setSitios(List<Sitio> sitios) {
		this.sitios = sitios;
	}

	public ISitioDao getiSitioDao() {
		return iSitioDao;
	}

	public void setiSitioDao(ISitioDao iSitioDao) {
		this.iSitioDao = iSitioDao;
	}

	public void save(Propietario productor) {
		productor = getiPropietarioDao().find(productor.getId());		
		if (sitio.getId() == null){
			productor.agregarSitio(sitio);
			getiSitioDao().create(sitio);
		}
		else
			getiSitioDao().update(sitio);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta",
				"Operación realizada con éxito.");
		if (FacesContext.getCurrentInstance() != null)
			FacesContext.getCurrentInstance().addMessage(null, msg);

		resetSitio();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminar(Propietario productor, Sitio sitio) {
		productor = getiPropietarioDao().find(productor.getId());
		productor.eliminarSitio(sitio);
		getiPropietarioDao().update(productor);
		getiSitioDao().delete(sitio.getId());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Borrar", "Operación realizada con éxito.");
		if (FacesContext.getCurrentInstance() != null)
			FacesContext.getCurrentInstance().addMessage(null, msg);

		resetSitio();
	}

	public List<Sitio> getSitios() {
		return getiSitioDao().findAll();
	}

	public void resetSitio() {
		init();
		simpleModel.getMarkers().clear();
		// draggableModel.getMarkers().clear();
	}

	public void seleccionar(Sitio sitio) {
		setSitio(sitio);
	}
	
	public void onMarkerDrag(MarkerDragEvent event) {  
        marker = event.getMarker();  
        getSitio().getCoordenada().lat(marker.getLatlng().getLat()).lng(marker.getLatlng().getLng());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + 
        marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
        
    }  
	
	public void cambioDepto()
	{
		if (getSitio()!=null && getSitio().getDepartamento() != null)
		{
			//getSitio().setCoordenada(getSitio().getDepartamento().getCoordenada());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cambio de Depto:", getSitio().getDepartamento().getDescripcion()));			
		}
	}

}
