package integrador;

public class Ronda {
    //private String nro;

    public int puntos(Pronostico pronostico, Partido partido){
        int puntaje = 0;
        if (partido.resultado(partido).equals(pronostico.getResultado(pronostico)))
            puntaje++;
        return puntaje;
    }
}
