package com.dico.MalariaDataApp.services;

import com.dico.MalariaDataApp.models.Supplier;
import com.dico.MalariaDataApp.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
	@Autowired
	private DataRepository dataRepository;

	//Get All Suppliers
	public List<Supplier> findAll(){
		return dataRepository.findAll();
	}

	//Get Supplier By Id
	public Supplier findById(int id) {
		return dataRepository.findById(id).orElse(null);
	}

	//Delete Supplier
	public void deleteById(int id) {
		dataRepository.deleteById(id);
	}

	//Update Supplier
	public void save(Supplier supplier) {
		dataRepository.save(supplier);
	}

}
