package seguros.producto.gestionarproducto;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import seguros.producto.gestionarproducto.configuration.JWTAuthorizationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	  private static final String[] AUTH_WHITELIST = {
			  "/v2/api-docs",
              "/configuration/ui",
              "/swagger-resources/**",
              "/configuration/security",
              "/swagger-ui.html",
              "/webjars/**",
              "/health",
              "/refresh"
	    };
	  
	@Override
	   protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
		//	.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers(AUTH_WHITELIST).permitAll()
		//	.anyRequest().authenticated()
			.and()
			.headers()	        
			.addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy","default-src 'self'; script-src 'self' 'unsafe-eval' cdn.pendo.io; connect-src 'self' app.pendo.io; img-src 'self' data: cdn.pendo.io app.pendo.io; style-src 'self' 'unsafe-inline' app.pendo.io cdn.pendo.io"))
			.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials","true"))
			.httpStrictTransportSecurity()
			.includeSubDomains(true)
			.maxAgeInSeconds(31536000);
			http.cors();
	   }
	
	
	
}