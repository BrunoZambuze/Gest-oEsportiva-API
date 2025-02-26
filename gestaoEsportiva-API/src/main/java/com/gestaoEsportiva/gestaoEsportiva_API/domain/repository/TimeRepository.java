package com.gestaoEsportiva.gestaoEsportiva_API.domain.repository;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long>, TimeRepositoryQueries {
}
