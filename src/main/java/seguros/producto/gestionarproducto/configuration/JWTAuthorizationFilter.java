package seguros.producto.gestionarproducto.configuration;

import static seguros.producto.gestionarproducto.utils.Constants.HEADER_AUTHORIZACION_KEY;
import static seguros.producto.gestionarproducto.utils.Constants.SUPER_SECRET_KEY;
import static seguros.producto.gestionarproducto.utils.Constants.TOKEN_BEARER_PREFIX;
import static  seguros.producto.gestionarproducto.utils.Constants.PAYLOAD_ROLES;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.google.gson.JsonObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.utils.Utils;




public class JWTAuthorizationFilter extends OncePerRequestFilter  {



	private static final String MSG_NOTFOUND_HEADER_AUTHORIZATION = "No se encontró header Authorization";
	private static final String MSG_UNAUTHORIZED = "No autorizado";
	private static final String MSG_INVALID_TOKEN = "El token no es válido";
	private static final String FIELD_ERROR="error";
	private static final String MSG_ROLES_NOT_PRESENT =  "No se pudo indentificar los roles";
	private static final String FIELD_SUBJECT="subject";
	private static final String CHARACTER_ENCODING = "utf-8";

	 
	 private static final String[] AUTH_WHITELIST_FILTER = {
			"/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars",
            "/health",
            "/refresh"
	    };
	 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		try {
			
			if (existeJWTToken(request)) {
				Claims claims = validateToken(request);
				if (claims.get(PAYLOAD_ROLES) != null) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					response.setCharacterEncoding(CHARACTER_ENCODING);
					ExceptionResponse errorResponse = new ExceptionResponse();
					errorResponse.setErrorMessage(MSG_UNAUTHORIZED);
					errorResponse.setRequestedURI(request.getRequestURI());
					JsonObject details = new JsonObject();
					details.addProperty(FIELD_ERROR, MSG_ROLES_NOT_PRESENT);
					details.addProperty(FIELD_SUBJECT, MSG_UNAUTHORIZED);
					errorResponse.setDetails(details);
		            writer.write(Utils.convertObjectToJson(errorResponse));
		            writer.flush();
		            writer.close();
				}
			} else {				
					SecurityContextHolder.clearContext();
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					response.setCharacterEncoding(CHARACTER_ENCODING);
					ExceptionResponse errorResponse = new ExceptionResponse();
					errorResponse.setErrorMessage(MSG_UNAUTHORIZED);
					errorResponse.setRequestedURI(request.getRequestURI());
					JsonObject details = new JsonObject();
					details.addProperty(FIELD_ERROR, MSG_NOTFOUND_HEADER_AUTHORIZATION);
					details.addProperty(FIELD_SUBJECT, MSG_UNAUTHORIZED);
					errorResponse.setDetails(details);
		            writer.write(Utils.convertObjectToJson(errorResponse));
		            writer.flush();
		            writer.close();
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setCharacterEncoding(CHARACTER_ENCODING);
			ExceptionResponse errorResponse = new ExceptionResponse();
			errorResponse.setErrorMessage(MSG_INVALID_TOKEN);
			errorResponse.setRequestedURI(request.getRequestURI());
			JsonObject details = new JsonObject();
			details.addProperty(FIELD_ERROR, e.getMessage());
			details.addProperty(FIELD_SUBJECT, MSG_INVALID_TOKEN);
			errorResponse.setDetails(details);			
            writer.write(Utils.convertObjectToJson(errorResponse));
          
		}
		finally {
			    writer.flush();
	            writer.close();
		}
	}	

	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER_AUTHORIZACION_KEY).replace(TOKEN_BEARER_PREFIX, "");
		return Jwts.parser().setSigningKey(SUPER_SECRET_KEY.getBytes()).parseClaimsJws(jwtToken).getBody();
	}


	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List<String>) claims.get(PAYLOAD_ROLES);

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList()));
		
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	private boolean existeJWTToken(HttpServletRequest request) {
		String authenticationHeader = request.getHeader(HEADER_AUTHORIZACION_KEY);
		if (authenticationHeader == null || !authenticationHeader.startsWith(TOKEN_BEARER_PREFIX))
			return false;
		return true;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	
	return Utils.matcher(AUTH_WHITELIST_FILTER, request.getRequestURI());
	}
	
	
	

	
	
}
