package seguros.producto.gestionarproducto.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component("generalProps")
@Data
@ConfigurationProperties(prefix="general")
public class Properties {

	
	private String KAFKA_SSL_TRUSTSTORE_PATH_KEY;
	
	private String KAFKA_SSL_TRUSTSTORE_PALABRAPASE_KEY;
	
	private String KAFKA_SSL_TRUSTSTORE_PATH_VALUE;
	
	private String KAFKA_SSL_TRUSTSTORE_PALABRAPASE_VALUE;
	
	private String BOOTSTRAP_SERVERS_KAFKA;	
	
	private String KAFKA_CONSUMER_STATE_GROUP_ID;
	
	private String KAFKA_CONSUMER_RULES_GROUP_ID;
	
	private String KAFKA_SECURITY_PROTOCOL_KEY;
	
	private String KAFKA_SECURITY_PROTOCOL_VALUE;
	
	private String TOPIC_FLOW_STATE_CARGOABONO;
	
	private String TOPIC_FLOW_TRACK_CARGOABONO;	
	
	private int BATCH_SIZE_CONFIG;
	
	private int BUFFER_MEMORY_CONFIG;
	
	private int LINGER_MS_CONFIG;
	
	private String APP_NAME;
	
	private String PREVIOUS_APP_MONTHLY;
		
	private int pageSize;
	
	private String KAFKA_PRODUCER_CLIENT_ID_FLOW_TRACK;
	
	private String KAFKA_PRODUCER_CLIENT_ID_FLOW_STATE;
		
	private String PROCESS_NAME_BUSSINESS_RULE;	
	
	private String PREFIX_PARAMETER_MONTH;
	
	private String PREFIX_PARAMETER_YEAR;
	
	private String PREFIX_PARAMETER_CALLER;
	
	private String PREFIX_PARAMETER_CYCLE;
	
	private String secret;
	
	private Long READ_TIMEOUT;
	
	private Long CONNECTION_TIMEOUT;
	
	private String url_encrypt;
	
	private String url_decrypt;
	
	private String ROLE_FUNCIONAL;
	
	private String ROLE_APROBADOR;
	
	private String ROLE_CONTINUIDAD_OPERATIVA;
	
		 
}