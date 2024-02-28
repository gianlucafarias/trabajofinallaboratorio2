package Archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Enum.Categorias;
import Productos.Lacteos;

public class ArchivoAlimentos {
	
	public static String carpeta = "src//Archivos//ALIMENTOS";

	public static String archivo = "src//Archivos//ALIMENTOS//Alimentos.txt";
	
	public static void crearCarpetaAlimentos()
	{
	    Archivo.crearCarpeta(carpeta);
	}
	
	public static void crearArchivoAlimentos()
	{

		Archivo.crearArchivo(archivo);
	}
	
	public static void escribirFicheroAlimentos() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	    for (Lacteos elemento : Productos.Lacteos.ArrayLacteos) {
	        String aux = elemento.getCodigoBarra() + "|" +
	                      elemento.getMarca() + "|" +
	                      elemento.getDescripcion() + "|" +
	                      elemento.getPrecio() + "|" +
	                      elemento.getStock() + "|" +
	                      formatter.format(elemento.getFechaFabricacion()) + "|" +
	                      formatter.format(elemento.getFechaVencimiento()) ;

	        Archivo.escribirArchivo(archivo, aux);
	    }
	}
	
	public static void leerArchivoAlimentos()
	{
			File miArchivo = new File (archivo);
			
			try
			{
				BufferedReader lectura = new BufferedReader(new FileReader(miArchivo));
				String contenido = lectura.readLine();
				while (contenido != null) {
				    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		            String[] vectorContenido = contenido.split("[|]");
		            int codigoBarra = Integer.parseInt(vectorContenido[0]);
		            String marca = vectorContenido[1];
		            String descripcion = vectorContenido[2];
		            int stock = Integer.parseInt(vectorContenido[3]);
		            double precio = Double.parseDouble(vectorContenido[4]);
		            LocalDate fechaFabricacion = LocalDate.parse(vectorContenido[5], formatter); 
		            LocalDate fechaVencimiento = LocalDate.parse(vectorContenido[6], formatter);
		            Lacteos lacteo = new Lacteos(codigoBarra, Categorias.ALIMENTOS, marca, descripcion, precio, stock, fechaFabricacion, fechaVencimiento);
		            Lacteos.ArrayLacteos.add(lacteo);
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
