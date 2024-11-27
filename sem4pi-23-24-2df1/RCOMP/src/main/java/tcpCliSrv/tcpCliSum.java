package tcpCliSrv;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class tcpCliSum {

    public static void main(String[] args) {

        Socket sock = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;


        try {
            sock = new Socket("127.0.0.1", 8080);


            inputStreamReader = new InputStreamReader(sock.getInputStream());
            outputStreamWriter = new OutputStreamWriter(sock.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                String msgToSend = scanner.nextLine();
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                System.out.println("Server: " + bufferedReader.readLine());

                if (msgToSend.equalsIgnoreCase("bye")) {
                    break;
                }

            }
        } catch(IOException ex){
                System.out.println("Failed to establish TCP connection");
                System.exit(1);
            }finally{
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }catch (IOException ex){
                System.out.println("Failed to close bufferedReader and bufferedWriter");
            }
        }
    }
}