package org.info.info303.dao;

import org.info.info303.entities.Valeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValeurRepository extends JpaRepository<Valeur, Long>{

}
