package com.pocosoft.drone.service.ws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pocosoft.drone.service.db.service.MedicationRepoService;
import com.pocosoft.drone.service.entity.Medication;

@RestController
@RequestMapping(path= "/medication/service")
public class MedicationService {
	@Value("${image.filePath}")
	String filePath;
	@Autowired
	MedicationRepoService medRepoService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addMedication(@Valid @RequestBody Medication med, BindingResult error)
	{
		if (error.hasErrors())
		{
			System.err.println("Error Found");
			return ResponseEntity.badRequest().body(error.getFieldErrors().get(0).getDefaultMessage());
		}
		medRepoService.RegisterNewMedication(med);
		return new ResponseEntity<String>("Medication Saved",HttpStatus.CREATED);
	}
	
	@GetMapping("/run/test")
	public String runTest()
	{
		try {
			medRepoService.testEntitySave();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Done, please check DB";
	}
	
	@GetMapping("/getImage/{medicationId}")
	public ResponseEntity<String> getImageById(@PathVariable("medicationId") Long id)
	{
		Medication med = medRepoService.getMedicationById(id);
		if(med == null)
			return ResponseEntity.notFound().build();
		else
		{
			byte [] imageBytes = med.getImage();
			
			File file = new File(filePath + File.separator + id+ ".jpeg");
			if(file.exists())
			{
				file.delete();
			}
			try {
				 
	         
	            OutputStream os = new FileOutputStream(file);
	            os.write(imageBytes);
	            os.close();
	            return ResponseEntity.ok("Image Saved to Image Directory Successfully");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return new ResponseEntity<String> ("Failed to Save file to Output directory", HttpStatus.FAILED_DEPENDENCY);
			}
		}
	}

}
