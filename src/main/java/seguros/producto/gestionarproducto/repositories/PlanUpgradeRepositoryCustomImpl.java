package seguros.producto.gestionarproducto.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import seguros.producto.gestionarproducto.configuration.PropertiesSql;
import seguros.producto.gestionarproducto.dto.PlanUpgradeDto;
import seguros.producto.gestionarproducto.dto.ProdDto;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

public class PlanUpgradeRepositoryCustomImpl implements PlanUpgradeRepositoryCustom {

    private static final String VALUE_UNDEFINED="No se ha podido indentificar valor para el par\u00E1metro de salida: ";

    static final String ID_PRODUCTO = "idProducto";
    static final String NEMOU = "nemoU";
    static final String NEMOP = "nemoP";

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PropertiesSql propertiesSql;

    @SuppressWarnings("unchecked")
    @Override
    public List<PlanUpgradeDto> getPlanUpgrade(Long id) throws ProductoException {
        List<PlanUpgradeDto> lista = new ArrayList<>();
        List<Object[]> record=null;
        String procedureName = propertiesSql.getLISTAR_UPGRADES_POR_PRODUCTO();
        try{
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureName);
            storedProcedureQuery.registerStoredProcedureParameter(ID_PRODUCTO, Long.class, ParameterMode.IN);
            storedProcedureQuery.setParameter(ID_PRODUCTO,id );

            storedProcedureQuery.execute();
            record = storedProcedureQuery.getResultList();
            if(record!=null && !record.isEmpty()){
                   record.stream().forEach(p-> {
                        PlanUpgradeDto planUpgradeDto = new PlanUpgradeDto();
                        planUpgradeDto.setIdUpgrade(Long.valueOf( p[0].toString() ));
                        planUpgradeDto.setNemoVigente( p[1].toString() );
                        planUpgradeDto.setPlanVigente( p[2].toString() );
                        planUpgradeDto.setNemoUpgrade( p[3].toString() );
                        planUpgradeDto.setPlanUpgrade( p[4].toString() );
                        planUpgradeDto.setNemoPrevio( p[5].toString() );
                        planUpgradeDto.setPlanPrevio( p[6].toString() );
                        planUpgradeDto.setDiasRenuncia(Integer.valueOf( p[7].toString() ));
                        lista.add(planUpgradeDto);
                   });
            }else{
                ProductoException exc = new ProductoException();
                exc.setErrorMessage(VALUE_UNDEFINED );
                exc.setDetail(VALUE_UNDEFINED );
                exc.setConcreteException(exc);
                throw exc;
            }
        }catch(ProductoException e){
            throw e;
        }
        catch(Exception e) {
            ProductoException exc = new ProductoException(e);
            throw exc;
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProdDto> getProductoPorNemotecnico(String nemo) throws ProductoException {
        List<ProdDto> lista = new ArrayList<>();
        List<Object[]> record=null;
        String procedureName = propertiesSql.getLISTAR_PRODUCTOS_POR_NEMOTECNICO();
        try{
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureName);
            storedProcedureQuery.registerStoredProcedureParameter("nemo", String.class, ParameterMode.IN);
            storedProcedureQuery.setParameter("nemo",nemo );

            storedProcedureQuery.execute();
            record = storedProcedureQuery.getResultList();
            if(record!=null && !record.isEmpty()){
                    record.stream().forEach(p-> {
                        ProdDto prodDto = new ProdDto();
                        prodDto.setId(Long.valueOf( p[0].toString() ));
                        prodDto.setNemo( p[1].toString() );
                        prodDto.setNombre( p[2].toString() );
                        lista.add(prodDto);
                    });
            }else{
                ProductoException exc = new ProductoException();
                exc.setErrorMessage(VALUE_UNDEFINED );
                exc.setDetail(VALUE_UNDEFINED );
                exc.setConcreteException(exc);
                throw exc;
            }
        }catch(ProductoException e){
            throw e;
        }
        catch(Exception e) {
            ProductoException exc = new ProductoException(e);
            throw exc;
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProdDto> getPlanesExistentesPorNemotecnico(Long id, String nemoU, String nemoP) throws ProductoException {
        List<ProdDto> lista = new ArrayList<>();
        List<Object[]> record=null;
        String procedureName = propertiesSql.getLISTAR_PLANES_EXISTENTES_POR_NEMOTECNICO();
        try{
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureName);
            storedProcedureQuery.registerStoredProcedureParameter(ID_PRODUCTO, Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(NEMOU, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(NEMOP, String.class, ParameterMode.IN);
            storedProcedureQuery.setParameter(ID_PRODUCTO,id );
            storedProcedureQuery.setParameter(NEMOU,nemoU );
            storedProcedureQuery.setParameter(NEMOP,nemoP );

            storedProcedureQuery.execute();
            record = storedProcedureQuery.getResultList();
            if(record!=null && !record.isEmpty()){
                    record.stream().forEach(p-> {
                        ProdDto prodDto = new ProdDto();
                        prodDto.setId(Long.valueOf( p[0].toString() ));
                        prodDto.setNemo( p[1].toString() );
                        prodDto.setNombre( p[2].toString() );
                        lista.add(prodDto);
                    });
            }else{
                ProductoException exc = new ProductoException();
                exc.setErrorMessage(VALUE_UNDEFINED);
                exc.setDetail(VALUE_UNDEFINED);
                exc.setConcreteException(exc);
                throw exc;
            }
        }catch(ProductoException e){
            throw e;
        }
        catch(Exception e) {
            ProductoException exc = new ProductoException(e);
            throw exc;
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProdDto> getPlanesAceptadosPorNemotecnico(Long id, String nemoU, String nemoP) throws ProductoException {
        List<ProdDto> lista = new ArrayList<>();
        List<Object[]> record=null;
        String procedureName = propertiesSql.getLISTAR_PLANES_ACEPTADOS_POR_NEMOTECNICO();
        try{
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureName);
            storedProcedureQuery.registerStoredProcedureParameter(ID_PRODUCTO, Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(NEMOU, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(NEMOP, String.class, ParameterMode.IN);
            storedProcedureQuery.setParameter(ID_PRODUCTO,id );
            storedProcedureQuery.setParameter(NEMOU,nemoU );
            storedProcedureQuery.setParameter(NEMOP,nemoP );

            storedProcedureQuery.execute();
            record = storedProcedureQuery.getResultList();
            if(record!=null && !record.isEmpty()){
                record.stream().forEach(p-> {
                    ProdDto prodDto = new ProdDto();
                    prodDto.setId(Long.valueOf( p[0].toString() ));
                    prodDto.setNemo( p[1].toString() );
                    prodDto.setNombre( p[2].toString() );
                    lista.add(prodDto);
                });
            }else{
                ProductoException exc = new ProductoException();
                exc.setErrorMessage(VALUE_UNDEFINED);
                exc.setDetail(VALUE_UNDEFINED);
                exc.setConcreteException(exc);
                throw exc;
            }
        }catch(ProductoException e){
            throw e;
        }
        catch(Exception e) {
            ProductoException exc = new ProductoException(e);
            throw exc;
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deletePlanUpgrade(Long id, Long idUpgrade) throws ProductoException {
        String procedureName = propertiesSql.getELIMINAR_UPGRADES_POR_PRODUCTO();
        try{
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureName);
            storedProcedureQuery.registerStoredProcedureParameter(ID_PRODUCTO, Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("idUpgrade", Long.class, ParameterMode.IN);
            storedProcedureQuery.setParameter(ID_PRODUCTO,id );
            storedProcedureQuery.setParameter("idUpgrade",idUpgrade );
            storedProcedureQuery.execute();
        }catch(ProductoException e){
            throw e;
        }
        catch(Exception e) {
            ProductoException exc = new ProductoException(e);
            throw exc;
        }
    }
}
