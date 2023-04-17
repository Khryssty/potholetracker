package com.techelevator;

import com.techelevator.model.FileStoragePropertiesDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStoragePropertiesDto.class
})

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
