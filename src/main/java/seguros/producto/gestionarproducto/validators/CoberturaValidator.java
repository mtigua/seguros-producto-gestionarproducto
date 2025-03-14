package seguros.producto.gestionarproducto.validators;

import seguros.producto.gestionarproducto.dto.CoberturaDTO;
import seguros.producto.gestionarproducto.utils.TipoCoberturaEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class CoberturaValidator implements ConstraintValidator<CoberturaConstraint, Object> {

	public static final String MENSAJE_ERROR_REQUIRED_COBERTURA= "El campo cobertura es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_TIPO= "El campo tipo es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_MONTO_ASEGURADO= "El campo monto asegurado es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_EN= "El campo EN es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_EDAD_MAXIMA= "El campo edad maxima es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_TASA= "El campo valor tasa es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_TASA_ZERO = "El campo valor tasa debe ser igual a 0";
	public static final String MENSAJE_ERROR_REQUIRED_TARIFA= "El campo valor tarifa es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_TARIFA_VALOR= "El campo valor tarifa debe ser igual a 0";
	public static final String MENSAJE_ERROR_REQUIRED_PRIMA_MINIMA= "El campo prima minima es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_MONTO_PRIMA= "El campo monto prima debe ser igual a 0";
	public static final String MENSAJE_ERROR_REQUIRED_EDAD_MAXIMA_PERMANENCIA= "El campo edad lastima permanencia es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_POR_CAPITAL_ASEGURADO= "El campo porcentage es requerido";

	public static final String fieldNameValor_Cobertura= "cobertura";
	public static final String fieldNameValorTipo= "tipoCobertura";
	public static final String fieldNameValorMonto_Asegurado= "montoAsegurado";
	public static final String fieldNameValor_EdadMaximaIngreso= "edadMaximaIngreso";
	public static final String fieldNameValor_PorcentajeSobreCapitalAsegurado= "porcentajeSobreCapitalAsegurado";
	public static final String fieldNameValor_PrimaMinima= "primaMinima";
	public static final String fieldNameValor_EdadMaximaPermanencia= "edadMaximaPermanencia";
	public static final String fieldNameValor_Tasa= "tasa";
	public static final String fieldNameValor_Tarifa= "tarifa";
	public static final String fieldNameValor_En= "en";
	public static final String fieldNameValor_Prima= "montoPrima";

    @Override
    public void initialize(CoberturaConstraint coberturaConstraint) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	CoberturaDTO coberturaDTO1 = (CoberturaDTO) obj;

    	if (coberturaDTO1.getCobertura() == null) {
			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_COBERTURA, fieldNameValor_Cobertura);
		}
    	if (coberturaDTO1.getTipoCobertura() == null) {
			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_TIPO, fieldNameValorTipo);
		}

    	if (TipoCoberturaEnum.TASA.getId().equals(coberturaDTO1.getTipoCobertura())){
			if (coberturaDTO1.getTasa() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_TASA, fieldNameValor_Tasa);
				return false;
			}
			if (coberturaDTO1.getEn() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_EN, fieldNameValor_En);
				return false;
			}
			if (validateMount(cxt, coberturaDTO1)) return false;
			if (coberturaDTO1.getPorcentajeSobreCapitalAsegurado() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_POR_CAPITAL_ASEGURADO, fieldNameValor_PorcentajeSobreCapitalAsegurado);
				return false;
			}
			if ((coberturaDTO1.getMontoPrima().compareTo(BigDecimal.ZERO) != 0)) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_MONTO_PRIMA, fieldNameValor_Prima);
				return false;
			}
			if ((coberturaDTO1.getTarifa().compareTo(BigDecimal.ZERO) != 0)) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_TARIFA_VALOR, fieldNameValor_Tarifa);
				return false;
			}
		}

		if (TipoCoberturaEnum.TARIFA.getId().equals(coberturaDTO1.getTipoCobertura())){
			if (coberturaDTO1.getTarifa() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_TARIFA, fieldNameValor_Tarifa);
				return false;
			}
			if (coberturaDTO1.getMontoAsegurado() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_MONTO_ASEGURADO, fieldNameValorMonto_Asegurado);
				return false;
			}
			if (coberturaDTO1.getEdadMaximaIngreso() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_EDAD_MAXIMA, fieldNameValor_EdadMaximaIngreso);
				return false;
			}
			if (coberturaDTO1.getEdadMaximaPermanencia() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_EDAD_MAXIMA_PERMANENCIA, fieldNameValor_EdadMaximaPermanencia);
				return false;
			}
			if (coberturaDTO1.getPorcentajeSobreCapitalAsegurado() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_POR_CAPITAL_ASEGURADO, fieldNameValor_PorcentajeSobreCapitalAsegurado);
				return false;
			}
			if ((coberturaDTO1.getMontoPrima().compareTo(BigDecimal.ZERO) != 0)) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_MONTO_PRIMA, fieldNameValor_Prima);
				return false;
			}
			if ((coberturaDTO1.getTasa().compareTo(BigDecimal.ZERO) != 0)) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_TASA_ZERO, fieldNameValor_Tasa);
				return false;
			}
		}

		if (TipoCoberturaEnum.TRAMO.getId().equals(coberturaDTO1.getTipoCobertura())){
			if ((coberturaDTO1.getTasa().compareTo(BigDecimal.ZERO) != 0)) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_TASA_ZERO, fieldNameValor_Tasa);
				return false;
			}
			if ((coberturaDTO1.getTarifa().compareTo(BigDecimal.ZERO) != 0)) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_TARIFA_VALOR, fieldNameValor_Tarifa);
				return false;
			}
			if ((coberturaDTO1.getMontoPrima().compareTo(BigDecimal.ZERO) != 0)) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_MONTO_PRIMA, fieldNameValor_Prima);
				return false;
			}
			if (validateMount(cxt, coberturaDTO1)) return false;
			if (coberturaDTO1.getEdadMaximaPermanencia() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_EDAD_MAXIMA_PERMANENCIA, fieldNameValor_EdadMaximaPermanencia);
				return false;
			}
			if (coberturaDTO1.getPorcentajeSobreCapitalAsegurado() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_POR_CAPITAL_ASEGURADO, fieldNameValor_PorcentajeSobreCapitalAsegurado);
				return false;
			}
		}
    	return true;
    }

	private boolean validateMount(ConstraintValidatorContext cxt, CoberturaDTO coberturaDTO1) {
		if (coberturaDTO1.getMontoAsegurado() == null) {
			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_MONTO_ASEGURADO, fieldNameValorMonto_Asegurado);
			return true;
		}
		if (coberturaDTO1.getEdadMaximaIngreso() == null) {
			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_EDAD_MAXIMA, fieldNameValor_EdadMaximaIngreso);
			return true;
		}
		if (coberturaDTO1.getPrimaMinima() == null) {
			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_PRIMA_MINIMA, fieldNameValor_PrimaMinima);
			return true;
		}
		return false;
	}

	private void customMessageForValidation(ConstraintValidatorContext cxt, String message, String field) {
    	cxt.disableDefaultConstraintViolation();
    	cxt.buildConstraintViolationWithTemplate(message).addPropertyNode(field).addConstraintViolation();
    }
    
}
