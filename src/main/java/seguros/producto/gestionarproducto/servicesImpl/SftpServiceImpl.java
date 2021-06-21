package seguros.producto.gestionarproducto.servicesImpl;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import seguros.producto.gestionarproducto.configuration.Properties;
import seguros.producto.gestionarproducto.dto.RutaFichero;
import seguros.producto.gestionarproducto.services.SftpService;
	
@Service
public class SftpServiceImpl implements SftpService{

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private Properties properties;
	
	private static final List<String> validExtension=  Arrays.asList("doc","pdf");
	private static final String MSG_EXTENSION_NOT_VALID= "Tipo de fichero no permitido";
	private static final String QUERY_PARAM_REMOTE_PATH="{remotePath}";

	@Override
    public   List<RutaFichero>  uploadFile(List<MultipartFile> files, String remoteDir,String token) throws SftpException {
		
		 List<RutaFichero> lista=new ArrayList<>();
		
	    try {
	    
	    	if(isValid(files, validExtension)) {
	    		String urlUpload= properties.getURL_UPLOAD_TO_SFTP()==null?"":properties.getURL_UPLOAD_TO_SFTP();
	    	
	    		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
	    		
	    		for(MultipartFile file : files) {
	    			final ByteArrayResource byteArrayResource = new ByteArrayResource(file.getBytes()) {
	    				@Override
	    				public String getFilename() {   
	    					return file.getOriginalFilename();
	    				}
	    			};
	    			
	    			body.add("files",byteArrayResource);
	    		}
	    		    
	    		HttpHeaders headers = new HttpHeaders();
	    		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	    		headers.setBearerAuth(token);
	    		HttpEntity<MultiValueMap<String, Object>> requestEntity	  = new HttpEntity<>(body, headers);

	    		String serverUrl = urlUpload.replace(QUERY_PARAM_REMOTE_PATH, remoteDir);

	    		
	    		ResponseEntity<RutaFichero[]> response = restTemplate.postForEntity(serverUrl, requestEntity, RutaFichero[].class);
	    	
	    		if(response.getBody()!=null) {
	    		   lista= Arrays.asList(response.getBody());   
	    		}
	    	
	    	}
	    	else {
	    		SftpException ePass = new SftpException();
				ePass.setConcreteException(ePass);
				ePass.setErrorMessage(MSG_EXTENSION_NOT_VALID);
				ePass.setDetail(MSG_EXTENSION_NOT_VALID);
				throw ePass;
	    	}
	
	           
		} 
	    catch (SftpException e) {
	        throw e;
	     }
	    catch (Exception e) {
	    	    SftpException ex = new SftpException(e);
		        throw ex;
		}
	    return lista;
	
	}
	
	
	@Override
	public byte[] downloadFile(String remotePath,String token) throws SftpException {
		
		byte[] file=null;
		
	    try  {
	    	    String urlDonwload= properties.getURL_DOWNLOAD_FROM_SFTP()==null?"":properties.getURL_DOWNLOAD_FROM_SFTP();
	    		String serverUrl = urlDonwload.replace(QUERY_PARAM_REMOTE_PATH, remotePath);
	    		
	    		HttpHeaders headers = new HttpHeaders();
	    		headers.setBearerAuth(token);
	    		ResponseEntity<byte[]> response = restTemplate.exchange(serverUrl,HttpMethod.GET, new HttpEntity<Object>(headers),byte[].class);
	    	
	    		file= response.getBody();   	    			
	           
		} 
	    catch (SftpException e) {
	        throw e;
	     }
	    catch (Exception e) {
	    	    SftpException ex = new SftpException(e);
		        throw ex;
		}
	    return file;
	}
	

	@Override
	public boolean isValid(List<MultipartFile> files, List<String> validExtension) throws SftpException {
		boolean isValid=true;
	
		Optional<MultipartFile> optional  = files.stream().
				filter(x -> !validExtension.contains( FilenameUtils.getExtension(x.getOriginalFilename()) ) )
                .findFirst();
		
		if(optional.isPresent()) {
			isValid=false;
		}
		
		return isValid;
		
		
	}
	

}
