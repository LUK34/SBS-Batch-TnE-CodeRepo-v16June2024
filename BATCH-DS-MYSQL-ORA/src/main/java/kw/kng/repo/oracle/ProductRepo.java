package kw.kng.repo.oracle;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.oracle.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> 
{

}
