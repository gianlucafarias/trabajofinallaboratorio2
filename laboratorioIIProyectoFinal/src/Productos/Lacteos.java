package Productos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import Archivos.Archivo;
import Archivos.ArchivoAlimentos;
import Enum.Categorias;
import Interfaces.Fecha;

public class Lacteos extends Productos implements Fecha {
	
	public static ArrayList<Lacteos> ArrayLacteos = new ArrayList<Lacteos>();
    private static Queue<Lacteos> queueLacteos = new LinkedList<>();

	private LocalDate fechaFabricacion;
	private LocalDate fechaVencimiento;

	public Lacteos(int codigoBarra, Categorias codigoCategoria, String marca, String descripcion, double precio,
			int stock, LocalDate fechaFabricacion, LocalDate fechaVencimiento) {
		super(codigoBarra, Categorias.ALIMENTOS, marca, descripcion, precio, stock);
		this.fechaFabricacion = fechaFabricacion;
		this.fechaVencimiento = fechaVencimiento;
	    ArrayLacteos.add(this);
	    agregarALaCola(this);
	    
	}
	
	private static void agregarALaCola(Lacteos lacteo) {
        queueLacteos.offer(lacteo);
    }

	public LocalDate getFechaFabricacion() {
		return fechaFabricacion;
	}

	public void setFechaFabricacion(LocalDate fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	
	public static void crearLacteo() {
		int codigoBarra, stock, dia, mes, anio;
		String marca, descripcion;
		double precio;
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		Scanner entrada = new Scanner(System.in);
		do {
            System.out.println("Ingrese Codigo de Barras del Producto: ");
            codigoBarra = entrada.nextInt();
            entrada.nextLine();
            if (!validarCodigoBarras(codigoBarra)) {
                System.out.println("Ya existe un producto con este código de barras. Ingrese un código de barras único.");
            }
        } while (!validarCodigoBarras(codigoBarra));

		System.out.println("Ingrese la marca del Producto: ");
		marca = entrada.nextLine();
		System.out.println("Ingrese la descripcion del Producto: ");
		descripcion = entrada.nextLine();
		stock = Productos.ingresarNumeroEntero("Ingrese Cantidad de unidades en Stock: ");
		precio = Productos.ingresarNumeroDouble("Ingrese el Precio del Producto: ");
		System.out.println("Ingrese la fecha de fabricacion del producto(en formato dia-mes-anio): ");
        LocalDate fechaFabricacion = ingresarFechaValidada(entrada, formatter);
	    System.out.println("Ingrese la fecha de vencimiento (en formato dia-mes-anio): ");
        LocalDate fechaVencimiento = ingresarFechaVencimientoValidada(entrada, formatter);
	    
	    try {
	    	Lacteos productoLacteo = new Lacteos (codigoBarra, Categorias.ALIMENTOS, marca, descripcion, precio, stock, fechaVencimiento, fechaFabricacion);
		//    ArrayLacteos.add(productoLacteo);
			String guardarLacteo = codigoBarra + "|" + marca + "|" + descripcion + "|" + stock + "|" + precio + "|" + fechaFabricacion.format(formatter) + "|" + fechaVencimiento.format(formatter);
			Archivo.escribirArchivo(ArchivoAlimentos.archivo, guardarLacteo);
	        System.out.println("Producto creado y agregado exitosamente a la lista.");
	    } catch (Exception e) {
	        System.out.println("Error al agregar el producto.");
	    }
	   
	}
	

    public static String obtenerFechaEnFormatoString(LocalDate fecha) {
        int dia = fecha.getDayOfMonth();
        int mes = fecha.getMonthValue() ;
        int anio = fecha.getYear() ;
        return String.format(dia + "-" + mes + "-" + anio);
    }

    public int diferenciaFechas() {
        LocalDate fechaActual = LocalDate.now();
        long diasDiferencia = fechaVencimiento.toEpochDay() - fechaActual.toEpochDay();
        if (diasDiferencia > 10) {
            return 1; // "Vigente"
        } else if (diasDiferencia >= 0) {
            return 2; // "Pronto a vencer"
        } else if (diasDiferencia == 0) {
            return 3; // "Vence hoy"
        } else {
            return 4; // "Vencido"
        }
    }

	public String mostrarFechaFormateada(LocalDate fecha) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Lacteos mostrarProductoPorCodigoBarra(int codigoBarra) {
	    for (Lacteos lacteo : ArrayLacteos) {
	        if (lacteo.getCodigoBarra() == codigoBarra) {
	            return lacteo;
	        }
	    }
	    return null;
	}
	
	private static LocalDate ingresarFechaValidada(Scanner entrada, DateTimeFormatter formatter) {
        while (true) {
            String inputFecha = entrada.nextLine();

            try {
                LocalDate fecha = LocalDate.parse(inputFecha, formatter);

                if (!fecha.isAfter(LocalDate.now())) {
                    return fecha;
                } else {
                    System.out.println("La fecha no puede ser posterior a la fecha actual. Intente nuevamente:");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Intente nuevamente:");
            }
        }
    }
	
	private static LocalDate ingresarFechaVencimientoValidada(Scanner entrada, DateTimeFormatter formatter) {
        while (true) {
            String inputFecha = entrada.nextLine();

            try {
                LocalDate fecha = LocalDate.parse(inputFecha, formatter);
                return fecha;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Intente nuevamente:");
            }
        }
    }
	
	public static void mostrarColaLacteos() {
        if (queueLacteos.isEmpty()) {
            System.out.println("La cola de Productos está vacia.");
        } else {
            System.out.println("Productos en la cola de alimentos: \n");
            for (Lacteos lacteo : queueLacteos) {
            	System.out.println(lacteo.obtenerInformacionImportante(lacteo.getCodigoBarra()));            }
        }
    }
}

