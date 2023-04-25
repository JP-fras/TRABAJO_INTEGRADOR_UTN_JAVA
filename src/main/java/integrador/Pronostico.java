package integrador;

public class Pronostico {
    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultado;

    public String getIdPronostico() {
        return IdPronostico;
    }

    public void setIdPronostico(String idPronostico) {
        IdPronostico = idPronostico;
    }

    private String IdPronostico;

    private String nombre;

    public Pronostico(){};

    public String getResultado(Pronostico pronostico){
        return pronostico.resultado.getResultado();
    }

    public void setresultado(ResultadoEnum result){
        this.resultado = result;
    }
    //public int puntos(){};
    public String nombre() {
            return nombre;
        }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
