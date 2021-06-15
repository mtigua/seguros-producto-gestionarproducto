package seguros.producto.gestionarproducto.servicesImpl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import seguros.producto.gestionarproducto.configuration.Properties;
import seguros.producto.gestionarproducto.dto.ActionType;
import seguros.producto.gestionarproducto.dto.CoberturaDTO;
import seguros.producto.gestionarproducto.dto.CoberturaProductoCorrelativoDto;
import seguros.producto.gestionarproducto.dto.CoberturaProductoDto;
import seguros.producto.gestionarproducto.dto.DeducibleDTO;
import seguros.producto.gestionarproducto.dto.EstadoProductoDto;
import seguros.producto.gestionarproducto.dto.InfoProductoDto;
import seguros.producto.gestionarproducto.dto.OrdenCoberturaDTO;
import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.dto.PrimaSobreQueDto;
import seguros.producto.gestionarproducto.dto.ProductoDoDto;
import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.dto.RecargoPorAseguradoDto;
import seguros.producto.gestionarproducto.dto.State;
import seguros.producto.gestionarproducto.dto.TarifaEsDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoSaveDto;
import seguros.producto.gestionarproducto.dto.TipoIvaDTO;
import seguros.producto.gestionarproducto.dto.TipoMultaDto;
import seguros.producto.gestionarproducto.dto.TipoTasaDto;
import seguros.producto.gestionarproducto.dto.TipoTramoDto;
import seguros.producto.gestionarproducto.dto.TramoDto;
import seguros.producto.gestionarproducto.dto.TramoListDto;
import seguros.producto.gestionarproducto.entities.Canal;
import seguros.producto.gestionarproducto.entities.CoberturaProducto;
import seguros.producto.gestionarproducto.entities.CoberturaProductoKey;
import seguros.producto.gestionarproducto.entities.DestinoVenta;
import seguros.producto.gestionarproducto.entities.EstadoIntegracion;
import seguros.producto.gestionarproducto.entities.ModoTraspaso;
import seguros.producto.gestionarproducto.entities.PrimaSobreQue;
import seguros.producto.gestionarproducto.entities.Producto;
import seguros.producto.gestionarproducto.entities.ProductoDo;
import seguros.producto.gestionarproducto.entities.RecargoPorAsegurado;
import seguros.producto.gestionarproducto.entities.TarifaEs;
import seguros.producto.gestionarproducto.entities.TarifaPor;
import seguros.producto.gestionarproducto.entities.TerminoCorto;
import seguros.producto.gestionarproducto.entities.TipoAjuste;
import seguros.producto.gestionarproducto.entities.TipoCobertura;
import seguros.producto.gestionarproducto.entities.TipoDescuento;
import seguros.producto.gestionarproducto.entities.TipoMulta;
import seguros.producto.gestionarproducto.entities.TipoPeriodo;
import seguros.producto.gestionarproducto.entities.TipoPromocion;
import seguros.producto.gestionarproducto.entities.TipoRecargo;
import seguros.producto.gestionarproducto.entities.TipoSeguro;
import seguros.producto.gestionarproducto.entities.TipoTarifa;
import seguros.producto.gestionarproducto.entities.TipoTasa;
import seguros.producto.gestionarproducto.entities.TipoTramo;
import seguros.producto.gestionarproducto.entities.TipoTraspaso;
import seguros.producto.gestionarproducto.entities.Tramo;
import seguros.producto.gestionarproducto.exceptions.ForbiddenException;
import seguros.producto.gestionarproducto.exceptions.ResourceNotFoundException;
import seguros.producto.gestionarproducto.repositories.CanalRepository;
import seguros.producto.gestionarproducto.repositories.CoberturaRepository;
import seguros.producto.gestionarproducto.repositories.DestinoVentaRepository;
import seguros.producto.gestionarproducto.repositories.ModoTraspasoRepository;
import seguros.producto.gestionarproducto.repositories.PrimaSobreQueRepository;
import seguros.producto.gestionarproducto.repositories.ProductoRepository;
import seguros.producto.gestionarproducto.repositories.RecargoPorAseguradoRepository;
import seguros.producto.gestionarproducto.repositories.TarifaEsRepository;
import seguros.producto.gestionarproducto.repositories.TarifaPorRepository;
import seguros.producto.gestionarproducto.repositories.TerminoCortoRepository;
import seguros.producto.gestionarproducto.repositories.TipoAjusteRepository;
import seguros.producto.gestionarproducto.repositories.TipoCoberturaRepository;
import seguros.producto.gestionarproducto.repositories.TipoDescuentoRepository;
import seguros.producto.gestionarproducto.repositories.TipoMultaRepository;
import seguros.producto.gestionarproducto.repositories.TipoPeriodoRepository;
import seguros.producto.gestionarproducto.repositories.TipoPromocionRepository;
import seguros.producto.gestionarproducto.repositories.TipoRecargoRepository;
import seguros.producto.gestionarproducto.repositories.TipoSeguroRepository;
import seguros.producto.gestionarproducto.repositories.TipoTarifaRepository;
import seguros.producto.gestionarproducto.repositories.TipoTasaRepository;
import seguros.producto.gestionarproducto.repositories.TipoTramoRepository;
import seguros.producto.gestionarproducto.repositories.TipoTraspasoRepository;
import seguros.producto.gestionarproducto.repositories.TramoRepository;
import seguros.producto.gestionarproducto.services.EstadoIntegracionService;
import seguros.producto.gestionarproducto.services.ProductoService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
public class ProductoServiceImpl implements ProductoService {


	private static final Long VALUE_UNDEFINED=-1L;
	private static final String MSG_NOT_FOUND = "El recurso solicitado no existe";
	private static final String MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT = "No est\u00E1 permitido un valor para el campo tramoPara. Solo es permitido para productos de tipo de ramo Hogar";
	private static final String MSG_FORBIDDEN_TRAMOS_BY_PRODUCT = "No est\u00E1 permitido la administraci\u00F3n de tramos para este producto";
	private static final String MSG_FORBIDDEN_TERMINOS_CORTOS_BY_PRODUCT = "No est\u00E1 permitido la administraci\u00F3n de t\u00E9rminos cortos para este producto";
	private static final String MSG_FORBIDDEN_RECARGO_POR_ASEGURADO_BY_PRODUCT = "No est\u00E1 permitido la administraci\u00F3n de recargo por asegurado para este producto";

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
	private TramoRepository tramoRepository;

	@Autowired
	private TipoTramoRepository tipoTramoRepository;

	@Autowired
	private TipoTasaRepository tipoTasaRepository;

	@Autowired
	private TarifaEsRepository tarifaEsRepository;

	@Autowired
	private PrimaSobreQueRepository tramoParaRepository;

	@Autowired
	private RecargoPorAseguradoRepository recargoPorAseguradoRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private TipoCoberturaRepository tipoCoberturaRepository ;

	@Autowired
	private CoberturaRepository coberturaRepository;

	@Autowired
	private PrimaSobreQueRepository primaSobreQueRepository;

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
				ResourceNotFoundException ePass = new ResourceNotFoundException();
				ePass.setConcreteException(ePass);
				ePass.setErrorMessage(MSG_NOT_FOUND);
				ePass.setDetail(MSG_NOT_FOUND);
				throw ePass;
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
	public List<TerminoCortoDto> getTerminosCortosByProduct(Long id) throws ProductoException,ResourceNotFoundException,ForbiddenException {
		List<TerminoCortoDto> lista= new ArrayList<>();

		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				Producto producto=productoO.get();

				Boolean aplicaTc= producto.getAplicaTc();
				if(Boolean.TRUE.equals(aplicaTc) ) {
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
							t.setMoneda("N/A");
						}
						return t;

					}).collect(Collectors.toList());
					Collections.sort(lista,(TerminoCortoDto f1,TerminoCortoDto f2) -> f1.getMesHasta().compareTo(f2.getMesHasta()));
				}

				else {
						ForbiddenException fe = new ForbiddenException();
						fe.setConcreteException(fe);
						fe.setErrorMessage(MSG_FORBIDDEN_TERMINOS_CORTOS_BY_PRODUCT);
						fe.setDetail(MSG_FORBIDDEN_TERMINOS_CORTOS_BY_PRODUCT);
						throw fe;
				}

			}
			else {
				ResourceNotFoundException et = new ResourceNotFoundException();
				et.setConcreteException(et);
				et.setErrorMessage(MSG_NOT_FOUND);
				et.setDetail(MSG_NOT_FOUND);
				throw et;
			}
		}
		catch(ForbiddenException e) {
			throw e;
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
			throws ProductoException, ResourceNotFoundException,ForbiddenException {

		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				Producto producto=productoO.get();
				Boolean aplicaTc= producto.getAplicaTc();

				if(Boolean.TRUE.equals(aplicaTc) ) {

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
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_TERMINOS_CORTOS_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_TERMINOS_CORTOS_BY_PRODUCT);
					throw fe;
			}

			}
			else {
				ResourceNotFoundException esave = new ResourceNotFoundException();
				esave.setConcreteException(esave);
				esave.setErrorMessage(MSG_NOT_FOUND);
				esave.setDetail(MSG_NOT_FOUND);
				throw esave;
			}
		}
		catch(ForbiddenException e) {
			throw e;
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
	public void deleteTerminosCortosByProduct(Long idProducto, Long idTerminoCorto)	throws ProductoException, ResourceNotFoundException,ForbiddenException {

		try {

			Optional<Producto> productoO= productoRepository.findById(idProducto);
			Optional<TerminoCorto> terminoCortoO= terminoCortoRepository.findById(idTerminoCorto);

			if(productoO.isPresent() && terminoCortoO.isPresent()) {
				Boolean aplicaTc= productoO.get().getAplicaTc();

				if(Boolean.TRUE.equals(aplicaTc) ) {
				    Producto producto=productoO.get();
					TerminoCorto terminoCorto=terminoCortoO.get();
					producto.removeTerminoCorto(terminoCorto);

					productoRepository.save(producto);
				}
				else {
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_TERMINOS_CORTOS_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_TERMINOS_CORTOS_BY_PRODUCT);
					throw fe;
			   }

			}
			else {
				ResourceNotFoundException edelete = new ResourceNotFoundException();
				edelete.setConcreteException(edelete);
				edelete.setErrorMessage(MSG_NOT_FOUND);
				edelete.setDetail(MSG_NOT_FOUND);
				throw edelete;
			}
		}
		catch(ForbiddenException e) {
			throw e;
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
	public void updateTerminosCortosByProduct(Long id, Long idTerminoCorto, TerminoCortoSaveDto terminosCortoDto)	throws ProductoException, ResourceNotFoundException,ForbiddenException {
          try {

			Optional<Producto> productoO= productoRepository.findById(id);
			Optional<TerminoCorto> terminoCortoO= terminoCortoRepository.findById(idTerminoCorto);

			if(productoO.isPresent() && terminoCortoO.isPresent()) {
				Producto producto=productoO.get();
				TerminoCorto terminoCorto=terminoCortoO.get();
                Boolean aplicaTc= productoO.get().getAplicaTc();

				if(Boolean.TRUE.equals(aplicaTc) ) {
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
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_TERMINOS_CORTOS_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_TERMINOS_CORTOS_BY_PRODUCT);
					throw fe;
			   }

			}
			else {
				ResourceNotFoundException eupdate = new ResourceNotFoundException();
				eupdate.setConcreteException(eupdate);
				eupdate.setErrorMessage(MSG_NOT_FOUND);
				eupdate.setDetail(MSG_NOT_FOUND);
				throw eupdate;
			}
		}
        catch(ForbiddenException e) {
  			throw e;
  		}
		catch(ResourceNotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}


	}


	@Override
	public InfoProductoDto getInfoProducto(Long id) throws ProductoException, ResourceNotFoundException {
		InfoProductoDto infoProductoDto=null;

		try {

				Optional<Producto> productoO= productoRepository.findById(id);

				if(productoO.isPresent()) {
					infoProductoDto= productoRepository.getInfoProducto(id);
				}
				else {
					ResourceNotFoundException einfo = new ResourceNotFoundException();
					einfo.setConcreteException(einfo);
					einfo.setErrorMessage(MSG_NOT_FOUND);
					einfo.setDetail(MSG_NOT_FOUND);
					throw einfo;
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


	@Transactional
	@Override
	public List<TramoListDto> getTramosByProduct(Long id) throws ProductoException, ResourceNotFoundException,ForbiddenException {
		List<TramoListDto> lista= new ArrayList<>();

		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				Producto producto=productoO.get();
				TipoTarifa tipotarifa=producto.getTipoTarifa();

				if(tipotarifa!=null && tipotarifa.getId()==1) {
					Set<Tramo> listaTramos= producto.getTramos();

					lista=listaTramos.stream().map(e->{

						TramoListDto t= new TramoListDto();
						BeanUtils.copyProperties(e, t);


						if(e.getTipoTasa()!=null) {
							TipoTasaDto tipoTasaDto = new TipoTasaDto();
							 BeanUtils.copyProperties(e.getTipoTasa(), tipoTasaDto);
							 t.setTipoTasa(tipoTasaDto);
						 }
						if(e.getTipoTramo()!=null) {
							TipoTramoDto tipoTramoDto = new TipoTramoDto();
							 BeanUtils.copyProperties(e.getTipoTramo(), tipoTramoDto);
							 t.setTipoTramo(tipoTramoDto);
						 }
						if(e.getTarifaEs()!=null) {
							TarifaEsDto tarifaEsDto = new TarifaEsDto();
							 BeanUtils.copyProperties(e.getTarifaEs(), tarifaEsDto);
							 t.setTarifaEs(tarifaEsDto);
						 }
						if(e.getTramoPara()!=null) {
							PrimaSobreQueDto primaSobreQueDto = new PrimaSobreQueDto();
							 BeanUtils.copyProperties(e.getTramoPara(), primaSobreQueDto);
							 t.setTramoPara(primaSobreQueDto);
						 }


						return t;

					}).collect(Collectors.toList());
					Collections.sort(lista,(TramoListDto f1,TramoListDto f2) -> f1.getValorHasta().compareTo(f2.getValorHasta()));
				}
				else {
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
					throw fe;
				}

			}
			else {
				ResourceNotFoundException egetramos = new ResourceNotFoundException();
				egetramos.setConcreteException(egetramos);
				egetramos.setErrorMessage(MSG_NOT_FOUND);
				egetramos.setDetail(MSG_NOT_FOUND);
				throw egetramos;
			}
		}

		catch(ForbiddenException e) {
			throw e;
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
	public void saveTramosByProduct(Long id, TramoDto tramoDto, Long tipoRamo) throws ProductoException, ResourceNotFoundException,ForbiddenException {

		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				Producto producto=productoO.get();
	           TipoTarifa tipotarifa=producto.getTipoTarifa();

				if(tipotarifa!=null && tipotarifa.getId()==1) {
					  Tramo tramoEntity= new Tramo();

					  BeanUtils.copyProperties(tramoDto, tramoEntity);

					  TipoTramo tipoTramo=	tipoTramoRepository.getOne(tramoDto.getTipoTramo());
					  if(tipoTramo.getId()!=null) {
						  tramoEntity.setTipoTramo(tipoTramo);
					  }
					  TarifaEs tarifaEs= tarifaEsRepository.getOne(tramoDto.getTarifaEs());
					  if(tarifaEs.getId()!=null) {
						  tramoEntity.setTarifaEs(tarifaEs);
					  }

					 TipoTasa tipoTasa=null;
					  if(tramoDto.getTipoTasa()!=null && tramoDto.getTipoTasa()>0L) {
						   tipoTasa=  tipoTasaRepository.getOne(tramoDto.getTipoTasa());
						  if(tipoTasa.getId()!=null) {
							  tramoEntity.setTipoTasa(tipoTasa);
						  }
					  }

					  PrimaSobreQue tramoPara=	null;
					  if(tramoDto.getTramoPara()!=null && tramoDto.getTramoPara()>=0L) {
						  InfoProductoDto infoProductoDto= this.getInfoProducto(id);
						  Long idTipoRamo=infoProductoDto.getTipoRamo().getId();
						  if(idTipoRamo==2L) {
							  tramoPara= tramoParaRepository.getOne(tramoDto.getTramoPara());
						  }
						  else {
							    ProductoException e = new ProductoException();
								e.setConcreteException(e);
								e.setErrorMessage(MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT);
								e.setDetail(MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT);
								throw e;
						  }

					  }


					  Set<Tramo> listaTramos= producto.getTramos();

					  for(Tramo tr:listaTramos){
							  tr.setTipoTramo(tipoTramo);
							  tr.setTipoTasa(tipoTasa);
							  tr.setMoneda(tramoEntity.getMoneda());
							  tr.setTarifaEs(tarifaEs);
							  tr.setTramoPara(tramoPara);
					  };

					  tramoEntity.setTramoPara(tramoPara);
					  producto.addTramo(tramoEntity);

					 productoRepository.save(producto);
				}
				else {
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
					throw fe;
				}
			}
			else {
				ResourceNotFoundException esavetramo = new ResourceNotFoundException();
				esavetramo.setConcreteException(esavetramo);
				esavetramo.setErrorMessage(MSG_NOT_FOUND);
				esavetramo.setDetail(MSG_NOT_FOUND);
				throw esavetramo;
			}
		}
		catch(ForbiddenException e) {
			throw e;
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

	}

	@Transactional
	@Override
	public void deleteTramoByProduct(Long idProducto, Long idTramo)
			throws ProductoException, ResourceNotFoundException,ForbiddenException {
      try {

			Optional<Producto> productoO= productoRepository.findById(idProducto);
			Optional<Tramo> tramoO= tramoRepository.findById(idTramo);

			if(productoO.isPresent() && tramoO.isPresent()) {
				Producto producto=productoO.get();
				TipoTarifa tipotarifa=producto.getTipoTarifa();

				if(tipotarifa!=null && tipotarifa.getId()==1) {
						Tramo tramo=tramoO.get();
						producto.removeTramo(tramo);

						productoRepository.save(producto);
				}
				else {
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
					throw fe;
				}

			}
			else {
				ResourceNotFoundException edeletetramo = new ResourceNotFoundException();
				edeletetramo.setConcreteException(edeletetramo);
				edeletetramo.setErrorMessage(MSG_NOT_FOUND);
				edeletetramo.setDetail(MSG_NOT_FOUND);
				throw edeletetramo;
			}
		}
		catch(ResourceNotFoundException e) {
			throw e;
		}
        catch(ForbiddenException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}

	}

	@Transactional
	@Override
	public void updateTramoByProduct(Long id, Long idTramo, TramoDto tramoDto,Long tipoRamo)
			throws ProductoException, ResourceNotFoundException,ForbiddenException {

		try {

			Optional<Producto> productoO= productoRepository.findById(id);
			Optional<Tramo> tramoO= tramoRepository.findById(idTramo);

			if(productoO.isPresent() && tramoO.isPresent()) {
				  Producto producto=productoO.get();

				  Tramo tramoEntity= tramoO.get();
				  TipoTarifa tipotarifa=producto.getTipoTarifa();

				  if(tipotarifa!=null && tipotarifa.getId()==1) {
						  BeanUtils.copyProperties(tramoDto, tramoEntity);
						  tramoEntity.setId(idTramo);

						  TipoTramo tipoTramo=	tipoTramoRepository.getOne(tramoDto.getTipoTramo());
						  if(tipoTramo.getId()!=null) {
							  tramoEntity.setTipoTramo(tipoTramo);
						  }
						  TarifaEs tarifaEs= tarifaEsRepository.getOne(tramoDto.getTarifaEs());
						  if(tarifaEs.getId()!=null) {
							  tramoEntity.setTarifaEs(tarifaEs);
						  }

						 TipoTasa tipoTasa=null;
						  if(tramoDto.getTipoTasa()!=null && tramoDto.getTipoTasa()>0L) {
							   tipoTasa=  tipoTasaRepository.getOne(tramoDto.getTipoTasa());
							  if(tipoTasa.getId()!=null) {
								  tramoEntity.setTipoTasa(tipoTasa);
							  }
						  }

						  PrimaSobreQue tramoPara=	null;
						  if(tramoDto.getTramoPara()!=null && tramoDto.getTramoPara()>=0L) {
							  InfoProductoDto infoProductoDto= this.getInfoProducto(id);
							  Long idTipoRamo=infoProductoDto.getTipoRamo().getId();
							  if(idTipoRamo==2L) {
								  tramoPara= tramoParaRepository.getOne(tramoDto.getTramoPara());
								  if(tramoPara.getId()!=null) {
									  tramoEntity.setTipoTramo(tipoTramo);
								  }
							  }
							  else {
								    ProductoException e = new ProductoException();
									e.setConcreteException(e);
									e.setErrorMessage(MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT);
									e.setDetail(MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT);
									throw e;
							  }

						  }

						  Set<Tramo> listaTramos= producto.getTramos();

						  for(Tramo tr:listaTramos){
								  tr.setTipoTramo(tipoTramo);
								  tr.setTipoTasa(tipoTasa);
								  tr.setMoneda(tramoEntity.getMoneda());
								  tr.setTarifaEs(tarifaEs);
								  tr.setTramoPara(tramoPara);
						  };

						  producto.updateTramo(tramoEntity);

						 productoRepository.save(producto);
				  }
				  else {
						ForbiddenException fe = new ForbiddenException();
						fe.setConcreteException(fe);
						fe.setErrorMessage(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
						fe.setDetail(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
						throw fe;
					}

			}
				else {
					ResourceNotFoundException eupdatetramo = new ResourceNotFoundException();
					eupdatetramo.setConcreteException(eupdatetramo);
					eupdatetramo.setErrorMessage(MSG_NOT_FOUND);
					eupdatetramo.setDetail(MSG_NOT_FOUND);
					throw eupdatetramo;
				}
			}
		    catch(ForbiddenException e) {
				throw e;
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
	public List<RecargoPorAseguradoDto> getRecargoPorAseguradoByProduct(Long id) throws ProductoException,ResourceNotFoundException,ForbiddenException {
		InfoProductoDto infoProducto = null;
		List<RecargoPorAseguradoDto> lista= new ArrayList<>();
		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				infoProducto= productoRepository.getInfoProducto(id);
				Long idTipoRamo = infoProducto.getTipoRamo().getId();
				Long idTipoCompania = infoProducto.getIdTipoCompania();
				if(idTipoRamo == 8 || idTipoCompania == 1 || idTipoCompania == 3){
					Producto producto=productoO.get();
					Set<RecargoPorAsegurado> listaRecargoPorAsegurado= producto.getRecargoPorAsegurado();

					lista=listaRecargoPorAsegurado.stream().map(e->{

						RecargoPorAseguradoDto t= new RecargoPorAseguradoDto();
						BeanUtils.copyProperties(e, t);
						return t;
					}).collect(Collectors.toList());
					Collections.sort(lista,(RecargoPorAseguradoDto f1,RecargoPorAseguradoDto f2) -> f1.getDesdeAsegurado().compareTo(f2.getHastaAsegurado()));
				}else{
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_RECARGO_POR_ASEGURADO_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_RECARGO_POR_ASEGURADO_BY_PRODUCT);
					throw fe;
				}
			}
			else {
				ResourceNotFoundException et = new ResourceNotFoundException();
				et.setConcreteException(et);
				et.setErrorMessage(MSG_NOT_FOUND);
				et.setDetail(MSG_NOT_FOUND);
				throw et;
			}
		}
		catch(ResourceNotFoundException e) {
			throw e;
		}
		catch(ForbiddenException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}
		return lista;
	}


	@Transactional
	@Override
	public void saveRecargoPorAseguradoByProduct(Long id, List<RecargoPorAseguradoDto> recargoPorAsegurado)
			throws ProductoException, ResourceNotFoundException,ForbiddenException {
		InfoProductoDto infoProducto = null;
		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				Producto producto=productoO.get();
				infoProducto= productoRepository.getInfoProducto(id);
				Long idTipoRamo = infoProducto.getTipoRamo().getId();
				Long idTipoCompania = infoProducto.getIdTipoCompania();
				if(idTipoRamo == 8 || idTipoCompania == 1 || idTipoCompania == 3){
					recargoPorAsegurado.stream().forEach(e->{
						RecargoPorAsegurado recargoPorAseguradoEntity= new RecargoPorAsegurado();
						BeanUtils.copyProperties(e, recargoPorAseguradoEntity);
						producto.addRecargoPorAsegurado(recargoPorAseguradoEntity);
					});
					productoRepository.save(producto);
				}
				else{
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_RECARGO_POR_ASEGURADO_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_RECARGO_POR_ASEGURADO_BY_PRODUCT);
					throw fe;
				}
			}
			else {
				ResourceNotFoundException esave = new ResourceNotFoundException();
				esave.setConcreteException(esave);
				esave.setErrorMessage(MSG_NOT_FOUND);
				esave.setDetail(MSG_NOT_FOUND);
				throw esave;
			}
		}
		catch(ResourceNotFoundException e) {
			throw e;
		}
		catch(ForbiddenException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}

	}


	@Transactional
	@Override
	public void deleteRecargoPorAseguradoByProduct(Long idProducto, Long idRecargoPorAsegurado)	throws ProductoException, ResourceNotFoundException, ForbiddenException{
		InfoProductoDto infoProducto = null;

		try {

			Optional<Producto> productoO= productoRepository.findById(idProducto);
			Optional<RecargoPorAsegurado> recargoPorAsegurado0= recargoPorAseguradoRepository.findById(idRecargoPorAsegurado);

			if(productoO.isPresent() && recargoPorAsegurado0.isPresent()) {
				infoProducto= productoRepository.getInfoProducto(idProducto);
				Long idTipoRamo = infoProducto.getTipoRamo().getId();
				Long idTipoCompania = infoProducto.getIdTipoCompania();
				if(idTipoRamo == 8 || idTipoCompania == 1 || idTipoCompania == 3){
					Producto producto=productoO.get();
					RecargoPorAsegurado recargoPorAsegurado=recargoPorAsegurado0.get();
					producto.removeRecargoPorAsegurado(recargoPorAsegurado);

					productoRepository.save(producto);
				}
				else{
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_RECARGO_POR_ASEGURADO_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_RECARGO_POR_ASEGURADO_BY_PRODUCT);
					throw fe;
				}
			}
			else {
				ResourceNotFoundException edelete = new ResourceNotFoundException();
				edelete.setConcreteException(edelete);
				edelete.setErrorMessage(MSG_NOT_FOUND);
				edelete.setDetail(MSG_NOT_FOUND);
				throw edelete;
			}
		}
		catch(ResourceNotFoundException e) {
			throw e;
		}
		catch(ForbiddenException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}

	}

	@Transactional
	@Override
	public void updateRecargoPorAseguradoByProduct(Long id, Long idRecargoPorAsegurado, RecargoPorAseguradoDto recargoPorAseguradoDto)	throws ProductoException, ResourceNotFoundException, ForbiddenException {
		InfoProductoDto infoProducto = null;
		try {

			Optional<Producto> productoO= productoRepository.findById(id);
			Optional<RecargoPorAsegurado> recargoPorAseguradoO= recargoPorAseguradoRepository.findById(idRecargoPorAsegurado);

			if(productoO.isPresent() && recargoPorAseguradoO.isPresent()) {
				infoProducto= productoRepository.getInfoProducto(id);
				Long idTipoRamo = infoProducto.getTipoRamo().getId();
				Long idTipoCompania = infoProducto.getIdTipoCompania();
				if(idTipoRamo == 8 || idTipoCompania == 1 || idTipoCompania == 3){
					Producto producto=productoO.get();
					RecargoPorAsegurado recargoPorAsegurado=recargoPorAseguradoO.get();

					BeanUtils.copyProperties(recargoPorAseguradoDto, recargoPorAsegurado);
					recargoPorAsegurado.setId(idRecargoPorAsegurado);

					producto.updateRecargoPorAsegurado(recargoPorAsegurado);


					productoRepository.save(producto);
				}
				else {
					ResourceNotFoundException edelete = new ResourceNotFoundException();
					edelete.setConcreteException(edelete);
					edelete.setErrorMessage(MSG_NOT_FOUND);
					edelete.setDetail(MSG_NOT_FOUND);
					throw edelete;
				}

			}
			else {
				ResourceNotFoundException eupdate = new ResourceNotFoundException();
				eupdate.setConcreteException(eupdate);
				eupdate.setErrorMessage(MSG_NOT_FOUND);
				eupdate.setDetail(MSG_NOT_FOUND);
				throw eupdate;
			}
		}
		catch(ResourceNotFoundException e) {
			throw e;
		}
		catch(ForbiddenException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}


	}

	@Transactional
	@Override
	public void saveCoberturaProducto(CoberturaDTO cobertura)
			throws ProductoException, ResourceNotFoundException {

		try {
			List<CoberturaProductoDto> coberturas = productoRepository.findCoberturasDtoByProducto(cobertura.getProducto());
			AtomicReference<Integer> maxOrder = new AtomicReference<>(0);

			coberturas.stream().forEach(e->{
				if (e.getOrden() > maxOrder.get()){
					maxOrder.set(e.getOrden());
				}
			});

			CoberturaProducto coberturaEntity = new CoberturaProducto();
			CoberturaProductoKey coberturaKey = new CoberturaProductoKey(cobertura.getProducto(), cobertura.getCobertura());
			coberturaEntity.setId(coberturaKey);
			Optional<TipoCobertura> tipo = tipoCoberturaRepository.findById(cobertura.getTipoCobertura());
			tipo.ifPresent(coberturaEntity::setTipoCobertura);
			Optional<Producto> producto = productoRepository.findById(cobertura.getProducto());
			producto.ifPresent(coberturaEntity::setProducto);
			coberturaEntity.setEdadMaxIngreso(cobertura.getEdadMaximaIngreso());
			coberturaEntity.setEdadMaxPermanencia(cobertura.getEdadMaximaPermanencia());
			coberturaEntity.setIdDeducible(cobertura.getDeducible());
			coberturaEntity.setIva(cobertura.getCobeConsinIva());
			coberturaEntity.setMontoAsegurado(cobertura.getMontoAsegurado());
			coberturaEntity.setOrden(maxOrder.get() + 1);
			coberturaEntity.setPorcCapital(cobertura.getPorcentajeSobreCapitalAsegurado());
			coberturaEntity.setPrimaMinima(cobertura.getPrimaMinima());
			coberturaEntity.setValorPrima(cobertura.getMontoPrima());
			coberturaEntity.setTasa(cobertura.getTasa());

			// Todo: Aclarar de donde obtenemos este "PrimaSobre (ni idea)"
			Optional<PrimaSobreQue> primaSobreQue =  primaSobreQueRepository.findById(1L);
			primaSobreQue.ifPresent(coberturaEntity::setPrimaSobreQue);
			if (cobertura.getEn() != null){
				Optional<TipoTasa> tipoTasa = tipoTasaRepository.findById(cobertura.getEn());
				tipoTasa.ifPresent(coberturaEntity::setTipoTasa);
			}
			coberturaRepository.save(coberturaEntity);

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
	public void updateOrderCobertura(OrdenCoberturaDTO ordenCobertura)
			throws ProductoException, ResourceNotFoundException {

		try {
			List<CoberturaProductoDto> coberturas = productoRepository.findCoberturasDtoByProducto(ordenCobertura.getIdProducto());

			if(!coberturas.isEmpty() && ordenCobertura.getToIndexRow() < coberturas.size() && ordenCobertura.getToIndexRow() >= 0) {

				coberturas.get(ordenCobertura.getFromIndexRow())
						.setOrden(coberturas.get(ordenCobertura.getToIndexRow()).getOrden());
				CoberturaProductoKey coberturaKeyFrom = new CoberturaProductoKey(ordenCobertura.getIdProducto(), coberturas.get(ordenCobertura.getFromIndexRow()).getIdCobertura());
				Optional<CoberturaProducto> coberturaProductoFrom = coberturaRepository.findById(coberturaKeyFrom);
				coberturaProductoFrom.ifPresent(coberturaProducto -> {
					coberturaProducto.setOrden(coberturas.get(ordenCobertura.getFromIndexRow()).getOrden());
					coberturaRepository.save(coberturaProducto);
				});

				// up
				if (ordenCobertura.getFromIndexRow() > ordenCobertura.getToIndexRow()){
					for (int i = ordenCobertura.getToIndexRow();  i < ordenCobertura.getFromIndexRow(); i++ ){
						Integer orden = coberturas.get(i).getOrden() + 1;
						CoberturaProductoKey coberturaKey = new CoberturaProductoKey(ordenCobertura.getIdProducto(), coberturas.get(i).getIdCobertura());
						Optional<CoberturaProducto> coberturaProducto = coberturaRepository.findById(coberturaKey);
						coberturaProducto.ifPresent(producto -> {
							producto.setOrden(orden);
							coberturaRepository.save(producto);
						});
					}
				} else  {
					// down
					for (int i = ordenCobertura.getToIndexRow();  i > ordenCobertura.getFromIndexRow(); i-- ){
						Integer orden = coberturas.get(i).getOrden() - 1;
						CoberturaProductoKey coberturaKey = new CoberturaProductoKey(ordenCobertura.getIdProducto(), coberturas.get(i).getIdCobertura());
						Optional<CoberturaProducto> coberturaProducto = coberturaRepository.findById(coberturaKey);
						coberturaProducto.ifPresent(producto -> {
							producto.setOrden(orden);
							coberturaRepository.save(producto);
						});
					}
				}
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
	public List<CoberturaProductoCorrelativoDto> getCoberturasDtoByProductoCorrelative(Long id) throws ProductoException, ResourceNotFoundException {
		List<CoberturaProductoCorrelativoDto>  coberturasDtoCorrelative;

		try {
			Optional<Producto> productoOp = productoRepository.findById(id);
			if(productoOp.isPresent()) {
				coberturasDtoCorrelative =productoRepository.findCoberturasDtoByProductoCorrelative(id);
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

		return coberturasDtoCorrelative;
	}


	@Transactional
	@Override
	public List<TipoIvaDTO> getTipoIvaByProducto(Long id) throws ProductoException, ResourceNotFoundException {
		List<TipoIvaDTO>  tiposIvas;

		try {
			Optional<Producto> productoOp = productoRepository.findById(id);
			if(productoOp.isPresent()) {
				tiposIvas =productoRepository.findTipoIva(id);
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

		return tiposIvas;
	}


	@Transactional
	@Override
	public List<DeducibleDTO> getDeducibles(Long id) throws ProductoException, ResourceNotFoundException {
		List<DeducibleDTO>  deducibles;

		try {
			Optional<Producto> productoOp = productoRepository.findById(id);
			if(productoOp.isPresent()) {
				deducibles = productoRepository.findDeducibles(id);
			} else {
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

		return deducibles;
	}


}
