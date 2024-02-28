package Interfaces;

import java.time.LocalDate;

public interface Fecha {
	
    String mostrarFechaFormateada(LocalDate fecha);

    int diferenciaFechas();

}
