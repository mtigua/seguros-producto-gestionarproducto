package seguros.producto.gestionarproducto.services;

import java.util.List;

import seguros.producto.gestionarproducto.dto.*;
import seguros.producto.gestionarproducto.exceptions.ForbiddenException;
import seguros.producto.gestionarproducto.exceptions.ResourceNotFoundException;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

public interface ProductoService {

    public List<CoberturaProductoCorrelativoDto> getCoberturasDtoByProductoCorrelative(Long id) throws ProductoException,ResourceNotFoundException;
    public List<TipoIvaDTO> getTipoIvaByProducto(Long id) throws ProductoException,ResourceNotFoundException;
    public List<DeducibleDTO> getDeducibles(Long id) throws ProductoException,ResourceNotFoundException;
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
}
