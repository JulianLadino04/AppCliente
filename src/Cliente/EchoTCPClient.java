package Cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoTCPClient {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String SERVER = "localhost";
    public static final int PORT = 3400;
    private PrintWriter toNetwork;
    private BufferedReader fromNetwork;
    private Socket clientSideSocket;

    public EchoTCPClient() {
        System.out.println("Echo TCP Client está corriendo...");
    }

    public void init() throws Exception {
        clientSideSocket = new Socket(SERVER, PORT);
        createStreams(clientSideSocket);
        protocol(clientSideSocket);
        clientSideSocket.close();
    }

    public void protocol(Socket socket) throws Exception {
        System.out.print("Ingrese un comando: ");
        String fromUser = SCANNER.nextLine();
        toNetwork.println(fromUser);

        // Leer todas las líneas de la respuesta del servidor
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = fromNetwork.readLine()) != null && !line.isEmpty()) {
            response.append(line).append("\n");
        }
        System.out.println("[Client] from server:\n" + response.toString());
    }

    private void createStreams(Socket socket) throws Exception {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static void main(String args[]) throws Exception {
        EchoTCPClient ec = new EchoTCPClient();
        ec.init();
    }
}


