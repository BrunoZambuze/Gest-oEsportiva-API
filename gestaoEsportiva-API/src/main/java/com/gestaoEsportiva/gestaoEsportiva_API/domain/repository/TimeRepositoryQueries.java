package com.gestaoEsportiva.gestaoEsportiva_API.domain.repository;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Time;

public interface TimeRepositoryQueries {

    public Time findByIdOrElseThrowException(Long timeId);

}
