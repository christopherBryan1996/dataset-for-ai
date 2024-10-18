package com.artek.dataset.number;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api")
@CrossOrigin(origins= {"*"})
@RestController
public class NumberRestController {
	
	private int[] numbers= {28,28,28,28,28,28,28,28,28,28};
	
	@PostMapping("/numbers/{number}")
	public ResponseEntity<Object> createi(@RequestParam("file") MultipartFile image,@PathVariable Integer number){
		
		if(!image.isEmpty()) {
	        MediaType contentType = MediaType.parseMediaType(image.getContentType());
	        //if(contentType.equals(MediaType.IMAGE_PNG) || contentType.equals(MediaType.IMAGE_JPEG) || contentType.equals(MediaType.APPLICATION_PDF)) {
		    if(contentType.equals(MediaType.IMAGE_PNG) || contentType.equals(MediaType.IMAGE_JPEG)) {

	        	java.nio.file.Path directorioImagenes =  Paths.get("C:\\Users\\manci\\Documents\\Dataset\\Numeros\\"+number);
				String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
				try {
					
					byte[] bytesImg = image.getBytes();
					
					java.nio.file.Path rutacompleta = Paths.get(rutaAbsoluta + "//imagen"+ numbers[number]+".jpg");
					numbers[number]++;
					Files.write(rutacompleta, bytesImg);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }else {
	            return new ResponseEntity<>("Tipo de archivo no permitido.", HttpStatus.BAD_REQUEST);
	        }

			
		}
		
		return new ResponseEntity<Object>("Archivo cargado exitosamente.",HttpStatus.CREATED);
	}

}
