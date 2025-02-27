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
	private String logger_error_executing_get_tipo_traspaso;
	private String logger_error_executing_get_modo_traspaso;
	private String logger_error_executing_find_catalogo_cuotas;
	private String logger_error_executing_get_destino_venta;
	private String logger_error_executing_get_producto_do;
	private String logger_error_executing_save_producto;
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
	private String logger_error_executing_get_coberturas_by_product;
	private String logger_error_executing_get_tramo_by_product;
	private String logger_error_executing_save_tramo_by_product;
	private String logger_error_executing_update_tramo_by_product;
	private String logger_error_executing_delete_tramo_by_product;
	
	private String logger_error_executing_get_tarifa_es;
	private String logger_error_executing_get_tipo_tramo;
	private String logger_error_executing_update_orden_cobertura;
	private String logger_error_executing_save_cobertura;

	private String logger_error_executing_get_coberturas_by_product_correlative;
	private String logger_error_executing_tipo_iva_by_producto;
	private String logger_error_executing_deducibles;

	private String logger_error_executing_get_recargo_por_asegurado_by_product;
	private String logger_error_executing_save_recargo_por_asegurado_by_product;
	private String logger_error_executing_update_recargo_por_asegurado_by_product;
	private String logger_error_executing_delete_recargo_por_asegurado_by_product;

	private String logger_error_executing_get_detallepromocion_by_product;
	private String logger_error_executing_save_detallepromocion_by_product;
	private String logger_error_executing_update_detallepromocion_by_product;
	private String logger_error_executing_delete_detallepromocion_by_product;

	private String logger_error_executing_get_plan_upgrade_by_product;
	private String logger_error_executing_get_product_by_nemo;
	private String logger_error_executing_get_plan_aceptado_by_nemo;
	private String logger_error_executing_get_plan_existente_by_nemo;
	private String logger_error_executing_save_plan_upgrade_by_product;
	private String logger_error_executing_save_plan_grupo_oferta_by_product;
	private String logger_error_executing_update_plan_upgrade_by_product;
	private String logger_error_executing_delete_plan_upgrade_by_product;
	private String logger_error_executing_get_profesion_by_product;
	private String logger_error_executing_save_profesion_by_product;
	private String logger_error_executing_update_profesion_by_product;
	private String logger_error_executing_delete_profesion_by_product;
	private String logger_error_executing_copy_profesion_from_by_product;
	private String logger_error_executing_save_criterio_by_product_profesion;
	private String logger_error_executing_get_criterios_by_product_profesion;
	private String logger_error_executing_get_info_section_producto;
}