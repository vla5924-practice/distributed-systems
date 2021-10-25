package Player;

import Models.Turn;
import Models.TurnResult;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class PlayerService extends Thread {
    ServerSocket server;
    Socket socket = new Socket();
    Player player;
    int port;
    boolean connected = false;
    boolean iAmHost = false;

    ObjectInputStream istream;
    ObjectOutputStream ostream;

    public PlayerService(Player player_, int port_) {
        player = player_;
        port = port_;
    }

    private void debugLog(String message) {
        System.out.println(message);
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean isHost() { return iAmHost; }

    @Override
    public void run() {
        try {
            try {
                debugLog("Trying to connect");
                socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), port), 1000);
                connected = true;
                debugLog("Connected to existing socket");
            } catch (SocketTimeoutException e) {
                player.onWaitingOtherPlayer();
                debugLog("You will be the host");
                iAmHost = true;
                server = new ServerSocket(port, 0, InetAddress.getLocalHost());
                socket = server.accept();
                connected = true;
                debugLog("Connected to other player");
            }
            ostream = new ObjectOutputStream(socket.getOutputStream());
            istream = new ObjectInputStream(socket.getInputStream());
            debugLog("Streams are defined");
        } catch (IOException e) {
            e.printStackTrace();
        }
        debugLog("Connection established");
        player.onConnectionEstablished();
        do {
            Object obj;
            try {
                obj = istream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                connected = false;
                player.onDisconnection();
                break;
            }
            if (obj instanceof Turn) {
                processTurn((Turn) obj);
            } else if (obj instanceof TurnResult) {
                processTurnResult((TurnResult) obj);
            } else {
                debugLog("Unknown type");
            }
        } while (true);
    }

    private void processTurn(Turn turn) {
        TurnResult turnResult = player.processOppTurn(turn);
        try {
            ostream.writeObject(turnResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processTurnResult(TurnResult turnResult) {
        player.processOppTurnResult(turnResult);
    }

    public void sendTurn(Turn turn) {
        try {
            ostream.writeObject(turn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (socket != null && !socket.isClosed())
                socket.close();
            if (server != null && !server.isClosed())
                server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connected = false;
        player.onDisconnection();
    }
}
