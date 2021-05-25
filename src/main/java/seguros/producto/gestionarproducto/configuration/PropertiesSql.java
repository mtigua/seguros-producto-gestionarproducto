package seguros.producto.gestionarproducto.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;


@Component
@Data
@ConfigurationProperties(prefix="sql.query")
public class PropertiesSql {
	
	
	private String LISTAR_MONEDAS;
	private String LISTAR_COMPANIAS;
	private String LISTAR_NEGOCIOS_POR_COMPANIA;
	private String LISTAR_RAMOS_POR_COMPANIA_NEGOCIO;
	private String LISTAR_SUBTIPOS_POR_COMPANIA_RAMO;
	private String LISTAR_PRODUCTOS_POR_SUBTIPO;
	private String LISTAR_GRUPOS_MATRIZ;
	private String LISTAR_GRUPOS;
	private String LISTAR_EQUIVALENCIAS_SEGUROS;
	private String LISTAR_GRUPOS_MEJOR_OFERTA;
	private String BUSCAR_POLIZA;
	private String BUSCAR_RUT;
	private String BUSCAR_RUT_SIN_DIGITO_PRODUCT_MANAGER;
	private String DESENCRIPTAR;
	private String GENERATE_NEMOTECNICO;
	private String LISTAR_PRODUCTOS_PAGINADO;
	private String LISTAR_ASOCIADO;
	private String LISTAR_ASOCIADO_EMISION;
	private String LISTAR_PLAN_COBERTURA;
	private String VALIDAR_BUSCAR_CODIGOPOS;
	private String GET_DATA_INFO_PRODUCTO;
	private String LISTAR_COBERTURAS_POR_PRODUCTO;

}