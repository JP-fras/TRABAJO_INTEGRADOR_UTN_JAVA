package integrador;

import junit.framework.TestCase;
import org.junit.Test;

public class RondaTest extends TestCase {
    //TEST PARA COMPROBAR QUE LA FUNCION PARA CALCULAR LOS PUNTOS FUNCIONE CORRECTAMENTE
    @Test
    public void testPuntos() {
        Persona c = new Persona("prueba");
        Pronostico pronostico = new Pronostico("prueba");
        Partido partido = new Partido();
        partido.setGolesEquipo1(1);
        partido.setGolesEquipo2(0);
        partido.resultado(partido);
        ResultadoEnum resultado = new ResultadoEnum();
        resultado.setresultado(resultado.ganaEquipo1());
        pronostico.setresultado(resultado);
        assertEquals(1, c.puntos(pronostico, partido));
    }
}