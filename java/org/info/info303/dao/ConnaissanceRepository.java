package org.info.info303.dao;

import org.info.info303.entities.Connaissance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnaissanceRepository extends JpaRepository<Connaissance, Long>{

}
