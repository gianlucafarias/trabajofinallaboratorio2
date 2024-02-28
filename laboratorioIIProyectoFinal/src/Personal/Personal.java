package Personal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Archivos.Archivo;
import Archivos.ArchivoAlimentos;
import Archivos.ArchivoPersonal;
import Enum.Categorias;
import Productos.Lacteos;
import Productos.Productos;

public class Personal {
	
	private String nombre, apellido, legajo;
	private int dni;
	public static ArrayList<Personal> ArrayPersonal = new ArrayList<Personal>();

	public Personal(String nombre, String apellido, String legajo, int dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.legajo = legajo;
		this.dni = dni;
		
		ArrayPersonal.add(this);
	}
	

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getLegajo() {
		return legajo;
	}
	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public static void mostrarCantidadPersonal() {
	    System.out.println("Cantidad de Personal: " + ArrayPersonal.size());
	}
	
	public static void crearPersonal() {
		String nombre, apellido, legajo;
		int dni;
		Scanner entrada = new Scanner(System.in);
		System.out.println("1. Ingrese el nombre del nuevo Personal: ");
		nombre = entrada.nextLine();
		System.out.println("2. Ingrese el apellido del nuevo Personal: ");
		apellido = entrada.nextLine();
		System.out.println("3. Ingrese el Legajo del nuevo Personal (este servira como identificador de personal): ");
		legajo = entrada.nextLine();
		dni = Productos.ingresarNumeroEntero("4. Ingrese el DNI del nuevo Personal: ");
				
		 try {
				Personal personal = new Personal (nombre, apellido, legajo, dni);
				ArrayPersonal.add(personal);
				String guardarPersonal = nombre + "|" + apellido + "|" + legajo + "|" + dni;
				Archivo.escribirArchivo(ArchivoPersonal.archivo, guardarPersonal);
		        System.out.println("Personal creado exitosamente.");
		    } catch (Exception e) {
		        System.out.println("Error al agregar el Personal.");
		    }
	}
	
	public static String obtenerLegajoPorDni(int dni) {
		for (Personal personal : ArrayPersonal) {
            if (personal.getDni() == dni) {
                return "PERSONAL ENCONTRADO\n\n" + personal.getLegajo();
            }
        }
        return "Personal no encontrado";
    }
	
	public static String obtenerDatosPorLegajo(String legajo) {
        for (Personal personal : ArrayPersonal) {
            if (personal.getLegajo().equals(legajo)) {
                return "PERSONAL ENCONTRADO\n\n" + "Nombre: " + personal.getNombre() + "\nApellido: " + personal.getApellido() +
                       "\nDNI: " + personal.getDni() + "\nLegajo: " + personal.getLegajo();
            }
        }
        return "Personal no encontrado";
    }
	
	public static void mostrarTodoElPersonal() {
        for (Personal personal : ArrayPersonal) {
            System.out.println("Nombre: " + personal.getNombre() + "\nApellido: " + personal.getApellido() +
                    "\nDNI: " + personal.getDni() + "\nLegajo: " + personal.getLegajo() + "\n---");
        }
    }
}
