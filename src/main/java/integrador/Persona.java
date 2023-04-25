package integrador;

import java.time.LocalDateTime;

public class Persona {
    private String nombre;
    private String apellido;
    private LocalDateTime fechaNacimiento;

    private int Puntaje;
    public Persona(String nombre){
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nuevoNombre){
        this.nombre = nuevoNombre;
    }

    public void setApellido(String nuevoApellido){
        this.apellido = nuevoApellido;
    }

    public void setFechaNacimiento(LocalDateTime nuevaFechaNacimiento){
        this.fechaNacimiento = nuevaFechaNacimiento;
    }
    public int getPuntaje() {
        return Puntaje;
    }

    public void setPuntaje(int puntaje) {
        Puntaje = puntaje;
    }
    public int calcularEdad(){
        int edad = LocalDateTime.now().getYear() - fechaNacimiento.getYear();
        if(LocalDateTime.now().getMonthValue() < fechaNacimiento.getMonthValue())
            edad--;
        else{
            if(LocalDateTime.now().getMonthValue() == fechaNacimiento.getMonthValue()){
                if(LocalDateTime.now().getDayOfMonth() < fechaNacimiento.getDayOfMonth())
                    edad--;
            }
        }
        return edad;
    }

    public int puntos(Pronostico pronostico, Partido partido){
        int puntaje = 0;
        if (partido.resultado(partido).equals(pronostico.getResultado(pronostico)))
            puntaje++;
        return puntaje;
    }

    public int sumarPuntajePorRonda(Pronostico pronostico, Partido partido, Ronda ronda, int puntaje ) {

        if (ronda.getPartido(1).resultado(partido).equals(pronostico.getResultado(pronostico)))
            puntaje++;
        return puntaje;

    }
}


