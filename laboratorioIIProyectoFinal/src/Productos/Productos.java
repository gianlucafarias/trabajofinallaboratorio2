package Productos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Enum.Categorias;

public abstract class Productos {

	private int codigoBarra;
	private Categorias codigoCategoria;
	private String marca;
	private String descripcion;
	private double precio;
	private int stock;

    public static List<Productos> arrayProductos = new ArrayList<>();
    
    
	public Productos(int codigoBarra, Categorias codigoCategoria, String marca, String descripcion, double precio,
			int stock) {
		this.codigoBarra = codigoBarra;
		this.codigoCategoria = codigoCategoria;
		this.marca = marca;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		
        arrayProductos.add(this);
	}
	
	public int getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(int codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public Categorias getCodigoCategoria() {
		return codigoCategoria;
	}
	public void setCodigoCategoria(Categorias codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	
	public static void mostrarCantidadProductos() {
	    System.out.println("Cantidad de productos indexados: " + arrayProductos.size());
	}
	 
	public static List<Productos> obtenerListaProductos() {
        return arrayProductos;
    }
	
	public String obtenerInformacionImportante(int codigoBarras) {
        if (codigoBarras == getCodigoBarra()) {
            return "\nMarca: " + getMarca() + 
            		"\nDescripción: " + getDescripcion() +
                   "\nPrecio: " + getPrecio();
        } else {
            return "Producto no encontrado";
        }
    }
	
	public static int ingresarNumeroEntero(String leyenda)
	{
		int numero;
		String auxiliar;
		Scanner entrada = new Scanner (System.in);
		System.out.println(leyenda);
		auxiliar = entrada.nextLine();
		while (esNumeroEntero(auxiliar) == false)
		{
			System.out.println("ERROR: " + leyenda);
			auxiliar = entrada.nextLine();
		}
		if (esNumeroEntero(auxiliar) == true)
		{
			numero = Integer.parseInt(auxiliar);
		}
		else
		{
			System.out.println("ERROR: Se asigna 0.");
			numero = 0;
		}
		return numero;
	}
		
	public static double ingresarNumeroDouble(String mensaje)
	{
		double numero;
		String auxiliar;
		Scanner entrada = new Scanner (System.in);
		System.out.println(mensaje);
		auxiliar = entrada.nextLine();
		while (esNumeroDouble(auxiliar) == false)
		{
			System.out.println("Error: " + mensaje);
			auxiliar = entrada.nextLine();
		}
		if (esNumeroDouble(auxiliar) == true)
		{
			numero = Double.parseDouble(auxiliar);
		}
		else
		{
			System.out.println("Error: Se asigna 0 por default");
			numero = 0;
		}
		return numero;
	}
	
	public static boolean esNumeroEntero(String entrada) {
        boolean flag;
        try {
            Integer.parseInt(entrada);
            flag = true;
        } 
        catch (NumberFormatException e) {
            flag = false;
        }
        return flag;
    }
	
	public static boolean esNumeroDouble(String entrada) {
        boolean flag;
        try {
            Double.parseDouble(entrada);
            flag = true;
        } 
        catch (NumberFormatException e) {
            flag = false;
        }
        return flag;
    }
	
	public static boolean validarCodigoBarras(int codigoBarras) {
        for (Productos producto : arrayProductos) {
            if (codigoBarras == producto.getCodigoBarra()) {
                return false; // El código de barras ya existe, no es válido
            }
        }
        return true; // El código de barras es válido
    }
	
}
