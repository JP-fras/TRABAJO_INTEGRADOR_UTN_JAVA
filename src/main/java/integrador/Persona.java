package integrador;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Persona {
    private String nombre;
    private LocalDateTime fechaNacimiento;
    private ArrayList<Integer> partidosAcertadosp;
    private int Aciertos;
    private int Puntaje;

    public Persona(String nombre){
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nuevoNombre){
        this.nombre = nuevoNombre;
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


    public int getAciertos() {
        return Aciertos;
    }

    public void setAciertos(int aciertos) {
        Aciertos = aciertos;
    }



    public int puntos(Pronostico pronostico, Partido partido){
        int puntaje = 0;
        if (partido.resultado(partido).equals(pronostico.getResultado(pronostico)))
            puntaje++;
        return puntaje;
    }

    public int aciertos(Pronostico pronostico, Partido partido){
        int aciertos = 0;
        if (partido.resultado(partido).equals(pronostico.getResultado(pronostico)))
            aciertos++;
        return aciertos;
    }

    public ArrayList<Integer> getPartidosAcertadosp() {
        return partidosAcertadosp;
    }

    public void setPartidosAcertadosp(ArrayList<Integer> partidosAcertadosp) {
        this.partidosAcertadosp = partidosAcertadosp;
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

}


