import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class File_Server {

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            System.out.println("Server is running on port 3000");

            try (Socket socket = serverSocket.accept();
                    DataInputStream dis = new DataInputStream(socket.getInputStream())) {

                System.out.println("Connected to the client");

                // Receive file name and size
                String fileName = dis.readUTF();
                long fileSize = dis.readLong();

                // Save file using the received name
                try (FileOutputStream fos = new FileOutputStream(fileName)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    long totalRead = 0;

                    while (totalRead < fileSize && (bytesRead = dis.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                        totalRead += bytesRead;
                    }

                    System.out.println("File received and saved as: " + fileName);
                }

            } catch (IOException e) {
                System.out.println("Error during file transfer: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        File_Server server = new File_Server();
        server.start();
    }
}
