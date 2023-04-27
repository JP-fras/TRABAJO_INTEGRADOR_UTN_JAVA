package integrador;

public class Ronda {
    private String nro;
    private Partido[] partidos;
    public Ronda(String nro, Partido[] partidos) {
        this.partidos = partidos;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public Partido getPartido(int i) {
        return partidos[i];
    }

    public void setPartidos(Partido partido, int i) {
        this.partidos[i] = partido;
    }

    public Partido[] getPartidos() {
        return partidos;
    }
}
