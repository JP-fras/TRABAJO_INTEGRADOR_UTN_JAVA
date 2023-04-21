package org.example;

import integrador.*;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, java.sql.SQLException {
        sacarPuntajePorPersona();


    }

    public static void sacarPuntajePorPersona() throws java.io.IOException, java.sql.SQLException {
        Map<String, Persona> personas = new HashMap<>(); // Mapa para guardar el puntaje de cada persona
        int i = 0;
        Statement stmt = null;
        try {
            cargarClase();

            Connection conn = DriverManager.getConnection("jdbc:mysql://db4free.net/dbcursojavajpf",
                    "jpfras", "Pellegrini13");



            String consulta = "SELECT *  FROM pronostico";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            String consulta2 = "SELECT *  FROM partido";
            Statement statement2 = conn.createStatement();
            ResultSet resultSet2 = statement2.executeQuery(consulta2);
            while (resultSet.next() || resultSet2.next()) {
                String nombre = resultSet.getString("participante");
                String equipo1n = resultSet.getString("equipo1");
                String gana1 = resultSet.getString("gana1");
                String empata = resultSet.getString("empata");
                String gana2 = resultSet.getString("gana2");
                String equipo2n = resultSet.getString("equipo2");


                String nombreP = nombre;
                Pronostico pronostico = new Pronostico();
                ResultadoEnum resultado = new ResultadoEnum();
                Equipo equipo1 = new Equipo();
                Equipo equipo2 = new Equipo();
                Partido partido = new Partido();
                Ronda ronda = new Ronda();

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
                partido.setEquipo1(equipo1);
                partido.setEquipo2(equipo2);

                if (resultSet2.next()) {
                    String golesEquipo1 = resultSet2.getString("cant_goles1");
                    String golesEquipo2 = resultSet2.getString("cant_goles2");
                    partido.setGolesEquipo1(Integer.parseInt(golesEquipo1));
                    partido.setGolesEquipo2(Integer.parseInt(golesEquipo2));
                }
                Persona persona = personas.getOrDefault(nombre, new Persona(nombre)); // Obtener objeto Persona del mapa, o crear uno nuevo si no existe
                persona.setPuntaje(persona.getPuntaje() + ronda.puntos(pronostico, partido)); // Actualizar puntaje
                persona.setNombre(nombre);
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





        // Imprimir puntajes de cada persona
        for (Persona persona : personas.values()) {
            System.out.println("El puntaje de " + persona.getNombre()+ " es de: " + persona.getPuntaje());
        }
    }
    public static void cargarClase() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    }


}

