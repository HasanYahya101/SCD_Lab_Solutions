import java.io.*;
import java.net.*;

public class Web_Server {
    private static final int PORT = 8080;

    private static void handleRequest(Socket clientSocket) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);) {
            // Read the request
            String requestLine = reader.readLine();
            System.out.println("Request: " + requestLine);

            if (requestLine != null && requestLine.startsWith("GET")) {
                // Send HTTP response
                writer.println("HTTP/1.1 200 OK");
                writer.println("Content-Type: text/plain");
                writer.println();
                writer.println("Hello, this is the server's response!");
            } else {
                writer.println("HTTP/1.1 400 Bad Request");
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("Error handling client request: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Handle the request
                handleRequest(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
