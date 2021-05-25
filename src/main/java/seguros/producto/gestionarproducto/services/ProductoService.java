package seguros.producto.gestionarproducto.services;

import java.util.List;

import seguros.producto.gestionarproducto.dto.*;
import seguros.producto.gestionarproducto.exceptions.ResourceNotFoundException;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

public interface ProductoService {
	
	public List<ProductoDto> findAll() throws ProductoException;
	public InfoProductoDto save(ProductoDto producto) throws ProductoException;
	public 	PageProductoDto findAllPaginated( int page, int size, Integer idCompania, Integer idNegocio, Integer idRamo, String nemotecnico, String descripcion) throws ProductoException,PcbsException;
    public String encrypt(String palabrasePase) throws ProductoException;
    public String decrypt(String palabrasePase) throws ProductoException;
    public String getPassProductManagerByIdProducto(Long idProducto) throws ProductoException,ResourceNotFoundException;
    public void enableDisable(EstadoProductoDto estadoProductoDto) throws ProductoException;
    public List<TerminoCortoDto> getTerminosCortosByProduct(Long id) throws ProductoException,ResourceNotFoundException;
    public void saveTerminosCortosByProduct(Long id, List<TerminoCortoSaveDto> terminosCortos) throws ProductoException,ResourceNotFoundException;
    public void deleteTerminosCortosByProduct(Long idProducto,Long idTerminoCorto) throws ProductoException,ResourceNotFoundException;
    public void updateTerminosCortosByProduct(Long id, Long idTerminoCorto, TerminoCortoSaveDto terminosCorto) throws ProductoException,ResourceNotFoundException;
    public InfoProductoDto getInfoProducto(Long id)  throws ProductoException,ResourceNotFoundException;
    public List<CoberturaProductoDto> getCoberturasDtoByProducto(Long id) throws ProductoException,ResourceNotFoundException;
}
