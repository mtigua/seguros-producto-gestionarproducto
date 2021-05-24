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
	private String logger_error_executing_get_subtipo_por_compania_ramo;
	private String logger_error_executing_get_producto_por_subtipo;
	private String logger_error_executing_get_grupo_matriz;
	private String logger_error_executing_get_grupo;
	private String logger_error_executing_get_equivalencia_seguro;
	private String logger_error_executing_get_grupo_mejor_oferta;
	private String logger_error_executing_get_tipo_traspaso;
	private String logger_error_executing_get_modo_traspaso;
	private String logger_error_executing_find_numpoliza;
	private String logger_error_executing_find_asociado;
	private String logger_error_executing_find_catalogo_cuotas;
	private String logger_error_executing_find_rut;
	private String logger_error_executing_get_destino_venta;
	private String logger_error_executing_find_rut_product_manager;
	private String logger_error_executing_get_producto_do;
	private String logger_error_executing_decrypt_password_product_manager;
	private String logger_error_executing_save_producto;
	private String logger_error_executing_list_plan_cobertura;
	private String logger_error_executing_find_codigopos;
	private String logger_error_executing_get_palabrapase_product_manager;
	private String logger_error_executing_get_tipo_multa;
	private String logger_error_executing_get_terminos_corto_by_product;
	private String logger_error_executing_save_terminos_corto_by_product;
	private String logger_error_executing_update_terminos_corto_by_product;
	private String logger_error_executing_delete_terminos_corto_by_product;
	private String logger_error_executing_get_info_producto;
	private String logger_error_executing_get_tipo_cobertura;
	private String logger_error_executing_get_parentesco;
	private String logger_error_executing_get_tipo_tasa;
	private String logger_error_executing_get_prima_sobre_que;

}