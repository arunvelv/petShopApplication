package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VaccinationsDAO;
import com.model.Vaccinations;

@Service
public class VaccinationsService {

	@Autowired
	VaccinationsDAO VaccinationsDAO;
	
	public void addVaccinations(Vaccinations v)
	{
		VaccinationsDAO.save(v);
	}
	
	public Vaccinations getById(int VaccinationId)
	{
		Vaccinations vac = VaccinationsDAO.findById(VaccinationId).get();
		return vac;
	}
	
	
	public List<Vaccinations> findAvailableVaccinations()
	
	{
		List<Vaccinations> list = VaccinationsDAO.findByAvailable(true);
		return list;
	}
	
	public List<Vaccinations> findUnavailableVaccinations()
	{
		List<Vaccinations> list = VaccinationsDAO.findByAvailable(false);
		return list;
	}
	public void updateVaccinations(int vaccinationId, Vaccinations vaccinations)
	{
		Vaccinations vac = VaccinationsDAO.findById(vaccinationId).get();
		vac.setName(vaccinations.getName());
		vac.setDescription(vaccinations.getDescription());
		vac.setPrice(vaccinations.getPrice());
		vac.setAvailable(vaccinations.isAvailable());
		
		VaccinationsDAO.save(vac);
		
		
	}
	
	public List<Vaccinations> getAll()
	{
		List<Vaccinations> list = VaccinationsDAO.findAll();
		return list;
	}
}
