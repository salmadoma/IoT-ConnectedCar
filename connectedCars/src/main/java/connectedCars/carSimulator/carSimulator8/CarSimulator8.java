package connectedCars.carSimulator.carSimulator8;


import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class CarSimulator8 {

    public static void main(String[] args)
    {

        SpringApplication.run(CarSimulator8.class);
    }

    @Bean
    public CarSimulator carSimulator() throws InterruptedException, URISyntaxException {
        String journeysFile = "src/main/java/connectedCars/carSimulator/carSimulator8/file.csv";
        int VIN = 8;
        CarSimulator carSimulator = new CarSimulator(journeysFile,VIN);
        carSimulator.processInputFile();
        return carSimulator;
    }

    
}
