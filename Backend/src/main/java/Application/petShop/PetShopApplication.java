package Application.petShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.filter.JwtFilter;
import com.service.*;
 

@SpringBootApplication(scanBasePackages="com.controller,com.service")
@EntityScan("com.model")
@EnableJpaRepositories("com.dao")
public class PetShopApplication {	

	public static void main(String[] args) {
		SpringApplication.run(PetShopApplication.class, args);
	}
	
	@Bean
	@DependsOn("userDetailsService")
	public DaoAuthenticationProvider daoAuthenticationProvider() {
	    CustomUserDetailService service=userDetailsService();
	    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    provider.setUserDetailsService(service);
	    provider.setPasswordEncoder(passwordEncoder());
	    return provider;
	}
 
	@Bean
	public CustomUserDetailService userDetailsService() {
		
	
	    return new CustomUserDetailService(); // Implement your own user details service
	}
 
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(); // Use a password encoder of your choice
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    System.out.println("invoked");
		http
	        .csrf().disable() // Disable CSRF protection
	        
	        .authorizeRequests()
	            .requestMatchers(HttpMethod.POST,"/api/login").permitAll()
	            
//	                .requestMatchers(HttpMethod.PUT,"/api/manager/register/*").hasRole("ADMIN")
	                .requestMatchers("/api/user/register").permitAll()
	                .requestMatchers("/api/admin/register").permitAll()

	                .requestMatchers("/api/admin/login").hasAnyRole("ADMIN")
	                
	                .requestMatchers(HttpMethod.POST, "/api/v1/customers/add").hasAnyRole("ADMIN")	
	                .requestMatchers(HttpMethod.GET, "/api/v1/customers").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"api/v1/customers/{id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/customers/name/{firstname}/{lastname} ").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/apiv1/customers/city/{city}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/customers/state/{state}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/customers/transactions/{id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/customers/transactions-status/{status}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/customers/no-transactions").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/customers/pets/{id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT,"/api/v1/customers/update/{id}").hasAnyRole("ADMIN")
	                
	                
	                .requestMatchers(HttpMethod.POST,"/api/v1/employees/add ").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/employees").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/employees/{employeeId} ").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/employees/name/{name}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/employees/position/{position}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT,"/api/v1/employees/update/{employeeId}").hasAnyRole("ADMIN")
	                
	                
	                .requestMatchers(HttpMethod.POST,"/api/v1/address/add ").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/address").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/address/{addressId} ").hasAnyRole("ADMIN")  
	                .requestMatchers(HttpMethod.PUT,"/api/v1/employees/update/{address_id}").hasAnyRole("ADMIN")
	                
	                
	              
	                
	                .requestMatchers(HttpMethod.POST,"/api/v1/suppliers/add").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/suppliers").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/suppliers/{id} ").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/suppliers/name/{name}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/suppliers/city/{city}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/suppliers/state/{state}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT,"/api/suppliers/update/{id}").hasAnyRole("ADMIN")
	            
	                
	                .requestMatchers(HttpMethod.GET,"/api/v1/transaction_history ").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.POST,"/api/v1/transaction_history/add").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/transaction_history/{transaction_id} ").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/transaction_history/successful").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/v1/transaction_history/failed").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/v1/transaction_history/by_customer/{customer_id}").hasAnyRole("ADMIN")
	           
	                
	                .requestMatchers(HttpMethod.POST,"/api/v1/serices/add").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/services/{id} ").hasAnyRole("USER","ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/services").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/v1/services/available").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/v1/services/unavailable").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.PUT,"/api/v1/services/update/{serviceId}").hasAnyRole("ADMIN")
	                
	      
	                	
	                
	                .requestMatchers(HttpMethod.POST,"/api/v1/vaccinations/add ").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/vaccinations ").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/v1/vaccinations/{vaccination_id}").hasAnyRole("USER","ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/vaccinations/available ").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/v1/vaccinations/unavailable").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.PUT,"/api/v1/vaccinations/update/{vaccination_id}").hasAnyRole("ADMIN")
	                
	                
	                .requestMatchers(HttpMethod.POST,"/api/v1/pets/add ").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/pets").hasAnyRole("USER","ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/pets/{pet_id}").hasAnyRole("USER","ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/pets/category/{category} ").hasAnyRole("USER","ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/pets/grooming_services/{pet_id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/pets/vaccinations/{pet_id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/pets/food_info/{pet_id} ").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/pets/suppliers/{pet_id} ").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/pets/transaction_history/{pet_id} ").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT,"/api/v1/pets/update/{pet_id} ").hasAnyRole("ADMIN")
	        
	                
	            
	                
	                .requestMatchers(HttpMethod.POST,"/api/v1/categories/add").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/categories").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/v1/categories/{category_id}").hasAnyRole("USER","ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/categories/name/{category_name}").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.PUT,"/api/v1/categories/update/{category_id}").hasAnyRole("ADMIN")
	                
	                
	                
	                .requestMatchers(HttpMethod.POST,"/api/v1/pet_foods/add").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/pet_foods").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/v1/pet_foods/{food_id}").hasAnyRole("USER","ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/v1/pet_foods/search").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/v1/pet_foods/food_type/{type}").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/v1/pet_foods/brand/{brand_name}").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.PUT,"/api/v1/pet_foods/update/{food_id} ").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT,"/api/v1/pet_foods/quantity/{food_id}").hasAnyRole("ADMIN")
	               
	              
	            .anyRequest().authenticated()
	            .and()
	             .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
	           
	        .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	            .sessionManagement().disable()
	            	
	            
	                
	        .authenticationManager(new ProviderManager(daoAuthenticationProvider()));
	        
	
	        
	    return http.build();
	}

}
