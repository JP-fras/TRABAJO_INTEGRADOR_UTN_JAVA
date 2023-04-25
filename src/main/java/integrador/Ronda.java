package integrador;

public class Ronda {
    public Ronda(String nro, Partido[] partidos) {
        this.partidos = partidos;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    private String nro;


    public Partido getPartido(int i) {
        return partidos[i];
    }

    public void setPartidos(Partido partido, int i) {
        this.partidos[i] = partido;
    }

    private Partido[] partidos;

    private Integer cuantasGanoPorRonda;



}
