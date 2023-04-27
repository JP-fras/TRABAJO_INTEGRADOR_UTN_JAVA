package integrador;

public class Pronostico {
    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultado;
    private String IdPartido;
    private String nombre;
    private String IdPronostico;

    public String getIdPronostico() {
        return IdPronostico;
    }

    public void setIdPronostico(String idPronostico, String idPartidoPronostico) {
        IdPronostico = idPronostico;
        IdPartido = idPartidoPronostico;
    }

    public String getIdPartido() {
        return IdPartido;
    }

    public void setIdPartido(String idPartido) {
        IdPartido = idPartido;
    }


    public String nombre() {
        return nombre;
    }

    public Pronostico(String idPronostico){
        this.IdPronostico = idPronostico;
    };

    public String getResultado(Pronostico pronostico){
        return pronostico.resultado.getResultado();
    }

    public void setresultado(ResultadoEnum result){
        this.resultado = result;
    }
    //public int puntos(){};

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }





}
