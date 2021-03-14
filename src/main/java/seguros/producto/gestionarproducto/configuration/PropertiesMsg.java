package seguros.producto.gestionarproducto.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix="msg")
public class PropertiesMsg {
	
	
	private String logger_error_executing_get_productos;
	private String logger_error_executing_get_canal;
	private String logger_error_executing_get_concepto;
	private String logger_error_executing_get_homologacion_identificador;
	private String logger_error_executing_get_tarifa_por;
	private String logger_error_executing_get_tipo_ajuste;
	private String logger_error_executing_get_tipo_descuento;
	private String logger_error_executing_get_tipo_periodo;
	private String logger_error_executing_get_tipo_promocion;
	private String logger_error_executing_get_tipo_recargo;
	private String logger_error_executing_get_tipo_seguro;
	private String logger_error_executing_get_tipo_tarifa;
	private String logger_error_executing_get_moneda;
	private String logger_error_executing_get_compania;
	private String logger_error_executing_get_negocio_por_compania;
	private String logger_error_executing_get_ramo_por_compania_negocio;
	private String logger_error_executing_get_tipo_traspaso;
	private String logger_error_executing_get_modo_traspaso;
		 
}