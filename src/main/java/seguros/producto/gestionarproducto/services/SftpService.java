package seguros.producto.gestionarproducto.services;


import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import seguros.producto.gestionarproducto.dto.RutaFichero;
import seguros.producto.gestionarproducto.servicesImpl.SftpException;
	

public interface SftpService {	
	
	
	
    public  List<RutaFichero> uploadFile(List<MultipartFile> files, String remoteDir,String token) throws SftpException ;
    public  boolean isValid(List<MultipartFile> files,List<String> validExtension) throws SftpException ;
    public  byte[] downloadFile(String remotePath,String token) throws SftpException ;

}
