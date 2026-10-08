package psp01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Tuberia {

    public static void main(String[] args) {

        // Se crean los dos procesos que formarán la tubería.
        ProcessBuilder pb1 = new ProcessBuilder("CMD", "/C", "tasklist");
        ProcessBuilder pb2 = new ProcessBuilder("CMD", "/C", "find \"chrome.exe\"");

        try {
            Process proceso1 = pb1.start();
            Process proceso2 = pb2.start();

            // Se prepara la lectura de la salida del primer proceso.
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(proceso1.getInputStream())
            );

            // Se prepara la escritura de los datos en la entrada del segundo proceso.
            PrintStream ps = new PrintStream(proceso2.getOutputStream());

            String linea;

            // Se envía la salida de 'tasklist' como entrada de 'find'.
            while ((linea = br.readLine()) != null) {
                ps.println(linea);
            }

            ps.close();
            br.close();

            // Se lee la salida generada por el segundo proceso.
            BufferedReader br2 = new BufferedReader(
                    new InputStreamReader(proceso2.getInputStream())
            );

            // Se muestra por pantalla el resultado de 'find'.
            while ((linea = br2.readLine()) != null) {
                System.out.println(linea);
            }

            br2.close();

            // Se espera a que ambos procesos terminen.
            proceso1.waitFor();
            proceso2.waitFor();

            System.out.println("Finalizada la ejecución");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}