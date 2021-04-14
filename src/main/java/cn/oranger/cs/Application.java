package cn.oranger.cs;

import cn.oranger.cs.broker.InitialMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Oranger
 * @date 2020/12/29
 */
@SpringBootApplication
public class Application {
    @Autowired
    private InitialMatrix initialMatrix;

    public static void main(String[] args) {

        // Spring应用启动起来
        SpringApplication.run(Application.class,args);
    }
}