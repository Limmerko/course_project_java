package vlsu.pri117.mep.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloundinaryConfig {

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "konoha",
                "api_key", "545317982164621",
                "api_secret", "6XWKHqBUIrNLIwVlEp6B780tLJY"
        ));
    }
}
