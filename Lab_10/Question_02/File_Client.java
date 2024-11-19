import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class File_Client {
    public void start() {
        try (
                Socket socket = new Socket("localhost", 3000);
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connected to the server");

            System.out.print("Enter the path of the file to send: ");
            String filePath = scanner.nextLine();
            File file = new File(filePath);

            while (!file.exists()) {
                System.out.print("File not found. Enter filename again:");
                filePath = scanner.nextLine();
                file = new File(filePath);
            }

            dos.writeUTF(file.getName());
            dos.writeLong(file.length());

            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    dos.write(buffer, 0, bytesRead);
                }
                System.out.println("File sent successfully.");
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Disconnected from the server");
        }
    }

    public static void main(String[] args) {
        File_Client client = new File_Client();
        client.start();
    }
}
