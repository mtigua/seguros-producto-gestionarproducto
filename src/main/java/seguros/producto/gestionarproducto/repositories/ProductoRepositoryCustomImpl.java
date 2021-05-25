package seguros.producto.gestionarproducto.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.configuration.PropertiesSql;
import seguros.producto.gestionarproducto.dto.CanalDto;
import seguros.producto.gestionarproducto.dto.CompaniaDto;
import seguros.producto.gestionarproducto.dto.InfoProductoDto;
import seguros.producto.gestionarproducto.dto.NegocioDto;
import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.dto.ProductoPageDto;
import seguros.producto.gestionarproducto.dto.RamoDto;
import seguros.producto.gestionarproducto.dto.TipoSeguroDto;
import seguros.producto.gestionarproducto.entities.Canal;
import seguros.producto.gestionarproducto.entities.Producto;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

@Repository
public class ProductoRepositoryCustomImpl implements ProductoRepositoryCustom{

	
	
	private static final String VALUE_UNDEFINED="No se ha podido indentificar valor para el par\u00E1metro de salida: ";
	static final String RESULT = "result";
	static final String EXISTE = "existe";
	
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
		  else {
			    ProductoException exc = new ProductoException();
				exc.setErrorMessage(VALUE_UNDEFINED + "existe");	        	
				exc.setDetail(VALUE_UNDEFINED + "existe");
				exc.setConcreteException(exc);
				throw exc;
		  }	
			
			if(record!=null) {
				if(!record.isEmpty()) {
					record.stream().forEach(p -> {
						ProductoPageDto productoPageDto =  new ProductoPageDto();
						productoPageDto.setId( Long.valueOf( p[0].toString() ) );
						productoPageDto.setNemot( p[1].toString() );
						productoPageDto.setDescrip( p[2].toString() );
						Long habilitado = p[11] == null?0L:Long.valueOf( p[11].toString()); 
						productoPageDto.setHabilitado(habilitado);
						
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
						
						Producto productoEntity= entityManager.find(Producto.class, productoPageDto.getId());
						if(productoEntity!=null) {
							Set<Canal> canales=productoEntity.getCanales();
							List<CanalDto> canalesDto= canales.stream().map( item-> {
								CanalDto canalDto= new CanalDto();
								BeanUtils.copyProperties(item, canalDto);
								return canalDto;
							}).collect(Collectors.toList());
							
							productoPageDto.setCanales(canalesDto);
						}
						lista.add(productoPageDto); 
					});
				}
			}
			
			pageProductoDto.setTotalElements(totalElements);
			pageProductoDto.setProductos(lista);
		}
		catch(ProductoException e){
				throw e;
		}
		catch(Exception e) {
			ProductoException exc = new ProductoException(e);
			throw exc;
		}

		return pageProductoDto;
	}






	@SuppressWarnings({ "unchecked" })
	@Override
	public InfoProductoDto getInfoProducto(Long id) throws ProductoException {
	
		InfoProductoDto infoProductoDto= new InfoProductoDto();
		String procedureName = propertiesSql.getGET_DATA_INFO_PRODUCTO();
		List<Object[]> record=null;
		
	   try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureName);	
			storedProcedureQuery.registerStoredProcedureParameter("idProducto", Long.class, ParameterMode.IN);
			storedProcedureQuery.setParameter("idProducto",id );
			
			storedProcedureQuery.execute();
			record = storedProcedureQuery.getResultList();
			
			if(record!=null) {
				if(!record.isEmpty()) {
					infoProductoDto.setNemotecnico( record.get(0)[1].toString() );
					infoProductoDto.setCompania( record.get(0)[2].toString() );
					infoProductoDto.setNegocio( record.get(0)[3].toString() );
					infoProductoDto.setRamo( record.get(0)[4].toString() );
				}
			}
       }
       catch(Exception e) {
    	    ProductoException exc = new ProductoException(e);
			throw exc;
       }
       
       return infoProductoDto;
	}
	


	@Override
    public String generateNemotecnico() throws ProductoException {
        String generateNemotecnico = propertiesSql.getGENERATE_NEMOTECNICO();
        String newNemotecnico = null;

        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(generateNemotecnico);
            storedProcedureQuery.registerStoredProcedureParameter(RESULT, String.class, ParameterMode.OUT);

            storedProcedureQuery.execute();

            Object result = storedProcedureQuery.getOutputParameterValue(RESULT);
            if (result != null) {
                newNemotecnico = String.valueOf(storedProcedureQuery.getOutputParameterValue(RESULT));
            } else {
            	ProductoException executeGenerateNemotecnico = new ProductoException();
                executeGenerateNemotecnico.setErrorMessage(VALUE_UNDEFINED + EXISTE);
                executeGenerateNemotecnico.setDetail(VALUE_UNDEFINED + EXISTE);
                executeGenerateNemotecnico.setConcreteException(executeGenerateNemotecnico);
                throw executeGenerateNemotecnico;
            }

        } catch (ProductoException e) {
            throw e;
        } catch (Exception e) {
            throw new ProductoException(e);
        }

        return newNemotecnico;
    }




	
}
