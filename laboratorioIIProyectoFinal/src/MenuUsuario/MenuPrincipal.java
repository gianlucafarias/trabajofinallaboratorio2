package MenuUsuario;

import java.time.LocalDate;
import java.util.Scanner;

import Archivos.ArchivoAlimentos;
import Archivos.ArchivoPersonal;
import Archivos.ArchivoRegaleria;
import Enum.Categorias;
import Personal.Personal;
import Productos.Lacteos;
import Productos.Productos;
import Productos.Regaleria;

public class MenuPrincipal {

    public static void main(String[] args) {
        int opcion;

        Scanner entrada = new Scanner(System.in);
        
		ArchivoAlimentos.crearCarpetaAlimentos();
		ArchivoAlimentos.crearArchivoAlimentos();
		ArchivoAlimentos.leerArchivoAlimentos();
		
		ArchivoRegaleria.crearCarpetaRegaleria();
		ArchivoRegaleria.crearArchivoRegaleria();
		ArchivoRegaleria.leerArchivoRegaleria();
		
		ArchivoPersonal.crearCarpetaPersonal();
		ArchivoPersonal.crearArchivopersonal();
		ArchivoPersonal.leerArchivoPersonal();

        System.out.println("\n\n----- SISTEMA DE GESTION DE SUPERMERCADO ----- \n\n");
        while (true) {
            do {
                Productos.mostrarCantidadProductos();
                System.out.println("1 - Consultar Productos");
                System.out.println("2 - Cargar Producto");
                System.out.println("3 - Personal");
                System.out.println("\n0 - Salir");
                opcion = Productos.ingresarNumeroEntero("Ingrese una Opción: ");
            } while (opcion < 0 || opcion > 4);

            switch (opcion) {
                case 1:
                    do {
                        System.out.println("1 - Mostrar todos los productos");
                        System.out.println("2 - Mostrar por categoria");
                        System.out.println("3 - Buscar por codigo");
                        System.out.println("4 - Consultar fecha de vencimiento de un producto");
                        System.out.println("\n0 - Volver\n\n");
                        opcion = Productos.ingresarNumeroEntero("Ingrese una Opción: ");
                    //    entrada.nextLine();
                    } while (opcion < 0 || opcion > 5);
                    switch (opcion) {
                    case 1:
                    	for (Productos producto : Productos.arrayProductos) {
                    	    System.out.println(producto.obtenerInformacionImportante(producto.getCodigoBarra()));
                    	}
                        break;
                        case 2:
                            do {
                                System.out.println("1 - Mostrar Todos los Productos de Alimentos");
                                System.out.println("2 - Mostrar Ultimos Productos en Regaleria");
                                opcion = Productos.ingresarNumeroEntero("Ingrese una Opción: ");
                               // entrada.nextLine();
                                break;
                            } while (opcion < 0 || opcion > 3);
                            switch (opcion) {
                                case 1:
                                	Lacteos.mostrarColaLacteos();
                                	break;
                                case 2:
                            	    System.out.println("Ingrese la cantidad de productos para mostrar: ");
                                    opcion = Productos.ingresarNumeroEntero("Ingrese una Opción: ");
                                 //   entrada.nextLine();
                                    Regaleria.mostrarUltimosProductos(opcion);
                                    break;
                                
                            }
                            break;
                        case 3:
                        	boolean flag = false;
                            int codigoBarras = Productos.ingresarNumeroEntero("Ingrese el Codigo de Barras del producto: ");
                            for (Productos producto : Productos.arrayProductos) {
                            	if (producto.obtenerInformacionImportante(codigoBarras).equals("Producto no encontrado")) {
                            		continue;
                            		}
                            	 System.out.println(producto.obtenerInformacionImportante(codigoBarras));
                                 flag = true;
                             }
                             if (!flag) {
                                 System.out.println("Producto no encontrado");
                             }
                             break;
                        case 4:
                            int codigoBarraConsulta = Productos.ingresarNumeroEntero("Ingrese el código de barras del producto para consultar su vencimiento: ");
                         //   entrada.nextLine();
                            Lacteos productoConsultado = Lacteos.mostrarProductoPorCodigoBarra(codigoBarraConsulta);
                            if (productoConsultado != null) {
                                int estadoVencimiento = productoConsultado.diferenciaFechas();
                                switch (estadoVencimiento) {
                                    case 1:
                                        System.out.println("Vigente");
                                        System.out.println("Fecha de Vencimiento: " + Lacteos.obtenerFechaEnFormatoString(productoConsultado.getFechaVencimiento()));

                                        break;
                                    case 2:
                                        System.out.println("Pronto a vencer");
                                        System.out.println("Fecha de Vencimiento: " + Lacteos.obtenerFechaEnFormatoString(productoConsultado.getFechaVencimiento()));
                                        break;
                                    case 3:
                                        System.out.println("Vence hoy");
                                        System.out.println("Fecha de Vencimiento: " + Lacteos.obtenerFechaEnFormatoString(productoConsultado.getFechaVencimiento()));
                                        break;
                                    case 4:
                                        System.out.println("Vencido");
                                        System.out.println("Fecha de Vencimiento: " + Lacteos.obtenerFechaEnFormatoString(productoConsultado.getFechaVencimiento()));
                                        break;
                                    default:
                                        System.out.println("Error al consultar el vencimiento");
                                }
                            } else {
                                System.out.println("Producto no encontrado");
                            }
                            break;
                    }
                    break;

                case 2:
                    do {
                        System.out.println("1-Lacteos");
                        System.out.println("2-Regaleria");
                        System.out.println("0-Volver atras");
                        opcion = Productos.ingresarNumeroEntero("Ingrese una Opción: ");
                      //  entrada.nextLine();
                    } while (opcion < 0 || opcion > 3);
                    switch (opcion) {
                        case 1:
                            Lacteos.crearLacteo();
                            break;
                        case 2:
                            Regaleria.crearRegaleria();
                            break;
                    }
                    break;

                case 3:
                    do {
                    	Personal.mostrarCantidadPersonal();
                        System.out.println("1 - Cargar Personal");
                        System.out.println("2 - Buscar Personal por Legajo");
                        System.out.println("3 - Buscar Legajo por DNI");
                        System.out.println("4 - Mostrar todo el Personal");
                        System.out.println("0 - Volver");
                        opcion = Productos.ingresarNumeroEntero("Ingrese una Opción: ");
                     //   entrada.nextLine();
                    } while (opcion < 0 || opcion > 5);
                    switch (opcion) {
                        case 1:
                            Personal.crearPersonal();
                            break;
                        case 2:
                        	String legajo;
                            System.out.println("Ingrese el Legajo del Personal (alfanumerico): ");
                            legajo = entrada.nextLine();
                            System.out.println(Personal.obtenerDatosPorLegajo(legajo));

                            break;
                        case 3:
                        	int dni;
                            dni = Productos.ingresarNumeroEntero("Ingrese el DNI del Personal: ");
                            System.out.println(Personal.obtenerLegajoPorDni(dni));
                            break;
                        case 4:
                            Personal.mostrarTodoElPersonal();
                            break;
                    }
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    System.exit(0);
            }
        }
    }
}