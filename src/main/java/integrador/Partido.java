package integrador;

public class Partido {
    private int golesEquipo1;
    private int golesEquipo2;

    public Partido(){}

    public void setEquipo1(Equipo equip1){
    }

    public void setEquipo2(Equipo equip2){
    }

    public void setGolesEquipo1(int goles){
        this.golesEquipo1 = goles;
    }

    public void setGolesEquipo2(int goles){
        this.golesEquipo2 = goles;
    }
    public String resultado(Partido partido){
        ResultadoEnum result = new ResultadoEnum();
        if(partido.golesEquipo1 > partido.golesEquipo2) {
            return result.ganaEquipo1();
        }
        else if(partido.golesEquipo1 < partido.golesEquipo2){
                return result.ganaEquipo2();
        }
        else{
            return result.empate();
        }
    }
}
