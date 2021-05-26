package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import seguros.producto.gestionarproducto.configuration.Properties;
import seguros.producto.gestionarproducto.dto.ActionType;
import seguros.producto.gestionarproducto.dto.CoberturaProductoDto;
import seguros.producto.gestionarproducto.dto.EstadoProductoDto;
import seguros.producto.gestionarproducto.dto.InfoProductoDto;
import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.dto.ProductoDoDto;
import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.dto.State;
import seguros.producto.gestionarproducto.dto.TerminoCortoDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoSaveDto;
import seguros.producto.gestionarproducto.dto.TipoMultaDto;
import seguros.producto.gestionarproducto.entities.Canal;
import seguros.producto.gestionarproducto.entities.DestinoVenta;
import seguros.producto.gestionarproducto.entities.EstadoIntegracion;
import seguros.producto.gestionarproducto.entities.ModoTraspaso;
import seguros.producto.gestionarproducto.entities.Producto;
import seguros.producto.gestionarproducto.entities.ProductoDo;
import seguros.producto.gestionarproducto.entities.TarifaPor;
import seguros.producto.gestionarproducto.entities.TerminoCorto;
import seguros.producto.gestionarproducto.entities.TipoAjuste;
import seguros.producto.gestionarproducto.entities.TipoDescuento;
import seguros.producto.gestionarproducto.entities.TipoMulta;
import seguros.producto.gestionarproducto.entities.TipoPeriodo;
import seguros.producto.gestionarproducto.entities.TipoPromocion;
import seguros.producto.gestionarproducto.entities.TipoRecargo;
import seguros.producto.gestionarproducto.entities.TipoSeguro;
import seguros.producto.gestionarproducto.entities.TipoTarifa;
import seguros.producto.gestionarproducto.entities.TipoTraspaso;
import seguros.producto.gestionarproducto.exceptions.ResourceNotFoundException;
import seguros.producto.gestionarproducto.repositories.CanalRepository;
import seguros.producto.gestionarproducto.repositories.DestinoVentaRepository;
import seguros.producto.gestionarproducto.repositories.ModoTraspasoRepository;
import seguros.producto.gestionarproducto.repositories.ProductoRepository;
import seguros.producto.gestionarproducto.repositories.TarifaPorRepository;
import seguros.producto.gestionarproducto.repositories.TerminoCortoRepository;
import seguros.producto.gestionarproducto.repositories.TipoAjusteRepository;
import seguros.producto.gestionarproducto.repositories.TipoDescuentoRepository;
import seguros.producto.gestionarproducto.repositories.TipoMultaRepository;
import seguros.producto.gestionarproducto.repositories.TipoPeriodoRepository;
import seguros.producto.gestionarproducto.repositories.TipoPromocionRepository;
import seguros.producto.gestionarproducto.repositories.TipoRecargoRepository;
import seguros.producto.gestionarproducto.repositories.TipoSeguroRepository;
import seguros.producto.gestionarproducto.repositories.TipoTarifaRepository;
import seguros.producto.gestionarproducto.repositories.TipoTraspasoRepository;
import seguros.producto.gestionarproducto.services.EstadoIntegracionService;
import seguros.producto.gestionarproducto.services.ProductoService;


@Service
public class ProductoServiceImpl implements ProductoService {
	

	private static final Long VALUE_UNDEFINED=-1L;
	private static final String MSG_NOT_FOUND = "El recurso solicitado no existe";
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private TipoSeguroRepository tipoSeguroRepository;

	@Autowired
	private ModoTraspasoRepository modoTraspasoRepository;
	
	@Autowired
	private TipoPromocionRepository tipoPromocionRepository;
	
	@Autowired
	private TipoRecargoRepository tipoRecargoRepository;
	
	@Autowired
	private TipoAjusteRepository tipoAjusteRepository;
	
	@Autowired
	private TipoDescuentoRepository tipoDescuentoRepository;
	
	@Autowired
	private TarifaPorRepository tarifaPorRepository;	
	
	@Autowired
	private TipoTarifaRepository tipoTarifaRepository;
	
	@Autowired
	private TipoPeriodoRepository tipoPeriodoRepository;
	
	@Autowired
	private TipoTraspasoRepository tipoTraspasoRepository;
	
	@Autowired
	private DestinoVentaRepository destinoVentaRepository;
	
	
	@Autowired
	private EstadoIntegracionService estadoIntegracionService;
	
	@Autowired
	private CanalRepository canalRepository;
	
	@Autowired
	private TipoMultaRepository tipoMultaRepository;
	
	@Autowired
	private TerminoCortoRepository terminoCortoRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Properties properties;
	
	@Transactional
	@Override
	public List<ProductoDto> findAll() throws ProductoException {
		List<ProductoDto> list=new ArrayList<>();
		
		try {
			list= productoRepository.findAll().stream().map(item ->{
				ProductoDto p= new ProductoDto();
				BeanUtils.copyProperties(item, p);
				p.setTipoSeguro(item.getTipoSeguro().getId());
				p.setTipoPromocion(item.getTipoPromocion().getId());
				p.setTipoRecargo(item.getTipoRecargo().getId());
				p.setTipoAjuste(item.getTipoAjuste().getId());
				p.setTipoDescuento(item.getTipoDescuento().getId());
				p.setTarifaPor(item.getTarifaPor().getId());
				p.setTipoTarifa(item.getTipoTarifa().getId());
				p.setTipoPeriodo(item.getTipoPeriodo().getId());
				p.setTipoTraspaso(item.getTipoTraspaso().getId());
				p.setTipoAcreedor(item.getTipoAcreedor().getId());
				p.setTipoFacturar(item.getTipoFacturar().getId());
				ProductoDoDto productoDoDto= new ProductoDoDto();
				BeanUtils.copyProperties(item.getProductDo(), productoDoDto);
				productoDoDto.setDoplAQuienSeVende(item.getProductDo().getDoplAQuienSeVende().getId());
				p.setProductDo(productoDoDto);
				return p;
				 
			}).collect(Collectors.toList());
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}
		return list;
		
	}

	@Transactional
	@Override
	public InfoProductoDto save(ProductoDto producto) throws ProductoException {
		InfoProductoDto result=new InfoProductoDto();
		
		try {
			Producto productoEntity = producto.toEntity();
			String newNemotecnico = productoRepository.generateNemotecnico();

			if(producto.getTipoSeguro()!=null && !VALUE_UNDEFINED.equals(producto.getTipoSeguro()) ) {
				TipoSeguro tipoSeguro = tipoSeguroRepository.getOne(producto.getTipoSeguro());
				if(tipoSeguro.getId()!=null) {
				   productoEntity.setTipoSeguro(tipoSeguro);
				}
			}
			if(producto.getTipoAcreedor()!=null && !VALUE_UNDEFINED.equals(producto.getTipoAcreedor())) {
				ModoTraspaso tipoAcreedor = modoTraspasoRepository.getOne(producto.getTipoAcreedor());
				if(tipoAcreedor.getId()!=null) {
					productoEntity.setTipoAcreedor(tipoAcreedor);
				}
			}
			if(producto.getTipoFacturar()!=null && !VALUE_UNDEFINED.equals(producto.getTipoFacturar())) {
				ModoTraspaso tipoFacturar = modoTraspasoRepository.getOne(producto.getTipoFacturar());
				if(tipoFacturar.getId()!=null) {
					productoEntity.setTipoFacturar(tipoFacturar);
				}
			}
			if(producto.getTipoPromocion()!=null && !VALUE_UNDEFINED.equals(producto.getTipoPromocion()) ) {
				TipoPromocion tipoPromocion = tipoPromocionRepository.getOne(producto.getTipoPromocion());
				if(tipoPromocion.getId()!=null) {
					productoEntity.setTipoPromocion(tipoPromocion);
				}
			}
			if(producto.getTipoRecargo()!=null && !VALUE_UNDEFINED.equals(producto.getTipoRecargo()) ) {
				TipoRecargo tipoRecargo = tipoRecargoRepository.getOne(producto.getTipoRecargo());
				if(tipoRecargo.getId()!=null) {
				  productoEntity.setTipoRecargo(tipoRecargo);
				}
			}
			if(producto.getTipoAjuste()!=null && !VALUE_UNDEFINED.equals(producto.getTipoAjuste()) ) {
				TipoAjuste tipoAjuste = tipoAjusteRepository.getOne(producto.getTipoAjuste());
				if(tipoAjuste.getId()!=null) {
			     	productoEntity.setTipoAjuste(tipoAjuste);
				}
			}
			if(producto.getTipoDescuento()!=null && !VALUE_UNDEFINED.equals(producto.getTipoDescuento()) ) {
				TipoDescuento tipoDescuento = tipoDescuentoRepository.getOne(producto.getTipoDescuento());
				if(tipoDescuento.getId()!=null) {
					productoEntity.setTipoDescuento(tipoDescuento);
				}
			}
			if(producto.getTarifaPor()!=null && !VALUE_UNDEFINED.equals(producto.getTarifaPor())) {
				TarifaPor tarifaPor = tarifaPorRepository.getOne(producto.getTarifaPor());
				if(tarifaPor.getId()!=null) {
					productoEntity.setTarifaPor(tarifaPor);
				}			
			}
			if(producto.getTipoTarifa()!=null && !VALUE_UNDEFINED.equals(producto.getTipoTarifa()) ) {
				TipoTarifa tipoTarifa = tipoTarifaRepository.getOne(producto.getTipoTarifa());
				if(tipoTarifa.getId()!=null) {
				  productoEntity.setTipoTarifa(tipoTarifa);
				}
			}
			if(producto.getTipoPeriodo()!=null && !VALUE_UNDEFINED.equals(producto.getTipoPeriodo()) ) {
				TipoPeriodo tipoPeriodo = tipoPeriodoRepository.getOne(producto.getTipoPeriodo());
				if(tipoPeriodo.getId() != null) {
				  productoEntity.setTipoPeriodo(tipoPeriodo);
				}
			}
			if(producto.getTipoTraspaso()!=null && !VALUE_UNDEFINED.equals(producto.getTipoTraspaso()) ) {
				TipoTraspaso tipoTraspaso = tipoTraspasoRepository.getOne(producto.getTipoTraspaso());
				if(tipoTraspaso.getId()!=null) {
				  productoEntity.setTipoTraspaso(tipoTraspaso);
				}
			}
			
			if( producto.getProductDo()!=null ) {
				ProductoDo productoDo = producto.getProductDo().toEntity();
				if(producto.getProductDo().getDoplAQuienSeVende()!=null && !VALUE_UNDEFINED.equals(producto.getProductDo().getDoplAQuienSeVende()) ) {
					DestinoVenta destinoVenta = destinoVentaRepository.getOne(producto.getProductDo().getDoplAQuienSeVende());
					
					if(destinoVenta.getId()!=null) {
						productoDo.setDoplAQuienSeVende(destinoVenta);	
					}				
					productoEntity.setProductDo(productoDo);
				}
			}			
				
			
			productoEntity.setNemot(newNemotecnico);
			
			String palabraPase= encrypt(productoEntity.getPalabaraPaseProductManager());
			productoEntity.setPalabaraPaseProductManager(palabraPase);		
			
			
			productoRepository.save(productoEntity);
			
			EstadoIntegracion estadoIntegracion = new EstadoIntegracion();
			
			// TODO implementar guardado de canal para todos
			Canal canal= canalRepository.getOne(4L);
			
			if(canal.getId()!=null) {
				estadoIntegracion.setCanal(canal);
			}
			
			Arrays.asList(producto.getCanales()).stream().forEach((p) ->{
				Canal canalEntity= canalRepository.getOne(p);
				if(canalEntity.getId()!=null) {					
					productoEntity.addCanal(canalEntity);
				}
			});
			
			
			estadoIntegracion.setIdProducto(productoEntity.getId());
			estadoIntegracion.setState(State.Pendiente);
			estadoIntegracion.setTipoAccion(ActionType.Crear);
			
			estadoIntegracionService.save(estadoIntegracion);
			
			result.setNemotecnico(newNemotecnico);
			result.setId(productoEntity.getId());
		}
		catch(ProductoException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}
		return result;
	}

	@Transactional
	@Override
	public PageProductoDto findAllPaginated(int page, int size, Integer idCompania, Integer idNegocio,
			Integer idRamo, String nemotecnico, String descripcion) throws ProductoException {
	
		PageProductoDto pageProductoDto= null;
		
		try {
			pageProductoDto= productoRepository.findAllPaginated(page, size, idCompania, idNegocio, idRamo, nemotecnico, descripcion);
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}
		return pageProductoDto;
	}

	@Override
	public String encrypt(String palabrasePase) throws ProductoException {
		String result = null;
		String urlEncript= properties.getUrl_encrypt();
		
		try {
			  result = restTemplate.postForObject(urlEncript, palabrasePase, String.class);
			
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}
		return result;
	}

	@Override
	public String decrypt(String palabrasePase) throws ProductoException {
		String result = null;
		String urlDecrypt= properties.getUrl_decrypt();
		
		try {
			 result = restTemplate.postForObject(urlDecrypt, palabrasePase, String.class);
			
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}
		return result;
	}

	@Override
	public String getPassProductManagerByIdProducto(Long idProducto) throws ProductoException,ResourceNotFoundException {
		String result = null;
		String urlDecript= properties.getUrl_decrypt();
		
		try {
			Optional<Producto> producto= productoRepository.findById(idProducto);
			if(producto.isPresent()) {
				Producto productoEntitiy = producto.get();
				String palabraPase = productoEntitiy.getPalabaraPaseProductManager();

				result = restTemplate.postForObject(urlDecript, palabraPase, String.class);
			}
			else {
				ResourceNotFoundException e = new ResourceNotFoundException();
				e.setConcreteException(e);
				e.setErrorMessage(MSG_NOT_FOUND);
				e.setDetail(MSG_NOT_FOUND);
				throw e;
			}
			   
			
		}
		catch(ResourceNotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}
		return result;
	}

	@Override
	public void enableDisable(EstadoProductoDto estadoProductoDto) throws ProductoException {
		try {
			estadoProductoDto.getIdsProducto().forEach( id -> {
				Optional<Producto> producto = productoRepository.findById(id);
				if(producto.isPresent()) {
					Producto productoEntity = producto.get();
					productoEntity.setHabilitado(estadoProductoDto.getHabilitado());
					productoRepository.save(productoEntity);
				}
				
			});
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}
	}

	
	@Transactional
	@Override
	public List<TerminoCortoDto> getTerminosCortosByProduct(Long id) throws ProductoException,ResourceNotFoundException {
		List<TerminoCortoDto> lista= new ArrayList<>();
	
		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				Producto producto=productoO.get();
				Set<TerminoCorto> listaTerminoCorto= producto.getTerminosCortos();
			
				lista=listaTerminoCorto.stream().map(e->{
					
					TerminoCortoDto t= new TerminoCortoDto(); 
					BeanUtils.copyProperties(e, t);
					if(e.getTipoMulta()!=null) { 
						TipoMultaDto tipoMultaDto = new TipoMultaDto();
						 BeanUtils.copyProperties(e.getTipoMulta(), tipoMultaDto);
						 t.setTipoMulta(tipoMultaDto);
					 }
					if(e.getMoneda() == null) {
						e.setMoneda("N/A");
					}
					return t;
					
				}).collect(Collectors.toList());
				Collections.sort(lista,(TerminoCortoDto f1,TerminoCortoDto f2) -> f1.getMesHasta().compareTo(f2.getMesHasta()));
			}
			else {
				ResourceNotFoundException e = new ResourceNotFoundException();
				e.setConcreteException(e);
				e.setErrorMessage(MSG_NOT_FOUND);
				e.setDetail(MSG_NOT_FOUND);
				throw e;
			}
		}
		catch(ResourceNotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}
		return lista;
	}

	
	@Transactional
	@Override
	public void saveTerminosCortosByProduct(Long id, List<TerminoCortoSaveDto> terminosCortos)
			throws ProductoException, ResourceNotFoundException {

		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				Producto producto=productoO.get();				
				
				  terminosCortos.stream().forEach(e->{
				  
				  TerminoCorto terminoCortoEntity= new TerminoCorto();
				  
				  BeanUtils.copyProperties(e, terminoCortoEntity); 
				  TipoMulta tipoMulta=	  tipoMultaRepository.getOne(e.getTipoMulta());
				  if(tipoMulta.getId()!=null) { 
					  terminoCortoEntity.setTipoMulta(tipoMulta); 
					  }
				  if(terminoCortoEntity.getMoneda().equalsIgnoreCase("-1")) {
					  terminoCortoEntity.setMoneda(null);
				  }
				  producto.addTerminoCorto(terminoCortoEntity);
				  
				  });
				 

				 productoRepository.save(producto);
				
			}
			else {
				ResourceNotFoundException e = new ResourceNotFoundException();
				e.setConcreteException(e);
				e.setErrorMessage(MSG_NOT_FOUND);
				e.setDetail(MSG_NOT_FOUND);
				throw e;
			}
		}
		catch(ResourceNotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}
		
	}

	
	@Transactional
	@Override
	public void deleteTerminosCortosByProduct(Long idProducto, Long idTerminoCorto)	throws ProductoException, ResourceNotFoundException {
		
		try {
			
			Optional<Producto> productoO= productoRepository.findById(idProducto);
			Optional<TerminoCorto> terminoCortoO= terminoCortoRepository.findById(idTerminoCorto);
			
			if(productoO.isPresent() && terminoCortoO.isPresent()) {
				Producto producto=productoO.get();				
				TerminoCorto terminoCorto=terminoCortoO.get();
				producto.removeTerminoCorto(terminoCorto);			 

				productoRepository.save(producto);
				
			}
			else {
				ResourceNotFoundException e = new ResourceNotFoundException();
				e.setConcreteException(e);
				e.setErrorMessage(MSG_NOT_FOUND);
				e.setDetail(MSG_NOT_FOUND);
				throw e;
			}
		}
		catch(ResourceNotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}
		
	}

	@Transactional
	@Override
	public void updateTerminosCortosByProduct(Long id, Long idTerminoCorto, TerminoCortoSaveDto terminosCortoDto)	throws ProductoException, ResourceNotFoundException {
          try {
			
			Optional<Producto> productoO= productoRepository.findById(id);
			Optional<TerminoCorto> terminoCortoO= terminoCortoRepository.findById(idTerminoCorto);
			
			if(productoO.isPresent() && terminoCortoO.isPresent()) {
				Producto producto=productoO.get();				
				TerminoCorto terminoCorto=terminoCortoO.get();
				
				BeanUtils.copyProperties(terminosCortoDto, terminoCorto);
				terminoCorto.setId(idTerminoCorto);
				  TipoMulta tipoMulta=	  tipoMultaRepository.getOne(terminosCortoDto.getTipoMulta());
				  if(tipoMulta.getId()!=null) { 
					  terminoCorto.setTipoMulta(tipoMulta); 
				  }
				  if(terminoCorto.getMoneda().equalsIgnoreCase("-1")) {
					  terminoCorto.setMoneda(null);
				  }
				
				producto.updateTerminoCorto(terminoCorto);
						 

				productoRepository.save(producto);
				
			}
			else {
				ResourceNotFoundException e = new ResourceNotFoundException();
				e.setConcreteException(e);
				e.setErrorMessage(MSG_NOT_FOUND);
				e.setDetail(MSG_NOT_FOUND);
				throw e;
			}
		}
		catch(ResourceNotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}
		
		
	}

	@Transactional
	@Override
	public InfoProductoDto getInfoProducto(Long id) throws ProductoException, ResourceNotFoundException {
		InfoProductoDto infoProductoDto=null;
		
		try {
				
				Optional<Producto> productoO= productoRepository.findById(id);
				
				if(productoO.isPresent()) {
					infoProductoDto= productoRepository.getInfoProducto(id);					
				}
				else {
					ResourceNotFoundException e = new ResourceNotFoundException();
					e.setConcreteException(e);
					e.setErrorMessage(MSG_NOT_FOUND);
					e.setDetail(MSG_NOT_FOUND);
					throw e;
				}
			}
			catch(ProductoException e) {
				throw e;
			}
			catch(ResourceNotFoundException e) {
				throw e;
			}
			catch(Exception e) {
				throw new ProductoException(e);
			}
		return infoProductoDto;
			
	}

	@Transactional
	@Override
	public List<CoberturaProductoDto> getCoberturasDtoByProducto(Long id) throws ProductoException, ResourceNotFoundException {
		List<CoberturaProductoDto>  coberturasDto;

		try {
			Optional<Producto> productoOp = productoRepository.findById(id);
			if(productoOp.isPresent()) {
				coberturasDto =productoRepository.findCoberturasDtoByProducto(id);
			}
			else {
				ResourceNotFoundException e = new ResourceNotFoundException();
				e.setConcreteException(e);
				e.setErrorMessage(MSG_NOT_FOUND);
				e.setDetail(MSG_NOT_FOUND);
				throw e;
			}
		}
		catch(ResourceNotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}

		return coberturasDto;
	}


}
