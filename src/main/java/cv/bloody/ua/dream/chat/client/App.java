package cv.bloody.ua.dream.chat.client;

import cv.bloody.ua.dream.chat.client.packet.server.AuthPacket;
import cv.bloody.ua.dream.chat.client.packet.server.PacketImChatMessage;
import cv.bloody.ua.dream.chat.client.packet.server.RegistrationPacket;
import cv.bloody.ua.dream.chat.client.packet.server.ServerPacket;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    //o - auth , 1 = wait to auth , 2 - message
    public static AtomicInteger state;
    private static Network network;

    public static void main(String[] args) throws InterruptedException {
        network = new Network();
        state = new AtomicInteger(0);
        while (network.getChannel() == null) {
            Thread.sleep(500);
            System.out.println("Wait to connect to the server...");
        }
        startLogin();
    }

    public static void startMessage() {

    }

    public static void startLogin() throws InterruptedException {
        while (state.get() == 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter 1 - to login | 2 - to register | else - to leave");
            String line = scanner.nextLine();
            if (line.contains("1")) {
                System.out.println("Enter login: ");
                String login = scanner.nextLine();
                System.out.println("Enter password: ");
                String password = scanner.nextLine();
                System.out.println("SendPacket");
                if(state.get() != 2) {
                    state.set(1);
                    sendPacket(new AuthPacket(login, password));
                }
            }else if (line.contains("2")) {
                System.out.println("Enter login: ");
                String login = scanner.nextLine();
                System.out.println("Enter password: ");
                String password = scanner.nextLine();
                System.out.println("SendPacket");
                if(state.get() != 2) {
                    state.set(1);
                    sendPacket(new RegistrationPacket(login, password));
                }
            }else {
                System.out.println("Leave...");
                network.getChannel().close();
                System.out.println("Leave confirm");
                return;
            }
            int mil = 0;
            while (state.get() == 1) {
                if(mil > 120) {
                    System.out.println("Time out to packet");
                    state.set(0);
                    break;
                }else {
                    Thread.sleep(100);
                    mil++;
                }
            }
        }
        System.out.println("Ви успішно увійшли в систему, можете спілкуватись з користувачами:");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if(line.equalsIgnoreCase("/leave")) {
                System.out.println("Leave...");
                network.getChannel().close();
                System.out.println("Leave confirm");
                return;
            }else {
                sendPacket(new PacketImChatMessage(line));
            }
        }
    }

    public static void sendPacket(ServerPacket packet) {
        network.getChannel().writeAndFlush(packet.toJSON().toString());
    }
}
