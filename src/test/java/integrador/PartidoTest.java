package integrador;

import junit.framework.TestCase;
import org.junit.Test;

public class PartidoTest extends TestCase {
    //TEST PARA COMPROBAR QUE EL METODO PARA SABER EL RESULTADO DE LOS PARTIDOS FUNCIONE CORRECTAMENTE
    @Test
    public void testResultado() {
        Partido partido = new Partido();
        partido.setGolesEquipo2(5);
        partido.setGolesEquipo1(2);
        ResultadoEnum result = new ResultadoEnum();
        assertEquals(result.ganaEquipo2(), partido.resultado(partido));
    }
}