package org.example;

import integrador.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        String archivoPronostico = "C:\\Users\\Pc\\IdeaProjects\\PROYECTO_INTEGRADOR_UTN_JAVA\\Archivos\\pronosticos.csv";
        String archivoResultados = "C:\\Users\\Pc\\IdeaProjects\\PROYECTO_INTEGRADOR_UTN_JAVA\\Archivos\\resultados.csv";

        sacarPuntajePorPersona(archivoPronostico, archivoResultados);


    }

    public static void sacarPuntajePorPersona(String archivoPronostico, String archivoResultados) throws java.io.IOException {
        Map<String, Persona> personas = new HashMap<>(); // Mapa para guardar el puntaje de cada persona
        int i = 0;
        // Leer pronósticos y actualizar puntajes de cada persona
        for (String linea : Files.readAllLines(Paths.get(archivoPronostico))) {
            String[] campos = linea.split(";");
            String nombre = campos[0];
            Pronostico pronostico = new Pronostico();
            ResultadoEnum resultado = new ResultadoEnum();
            Equipo equipo1 = new Equipo();
            Equipo equipo2 = new Equipo();
            Partido partido = new Partido();
            Ronda ronda = new Ronda();

            // Configurar los objetos con los datos de la línea
            equipo1.setNombre(campos[1]);
            equipo2.setNombre(campos[5]);
            if (campos[2].equals("X")) {
                resultado.setresultado(resultado.ganaEquipo1());
            } else if (campos[3].equals("X")) {
                resultado.setresultado(resultado.empate());
            } else if (campos[4].equals("X")) {
                resultado.setresultado(resultado.ganaEquipo2());
            }
            pronostico.setresultado(resultado);
            partido.setEquipo1(equipo1);
            partido.setEquipo2(equipo2);

            // Leer resultados
            int cantLineas2 = 0;
            for (String linea2 : Files.readAllLines(Paths.get(archivoResultados))) {
                cantLineas2++;
            }

            int indice = 0;
            String[] lineaResultados = new String[cantLineas2];
            for (String linea2 : Files.readAllLines(Paths.get(archivoResultados))) {
                lineaResultados[indice] = linea2;
                indice++;
            }
            String[] lineasResultados = Files.readAllLines(Paths.get(archivoResultados)).toArray(new String[0]);
            String[] camposResultados = lineasResultados[i].split(";");
            partido.setGolesEquipo1(Integer.parseInt(camposResultados[2]));
            partido.setGolesEquipo2(Integer.parseInt(camposResultados[3]));

            // Actualizar puntaje de la persona correspondiente
            Persona persona = personas.getOrDefault(nombre, new Persona(nombre)); // Obtener objeto Persona del mapa, o crear uno nuevo si no existe
            persona.setPuntaje(persona.getPuntaje() + ronda.puntos(pronostico, partido)); // Actualizar puntaje
            persona.setNombre(nombre);
            personas.put(nombre, persona); // Guardar objeto Persona actualizado en el mapa
            if((i = i + 1) > 3) {
                i = i - 1;
            } else {
                i = i;
            }
        }

        // Imprimir puntajes de cada persona
        for (Persona persona : personas.values()) {
            System.out.println("El puntaje de " + persona.getNombre()+ " es de: " + persona.getPuntaje());
        }
    }
}

