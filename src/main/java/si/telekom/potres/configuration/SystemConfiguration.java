package si.telekom.potres.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.time.Duration;

@Configuration
public class SystemConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate rt;

//        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

        rt = builder
                .connectTimeout(Duration.ofSeconds(60))
                .readTimeout(Duration.ofSeconds(60))
                .requestFactory
                        (
                                () ->
                                new SimpleClientHttpRequestFactory() {
                                    @Override
                                    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
                                        if(connection instanceof HttpsURLConnection ){
                                            ((HttpsURLConnection) connection).setHostnameVerifier((hostname, session) -> true);
                                        }
                                        super.prepareConnection(connection, httpMethod);
                                    }
                                }

                        )
                .build()
        ;
        return rt;
    }

}


