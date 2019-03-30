package connectedCars.carSimulator.carSimulator5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Date;

public class CarSimulator {

	String journeysFile;
    int VIN;

    public CarSimulator(String journeysFile,int VIN) {
        this.journeysFile = journeysFile;
        this.VIN = VIN;
    }
    public void postData(String line) throws URISyntaxException{
        final String baseUrl = "http://localhost:7070";
        URI uri = new URI(baseUrl); 
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(uri, line, String.class);
     
        System.out.println(result);
      }
    public int rand()
    {
    	int minimum = 7000;	//7 second 
    	int maximum = 12000;	//12 second
    	int randomNum;
    	Random rand = new Random();
    	randomNum = minimum + rand.nextInt((maximum - minimum) + 1);
    	return randomNum;
    }
    public String timestamp()
    {
    	 Date date= new Date();
    	 long timestamp = date.getTime();
    	 return Long.toString(timestamp);

    }
    public void processInputFile() throws InterruptedException, URISyntaxException{

        //reading file line by line in Java using BufferedReader
        FileInputStream fis = null;
        BufferedReader reader = null;

        try {
            fis = new FileInputStream(journeysFile);
            reader = new BufferedReader(new InputStreamReader(fis));

            System.out.println("Reading File For VIN:"+VIN);

            String line = reader.readLine(); //	overwrite/skip file header
            String base = VIN + "," + timestamp() + ",";
            line = reader.readLine();
            while(line != null){
            	System.out.println(base + line);	//to be changed to send to port
                postData(base + line);
                Thread.sleep(rand());		//read 1 line each 10 second
                base = VIN + "," + timestamp() + ",";
                line = reader.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CarSimulator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CarSimulator.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(CarSimulator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}
