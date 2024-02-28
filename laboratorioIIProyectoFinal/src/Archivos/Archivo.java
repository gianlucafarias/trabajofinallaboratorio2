package Archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Archivo {
	
	  public static void crearCarpeta(String rutaCarpeta) {
	        File miArchivo = new File(rutaCarpeta);

	        if (!miArchivo.exists()) {
	            miArchivo.mkdir();
	        }
	    }
	  
	
	  public static void crearArchivo(String rutaArchivo) {
	        File miArchivo = new File(rutaArchivo);

	        if (!miArchivo.exists()) {
	            try {
	                miArchivo.createNewFile();
	            } catch (IOException e) {
	                System.out.println("Error al crear el archivo: " + e.getMessage());
	            }
	        }
	    }
	
	public static void escribirArchivo(String ruta, String contenido)
	{
		File miArchivo = new File (ruta);
		try
		{
			PrintWriter escritura = new PrintWriter(new FileWriter(miArchivo, true));
			escritura.println(contenido);
			escritura.close();
		}
		catch (FileNotFoundException error)
		{
			System.out.println(error.getMessage());
		}
		catch ( IOException error)
		{
			System.out.println(error.getMessage());
		}
	}
}
