package Archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import Enum.Categorias;
import Productos.Regaleria;

public class ArchivoRegaleria {
	
	public static String carpeta = "src//Archivos//Regaleria";
	public static String archivo = "src//Archivos//Regaleria//Regaleria.txt";

	
	public static void crearCarpetaRegaleria()
	{
		Archivo.crearCarpeta(carpeta);
	}
	
	public static void crearArchivoRegaleria()
	{
		Archivo.crearArchivo(archivo);
	}
	
	public static void leerArchivoRegaleria()
	{
			File miArchivo = new File (archivo);
			
			try
			{
				BufferedReader lectura = new BufferedReader(new FileReader(miArchivo));
				String contenido = lectura.readLine();
				while (contenido != null) {
		            String[] vectorContenido = contenido.split("[|]");
		            int codigoBarra = Integer.parseInt(vectorContenido[0]);
		            String marca = vectorContenido[1];
		            String descripcion = vectorContenido[2];
		            int stock = Integer.parseInt(vectorContenido[3]);
		            double precio = Double.parseDouble(vectorContenido[4]);
		            String materialFabricacion = vectorContenido[5];
		    		Regaleria productoRegaleria = new Regaleria(codigoBarra, Categorias.REGALERIA, marca, descripcion, precio, stock, materialFabricacion);
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
