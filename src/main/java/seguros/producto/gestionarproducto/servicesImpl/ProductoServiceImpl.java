package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import seguros.producto.gestionarproducto.configuration.Properties;
import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.dto.ProductoDoDto;
import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.entities.DestinoVenta;
import seguros.producto.gestionarproducto.entities.ModoTraspaso;
import seguros.producto.gestionarproducto.entities.Producto;
import seguros.producto.gestionarproducto.entities.ProductoDo;
import seguros.producto.gestionarproducto.entities.TarifaPor;
import seguros.producto.gestionarproducto.entities.TipoAjuste;
import seguros.producto.gestionarproducto.entities.TipoDescuento;
import seguros.producto.gestionarproducto.entities.TipoPeriodo;
import seguros.producto.gestionarproducto.entities.TipoPromocion;
import seguros.producto.gestionarproducto.entities.TipoRecargo;
import seguros.producto.gestionarproducto.entities.TipoSeguro;
import seguros.producto.gestionarproducto.entities.TipoTarifa;
import seguros.producto.gestionarproducto.entities.TipoTraspaso;
import seguros.producto.gestionarproducto.repositories.ModoTraspasoRepository;
import seguros.producto.gestionarproducto.repositories.PCBSRepositoryCustom;
import seguros.producto.gestionarproducto.repositories.ProductoRepository;
import seguros.producto.gestionarproducto.repositories.TarifaPorRepository;
import seguros.producto.gestionarproducto.repositories.TipoAjusteRepository;
import seguros.producto.gestionarproducto.repositories.TipoDescuentoRepository;
import seguros.producto.gestionarproducto.repositories.TipoPeriodoRepository;
import seguros.producto.gestionarproducto.repositories.TipoPromocionRepository;
import seguros.producto.gestionarproducto.repositories.TipoRecargoRepository;
import seguros.producto.gestionarproducto.repositories.TipoSeguroRepository;
import seguros.producto.gestionarproducto.repositories.TipoTarifaRepository;
import seguros.producto.gestionarproducto.repositories.TipoTraspasoRepository;
import seguros.producto.gestionarproducto.services.ProductoService;
import seguros.producto.gestionarproducto.repositories.DestinoVentaRepository;


@Service
public class ProductoServiceImpl implements ProductoService {
	

	private static final Long VALUE_UNDEFINED=-1L;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private TipoSeguroRepository tipoSeguroRepository;
	
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
	private ModoTraspasoRepository modoTraspasoRepository;
	
	@Autowired
	private DestinoVentaRepository destinoVentaRepository;
	
	@Autowired
	private PCBSRepositoryCustom pcbsRepository;
	
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
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			ProductoException exc = new ProductoException(e);
			throw exc;
		}
		return list;
		
	}

	@Transactional
	@Override
	public String save(ProductoDto producto) throws ProductoException,PcbsException {
        String result="";
		
		try {
			Producto productoEntity = producto.toEntity();
			String newNemotecnico = pcbsRepository.generateNemotecnico();
			
			if(producto.getTipoSeguro()!=null && !VALUE_UNDEFINED.equals(producto.getTipoSeguro()) ) {
				TipoSeguro tipoSeguro = tipoSeguroRepository.getOne(producto.getTipoSeguro());
				if(tipoSeguro.getId()!=null) {
				   productoEntity.setTipoSeguro(tipoSeguro);
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
			
			result= newNemotecnico;
		}
		catch(PcbsException e) {
			throw e;
		}
		catch(Exception e) {
			ProductoException exc = new ProductoException(e);
			throw exc;
		}
		return result;
	}

	@Transactional
	@Override
	public PageProductoDto findAllPaginated(int page, int size, Integer idCompania, Integer idNegocio,
			Integer idRamo, String nemotecnico, String descripcion) throws ProductoException, PcbsException {
	
		PageProductoDto pageProductoDto= null;
		
		try {
			pageProductoDto= productoRepository.findAllPaginated(page, size, idCompania, idNegocio, idRamo, nemotecnico, descripcion);
		}
		catch(Exception e) {
			ProductoException exc = new ProductoException(e);
			throw exc;
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
				ProductoException exc = new ProductoException(e);
				throw exc;
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
				ProductoException exc = new ProductoException(e);
				throw exc;
		}
		return result;
	}

	

	

	
	
	


	
}
