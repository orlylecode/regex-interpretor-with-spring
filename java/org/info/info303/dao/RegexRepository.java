package org.info.info303.dao;

import org.info.info303.entities.Regex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegexRepository extends JpaRepository<Regex, Long>{

}
