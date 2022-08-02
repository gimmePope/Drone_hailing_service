package com.pocosoft.drone.service.db.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocosoft.drone.service.entity.Medication;
import com.pocosoft.drone.service.repository.MedicationRepository;

@Service
public class MedicationRepoService {
	
	@Autowired
	MedicationRepository medRepo;
	
	public void testEntitySave() throws IOException
	{
		Medication med = new Medication();
		med.setCode("POL_C");
		med.setName("POLYCROL");
		File file = new File("/Users/olujimio/Documents/Fiyifoluwa.jpeg");
		FileInputStream fl = new FileInputStream(file);
		byte[] arr = new byte[(int)file.length()];
		
		fl.read(arr);
		byte [] encode = Base64.getEncoder().encode(arr);
		System.out.println(new String(encode));
		
		med.setImage(arr);
		
		
		
		medRepo.save(med);
		
	}
	
	public Medication getMedicationById(Long id)
	{
		return medRepo.findById(id).orElse(null);
	}
	
	public Medication RegisterNewMedication(Medication med)
	{
		Medication aMedication = medRepo.save(med);
		
		return aMedication;
	}
	
	
	
	
	

}
