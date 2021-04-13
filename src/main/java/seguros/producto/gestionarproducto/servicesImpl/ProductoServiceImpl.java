package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.dto.ProductoDoDto;
import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.dto.ProductoPageDto;
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
			
			if(producto.getTipoSeguro()!=null) {
				TipoSeguro tipoSeguro = tipoSeguroRepository.getOne(producto.getTipoSeguro());
				productoEntity.setTipoSeguro(tipoSeguro);
			}
			if(producto.getTipoPromocion()!=null) {
				TipoPromocion tipoPromocion = tipoPromocionRepository.getOne(producto.getTipoPromocion());
				productoEntity.setTipoPromocion(tipoPromocion);
			}
			if(producto.getTipoRecargo()!=null) {
				TipoRecargo tipoRecargo = tipoRecargoRepository.getOne(producto.getTipoRecargo());
				productoEntity.setTipoRecargo(tipoRecargo);
			}
			if(producto.getTipoAjuste()!=null) {
				TipoAjuste tipoAjuste = tipoAjusteRepository.getOne(producto.getTipoAjuste());
				productoEntity.setTipoAjuste(tipoAjuste);
			}
			if(producto.getTipoDescuento()!=null) {
				TipoDescuento tipoDescuento = tipoDescuentoRepository.getOne(producto.getTipoDescuento());
				productoEntity.setTipoDescuento(tipoDescuento);
			}
			if(producto.getTarifaPor()!=null) {
				TarifaPor tarifaPor = tarifaPorRepository.getOne(producto.getTarifaPor());
				productoEntity.setTarifaPor(tarifaPor);
			}
			if(producto.getTipoTarifa()!=null) {
				TipoTarifa tipoTarifa = tipoTarifaRepository.getOne(producto.getTipoTarifa());
				productoEntity.setTipoTarifa(tipoTarifa);
			}
			if(producto.getTipoPeriodo()!=null) {
				TipoPeriodo tipoPeriodo = tipoPeriodoRepository.getOne(producto.getTipoPeriodo());
				productoEntity.setTipoPeriodo(tipoPeriodo);
			}
			if(producto.getTipoTraspaso()!=null) {
				TipoTraspaso tipoTraspaso = tipoTraspasoRepository.getOne(producto.getTipoTraspaso());
				productoEntity.setTipoTraspaso(tipoTraspaso);
			}
			if(producto.getTipoAcreedor()!=null) {
				ModoTraspaso tipoAcreedor = modoTraspasoRepository.getOne(producto.getTipoAcreedor());
				productoEntity.setTipoAcreedor(tipoAcreedor);
			}
			if(producto.getTipoFacturar()!=null) {
				ModoTraspaso tipoFacturar = modoTraspasoRepository.getOne(producto.getTipoFacturar());
				productoEntity.setTipoFacturar(tipoFacturar);
			}
			
			DestinoVenta destinoVenta = destinoVentaRepository.getOne(producto.getProductDo().getDoplAQuienSeVende());
						
			ProductoDo productoDo = producto.getProductDo().toEntity();
			productoDo.setDoplAQuienSeVende(destinoVenta);			
						
			productoEntity.setProductDo(productoDo);
			productoEntity.setNemot(newNemotecnico);
			
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

	

	

	
	
	


	
}
