package connectedCars.carSimulator.carSimulator1;


import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class CarSimulator1 {

    public static void main(String[] args)
    {

        SpringApplication.run(CarSimulator1.class);
    }

    @Bean
    public CarSimulator carSimulator() throws InterruptedException, URISyntaxException {
        String journeysFile = "src/main/java/connectedCars/carSimulator/carSimulator1/file.csv";
        int VIN = 1;
        CarSimulator carSimulator = new CarSimulator(journeysFile,VIN);
        carSimulator.processInputFile();
        return carSimulator;
    }

    
}
