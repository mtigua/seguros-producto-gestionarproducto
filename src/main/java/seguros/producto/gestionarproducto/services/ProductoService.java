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
	public InfoProductoDto save(ProductoDto producto) throws ProductoException,ResourceNotFoundException;
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

    public void saveGrupoAOfrecerByProduct(Long id, List<GrupoMejorOfertaRequestDto> grupoMejorOferta) throws ProductoException,ResourceNotFoundException,ForbiddenException;

    public List<PlanUpgradeDto> getPlanUpgradeByProduct(Long id) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public List<ProdDto> getProductByNemo(Long id,String nemo) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public List<ProdDto> getPlanesExistentesByNemo(Long id,String nemoU, String nemoP) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public List<ProdDto> getPlanesAceptadosByNemo(Long id,String nemoU, String nemoP) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void saveUpgradeByProduct(Long id, List<PlanUpgradeDto> upgrades, String nemoP) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void deleteUpgradeByProduct(Long idProducto,Long idUpgrade) throws ProductoException,ResourceNotFoundException,ForbiddenException;
    public void updateUpgradeByProduct(Long id, Long idUpgrade, PlanUpgradeDto planUpgradeDto) throws ProductoException,ResourceNotFoundException,ForbiddenException;


    public List<ProfesionDto> getProfesionesByProduct(Long id) throws ProductoException,ResourceNotFoundException;
    public void saveProfesionByProduct(Long id, ProfesionDto profesionDto) throws ProductoException,ResourceNotFoundException;
    public void updateProfesionByProduct(Long idProducto, Long idProfesion, ProfesionDto profesionDtoDto) throws ProductoException, ResourceNotFoundException;
    public void deleteProfesionByProduct(Long idProducto, Long idProfesion) throws ProductoException, ResourceNotFoundException;
    public List<CriterioListDto> getCriteriosDtoByProducto(Long idProducto, Long idProfesion) throws ProductoException,ResourceNotFoundException;
    public void saveCriterioByProductProfesion(Long idProducto, Long idProfesion, List<Long> listIdPregunta) throws ProductoException,ResourceNotFoundException;
    public void deleteProfesionesByProduct(Long idProducto) throws ProductoException,ResourceNotFoundException;
    public void copyProfesionFrom(Long idProducto, Long idProductoOrigen) throws ProductoException,ResourceNotFoundException;
    public List<ProdDto> findAllByCompaniaNegocioRamo(Integer idCompania, Integer idNegocio, Integer idRamo) throws ProductoException;

    
    public InfoProductoDto saveFormInicio(Long id,FormDataInicioSaveDto producto) throws ProductoException,ResourceNotFoundException;
    public InfoProductoDto saveFormEncabezado(Long id,FormDataEncabezadoSaveDto producto) throws ProductoException,ResourceNotFoundException;
    public InfoProductoDto saveFormGeneral(Long id,FormDataGeneralSaveDto producto) throws ProductoException,ResourceNotFoundException;
    public InfoProductoDto saveFormTraspaso(Long id,FormDataTraspasoSaveDto producto) throws ProductoException,ResourceNotFoundException;
    public InfoProductoDto saveFormVDD(Long id,FormDataVidaVehiculoDeclaracionSaveDto producto) throws ProductoException,ResourceNotFoundException;
    public InfoProductoDto saveFormDescripcionOperativa(Long id,FormDataDescripcionOperativaSaveDto producto) throws ProductoException,ResourceNotFoundException;
    
   
    public FormDataInicioSaveDto getFormInicio(Long id) throws ProductoException,ResourceNotFoundException;
    public FormDataEncabezadoSaveDto getFormEncabezado(Long id) throws ProductoException,ResourceNotFoundException;
    public FormDataGeneralSaveDto getFormGeneral(Long id) throws ProductoException,ResourceNotFoundException;
    public FormDataTraspasoSaveDto getFormTraspaso(Long id) throws ProductoException,ResourceNotFoundException;
    public FormDataVidaVehiculoDeclaracionSaveDto getFormVDD(Long id) throws ProductoException,ResourceNotFoundException;
    public FormDataDescripcionOperativaSaveDto getFormDescripcionOperativa(Long id) throws ProductoException,ResourceNotFoundException;
    
    public String generateNemotecnico() throws ProductoException;
    public void saveOrUpdateNemotecnico(NemotecnicoDto nemotecnico) throws ProductoException;
    public ProductoDto getProductoById(Long id) throws ProductoException,ResourceNotFoundException;
    

}
