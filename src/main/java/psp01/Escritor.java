package psp01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Escritor {

    public static void main(String[] args) {

        // Se prepara la lectura del número enviado por el proceso padre.
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        try {
            // Se recibe el número enviado por Lector.
            String linea = br.readLine();
            int numero = Integer.parseInt(linea);

            // Se muestran los diez números siguientes.
            for (int i = 1; i <= 10; i++) {
                System.out.println(numero + i);
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}