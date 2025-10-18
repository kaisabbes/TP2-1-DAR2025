package serverPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Serveur en attente de connexion sur le port 1234");

        try (ServerSocket serverSocket = new ServerSocket(1234);
             Socket clientSocket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            System.out.println("Un client est connecté !");

            String operation;
            while (true) {
                operation = in.readLine();
                if (operation == null || operation.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println("Operation : " + operation);
                double resultat = calculer(operation);
                out.println(resultat);
                System.out.println("Resultat : " + resultat);
            }

        } catch (IOException e) {
            System.out.println("Erreur serveur : " + e.getMessage());
        }

        System.out.println("Serveur fermé.");
    }

    private static double calculer(String operation) {
        try {
            String[] elements = operation.trim().split(" ");
            if (elements.length != 3) return Double.NaN;

            double op1 = Double.parseDouble(elements[0]);
            String operateur = elements[1];
            double op2 = Double.parseDouble(elements[2]);

            switch (operateur) {
                case "+": return op1 + op2;
                case "-": return op1 - op2;
                case "*": return op1 * op2;
                case "/": return (op2 != 0) ? op1 / op2 : Double.NaN;
                default: return Double.NaN;
            }
        } catch (Exception e) {
            System.out.println("Erreur de syntaxe : " + operation);
            return Double.NaN;
        }
    }
}