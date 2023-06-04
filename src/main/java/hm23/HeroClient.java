package hm23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HeroClient {

    public static final String SERVER_HOST = "localhost";
    public static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {

        var scanner = new Scanner(System.in);

        try (var heroSocket = new Socket(SERVER_HOST, SERVER_PORT)) {
            var out = new PrintWriter(heroSocket.getOutputStream(), true);
            var in = new BufferedReader(new InputStreamReader(heroSocket.getInputStream()));

            String userInput;
            while ((userInput = scanner.nextLine()) != null) {
                out.println(userInput);
                if (userInput.equals("exit")) {
                    break;
                }
                System.out.println("Server: " + in.readLine());
            }
        }
    }
}
