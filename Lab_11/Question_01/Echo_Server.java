import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Echo_Server {

    public void start() {
        try (ServerSocket serversocket = new ServerSocket(3000)) {
            System.out.println("Server is running on port 3000");
            try (Socket socket = serversocket.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);) {
                System.out.println("Connected to the client");

                while (true) {
                    String client_string = reader.readLine();

                    if (client_string == null || client_string.equalsIgnoreCase("exit")) {
                        break;
                    }

                    System.out.println("Client Message recieved: " + client_string);
                    // send the message back to the client
                    writer.println(client_string);
                    System.out.println("Same message sent back to client");
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Echo_Server server = new Echo_Server();
        server.start();
    }

}