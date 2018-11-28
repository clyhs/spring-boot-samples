package org.abigfish.helloworld;

import org.abigfish.helloworld.properties.HomeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner
{
	@Autowired
    private HomeProperties homeProperties;
	
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n" + homeProperties.toString());
        System.out.println();
	}
}
