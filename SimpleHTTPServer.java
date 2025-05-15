import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class SimpleHTTPServer {

    public static void main(String args[]) throws IOException {

        // Create a server socket listening on port 8080
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080 ....");

        // Continuous loop to accept incoming connections
        while (true) {
            try (Socket socket = server.accept()) {
                // Create a Date object to show the current date and time
                Date today = new Date();

                // HTTP Response header with proper content-type and status
                String httpResponse = "HTTP/1.1 200 OK\r\n" +
                                      "Content-Type: text/html\r\n" +
                                      "Content-Length: " + today.toString().length() + "\r\n\r\n" +
                                      "<html><body><h1>" + today.toString() + "</h1></body></html>";

                // Write the HTTP response to the client
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(httpResponse.getBytes("UTF-8"));
                outputStream.flush();  // Ensure that the data is written immediately
            }
        }
    }
}
