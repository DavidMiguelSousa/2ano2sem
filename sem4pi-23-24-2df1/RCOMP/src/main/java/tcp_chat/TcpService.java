package tcp_chat;

import domain.MessageCode;
import org.antlr.v4.runtime.misc.Pair;

public class TcpService {

    public static void connectClient(String ip) throws Exception {
        TcpChatCli.connect(ip);
    }

    public String communicate(MessageCode code, String data1, String data2) throws Exception {

        //prepare the message
        final byte versionBytes = 1;
        final byte codeBytes = (byte) code.code();
        final byte[] data1Bytes = data1.getBytes();
        final byte[] data2Bytes = data2.getBytes();

        //send the message
        TcpChatCli.sendMessage(versionBytes, codeBytes, data1Bytes, data2Bytes);

        //return the received response from the server
        return TcpChatCli.receiveMessage();
    }

    public Pair<MessageCode, String> responseCode(MessageCode messageCode, String username, String password) throws Exception {
        String response = communicate(messageCode, username, password);
        if (response.contains("Code: 2")) {
            return new Pair<>(MessageCode.ACK, response);
        } else if (response.contains("Code: 3")) {
            return new Pair<>(MessageCode.ERR, response);
        } else {
            return new Pair<>(MessageCode.ERR, "Unknown error");
        }
    }

    public static void disconnectClient() throws Exception {
        TcpChatSrv.remCli(TcpChatCli.socket());
    }

}
