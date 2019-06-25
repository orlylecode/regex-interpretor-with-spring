package org.info.info303.dao;


import org.info.info303.entities.Donnees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonneesRepository extends JpaRepository<Donnees, Long>{

}
