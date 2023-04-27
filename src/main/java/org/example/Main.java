package org.example;

import integrador.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, java.sql.SQLException {
        sacarPuntajePorPersona();

    }

    public static void sacarPuntajePorPersona() throws java.io.IOException, java.sql.SQLException {
        Map<String, Persona> personas = new HashMap<>(); // Mapa para guardar el puntaje de cada persona
        Map<String, Ronda> rondas = new HashMap<>();
        Map<String, Pronostico> pronosticos = new HashMap<>();
        ArrayList<Integer> partidosAcertados = new ArrayList<Integer>();
        Statement stmt = null;
        try {
            cargarClase();//Conecta con la base de datos

            Connection conn = DriverManager.getConnection("jdbc:mysql://db4free.net/dbcursojavajpf",
                    "jpfras", "Pellegrini13");

            String consulta = "SELECT *  FROM pronostico";//Traigo la data de mi tabla pronostico
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            String consulta2 = "SELECT *  FROM partido";//Traigo la data de mi tabla partido
            Statement statement2 = conn.createStatement();
            ResultSet resultSet2 = statement2.executeQuery(consulta2);
            while (resultSet.next() || resultSet2.next()) {
                String nombre = resultSet.getString("participante");
                String equipo1n = resultSet.getString("equipo1");
                String gana1 = resultSet.getString("gana1");
                String empata = resultSet.getString("empata");
                String gana2 = resultSet.getString("gana2");
                String equipo2n = resultSet.getString("equipo2");
                String idPronostico = resultSet.getString("id_pronostico");
                String idPartidoPronostico = resultSet.getString("id_partidos");


                Pronostico pronostico = pronosticos.getOrDefault(idPronostico, new Pronostico(idPronostico));
                ResultadoEnum resultado = new ResultadoEnum();
                Equipo equipo1 = new Equipo();
                Equipo equipo2 = new Equipo();
                Partido partido = new Partido();

                //Saco quien dice que gana en cada pronostico y lo guardo
                equipo1.setNombre(equipo1n);
                equipo2.setNombre(equipo2n);
                if (gana1.equals("1")) {
                    resultado.setresultado(resultado.ganaEquipo1());
                } else if (empata.equals("1")) {
                    resultado.setresultado(resultado.empate());
                } else if (gana2.equals("1")) {
                    resultado.setresultado(resultado.ganaEquipo2());
                }
                pronostico.setresultado(resultado);
                pronostico.setIdPartido(idPartidoPronostico);
                pronosticos.put(idPronostico, pronostico);
                partido.setEquipo1(equipo1);
                partido.setEquipo2(equipo2);


                //extraigo la data de la segunda tabla y la almaceno
                if (resultSet2.next()) {
                    Partido[] partidon = new Partido[20];
                    String golesEquipo1 = resultSet2.getString("cant_goles1");
                    String golesEquipo2 = resultSet2.getString("cant_goles2");
                    partido.setGolesEquipo1(Integer.parseInt(golesEquipo1));
                    partido.setGolesEquipo2(Integer.parseInt(golesEquipo2));
                    String nro = resultSet2.getString("ronda");
                    Ronda ronda = rondas.getOrDefault(nro, new Ronda(nro, partidon));
                    int idPartido = resultSet2.getInt("id_partido");
                    ronda.setPartidos(partido, idPartido);
                    rondas.put(nro, ronda);
                }
                Persona persona = personas.getOrDefault(nombre, new Persona(nombre)); // Obtener objeto Persona del mapa, o crear uno nuevo si no existe
                persona.setNombre(nombre);//seteo nombre de persona
                persona.setAciertos(persona.getAciertos() + persona.aciertos(pronostico, partido));// Actualizar puntaje
                if (partido.resultado(partido).equals(pronostico.getResultado(pronostico))) {
                    partidosAcertados.add(Integer.parseInt(idPartidoPronostico));
                    persona.setPartidosAcertadosp(partidosAcertados);
                }
                personas.put(nombre, persona); // Guardar objeto Persona actualizado en el mapa


            }





        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        finally {


            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }

        }


        //como haria para añadirle puntos extras si la persona acerto todos los partidos de la ronda
        for (Persona per : personas.values()) {
            //calcular que partidos acerto la persona(lo guardo), esta ese partido en el array
            ArrayList<Integer> partidosAcertadosPorPersona = new ArrayList<Integer>();
            for(int k : per.getPartidosAcertadosp()) {
                if (k != 0) {
                    partidosAcertadosPorPersona.add(k);
                }
            }


            for(Ronda ron : rondas.values()) {
                    if (partidosAcertadosPorPersona.contains(partidosAcertados)) {
                        /*esto no lo logre hacer pero lo que haria seria traer el puntaje de la persona y
                        si los partidos acertados por persona contienen a los partidos acertados por ronda
                        sumarle al puntaje(que serian los aciertos) los puntos extras*/
                }
            }

        }

        // Imprimir puntajes de cada persona
        for (Persona persona : personas.values()) {
            System.out.println("El puntaje de " + persona.getNombre()+ " es de: " + persona.getAciertos());
        }
    }
    public static void cargarClase() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    }


}

