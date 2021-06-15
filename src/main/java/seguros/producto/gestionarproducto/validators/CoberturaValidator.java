package seguros.producto.gestionarproducto.validators;

import seguros.producto.gestionarproducto.dto.CoberturaDTO;
import seguros.producto.gestionarproducto.utils.TipoCoberturaEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CoberturaValidator implements ConstraintValidator<CoberturaConstraint, Object> {

	public static final String MENSAJE_ERROR_REQUIRED_COBERTURA= "El campo cobertura es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_TIPO= "El campo tipo es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_MONTO_ASEGURADO= "El campo monto asegurado es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_EN= "El campo EN es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_EDAD_MAXIMA= "El campo edad maxima es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_TASA= "El campo valor tasa es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_TARIFA= "El campo valor tarifa es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_PRIMA_MINIMA= "El campo prima minima es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_EDAD_MAXIMA_PERMANENCIA= "El campo edad lastima permanencia es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_POR_CAPITAL_ASEGURADO= "El campo porcentage es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_PARENTESCO= "El campo parentesco es requerido";

	public static final String fieldNameValor_Cobertura= "cobertura";
	public static final String fieldNameValorTipo= "tipoCobertura";
	public static final String fieldNameValorMonto_Asegurado= "montoAsegurado";
	public static final String fieldNameValor_EdadMaximaIngreso= "edadMaximaIngreso";
	public static final String fieldNameValor_PorcentajeSobreCapitalAsegurado= "porcentajeSobreCapitalAsegurado";
	public static final String fieldNameValor_Parentesco= "paraParentesco";
	public static final String fieldNameValor_PrimaMinima= "primaMinima";
	public static final String fieldNameValor_EdadMaximaPermanencia= "edadMaximaPermanencia";
	public static final String fieldNameValor_Tasa= "tasa";
	public static final String fieldNameValor_Tarifa= "tarifaValor";
	public static final String fieldNameValor_En= "en";


    @Override
    public void initialize(CoberturaConstraint coberturaConstraint) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	CoberturaDTO coberturaDTO = (CoberturaDTO) obj;

    	if (coberturaDTO.getCobertura() == null) {
			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_COBERTURA, fieldNameValor_Cobertura);
		}
    	if (coberturaDTO.getTipoCobertura() == null) {
			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_TIPO, fieldNameValorTipo);
		}

    	if (TipoCoberturaEnum.TASA.getId().equals(coberturaDTO.getTipoCobertura())){
			if (coberturaDTO.getTasa() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_TASA, fieldNameValor_Tasa);
				return false;
			}
			if (coberturaDTO.getEn() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_EN, fieldNameValor_En);
				return false;
			}
			if (coberturaDTO.getMontoAsegurado() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_MONTO_ASEGURADO, fieldNameValorMonto_Asegurado);
				return false;
			}
			if (coberturaDTO.getEdadMaximaIngreso() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_EDAD_MAXIMA, fieldNameValor_EdadMaximaIngreso);
				return false;
			}
			if (coberturaDTO.getPrimaMinima() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_PRIMA_MINIMA, fieldNameValor_PrimaMinima);
				return false;
			}
			if (coberturaDTO.getPorcentajeSobreCapitalAsegurado() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_POR_CAPITAL_ASEGURADO, fieldNameValor_PorcentajeSobreCapitalAsegurado);
				return false;
			}
		}

		if (TipoCoberturaEnum.TARIFA.getId().equals(coberturaDTO.getTipoCobertura())){
			if (coberturaDTO.getTarifaValor() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_TARIFA, fieldNameValor_Tarifa);
				return false;
			}
			if (coberturaDTO.getMontoAsegurado() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_MONTO_ASEGURADO, fieldNameValorMonto_Asegurado);
				return false;
			}
			if (coberturaDTO.getEdadMaximaIngreso() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_EDAD_MAXIMA, fieldNameValor_EdadMaximaIngreso);
				return false;
			}
			if (coberturaDTO.getPrimaMinima() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_PRIMA_MINIMA, fieldNameValor_PrimaMinima);
				return false;
			}
			if (coberturaDTO.getEdadMaximaPermanencia() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_EDAD_MAXIMA_PERMANENCIA, fieldNameValor_EdadMaximaPermanencia);
				return false;
			}
			if (coberturaDTO.getPorcentajeSobreCapitalAsegurado() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_POR_CAPITAL_ASEGURADO, fieldNameValor_PorcentajeSobreCapitalAsegurado);
				return false;
			}
		}

		if (TipoCoberturaEnum.TRAMO.getId().equals(coberturaDTO.getTipoCobertura())){
			if (coberturaDTO.getMontoAsegurado() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_MONTO_ASEGURADO, fieldNameValorMonto_Asegurado);
				return false;
			}
			if (coberturaDTO.getEdadMaximaIngreso() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_EDAD_MAXIMA, fieldNameValor_EdadMaximaIngreso);
				return false;
			}
			if (coberturaDTO.getPrimaMinima() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_PRIMA_MINIMA, fieldNameValor_PrimaMinima);
				return false;
			}
			if (coberturaDTO.getEdadMaximaPermanencia() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_EDAD_MAXIMA_PERMANENCIA, fieldNameValor_EdadMaximaPermanencia);
				return false;
			}
			if (coberturaDTO.getPorcentajeSobreCapitalAsegurado() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_POR_CAPITAL_ASEGURADO, fieldNameValor_PorcentajeSobreCapitalAsegurado);
				return false;
			}
			if (coberturaDTO.getParaParentesco() == null) {
				customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_PARENTESCO, fieldNameValor_Parentesco);
				return false;
			}
		}
    	return true;
    }
    
    private void customMessageForValidation(ConstraintValidatorContext cxt, String message, String field) {
    	cxt.disableDefaultConstraintViolation();
    	cxt.buildConstraintViolationWithTemplate(message).addPropertyNode(field).addConstraintViolation();
    }
    
}
