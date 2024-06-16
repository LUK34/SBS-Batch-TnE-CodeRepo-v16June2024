package kw.kng.repo.mysql.ds2;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.mysql.ds2.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
