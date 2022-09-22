package edu.miu.post;

import eye2web.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PostApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }

//    Factory method to create bean
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
