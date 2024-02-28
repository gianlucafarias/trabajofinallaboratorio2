package Archivos;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Enum.Categorias;
import Personal.Personal;
import Productos.Regaleria;

public class ArchivoPersonal {
	
	public static String carpeta = "src//Archivos//PERSONAL";

	public static String archivo = "src//Archivos//PERSONAL//Personal.txt";
	
	public static void crearCarpetaPersonal()
	{
	    Archivo.crearCarpeta(carpeta);
	}
	
	public static void crearArchivopersonal()
	{

		Archivo.crearArchivo(archivo);
	}
	

	public static void leerArchivoPersonal()
	{
			File miArchivo = new File (archivo);
			
			try
			{
				BufferedReader lectura = new BufferedReader(new FileReader(miArchivo));
				String contenido = lectura.readLine();
				while (contenido != null) {
		            String[] vectorContenido = contenido.split("[|]");
		            String nombre = vectorContenido[0];
		            String apellido = vectorContenido[1];
		            String legajo = vectorContenido[2];
		            int dni = Integer.parseInt(vectorContenido[3]);
		    		Personal personal = new Personal (nombre, apellido, legajo, dni);
		            contenido = lectura.readLine();
				}
				lectura.close();
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
