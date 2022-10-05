package com.melaniebalam.util;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public class Utileria {
// Este metodo se utiliza para guardar imagenes
	public static String guardarArchivo(MultipartFile multiPart, String ruta) {
		//Obtenemos el nombre original del archivo
		String nombreOriginal = multiPart.getOriginalFilename();
		nombreOriginal = nombreOriginal.replace(" ", "-"); // esta linea de codigo sirve para que si las imagenes tienen espacio en el nombre le ponga -
		String nombreFinal = randomAlphaNumeric(8) + nombreOriginal; // es para que genere letras y numero aleatorios al guardar una imagen
		try {
			//Formamos el nombre del archivo para guardarlo en el disco duro
			File imageFile = new File(ruta+ nombreFinal);
			System.out.println("Archivo: " + imageFile.getAbsolutePath());
			//Guardamos fisicamente el archivo en HD
			multiPart.transferTo(imageFile);
			return nombreFinal;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}
	
	/*Metodo para generar una cadena aleatoria de longitud N
	 * @param count
	 * @return
	 */
	// metodo para que se genere una cadena aleatoria al guardar una imagen
	public static String randomAlphaNumeric(int count) {
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; 
		StringBuilder builder = new StringBuilder();
		while (count-- !=0) {
			int character = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));
		}
		return builder.toString();
	}
}
