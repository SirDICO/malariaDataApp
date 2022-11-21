package com.dico.MalariaDataApp.repositories;

import com.dico.MalariaDataApp.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Supplier, Integer> {

}
