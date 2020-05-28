package pl.seb.czech.ilegal.front.backend.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    
}
