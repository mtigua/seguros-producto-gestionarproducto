package seguros.producto.gestionarproducto.services;

import java.util.List;

import seguros.producto.gestionarproducto.dto.*;
import seguros.producto.gestionarproducto.exceptions.ForbiddenException;
import seguros.producto.gestionarproducto.exceptions.ResourceNotFoundException;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

public interface ProductoService {

    public List<CoberturaProductoCorrelativoDto> getCoberturasDtoByProductoCorrelative(Long id) throws ProductoException,ResourceNotFoundException;
    public List<TipoIvaDTO> getTipoIvaByProducto(Long id) throws ProductoException,ResourceNotFoundException;
    public List<DeducibleDTO> getDeducibles(Long id, Long idCobertura) throws ProductoException,ResourceNotFoundException;
	public List<ProductoDto> findAll() throws ProductoException;
	public InfoProductoDto save(ProductoDto producto) throws ProductoException;
	public void saveCoberturaProducto(CoberturaDTO cobertura) throws ProductoException;
	public void updateOrderCobertura(OrdenCoberturaDTO ordenCobertura) throws ProductoException;
	public 	PageProductoDto findAllPaginated( int page, int size, Integer idCompania, Integer idNegocio, Integer idRamo, String nemotecnico, String descripcion) throws ProductoException;
    public String encrypt(String palabrasePase) throws ProductoException;
    public String decrypt(String palabrasePase) throws ProductoException;
    public String getPassProductManagerByIdProducto(Long idProducto) throws ProductoException,ResourceNotFoundException;
    public void enableDisable(EstadoProductoDto estadoProductoDto) throws ProductoException;
    public List<TerminoCortoDto> getTerminosCortosByProduct(Long id) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void saveTerminosCortosByProduct(Long id, List<TerminoCortoSaveDto> terminosCortos) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void deleteTerminosCortosByProduct(Long idProducto,Long idTerminoCorto) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void updateTerminosCortosByProduct(Long id, Long idTerminoCorto, TerminoCortoSaveDto terminosCorto) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public InfoProductoDto getInfoProducto(Long id)  throws ProductoException,ResourceNotFoundException;
    public List<CoberturaProductoDto> getCoberturasDtoByProducto(Long id) throws ProductoException,ResourceNotFoundException;

    public List<TramoListDto> getTramosByProduct(Long id) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void saveTramosByProduct(Long id, TramoDto tramoDto,Long tipoRamo) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void deleteTramoByProduct(Long idProducto,Long idTramo) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void updateTramoByProduct(Long id, Long idTramo, TramoDto tramoDto,Long tipoRamo) throws ProductoException,ResourceNotFoundException,ForbiddenException;

    public List<TramoListDto> getTramosByCobertura(Long idProducto, Long idCobertura) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void saveTramosByProductCobertura(Long productId, Long coberturaId, TramoDto tramoDto,Long tipoRamo) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void updateTramoByCobertura(Long id, Long idCobertura, Long idTramo, TramoDto tramoDto,Long tipoRamo) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void deleteTramoByCobertura(Long idProducto, Long idCobertura, Long idTramo) throws ProductoException,ResourceNotFoundException,ForbiddenException;

    public List<RecargoPorAseguradoDto> getRecargoPorAseguradoByProduct(Long id) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void saveRecargoPorAseguradoByProduct(Long id, List<RecargoPorAseguradoDto> recargoPorAsegurado) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void deleteRecargoPorAseguradoByProduct(Long idProducto,Long idRecargoPorAsegurado) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void updateRecargoPorAseguradoByProduct(Long id, Long idRecargoPorAsegurado, RecargoPorAseguradoDto recargoPorAsegurado) throws ProductoException,ResourceNotFoundException,ForbiddenException;
   
    public List<DetallePromocionListDto> getDetallePromocionesByProduct(Long id) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void saveDetallePromocionByProduct(Long id, DetallePromocionDto detallePromocionDto) throws ProductoException,ResourceNotFoundException,ForbiddenException;
	public void updateDetallePromocionByProduct(Long idProducto, Long idDetallePromocion, DetallePromocionDto detallePromocionDto) throws ProductoException, ResourceNotFoundException,ForbiddenException;
	public void deleteDetallePromocionByProduct(Long idProducto, Long idDetallePromocion) throws ProductoException, ResourceNotFoundException,ForbiddenException;

    public List<PlanUpgradeDto> getPlanUpgradeByProduct(Long id) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public List<ProdDto> getProductByNemo(Long id,String nemo) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public List<ProdDto> getPlanesExistentesByNemo(Long id,String nemoU, String nemoP) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public List<ProdDto> getPlanesAceptadosByNemo(Long id,String nemoU, String nemoP) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void saveUpgradeByProduct(Long id, List<PlanUpgradeDto> upgrades, String nemoP) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void deleteUpgradeByProduct(Long idProducto,Long idUpgrade) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void updateUpgradeByProduct(Long id, Long idUpgrade, PlanUpgradeDto planUpgradeDto) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    
    
    public InfoProductoDto saveFormInicio(Long id,FormDataInicioDto producto) throws ProductoException,ResourceNotFoundException;
    public InfoProductoDto saveFormEncabezado(Long id,FormDataEncabezadoDto producto) throws ProductoException,ResourceNotFoundException;
    public InfoProductoDto saveFormGeneral(Long id,FormDataGeneralDto producto) throws ProductoException,ResourceNotFoundException;
    public InfoProductoDto saveFormTraspaso(Long id,FormDataTraspasoDto producto) throws ProductoException,ResourceNotFoundException;
    public InfoProductoDto saveFormVDD(Long id,FormDataVidaVehiculoDeclaracionDto producto) throws ProductoException,ResourceNotFoundException;
    public InfoProductoDto saveFormDescripcionOperativa(Long id,FormDataDescripcionOperativaDto producto) throws ProductoException,ResourceNotFoundException;
    
   
    public FormDataInicioDto getFormInicio(Long id) throws ProductoException,ResourceNotFoundException;
    public FormDataEncabezadoDto getFormEncabezado(Long id) throws ProductoException,ResourceNotFoundException;
    public FormDataGeneralDto getFormGeneral(Long id) throws ProductoException,ResourceNotFoundException;
    public FormDataTraspasoDto getFormTraspaso(Long id) throws ProductoException,ResourceNotFoundException;
    public FormDataVidaVehiculoDeclaracionDto getFormVDD(Long id) throws ProductoException,ResourceNotFoundException;
    public FormDataDescripcionOperativaDto getFormDescripcionOperativa(Long id) throws ProductoException,ResourceNotFoundException;
    
    
    
}
