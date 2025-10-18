package clientPackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Je suis un client connecté !");

            String operation;
            while (true) {
                System.out.print("Entrez une opération /'exit' pour quitter : ");
                operation = scanner.nextLine();

                out.println(operation);
                if (operation.equalsIgnoreCase("exit")) break;

                String resultat = in.readLine();
                System.out.println("Résultat reçu du serveur : " + resultat);
            }

        } catch (IOException e) {
            System.out.println("Erreur client : " + e.getMessage());
        }

        System.out.println("Connexion fermée.");
    }
}