package tcpCliSrv;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class tcpSrvSum {


    public static void main(String args[]) throws Exception {

        Socket sock = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(8080);

        while (true) {
            try {

                sock = serverSocket.accept();

                inputStreamReader = new InputStreamReader(sock.getInputStream());
                outputStreamWriter = new OutputStreamWriter(sock.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                Scanner scanner = new Scanner(System.in);

                while (true) {

                    String msgToSend = bufferedReader.readLine();

                    System.out.println("Client: " + msgToSend);
                    bufferedWriter.write("Msg Received.");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (msgToSend.equalsIgnoreCase("bye")) {
                        break;
                    }

                }

                serverSocket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();

            }catch (IOException ex){
                System.out.println("Failed to open server socket");
                System.exit(1);
            }



        }
    }
}

