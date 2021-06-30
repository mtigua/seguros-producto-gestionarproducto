package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
import seguros.producto.gestionarproducto.dto.DetallePromocionDto;
import seguros.producto.gestionarproducto.dto.DetallePromocionListDto;
import seguros.producto.gestionarproducto.dto.EstadoProductoDto;
import seguros.producto.gestionarproducto.dto.InfoProductoDto;
import seguros.producto.gestionarproducto.dto.OrdenCoberturaDTO;
import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.dto.PrimaSobreQueDto;
import seguros.producto.gestionarproducto.dto.ProductoDetallePromocionDto;
import seguros.producto.gestionarproducto.dto.ProductoDoDto;
import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.dto.RecargoPorAseguradoDto;
import seguros.producto.gestionarproducto.dto.PlanUpgradeDto;
import seguros.producto.gestionarproducto.dto.ProdDto;
import seguros.producto.gestionarproducto.dto.State;
import seguros.producto.gestionarproducto.dto.TarifaEsDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoSaveDto;
import seguros.producto.gestionarproducto.dto.TipoIvaDTO;
import seguros.producto.gestionarproducto.dto.TipoMultaDto;
import seguros.producto.gestionarproducto.dto.TipoPromocionDto;
import seguros.producto.gestionarproducto.dto.TipoTasaDto;
import seguros.producto.gestionarproducto.dto.TipoTramoDto;
import seguros.producto.gestionarproducto.dto.TramoDto;
import seguros.producto.gestionarproducto.dto.TramoListDto;
import seguros.producto.gestionarproducto.entities.Canal;
import seguros.producto.gestionarproducto.entities.CoberturaProducto;
import seguros.producto.gestionarproducto.entities.CoberturaProductoKey;
import seguros.producto.gestionarproducto.entities.DestinoVenta;
import seguros.producto.gestionarproducto.entities.DetallePromocion;
import seguros.producto.gestionarproducto.entities.EstadoIntegracion;
import seguros.producto.gestionarproducto.entities.ModoTraspaso;
import seguros.producto.gestionarproducto.entities.Parentesco;
import seguros.producto.gestionarproducto.entities.PrimaSobreQue;
import seguros.producto.gestionarproducto.entities.Producto;
import seguros.producto.gestionarproducto.entities.ProductoDo;
import seguros.producto.gestionarproducto.entities.RecargoPorAsegurado;
import seguros.producto.gestionarproducto.entities.PlanUpgrade;
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
import seguros.producto.gestionarproducto.entities.TramoCobertura;
import seguros.producto.gestionarproducto.exceptions.ForbiddenException;
import seguros.producto.gestionarproducto.exceptions.ResourceNotFoundException;
import seguros.producto.gestionarproducto.repositories.CanalRepository;
import seguros.producto.gestionarproducto.repositories.CoberturaRepository;
import seguros.producto.gestionarproducto.repositories.DestinoVentaRepository;
import seguros.producto.gestionarproducto.repositories.DetallePromocionRepository;
import seguros.producto.gestionarproducto.repositories.ModoTraspasoRepository;
import seguros.producto.gestionarproducto.repositories.ParentescoRepository;
import seguros.producto.gestionarproducto.repositories.PrimaSobreQueRepository;
import seguros.producto.gestionarproducto.repositories.ProductoRepository;
import seguros.producto.gestionarproducto.repositories.RecargoPorAseguradoRepository;
import seguros.producto.gestionarproducto.repositories.PlanUpgradeRepository;
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
import seguros.producto.gestionarproducto.repositories.TramoCoberturaRepository;
import seguros.producto.gestionarproducto.repositories.TramoRepository;
import seguros.producto.gestionarproducto.services.EstadoIntegracionService;
import seguros.producto.gestionarproducto.services.ProductoService;


@Service
public class ProductoServiceImpl implements ProductoService {


	private static final Long VALUE_UNDEFINED=-1L;
	private static final String MSG_NOT_FOUND = "El recurso solicitado no existe";
	private static final String MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT = "No est\u00E1 permitido un valor para el campo tramoPara. Solo es permitido para productos de tipo de ramo Hogar";
	private static final String MSG_FORBIDDEN_TRAMOS_BY_PRODUCT = "No est\u00E1 permitido la administraci\u00F3n de tramos para este producto";
	private static final String MSG_FORBIDDEN_TERMINOS_CORTOS_BY_PRODUCT = "No est\u00E1 permitido la administraci\u00F3n de t\u00E9rminos cortos para este producto";
	private static final String MSG_FORBIDDEN_RECARGO_POR_ASEGURADO_BY_PRODUCT = "No est\u00E1 permitido la administraci\u00F3n de recargo por asegurado para este producto";
	private static final String MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT = "No est\u00E1 permitido la administraci\u00F3n de upgrade para este producto";
	private static final String MSG_FORBIDDEN_COBERTURA_POR_ASEGURADO_BY_PRODUCT = "No est\u00E1 permitido la creación de una misma cobertura, intente con otro";
	private static final String MSG_FORBIDDEN_ERROR_REGISTER = "Error en el registro";
	private static final String MSG_FORBIDDEN_NEMOTECNICO_EN_USO = "El nemot\u00E9cnico ya esta en uso";

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
	private ParentescoRepository parentescoRepository;

	@Autowired
	private TipoTramoRepository tipoTramoRepository;

	@Autowired
	private TipoTasaRepository tipoTasaRepository;

	@Autowired
	private TarifaEsRepository tarifaEsRepository;

	@Autowired
	private PrimaSobreQueRepository tramoParaRepository;

	@Autowired
	private TramoCoberturaRepository tramoCoberturaRepository;

	@Autowired
	private RecargoPorAseguradoRepository recargoPorAseguradoRepository;

	@Autowired
	private PlanUpgradeRepository planUpgradeRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private TipoCoberturaRepository tipoCoberturaRepository ;

	@Autowired
	private CoberturaRepository coberturaRepository;

	@Autowired
	private PrimaSobreQueRepository primaSobreQueRepository;
	
	@Autowired
	private DetallePromocionRepository detallePromocionRepository;

	@Autowired
	private Properties properties;

	@Transactional
	@Override
	public List<ProductoDto> findAll() throws ProductoException {
		List<ProductoDto> listProducts;

		try {
			listProducts= productoRepository.findAll().stream().map(item ->{
				ProductoDto newProductoDto= new ProductoDto();
				BeanUtils.copyProperties(item, newProductoDto);
				newProductoDto.setTipoSeguro(item.getTipoSeguro().getId());
				newProductoDto.setTipoPromocion(item.getTipoPromocion().getId());
				newProductoDto.setTipoRecargo(item.getTipoRecargo().getId());
				newProductoDto.setTipoAjuste(item.getTipoAjuste().getId());
				newProductoDto.setTipoDescuento(item.getTipoDescuento().getId());
				newProductoDto.setTarifaPor(item.getTarifaPor().getId());
				newProductoDto.setTipoTarifa(item.getTipoTarifa().getId());
				newProductoDto.setTipoPeriodo(item.getTipoPeriodo().getId());
				newProductoDto.setTipoTraspaso(item.getTipoTraspaso().getId());
				newProductoDto.setTipoAcreedor(item.getTipoAcreedor().getId());
				newProductoDto.setTipoFacturar(item.getTipoFacturar().getId());
				ProductoDoDto productoDoDto= new ProductoDoDto();
				BeanUtils.copyProperties(item.getProductDo(), productoDoDto);
				productoDoDto.setDoplAQuienSeVende(item.getProductDo().getDoplAQuienSeVende().getId());
				newProductoDto.setProductDo(productoDoDto);
				return newProductoDto;

			}).collect(Collectors.toList());
		}
		catch(Exception e) {
			throw new ProductoException(e);
		}
		return listProducts;

	}

	@Transactional
	@Override
	public InfoProductoDto save(ProductoDto productoInfoEntity) throws ProductoException {
		InfoProductoDto result=new InfoProductoDto();

		try {
			Producto productoFirstEntity = productoInfoEntity.toEntity();
			boolean nemotecnicoEnUso= productoRepository.verificarSiExisteNemotecnico(productoFirstEntity.getNemot());

			if(!nemotecnicoEnUso) {
					if(productoInfoEntity.getTipoSeguro()!=null && !VALUE_UNDEFINED.equals(productoInfoEntity.getTipoSeguro()) ) {
						TipoSeguro tipoSeguro = tipoSeguroRepository.getOne(productoInfoEntity.getTipoSeguro());
						if(tipoSeguro.getId()!=null) {
						   productoFirstEntity.setTipoSeguro(tipoSeguro);
						}
					}
					if(productoInfoEntity.getTipoAcreedor()!=null && !VALUE_UNDEFINED.equals(productoInfoEntity.getTipoAcreedor())) {
						ModoTraspaso tipoAcreedor = modoTraspasoRepository.getOne(productoInfoEntity.getTipoAcreedor());
						if(tipoAcreedor.getId()!=null) {
							productoFirstEntity.setTipoAcreedor(tipoAcreedor);
						}
					}
					if(productoInfoEntity.getTipoFacturar()!=null && !VALUE_UNDEFINED.equals(productoInfoEntity.getTipoFacturar())) {
						ModoTraspaso tipoFacturar = modoTraspasoRepository.getOne(productoInfoEntity.getTipoFacturar());
						if(tipoFacturar.getId()!=null) {
							productoFirstEntity.setTipoFacturar(tipoFacturar);
						}
					}
					if(productoInfoEntity.getTipoPromocion()!=null && !VALUE_UNDEFINED.equals(productoInfoEntity.getTipoPromocion()) ) {
						TipoPromocion tipoPromocion = tipoPromocionRepository.getOne(productoInfoEntity.getTipoPromocion());
						if(tipoPromocion.getId()!=null) {
							productoFirstEntity.setTipoPromocion(tipoPromocion);
						}
					}
					if(productoInfoEntity.getTipoRecargo()!=null && !VALUE_UNDEFINED.equals(productoInfoEntity.getTipoRecargo()) ) {
						TipoRecargo tipoRecargo = tipoRecargoRepository.getOne(productoInfoEntity.getTipoRecargo());
						if(tipoRecargo.getId()!=null) {
						  productoFirstEntity.setTipoRecargo(tipoRecargo);
						}
					}
					if(productoInfoEntity.getTipoAjuste()!=null && !VALUE_UNDEFINED.equals(productoInfoEntity.getTipoAjuste()) ) {
						TipoAjuste tipoAjuste = tipoAjusteRepository.getOne(productoInfoEntity.getTipoAjuste());
						if(tipoAjuste.getId()!=null) {
					     	productoFirstEntity.setTipoAjuste(tipoAjuste);
						}
					}
					if(productoInfoEntity.getTipoDescuento()!=null && !VALUE_UNDEFINED.equals(productoInfoEntity.getTipoDescuento()) ) {
						TipoDescuento tipoDescuento = tipoDescuentoRepository.getOne(productoInfoEntity.getTipoDescuento());
						if(tipoDescuento.getId()!=null) {
							productoFirstEntity.setTipoDescuento(tipoDescuento);
						}
					}
					if(productoInfoEntity.getTarifaPor()!=null && !VALUE_UNDEFINED.equals(productoInfoEntity.getTarifaPor())) {
						TarifaPor tarifaPor = tarifaPorRepository.getOne(productoInfoEntity.getTarifaPor());
						if(tarifaPor.getId()!=null) {
							productoFirstEntity.setTarifaPor(tarifaPor);
						}
					}
					if(productoInfoEntity.getTipoTarifa()!=null && !VALUE_UNDEFINED.equals(productoInfoEntity.getTipoTarifa()) ) {
						TipoTarifa tipoTarifa = tipoTarifaRepository.getOne(productoInfoEntity.getTipoTarifa());
						if(tipoTarifa.getId()!=null) {
						  productoFirstEntity.setTipoTarifa(tipoTarifa);
						}
					}
					if(productoInfoEntity.getTipoPeriodo()!=null && !VALUE_UNDEFINED.equals(productoInfoEntity.getTipoPeriodo()) ) {
						TipoPeriodo tipoPeriodo = tipoPeriodoRepository.getOne(productoInfoEntity.getTipoPeriodo());
						if(tipoPeriodo.getId() != null) {
						  productoFirstEntity.setTipoPeriodo(tipoPeriodo);
						}
					}
					if(productoInfoEntity.getTipoTraspaso()!=null && !VALUE_UNDEFINED.equals(productoInfoEntity.getTipoTraspaso()) ) {
						TipoTraspaso tipoTraspaso = tipoTraspasoRepository.getOne(productoInfoEntity.getTipoTraspaso());
						if(tipoTraspaso.getId()!=null) {
						  productoFirstEntity.setTipoTraspaso(tipoTraspaso);
						}
					}
		
					if( productoInfoEntity.getProductDo()!=null ) {
						ProductoDo productoDo = productoInfoEntity.getProductDo().toEntity();
						if(productoInfoEntity.getProductDo().getDoplAQuienSeVende()!=null && !VALUE_UNDEFINED.equals(productoInfoEntity.getProductDo().getDoplAQuienSeVende()) ) {
							DestinoVenta destinoVenta = destinoVentaRepository.getOne(productoInfoEntity.getProductDo().getDoplAQuienSeVende());
		
							if(destinoVenta.getId()!=null) {
								productoDo.setDoplAQuienSeVende(destinoVenta);
							}
							productoFirstEntity.setProductDo(productoDo);
						}
					}
		
		
					String palabraPase= encrypt(productoFirstEntity.getPalabaraPaseProductManager());
					productoFirstEntity.setPalabaraPaseProductManager(palabraPase);
		
		
					productoRepository.save(productoFirstEntity);
		
					EstadoIntegracion estadoIntegracion = new EstadoIntegracion();
		
					Canal canal= canalRepository.getOne(4L);
		
					if(canal.getId()!=null) {
						estadoIntegracion.setCanal(canal);
					}
		
					Arrays.asList(productoInfoEntity.getCanales()).stream().forEach((p) ->{
						Canal canalEntity= canalRepository.getOne(p);
						if(canalEntity.getId()!=null) {
							productoFirstEntity.addCanal(canalEntity);
						}
					});
		
		
					estadoIntegracion.setIdProducto(productoFirstEntity.getId());
					estadoIntegracion.setState(State.Pendiente);
					estadoIntegracion.setTipoAccion(ActionType.Crear);
		
					estadoIntegracionService.save(estadoIntegracion);
		
					result.setNemotecnico(productoFirstEntity.getNemot());
					result.setId(productoFirstEntity.getId());
			}
			else {
				ProductoException ePass = new ProductoException();
				ePass.setConcreteException(ePass);
				ePass.setErrorMessage(MSG_FORBIDDEN_NEMOTECNICO_EN_USO);
				ePass.setDetail(MSG_FORBIDDEN_NEMOTECNICO_EN_USO);
				throw ePass;
			}
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
				ResourceNotFoundException resourceNotFoundExceptionCoberturaProducto = new ResourceNotFoundException();
				resourceNotFoundExceptionCoberturaProducto.setConcreteException(resourceNotFoundExceptionCoberturaProducto);
				resourceNotFoundExceptionCoberturaProducto.setErrorMessage(MSG_NOT_FOUND);
				resourceNotFoundExceptionCoberturaProducto.setDetail(MSG_NOT_FOUND);
				throw resourceNotFoundExceptionCoberturaProducto;
			}
		} catch(ResourceNotFoundException eNotFound) {
			throw eNotFound;
		}
		catch(Exception exceptionNotfound) {
			throw new ProductoException(exceptionNotfound);
		}

		return coberturasDto;
	}


	@Transactional
	@Override
	public List<TramoListDto> getTramosByProduct(Long id) throws ProductoException, ResourceNotFoundException,ForbiddenException {
		List<TramoListDto> listTramoByProduct2;

		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				Producto producto=productoO.get();
				TipoTarifa tipotarifa=producto.getTipoTarifa();

				if(tipotarifa!=null && tipotarifa.getId()==1) {
					Set<Tramo> listaTramos= producto.getTramos();

					listTramoByProduct2=listaTramos.stream().map(tramo->{

						TramoListDto tramoListDto= new TramoListDto();
						BeanUtils.copyProperties(tramo, tramoListDto);


						if(tramo.getTipoTasa()!=null) {
							TipoTasaDto tipoTasaDto = new TipoTasaDto();
							 BeanUtils.copyProperties(tramo.getTipoTasa(), tipoTasaDto);
							 tramoListDto.setTipoTasa(tipoTasaDto);
						 }
						if(tramo.getTipoTramo()!=null) {
							TipoTramoDto tipoTramoDto = new TipoTramoDto();
							 BeanUtils.copyProperties(tramo.getTipoTramo(), tipoTramoDto);
							 tramoListDto.setTipoTramo(tipoTramoDto);
						 }
						if(tramo.getTarifaEs()!=null) {
							TarifaEsDto tarifaEsDto = new TarifaEsDto();
							 BeanUtils.copyProperties(tramo.getTarifaEs(), tarifaEsDto);
							 tramoListDto.setTarifaEs(tarifaEsDto);
						 }
						if(tramo.getTramoPara()!=null) {
							PrimaSobreQueDto primaSobreQueDto = new PrimaSobreQueDto();
							 BeanUtils.copyProperties(tramo.getTramoPara(), primaSobreQueDto);
							 tramoListDto.setTramoPara(primaSobreQueDto);
						 }


						return tramoListDto;

					}).collect(Collectors.toList());
					Collections.sort(listTramoByProduct2,(TramoListDto f1,TramoListDto f2) -> f1.getValorHasta().compareTo(f2.getValorHasta()));
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
				ResourceNotFoundException resourceNotFoundExceptionRow1 = new ResourceNotFoundException();
				resourceNotFoundExceptionRow1.setConcreteException(resourceNotFoundExceptionRow1);
				resourceNotFoundExceptionRow1.setErrorMessage(MSG_NOT_FOUND);
				resourceNotFoundExceptionRow1.setDetail(MSG_NOT_FOUND);
				throw resourceNotFoundExceptionRow1;
			}
		}

		catch(ForbiddenException | ResourceNotFoundException e) {
			throw e;
		} catch(Exception e) {
			throw new ProductoException(e);
		}
		return listTramoByProduct2;
	}


	@Transactional
	@Override
	public List<TramoListDto> getTramosByCobertura(Long id,  Long idCobertura) throws ProductoException, ResourceNotFoundException,ForbiddenException {
		List<TramoListDto> listarTramo2;

		try {

			Optional<Producto> productoO= productoRepository.findById(id);
			CoberturaProductoKey coberturaProductoKey = new CoberturaProductoKey(id, idCobertura);
			Optional<CoberturaProducto> coberturaO= coberturaRepository.findById(coberturaProductoKey);

			if(productoO.isPresent() && coberturaO.isPresent()) {

				Producto producto=productoO.get();
				CoberturaProducto cobertura = coberturaO.get();

					Set<TramoCobertura> listaTramos= cobertura.getTramoCoberturas();

					listarTramo2=listaTramos.stream().map(e->{

						TramoListDto t3= new TramoListDto();
						BeanUtils.copyProperties(e, t3);

						if(e.getTipoTasa()!=null) {
							TipoTasaDto tipoTasaDto = new TipoTasaDto();
							BeanUtils.copyProperties(e.getTipoTasa(), tipoTasaDto);
							t3.setTipoTasa(tipoTasaDto);
						}
						if(e.getTipoTramo()!=null) {
							TipoTramoDto tipoTramoDto = new TipoTramoDto();
							BeanUtils.copyProperties(e.getTipoTramo(), tipoTramoDto);
							t3.setTipoTramo(tipoTramoDto);
						}
						if(e.getTarifaEs()!=null) {
							TarifaEsDto tarifaEsDto = new TarifaEsDto();
							BeanUtils.copyProperties(e.getTarifaEs(), tarifaEsDto);
							t3.setTarifaEs(tarifaEsDto);
						}
						if(e.getTramoPara()!=null) {
							PrimaSobreQueDto primaSobreQueDto = new PrimaSobreQueDto();
							BeanUtils.copyProperties(e.getTramoPara(), primaSobreQueDto);
							t3.setTramoPara(primaSobreQueDto);
						}

						return t3;

					}).collect(Collectors.toList());
					Collections.sort(listarTramo2,(TramoListDto f1,TramoListDto f2) -> f1.getValorHasta().compareTo(f2.getValorHasta()));

			} else {
				ResourceNotFoundException eTramoNotFound1 = new ResourceNotFoundException();
				eTramoNotFound1.setConcreteException(eTramoNotFound1);
				eTramoNotFound1.setErrorMessage(MSG_NOT_FOUND);
				eTramoNotFound1.setDetail(MSG_NOT_FOUND);
				throw eTramoNotFound1;
			}
		}

		catch(ForbiddenException | ResourceNotFoundException e) {
			throw e;
		} catch(Exception e) {
			throw new ProductoException(e);
		}
		return listarTramo2;
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
					  Tramo tramoEntity7= new Tramo();

					  BeanUtils.copyProperties(tramoDto, tramoEntity7);

					  TipoTramo tipoTramo7=	tipoTramoRepository.getOne(tramoDto.getTipoTramo());
					  if(tipoTramo7.getId()!=null) {
						  tramoEntity7.setTipoTramo(tipoTramo7);
					  }
					  TarifaEs tarifaEs7= tarifaEsRepository.getOne(tramoDto.getTarifaEs());
					  if(tarifaEs7.getId()!=null) {
						  tramoEntity7.setTarifaEs(tarifaEs7);
					  }

					 TipoTasa tipoTasa7=null;
					  if(tramoDto.getTipoTasa()!=null && tramoDto.getTipoTasa()>0L) {
						   tipoTasa7=  tipoTasaRepository.getOne(tramoDto.getTipoTasa());
						  if(tipoTasa7.getId()!=null) {
							  tramoEntity7.setTipoTasa(tipoTasa7);
						  }
					  }

					  PrimaSobreQue tramoPara7=	null;
					  if(tramoDto.getTramoPara()!=null && tramoDto.getTramoPara()>=0L) {
						  InfoProductoDto infoProductoDto= this.getInfoProducto(id);
						  Long idTipoRamo=infoProductoDto.getTipoRamo().getId();
						  if(idTipoRamo==2L) {
							  tramoPara7= tramoParaRepository.getOne(tramoDto.getTramoPara());
						  } else {
							    ProductoException e7 = new ProductoException();
								e7.setConcreteException(e7);
								e7.setErrorMessage(MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT);
								e7.setDetail(MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT);
								throw e7;
						  }

					  }


					  Set<Tramo> listaTramos= producto.getTramos();

					  for(Tramo tr4:listaTramos){
							  tr4.setTipoTramo(tipoTramo7);
							  tr4.setTipoTasa(tipoTasa7);
							  tr4.setMoneda(tramoEntity7.getMoneda());
							  tr4.setTarifaEs(tarifaEs7);
							  tr4.setTramoPara(tramoPara7);
					  }

					  tramoEntity7.setTramoPara(tramoPara7);
					  producto.addTramo(tramoEntity7);

					 productoRepository.save(producto);
				}
				else {
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
					throw fe;
				}
			} else {
				ResourceNotFoundException exceptionTramo6 = new ResourceNotFoundException();
				exceptionTramo6.setConcreteException(exceptionTramo6);
				exceptionTramo6.setErrorMessage(MSG_NOT_FOUND);
				exceptionTramo6.setDetail(MSG_NOT_FOUND);
				throw exceptionTramo6;
			}
		}
		catch(ForbiddenException | ResourceNotFoundException e) {
			throw e;
		}
		catch(ProductoException e) {
			throw e;
		} catch(Exception e) {
			throw new ProductoException(e);
		}

	}

	@Transactional
	@Override
	public void saveTramosByProductCobertura(Long productId, Long coberturaId, TramoDto tramoDto, Long tipoRamo) throws ProductoException, ResourceNotFoundException,ForbiddenException {

		try {
			Optional<Producto> producto2 = productoRepository.findById(productId);
			CoberturaProductoKey coberturaProductoKey = new CoberturaProductoKey(productId, coberturaId);
			Optional<CoberturaProducto> coberturaProducto = coberturaRepository.findById(coberturaProductoKey);
			if(producto2.isPresent() && coberturaProducto.isPresent()) {
				Producto producto=producto2.get();
				CoberturaProducto coberturaProducto1 = coberturaProducto.get();

					TramoCobertura tramoCoberturaEntity= new TramoCobertura();

					BeanUtils.copyProperties(tramoDto, tramoCoberturaEntity);

					// tramoCoberturaEntity.setCobertura(coberturaProducto1);
					tramoCoberturaEntity.setProducto(producto);
					tramoCoberturaEntity.setIdCobertura(coberturaId);

					TipoTramo tipoTramo3=	tipoTramoRepository.getOne(tramoDto.getTipoTramo());
					if(tipoTramo3.getId()!=null) {
						tramoCoberturaEntity.setTipoTramo(tipoTramo3);
					}
					TarifaEs tarifaEs2= tarifaEsRepository.getOne(tramoDto.getTarifaEs());
					if(tarifaEs2.getId()!=null) {
						tramoCoberturaEntity.setTarifaEs(tarifaEs2);
					}

					TipoTasa tipoTasa3=null;
					if(tramoDto.getTipoTasa()!=null && tramoDto.getTipoTasa()>0L) {
						tipoTasa3=  tipoTasaRepository.getOne(tramoDto.getTipoTasa());
						if(tipoTasa3.getId()!=null) {
							tramoCoberturaEntity.setTipoTasa(tipoTasa3);
						}
					}

					PrimaSobreQue tramoPara2=	null;
					if(tramoDto.getTramoPara()!=null && tramoDto.getTramoPara()>=0L) {
						InfoProductoDto infoProductoDto= this.getInfoProducto(productId);
						Long idTipoRamo=infoProductoDto.getTipoRamo().getId();
						if(idTipoRamo==2L) {
							tramoPara2= tramoParaRepository.getOne(tramoDto.getTramoPara());
						} else {
							ProductoException e4 = new ProductoException();
							e4.setConcreteException(e4);
							e4.setErrorMessage(MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT);
							e4.setDetail(MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT);
							throw e4;
						}

					}

					Set<TramoCobertura> listaTramos = coberturaProducto1.getTramoCoberturas();

					for(TramoCobertura tr : listaTramos){
						tr.setTipoTramo(tipoTramo3);
						tr.setTipoTasa(tipoTasa3);
						tr.setMoneda(tramoCoberturaEntity.getMoneda());
						tr.setTarifaEs(tarifaEs2);
						tr.setTramoPara(tramoPara2);
					}

					tramoCoberturaEntity.setTramoPara(tramoPara2);

					coberturaProducto1.addTramoCobertura(tramoCoberturaEntity);

					coberturaRepository.save(coberturaProducto1);
			} else {
				ResourceNotFoundException eTramoCobertura4 = new ResourceNotFoundException();
				eTramoCobertura4.setConcreteException(eTramoCobertura4);
				eTramoCobertura4.setErrorMessage(MSG_NOT_FOUND);
				eTramoCobertura4.setDetail(MSG_NOT_FOUND);
				throw eTramoCobertura4;
			}
		}
		catch(ForbiddenException | ProductoException e) {
			throw e;
		} catch(ResourceNotFoundException eNotFoundException5) {
			throw eNotFoundException5;
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
				Producto producto01=productoO.get();
				TipoTarifa tipotarifa=producto01.getTipoTarifa();

				if(tipotarifa!=null && tipotarifa.getId()==1) {
						Tramo tramo=tramoO.get();
						producto01.removeTramo(tramo);

						productoRepository.save(producto01);
				}
				else {
					ForbiddenException forbiddenException01 = new ForbiddenException();
					forbiddenException01.setConcreteException(forbiddenException01);
					forbiddenException01.setErrorMessage(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
					forbiddenException01.setDetail(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
					throw forbiddenException01;
				}

			}
			else {
				ResourceNotFoundException exceptionOnDeletedTramo = new ResourceNotFoundException();
				exceptionOnDeletedTramo.setConcreteException(exceptionOnDeletedTramo);
				exceptionOnDeletedTramo.setErrorMessage(MSG_NOT_FOUND);
				exceptionOnDeletedTramo.setDetail(MSG_NOT_FOUND);
				throw exceptionOnDeletedTramo;
			}
		}
		catch(ResourceNotFoundException | ForbiddenException e23) {
			throw e23;
		} catch(Exception e24) {
			throw new ProductoException(e24);
		}

	}


	@Transactional
	@Override
	public void deleteTramoByCobertura(Long idProducto, Long idCobertura, Long idTramo)
			throws ProductoException, ResourceNotFoundException,ForbiddenException {

      try {

			Optional<Producto> productoO= productoRepository.findById(idProducto);
			Optional<TramoCobertura> tramoO= tramoCoberturaRepository.findById(idTramo);
		  	CoberturaProductoKey coberturaProductoKey = new CoberturaProductoKey(idProducto, idCobertura);
		  	Optional<CoberturaProducto> coberturaProducto1 = coberturaRepository.findById(coberturaProductoKey);

			if(productoO.isPresent() && tramoO.isPresent() && coberturaProducto1.isPresent()) {
				Producto producto=productoO.get();
				CoberturaProducto coberturaProducto = coberturaProducto1.get();
				TipoTarifa tipotarifa=producto.getTipoTarifa();

				if(tipotarifa!=null && tipotarifa.getId()==1) {
						TramoCobertura tramo = tramoO.get();
						coberturaProducto.removeTramoCobertura(tramo);
						productoRepository.save(producto);
				} else {
					ForbiddenException fe24 = new ForbiddenException();
					fe24.setConcreteException(fe24);
					fe24.setErrorMessage(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
					fe24.setDetail(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
					throw fe24;
				}

			} else {
				ResourceNotFoundException edeletetramo24 = new ResourceNotFoundException();
				edeletetramo24.setConcreteException(edeletetramo24);
				edeletetramo24.setErrorMessage(MSG_NOT_FOUND);
				edeletetramo24.setDetail(MSG_NOT_FOUND);
				throw edeletetramo24;
			}
		}
		catch(ResourceNotFoundException | ForbiddenException e25) {
			throw e25;
		} catch(Exception e27) {
			throw new ProductoException(e27);
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
				  Producto producto30=productoO.get();

				  Tramo tramoEntity= tramoO.get();
				  TipoTarifa tipotarifa=producto30.getTipoTarifa();

				  if(tipotarifa!=null && tipotarifa.getId()==1) {
						  BeanUtils.copyProperties(tramoDto, tramoEntity);
						  tramoEntity.setId(idTramo);

						  TipoTramo tipoTramo4=	tipoTramoRepository.getOne(tramoDto.getTipoTramo());
						  if(tipoTramo4.getId()!=null) {
							  tramoEntity.setTipoTramo(tipoTramo4);
						  }
						  TarifaEs tarifaEs4= tarifaEsRepository.getOne(tramoDto.getTarifaEs());
						  if(tarifaEs4.getId()!=null) {
							  tramoEntity.setTarifaEs(tarifaEs4);
						  }

						 TipoTasa tipoTasa4=null;
						  if(tramoDto.getTipoTasa()!=null && tramoDto.getTipoTasa()>0L) {
							   tipoTasa4=  tipoTasaRepository.getOne(tramoDto.getTipoTasa());
							  if(tipoTasa4.getId()!=null) {
								  tramoEntity.setTipoTasa(tipoTasa4);
							  }
						  }

						  PrimaSobreQue tramoPara4=	null;
						  if(tramoDto.getTramoPara()!=null && tramoDto.getTramoPara()>=0L) {
							  InfoProductoDto infoProductoDto= this.getInfoProducto(id);
							  Long idTipoRamo=infoProductoDto.getTipoRamo().getId();
							  if(idTipoRamo==2L) {
								  tramoPara4= tramoParaRepository.getOne(tramoDto.getTramoPara());
								  if(tramoPara4.getId()!=null) {
									  tramoEntity.setTipoTramo(tipoTramo4);
								  }
							  }
							  else {
								    ProductoException e31 = new ProductoException();
									e31.setConcreteException(e31);
									e31.setErrorMessage(MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT);
									e31.setDetail(MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT);
									throw e31;
							  }

						  }

						  Set<Tramo> listaTramos= producto30.getTramos();

						  for(Tramo tr30:listaTramos){
								  tr30.setTipoTramo(tipoTramo4);
								  tr30.setTipoTasa(tipoTasa4);
								  tr30.setMoneda(tramoEntity.getMoneda());
								  tr30.setTarifaEs(tarifaEs4);
								  tr30.setTramoPara(tramoPara4);
						  }

						  producto30.updateTramo(tramoEntity);

						 productoRepository.save(producto30);
				  }
				  else {
						ForbiddenException fe30 = new ForbiddenException();
						fe30.setConcreteException(fe30);
						fe30.setErrorMessage(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
						fe30.setDetail(MSG_FORBIDDEN_TRAMOS_BY_PRODUCT);
						throw fe30;
					}

			}
				else {
					ResourceNotFoundException eupdatetramo30 = new ResourceNotFoundException();
					eupdatetramo30.setConcreteException(eupdatetramo30);
					eupdatetramo30.setErrorMessage(MSG_NOT_FOUND);
					eupdatetramo30.setDetail(MSG_NOT_FOUND);
					throw eupdatetramo30;
				}
			}
		    catch(ForbiddenException | ResourceNotFoundException e31) {
				throw e31;
			} catch(Exception e) {
				throw new ProductoException(e);
			}


	}

	@Transactional
	@Override
	public void updateTramoByCobertura(Long id, Long idCobertura, Long idTramo, TramoDto tramoDto,Long tipoRamo)
			throws ProductoException, ResourceNotFoundException,ForbiddenException {

		try {

			Optional<Producto> productoO= productoRepository.findById(id);
			CoberturaProductoKey coberturaProductoKey = new CoberturaProductoKey(id, idCobertura);
			Optional<CoberturaProducto> coberturaO= coberturaRepository.findById(coberturaProductoKey);
			Optional<TramoCobertura> tramoO = tramoCoberturaRepository.findById(idTramo);

			if(productoO.isPresent() && tramoO.isPresent() && coberturaO.isPresent()) {
				Producto producto=productoO.get();
				CoberturaProducto coberturaProducto = coberturaO.get();

				TramoCobertura tramoEntity= tramoO.get();
				TipoTarifa tipotarifa33=producto.getTipoTarifa();


					BeanUtils.copyProperties(tramoDto, tramoEntity);
					tramoEntity.setId(idTramo);

					TipoTramo tipoTramo33=	tipoTramoRepository.getOne(tramoDto.getTipoTramo());
					if(tipoTramo33.getId()!=null) {
						tramoEntity.setTipoTramo(tipoTramo33);
					}
					TarifaEs tarifaEs33= tarifaEsRepository.getOne(tramoDto.getTarifaEs());
					if(tarifaEs33.getId()!=null) {
						tramoEntity.setTarifaEs(tarifaEs33);
					}

					TipoTasa tipoTasa33=null;
					if(tramoDto.getTipoTasa()!=null && tramoDto.getTipoTasa()>0L) {
						tipoTasa33=  tipoTasaRepository.getOne(tramoDto.getTipoTasa());
						if(tipoTasa33.getId()!=null) {
							tramoEntity.setTipoTasa(tipoTasa33);
						}
					}

					PrimaSobreQue tramoPara33 = null;
					if(tramoDto.getTramoPara()!=null && tramoDto.getTramoPara()>=0L) {
						InfoProductoDto infoProductoDto= this.getInfoProducto(id);
						Long idTipoRamo=infoProductoDto.getTipoRamo().getId();
						if(idTipoRamo==2L) {
							tramoPara33= tramoParaRepository.getOne(tramoDto.getTramoPara());
							if(tramoPara33.getId()!=null) {
								tramoEntity.setTipoTramo(tipoTramo33);
							}
						} else {
							ProductoException e33 = new ProductoException();
							e33.setConcreteException(e33);
							e33.setErrorMessage(MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT);
							e33.setDetail(MSG_NOT_ALLOWED_TRAMO_PARA_SUBJECT);
							throw e33;
						}

					}

					Set<TramoCobertura> listaTramos33= coberturaProducto.getTramoCoberturas();

					for(TramoCobertura tr:listaTramos33){
						tr.setTipoTramo(tipoTramo33);
						tr.setTipoTasa(tipoTasa33);
						tr.setMoneda(tramoEntity.getMoneda());
						tr.setTarifaEs(tarifaEs33);
						tr.setTramoPara(tramoPara33);
					}

					coberturaProducto.updateCobertura(tramoEntity);

					coberturaRepository.save(coberturaProducto);
			}
			else {
				ResourceNotFoundException eupdatetramo33 = new ResourceNotFoundException();
				eupdatetramo33.setConcreteException(eupdatetramo33);
				eupdatetramo33.setErrorMessage(MSG_NOT_FOUND);
				eupdatetramo33.setDetail(MSG_NOT_FOUND);
				throw eupdatetramo33;
			}
		}
		catch(ForbiddenException | ResourceNotFoundException e33) {
			throw e33;
		} catch(Exception e33) {
			throw new ProductoException(e33);
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
		catch(ResourceNotFoundException | ForbiddenException e) {
			throw e;
		} catch(Exception e) {
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
				else{
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_RECARGO_POR_ASEGURADO_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_RECARGO_POR_ASEGURADO_BY_PRODUCT);
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
		catch(ResourceNotFoundException | ForbiddenException e) {
			throw e;
		} catch(Exception e) {
			throw new ProductoException(e);
		}


	}

	@Transactional
	@Override
	public List<PlanUpgradeDto> getPlanUpgradeByProduct(Long id) throws ProductoException,ResourceNotFoundException,ForbiddenException {
		List<PlanUpgradeDto> lista= new ArrayList<>();
		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				Producto producto=productoO.get();
				if(producto.getUpgrade()){
					lista = planUpgradeRepository.getPlanUpgrade(id);
				}else{
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
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
	public List<ProdDto> getProductByNemo(Long id,String nemo) throws ProductoException,ResourceNotFoundException,ForbiddenException {
		List<ProdDto> lista= new ArrayList<>();
		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				Producto producto=productoO.get();
				if(producto.getUpgrade()){
					lista = planUpgradeRepository.getProductoPorNemotecnico(nemo);
				}else{
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
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
	public List<ProdDto> getPlanesExistentesByNemo(Long id,String nemoU, String nemoP) throws ProductoException,ResourceNotFoundException,ForbiddenException {
		List<ProdDto> lista= new ArrayList<>();
		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				Producto producto=productoO.get();
				if(producto.getUpgrade()){
					lista = planUpgradeRepository.getPlanesExistentesOAceptadosPorNemotecnico(id,nemoU,nemoP,false);
				}else{
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
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
	public List<ProdDto> getPlanesAceptadosByNemo(Long id,String nemoU, String nemoP) throws ProductoException,ResourceNotFoundException,ForbiddenException {
		List<ProdDto> lista= new ArrayList<>();
		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				Producto producto=productoO.get();
				if(producto.getUpgrade()){
					lista = planUpgradeRepository.getPlanesExistentesOAceptadosPorNemotecnico(id,nemoU,nemoP,true);
				}else{
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
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
	public void saveUpgradeByProduct(Long id, List<PlanUpgradeDto> upgrades)
			throws ProductoException, ResourceNotFoundException,ForbiddenException {
		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			if(productoO.isPresent()) {
				Producto producto=productoO.get();
				if(producto.getUpgrade()){
					PlanUpgradeDto planUpgradeDto = upgrades.get(0);
					planUpgradeRepository.deletePlanUpgrade(id,Long.valueOf(planUpgradeDto.getPlanUpgrade()));
					upgrades.stream().forEach(e->{
						PlanUpgrade upgradeEntity= new PlanUpgrade();
						upgradeEntity.setPlanUpgrade(Long.valueOf(e.getPlanUpgrade()));
						upgradeEntity.setPlanVigente(Long.valueOf(e.getPlanVigente()));
						upgradeEntity.setPlanPrevio(Long.valueOf(e.getPlanPrevio()));
						upgradeEntity.setDiasRenuncia(e.getDiasRenuncia());
						producto.addPlanUpgrade(upgradeEntity);
					});
					productoRepository.save(producto);
				}
				else{
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
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
	public void deleteUpgradeByProduct(Long idProducto, Long idUpgrade)	throws ProductoException, ResourceNotFoundException, ForbiddenException{
		try {
			Optional<Producto> productoO= productoRepository.findById(idProducto);
			Optional<PlanUpgrade> planUpgrade0= planUpgradeRepository.findById(idUpgrade);
			if(productoO.isPresent() && planUpgrade0.isPresent()) {
				Producto producto=productoO.get();
				if(producto.getUpgrade()){
					PlanUpgrade planUpgrade=planUpgrade0.get();
					producto.removePlanUpgrade(planUpgrade);
					productoRepository.save(producto);
				}
				else{
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
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
		catch(ResourceNotFoundException | ForbiddenException e) {
			throw e;
		} catch(Exception e) {
			throw new ProductoException(e);
		}

	}

	@Transactional
	@Override
	public void updateUpgradeByProduct(Long id, Long idUpgrade, PlanUpgradeDto planUpgradeDto)	throws ProductoException, ResourceNotFoundException, ForbiddenException {
		try {
			Optional<Producto> productoO= productoRepository.findById(id);
			Optional<PlanUpgrade> planUpgrade0= planUpgradeRepository.findById(idUpgrade);

			if(productoO.isPresent() && planUpgrade0.isPresent()) {
				Producto producto=productoO.get();
				if(producto.getUpgrade()){
					PlanUpgrade planUpgrade=planUpgrade0.get();
					planUpgrade.setDiasRenuncia(planUpgradeDto.getDiasRenuncia());
					planUpgrade.setId(idUpgrade);
					producto.updatePlanUpgrade(planUpgrade);
					productoRepository.save(producto);
				}
				else{
					ForbiddenException fe = new ForbiddenException();
					fe.setConcreteException(fe);
					fe.setErrorMessage(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
					fe.setDetail(MSG_FORBIDDEN_PLAN_UPGRADE_BY_PRODUCT);
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
		catch(ResourceNotFoundException | ForbiddenException e) {
			throw e;
		} catch(Exception e) {
			throw new ProductoException(e);
		}


	}


	@Transactional
	@Override
	public void saveCoberturaProducto(CoberturaDTO cobertura)
			throws ProductoException, ResourceNotFoundException, ForbiddenException {

		try {
			CoberturaProductoKey coberturaKey = new CoberturaProductoKey(cobertura.getProducto(), (cobertura.getCobertura()));
			Optional<CoberturaProducto> coberturaProducto36 = coberturaRepository.findById(coberturaKey);

			if(!coberturaProducto36.isPresent()){
				List<CoberturaProductoDto> coberturas = productoRepository.findCoberturasDtoByProducto(cobertura.getProducto());
				AtomicReference<Integer> maxOrder = new AtomicReference<>(0);
				AtomicReference<Long> maxCobertura = new AtomicReference<>(0L);

				coberturas.stream().forEach(e->{
					maxCobertura.set(e.getMaxCobertura());
					if (e.getOrden() > maxOrder.get()){
						maxOrder.set(e.getOrden());
					}
				});

				CoberturaProducto coberturaEntity = new CoberturaProducto();
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
				coberturaEntity.setValorPrima(cobertura.getTarifa());
				coberturaEntity.setTasa(cobertura.getTasa());
				if (cobertura.getParaParentesco() != null){
					Optional<Parentesco> parentesco = parentescoRepository.findById(cobertura.getParaParentesco());
					parentesco.ifPresent(coberturaEntity::setParentesco);
				}

				// Todo: Aclarar de donde obtenemos este "PrimaSobre (ni idea)"
				Optional<PrimaSobreQue> primaSobreQue =  primaSobreQueRepository.findById(1L);
				primaSobreQue.ifPresent(coberturaEntity::setPrimaSobreQue);
				if (cobertura.getEn() != null){
					Optional<TipoTasa> tipoTasa = tipoTasaRepository.findById(cobertura.getEn());
					tipoTasa.ifPresent(coberturaEntity::setTipoTasa);
				}
				coberturaRepository.save(coberturaEntity);
			} else {
				ForbiddenException fe36 = new ForbiddenException();
				fe36.setConcreteException(fe36);
				fe36.setErrorMessage(MSG_FORBIDDEN_COBERTURA_POR_ASEGURADO_BY_PRODUCT);
				fe36.setDetail(MSG_FORBIDDEN_COBERTURA_POR_ASEGURADO_BY_PRODUCT);
				fe36.setSubject(MSG_FORBIDDEN_ERROR_REGISTER);
				throw fe36;
			}

		}
		catch(ResourceNotFoundException | ForbiddenException e35) {
			throw e35;
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
	public List<DeducibleDTO> getDeducibles(Long id, Long idCobertura) throws ProductoException, ResourceNotFoundException {
		List<DeducibleDTO>  deducibles;

		try {
			Optional<Producto> productoOp = productoRepository.findById(id);
			if(productoOp.isPresent()) {
				deducibles = productoRepository.findDeducibles(id, idCobertura);
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


	@Transactional
	@Override
	public ProductoDetallePromocionDto getDetallePromocionByProduct(Long id) throws ProductoException,ResourceNotFoundException,ForbiddenException {
		ProductoDetallePromocionDto productoDetallePromocionDto = null;
		List<DetallePromocionListDto> detallePromocionesDto;
		
		try {
			Optional<Producto> productoOp= productoRepository.findById(id);
			if(productoOp.isPresent()) {
				Producto producto=productoOp.get();
				productoDetallePromocionDto = new ProductoDetallePromocionDto();
				productoDetallePromocionDto.setId(producto.getId());

				if (producto.getTipoPromocion() != null) { 
					TipoPromocionDto tipoPromocionDto = new TipoPromocionDto();
					BeanUtils.copyProperties(producto.getTipoPromocion(),tipoPromocionDto);
				}
				
				Set<DetallePromocion>  detallePromociones  = producto.getDetallePromociones();
				
				detallePromocionesDto=detallePromociones.stream().map(detalle->{
					DetallePromocionListDto detallePromocionDto = new DetallePromocionListDto();
					BeanUtils.copyProperties(detalle, detallePromocionDto);
					
					if (detalle.getTipoPromocion()!=null) {
						TipoPromocionDto tipoPromocionDto = new TipoPromocionDto();
						BeanUtils.copyProperties(detalle.getTipoPromocion(),tipoPromocionDto);
						detallePromocionDto.setTipoPromocion(tipoPromocionDto);
					}
					
					return detallePromocionDto;
					
				}).collect(Collectors.toList());
				
				productoDetallePromocionDto.setDetallePromociones(detallePromocionesDto);
				
				
			}
			else {
				lanzarExcepcionRecursoNoEncontrado();
			}
			
		}
		catch(ForbiddenException | ResourceNotFoundException eg) {
			throw eg;
		} 
		catch(Exception eg) {
			throw new ProductoException(eg);
		}
		
		return productoDetallePromocionDto;
	 }

	@Transactional
	@Override
	public void saveDetallePromocionByProduct(Long id, DetallePromocionDto detallePromocionDto)
			throws ProductoException, ResourceNotFoundException, ForbiddenException {
		try {
			Optional<Producto> productoOp= productoRepository.findById(id);
			if(productoOp.isPresent()) {
				Producto producto=productoOp.get();
				DetallePromocion detallePromocion = new DetallePromocion();
				BeanUtils.copyProperties(detallePromocionDto, detallePromocion);
				
				TipoPromocion tipoPromocion = null;
				if (detallePromocionDto.getIdTipoPromocion()!=null) {
					tipoPromocion = tipoPromocionRepository.getOne(detallePromocionDto.getIdTipoPromocion());
					if (tipoPromocion!=null) {
						detallePromocion.setTipoPromocion(tipoPromocion);
					}
				}
				
				producto.addDetallePromocion(detallePromocion);
				productoRepository.save(producto);
			}
			else {
				lanzarExcepcionRecursoNoEncontrado();
			}
		}
		catch(ForbiddenException | ResourceNotFoundException es) {
			throw es;
		}
		catch(ProductoException es) {
			throw es;
		} catch(Exception es) {
			throw new ProductoException(es);
		}
		
	}

	
	@Transactional
	@Override
	public void updateDetallePromocionByProduct(Long idProducto, Long idDetallePromocion, DetallePromocionDto detallePromocionDto)
			throws ProductoException, ResourceNotFoundException,ForbiddenException {
		try {
			Optional<Producto> productoOp = productoRepository.findById(idProducto);
			Optional<DetallePromocion> detallePromocionOp = detallePromocionRepository.findById(idDetallePromocion);
			if(productoOp.isPresent() && detallePromocionOp.isPresent()) {
				Producto producto = productoOp.get();
				DetallePromocion detallePromocion = detallePromocionOp.get();
				BeanUtils.copyProperties(detallePromocionDto, detallePromocion);
				detallePromocion.setId(idDetallePromocion);
				
				if (detallePromocionDto.getIdTipoPromocion()!=null) {
					TipoPromocion tipoPromocion = tipoPromocionRepository.getOne(detallePromocionDto.getIdTipoPromocion());
					if (tipoPromocion!=null) {
						detallePromocion.setTipoPromocion(tipoPromocion);
					}
				}
				
				producto.updateDetallePromocion(detallePromocion);
				productoRepository.save(producto);
			}
			else {
				lanzarExcepcionRecursoNoEncontrado();
			}
		}
		catch(ResourceNotFoundException | ForbiddenException eu) {
			throw eu;
		} 
		catch(Exception eu) {
			throw new ProductoException(eu);
		}	
	}
	
	
	@Transactional
	@Override
	public void deleteDetallePromocionByProduct(Long idProducto, Long idDetallePromocion)
			throws ProductoException, ResourceNotFoundException,ForbiddenException {
		  try {
			Optional<Producto> productoOp= productoRepository.findById(idProducto);
			Optional<DetallePromocion> detallePromocionOp = detallePromocionRepository.findById(idDetallePromocion);
			if(productoOp.isPresent() && detallePromocionOp.isPresent()) {
				Producto producto = productoOp.get();
				DetallePromocion detallePromocion = detallePromocionOp.get();
				producto.removeDetallePromocion(detallePromocion);
				productoRepository.save(producto);
			}
			else {
				lanzarExcepcionRecursoNoEncontrado();
			}
		  }
		  catch(ResourceNotFoundException | ForbiddenException ed) {
				throw ed;
		  } 
		  catch(Exception ed) {
				throw new ProductoException(ed);
		  }	
	}

	
	private ResourceNotFoundException lanzarExcepcionRecursoNoEncontrado() {
		ResourceNotFoundException e = new ResourceNotFoundException();
		e.setConcreteException(e);
		e.setErrorMessage(MSG_NOT_FOUND);
		e.setDetail(MSG_NOT_FOUND);
		throw e;
	}

	
	
}
