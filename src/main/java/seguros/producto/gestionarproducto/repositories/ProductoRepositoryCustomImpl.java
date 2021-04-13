package seguros.producto.gestionarproducto.repositories;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.configuration.PropertiesSql;
import seguros.producto.gestionarproducto.dto.CompaniaDto;
import seguros.producto.gestionarproducto.dto.NegocioDto;
import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.dto.ProductoPageDto;
import seguros.producto.gestionarproducto.dto.RamoDto;
import seguros.producto.gestionarproducto.dto.TipoSeguroDto;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

@Repository
public class ProductoRepositoryCustomImpl implements ProductoRepositoryCustom{

	
	private static final String MSG_NOT_RESULT= "No se ha retornado respuesta desde el proceso";
	private static final String PROCESS_NOT_FOUND="Proceso no identificado";
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired 
	private PropertiesSql propertiesSql;	
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public PageProductoDto findAllPaginated(int page, int size, Integer idCompania, Integer idNegocio,
			Integer idRamo, String nemotecnico, String descripcion) throws ProductoException {	
		
		String procedureName = propertiesSql.getLISTAR_PRODUCTOS_PAGINADO();
		List<ProductoPageDto> lista= new ArrayList<>();
		List<Object[]> record=null;
		Integer totalElements=null;
		PageProductoDto pageProductoDto= new PageProductoDto();
		
		 try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureName);	
			storedProcedureQuery.registerStoredProcedureParameter("page", Integer.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("size", Integer.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("id_compania", Integer.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("id_negocio", Integer.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("id_ramo", Integer.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("nemo", String.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("descrp", String.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("total", Integer.class, ParameterMode.OUT);		
			
			
			storedProcedureQuery.setParameter("page",page );
			storedProcedureQuery.setParameter("size",size );
			storedProcedureQuery.setParameter("id_compania",idCompania );
			storedProcedureQuery.setParameter("id_negocio",idNegocio );
			storedProcedureQuery.setParameter("id_ramo",idRamo );
			storedProcedureQuery.setParameter("nemo",nemotecnico );
			storedProcedureQuery.setParameter("descrp",descripcion );
			
			storedProcedureQuery.execute();
			record = storedProcedureQuery.getResultList();
			
			Object result= storedProcedureQuery.getOutputParameterValue("total");
			if(result!=null) {
				totalElements= (int) storedProcedureQuery.getOutputParameterValue("total");
			}		
			
			if(record!=null) {
				if(!record.isEmpty()) {
					record.stream().forEach(p -> {
						ProductoPageDto productoPageDto =  new ProductoPageDto();
						productoPageDto.setId( Long.valueOf( p[0].toString() ) );
						productoPageDto.setNemot( p[1].toString() );
						productoPageDto.setDescrip( p[2].toString() );
						
						TipoSeguroDto tipoSeguro= new TipoSeguroDto();
						tipoSeguro.setId(Long.valueOf( p[3].toString() ));
						tipoSeguro.setNombre(p[4].toString());
						
						CompaniaDto compania= new CompaniaDto();
						compania.setId( Long.valueOf( p[5].toString() ));
						compania.setNombre(  p[6].toString() );
						
						NegocioDto negocio= new NegocioDto();
						negocio.setId(Long.valueOf( p[7].toString() ));
						negocio.setNombre( p[8].toString());
						
						RamoDto ramo= new RamoDto();
						ramo.setId(Long.valueOf( p[9].toString() ));
						ramo.setNombre(p[10].toString());
						
						productoPageDto.setCompania(compania);
						productoPageDto.setNegocio(negocio);
						productoPageDto.setRamo(ramo);
						productoPageDto.setTipoSeguro(tipoSeguro);
						
						lista.add(productoPageDto); 
					});
				}
			}
			
			pageProductoDto.setTotalElements(totalElements);
			pageProductoDto.setProductos(lista);
		}
		catch(Exception e) {
			PcbsException exc = new PcbsException(e);
			throw exc;
		}

		return pageProductoDto;
	}
	






	
}
