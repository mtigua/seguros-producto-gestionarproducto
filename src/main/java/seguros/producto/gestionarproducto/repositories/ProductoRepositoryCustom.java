package seguros.producto.gestionarproducto.repositories;

import seguros.producto.gestionarproducto.dto.*;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

import java.util.List;

public interface ProductoRepositoryCustom {
	
	
	
	public 	PageProductoDto findAllPaginated( int page, int size, Integer idCompania, Integer idNegocio, Integer idRamo, String nemotecnico, String descripcion) throws ProductoException;

	public InfoProductoDto getInfoProducto(Long id) throws ProductoException;

	public List<CoberturaProductoDto> findCoberturasDtoByProducto(Long id) throws ProductoException;

	public List<CoberturaProductoCorrelativoDto> findCoberturasDtoByProductoCorrelative(Long id) throws ProductoException;

	public List<TipoIvaDTO> findTipoIva(Long id) throws ProductoException;

	public List<DeducibleDTO> findDeducibles(Long id, Long idCobertura) throws ProductoException;
	
	public Integer verificarSiExisteNemotecnico(String nemotecnico)  throws ProductoException;
	
	public String generateNemotecnico() throws ProductoException ;

	public List<CriterioListDto> findCriteriosDtoByProductoProfesion(Long idProducto,Long idProfesion) throws ProductoException;
	public List<ProdDto> findAllByCompaniaNegocioRamo(Integer idCompania, Integer idNegocio, Integer idRamo);
	
	public void saveOrUpdateNemotecnico(NemotecnicoDto nemotecnico) throws ProductoException ;
}
