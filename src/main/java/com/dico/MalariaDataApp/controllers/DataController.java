package com.dico.MalariaDataApp.controllers;

import com.dico.MalariaDataApp.models.Supplier;
import com.dico.MalariaDataApp.services.CountryService;
import com.dico.MalariaDataApp.services.StateService;
import com.dico.MalariaDataApp.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DataController {

	@Autowired	private DataService dataService;
	@Autowired	private CountryService countryService;
	@Autowired	private StateService stateService;

	public Model addModelAttributes(Model model){
		model.addAttribute("suppliers", dataService.findAll());
		model.addAttribute("countries", countryService.findAll());
		model.addAttribute("states", stateService.findAll());
		return model;
	}

	@GetMapping("/deskofficers/data")
	public String findAll(Model model){
		addModelAttributes(model);
		return "/deskofficers/data";
	}

	@GetMapping("/deskofficers/dataAdd")
	public String addSupplier(Model model){
		model.addAttribute("countries", countryService.findAll());
		return "deskofficers/dataAdd";
	}

	//The op parameter is either Edit or Details
	@GetMapping("/deskofficers/data/{op}/{id}")
	public String editSupplier(@PathVariable Integer id, @PathVariable String op, Model model){
		Supplier supplier = dataService.findById(id);
		model.addAttribute("supplier", supplier);
		addModelAttributes(model);
		return "/deskofficers/data"+ op; //returns supplierEdit or supplierDetails
	}

	@PostMapping("/deskofficers/data")
	public String save(Supplier supplier) {
		dataService.save(supplier);
		return "redirect:/deskofficers/data";
	}

	@RequestMapping(value="/deskofficers/data/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteById(@PathVariable Integer id) {
		dataService.deleteById(id);
		return "redirect:/deskofficers/data";
	}

}
