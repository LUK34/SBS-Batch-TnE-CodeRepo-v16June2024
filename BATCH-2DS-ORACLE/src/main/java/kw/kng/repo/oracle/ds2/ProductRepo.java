package kw.kng.repo.oracle.ds2;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.oracle.ds2.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
