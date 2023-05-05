package hm23;

import hm22.HeroDto;
import hm22.HeroFabric;
import hm22.HeroService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HeroServer {
    private static HeroService heroService;
    protected static  ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws IOException {

        heroService = HeroFabric.createService(null);
        try (var serverSocket = new ServerSocket(8080)) {
            System.out.println("Connection successful");

            while (true) {
                System.out.println("Waiting for connection...");
                var clientSocket = serverSocket.accept();
                System.out.println("Connection successful");
                System.out.println("Waiting for input...");
                executorService.execute(() -> clientHandle(clientSocket));
            }
        }
    }

    private static void clientHandle(Socket clientSocket) {

        try {
            var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            var out = new PrintWriter(clientSocket.getOutputStream());


            while (true) {
                String clientCommand = in.readLine();
                if (clientCommand == null) {
                    break;
                }
                System.out.println("Received: " + clientCommand);

                if (clientCommand.startsWith("name")) {
                    String[] tokens = clientCommand.split("//s");
                    String heroName = tokens[1];
                    HeroDto heroDto = heroService.getHeroes().stream()
                            .filter(hero -> hero.getName().equals(heroName))
                            .findAny()
                            .orElse(null);
                    if (heroDto != null) {
                        out.println(heroDto);
                    } else
                        out.println("Not found...");
                    
                    if (clientCommand.equalsIgnoreCase("exit")) {
                        out.println("Main server initiate exiting...");
                        break;
                    } else {
                        out.println("Wrong command! Try again!");
                    }
                }
            }
            in.close();
            out.close();
            clientSocket.close();
            System.out.println("Client disconnected");

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
