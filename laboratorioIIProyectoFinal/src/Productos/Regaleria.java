package Productos;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import Archivos.Archivo;
import Archivos.ArchivoAlimentos;
import Archivos.ArchivoRegaleria;
import Enum.Categorias;

public class Regaleria extends Productos {
	
	public static ArrayList<Regaleria> ArrayRegaleria = new ArrayList<Regaleria>();
	private String materialFabricacion;
	public static Stack<Regaleria> stackRegaleria = new Stack<Regaleria>();

	public Regaleria(int codigoBarra, Categorias codigoCategoria, String marca, String descripcion, double precio,
			int stock, String materialFabricacion) {
		super(codigoBarra, Categorias.REGALERIA, marca, descripcion, precio, stock);
		this.materialFabricacion = materialFabricacion;
		ArrayRegaleria.add(this);
		stackRegaleria.push(this);
	}

	public String getMaterialFabricacion() {
		return this.materialFabricacion;
	}

	public void setMaterialFabricacion(String materialFabricacion) {
		this.materialFabricacion = materialFabricacion;
	}
	
	public static void crearRegaleria(){
		int codigoBarra, stock;
		String marca, descripcion, materialFabricacion;
		double precio;
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
		System.out.println("Ingrese el Material de fabricacion del Producto: ");
		materialFabricacion = entrada.nextLine();
		Categorias codigoCategoria = Categorias.REGALERIA;
        
		 try {
				Regaleria productoRegaleria = new Regaleria(codigoBarra, Categorias.REGALERIA, marca, descripcion, precio, stock, materialFabricacion);
				String guardarProducto = codigoBarra + "|" + marca + "|" + descripcion + "|" + stock + "|" + precio + "|" + materialFabricacion;
				Archivo.escribirArchivo(ArchivoRegaleria.archivo, guardarProducto);
		        System.out.println("Producto creado y agregado exitosamente a la lista.");
		    } catch (Exception e) {
		        System.out.println("Error al agregar el producto.");
		    }
	}
	
	public static void mostrarUltimosProductos(int cantidad) {
	    System.out.println("Últimos " + cantidad + " productos en Regalería:");
	    int contador = 0;
	    while (!stackRegaleria.isEmpty() && contador < cantidad) {
	        Regaleria producto = stackRegaleria.pop();
	        System.out.println("Marca: " + producto.getMarca() + ", Precio: " + producto.getPrecio() + ", Material de Fabricacion: " + producto.getMaterialFabricacion());
	        contador++;
	    }
	}


}
