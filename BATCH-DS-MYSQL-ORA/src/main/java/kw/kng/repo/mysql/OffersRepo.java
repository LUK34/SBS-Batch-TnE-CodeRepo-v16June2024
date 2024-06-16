package kw.kng.repo.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.mysql.Offers;

public interface OffersRepo extends JpaRepository<Offers, Integer> 
{

}
