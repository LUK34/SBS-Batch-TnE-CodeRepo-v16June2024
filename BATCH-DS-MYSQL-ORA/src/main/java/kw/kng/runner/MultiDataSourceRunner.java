package kw.kng.runner;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import kw.kng.entities.mysql.Offers;
import kw.kng.entities.oracle.Product;
import kw.kng.repo.mysql.OffersRepo;
import kw.kng.repo.oracle.ProductRepo;

@Component
public class MultiDataSourceRunner  implements CommandLineRunner
{

	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private OffersRepo orepo;
	
	@Override
	public void run(String... args) throws Exception 
	{
		/*
		System.out.println("==================================================================================");
		//save objects
		prepo.saveAll(Arrays.asList(new Product("table",100.0,60000.0),
									new Product("chair",10.0,60000.0),
									new Product("sofa",11.0,60000.0)
									));
		System.out.println("Products are saved");
		System.out.println("==================================================================================");
		orepo.saveAll(Arrays.asList(new Offers("Buy-1-Get-1","B1G1",100.0,LocalDateTime.of(2023,11,20,10,11)),
									new Offers("Buy-1-Get-2","B1G2",200.0,LocalDateTime.of(2023,11,20,10,11)),
									new Offers("Buy-2-Get-2","B2G2",100.0,LocalDateTime.of(2023,11,20,10,11))
						));
		System.out.println("Offers are saved");
		System.out.println("==================================================================================");
		*/
		System.out.println("Display Product Records:");
		prepo.findAll().forEach(System.out::println);
		System.out.println("==================================================================================");
		System.out.println("Display Offer Records:");
		orepo.findAll().forEach(System.out::println);
		System.out.println("==================================================================================");
	}

}
