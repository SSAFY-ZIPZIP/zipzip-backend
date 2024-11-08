package org.ssafy.zipzipapiapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.ssafy.zipzipapiapp","org.ssafy.zipzipkakaoclient","org.ssafy.zipzipmysqldomain",})
public class ZipzipAppApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipzipAppApiApplication.class, args);
    }
}
