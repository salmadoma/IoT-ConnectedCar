package connectedCars.carSimulator.carSimulator4;


import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class CarSimulator4 {

    public static void main(String[] args)
    {

        SpringApplication.run(CarSimulator4.class);
    }

    @Bean
    public CarSimulator carSimulator() throws InterruptedException, URISyntaxException {
        String journeysFile = "src/main/java/connectedCars/carSimulator/carSimulator4/file.csv";
        int VIN = 4;
        CarSimulator carSimulator = new CarSimulator(journeysFile,VIN);
        carSimulator.processInputFile();
        return carSimulator;
    }

    
}
