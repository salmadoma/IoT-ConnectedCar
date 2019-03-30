package connectedCars.carSimulator.carSimulator2;


import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class CarSimulator2 {

    public static void main(String[] args)
    {

        SpringApplication.run(CarSimulator2.class);
    }

    @Bean
    public CarSimulator carSimulator() throws InterruptedException, URISyntaxException {
        String journeysFile = "src/main/java/connectedCars/carSimulator/carSimulator2/file.csv";
        int VIN = 2;
        CarSimulator carSimulator = new CarSimulator(journeysFile,VIN);
        carSimulator.processInputFile();
        return carSimulator;
    }

    
}
