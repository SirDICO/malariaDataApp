package com.dico.MalariaDataApp.repositories;

import com.dico.MalariaDataApp.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

}
