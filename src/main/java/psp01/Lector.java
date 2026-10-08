package psp01;

import org.iesalandalus.programacion.utilidades.Entrada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Lector {

    public static void main(String[] args) {

        // Se solicita al usuario el número inicial.
        System.out.print("Introduce un número: ");
        int numero = Entrada.entero();

        // Se crea el proceso hijo que ejecutará Escritor.
        ProcessBuilder pb1 = new ProcessBuilder(
                "java",
                "-cp",
                "build/classes/java/main",
                "psp01.Escritor"
        );

        try {
            Process proceso1 = pb1.start();

            // Se envía el número introducido al proceso hijo.
            PrintStream ps = new PrintStream(proceso1.getOutputStream());
            ps.println(numero);
            ps.close();

            // Se prepara la lectura de los resultados del proceso hijo.
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(proceso1.getInputStream())
            );

            String linea;

            // Se muestran los resultados recibidos por consola.
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            br.close();
            proceso1.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
