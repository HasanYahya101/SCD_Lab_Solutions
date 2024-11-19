import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Echo_Client {
    public void start() {
        try (
                Socket socket = new Socket("localhost", 3000);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in);) {
            System.out.println("Connected to the server");

            while (true) {
                System.out.print("Client Message to send: ");
                String client_string = scanner.nextLine();
                writer.println(client_string);

                if (client_string.equalsIgnoreCase("exit")) {
                    break;
                }

                String server_string = reader.readLine();
                System.out.println("Server Message recieved: " + server_string);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Disconnected from the server");
        }
    }

    public static void main(String[] args) {
        Echo_Client client = new Echo_Client();
        client.start();
    }
}