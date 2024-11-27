package tcp_chat;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

class TcpChatCli {
    static InetAddress serverIP;
    static final int port = 8080;

    static Socket sock;
    static DataOutputStream sOut;
    static DataInputStream sIn;

    private static String response;
    private static final Object lock = new Object();
    private static volatile boolean running = true;

    public static Socket socket() {
        return sock;
    }

    public static void connect(String ip) throws Exception {
        serverIP = InetAddress.getByName(ip);

        sock = new Socket(serverIP, port);
        sOut = new DataOutputStream(sock.getOutputStream());
        sIn = new DataInputStream(sock.getInputStream());

        System.out.println("Connected to server");

        new Thread(TcpChatCli::listenForResponse).start();
    }

    public static void sendMessage(byte version, byte code, byte[] data1, byte[] data2) throws IOException, InterruptedException {
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        message.write(version);
        message.write(code);

        if (data1 != null) {
            byte data1lenL = (byte) (data1.length & 0xFF);
            byte data1lenM = (byte) ((data1.length >> 8) & 0xFF);
            message.write(data1lenL); // DATA1_LEN_L
            message.write((data1lenM) & 0xFF); // DATA1_LEN_M
            message.write(data1);
        } else {
            message.write(0);
            message.write(0);
        }

        if (data2 != null) {
            byte data2lenL = (byte) (data2.length & 0xFF);
            byte data2lenM = (byte) ((data2.length >> 8) & 0xFF);
            message.write(data2lenL); // DATA2_LEN_L
            message.write((data2lenM) & 0xFF); // DATA2_LEN_M
            message.write(data2);
        } else {
            message.write(0);
            message.write(0);
        }

        message.write(0); //end of message (first zero)
        message.write(0); //end of message (second zero)

        synchronized (lock) {
            response = ""; //clear previous response (if any)
            sOut.write(message.toByteArray());
            sOut.flush();
            lock.wait(); //wait for the server response
        }
    }

    public static String receiveMessage() throws InterruptedException {
        synchronized (lock) {
            while (response == null) {
                lock.wait();
            }
            return response;
        }
    }

    public static void listenForResponse() {
        while (running) {
            try {
                int version = sIn.read();
                int code = sIn.read();
                int data1LenL = sIn.read();
                int data1LenM = sIn.read();
                int data1Len = data1LenL + (data1LenM << 8);
                byte[] data1 = new byte[data1Len];
                sIn.readFully(data1, 0, data1Len);

                int data2LenL = sIn.read();
                int data2LenM = sIn.read();
                int data2Len = data2LenL + (data2LenM << 8);
                byte[] data2 = new byte[data2Len];
                sIn.readFully(data2, 0, data2Len);

                //read the end of message bytes
                sIn.read(); //should be 0
                sIn.read(); //should be 0

                synchronized (lock) {
                    response = "Version: " + version + ", Code: " + code + ", Data1: " + new String(data1) + ", Data2: " + new String(data2) + "\n";
                    lock.notifyAll(); //notify waiting threads that response is available
                }

            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }
        }

        //client socket is closed outside the loop
        try {
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}