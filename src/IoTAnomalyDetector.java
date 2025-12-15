import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


class Constants {
    public static final int MIN_PACKET_SIZE = 500;
    public static final int MAX_PACKET_SIZE = 2500;
    public static final double ANOMALY_THRESHOLD = 0.8;
    public static final double HIGH_ANOMALY_SCORE = 0.9;
    public static final double BEHAVIOR_SCORE_ADJUSTMENT = 0.3;
}


class Packet {
    private String data;
    private String sourceIP;
    private String destinationIP;
    private int size;

    public Packet(String data, String sourceIP, String destinationIP, int size) {
        this.data = data;
        this.sourceIP = sourceIP;
        this.destinationIP = destinationIP;
        this.size = size;
    }

    public String getData() { return data; }
    public String getSourceIP() { return sourceIP; }
    public String getDestinationIP() { return destinationIP; }
    public int getSize() { return size; }
}

class Features {
    private int packetSize;
    private double anomalyScore;

    public Features(int packetSize, double anomalyScore) {
        this.packetSize = packetSize;
        this.anomalyScore = anomalyScore;
    }

    public int getPacketSize() { return packetSize; }
    public double getAnomalyScore() { return anomalyScore; }
}


class BehaviorProfile {
    private int avgPacketSize;

    public BehaviorProfile(int avgPacketSize) {
        this.avgPacketSize = avgPacketSize;
    }

    public int getAvgPacketSize() { return avgPacketSize; }
}

enum ThreatType {
    NORMAL, DDOS
}

class DashboardWebSocketServer extends WebSocketServer {
    private List<WebSocket> connections = new CopyOnWriteArrayList<>();

    public DashboardWebSocketServer(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        connections.add(conn);
        System.out.println("Client connected: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        connections.remove(conn);
        System.out.println("Client disconnected: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Message: " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started on port " + getPort());
        setConnectionLostTimeout(0); // keep connections alive
    }

    public void broadcast(String data) {
        for (WebSocket conn : connections) {
            conn.send(data);
        }
    }
}

public class IoTAnomalyDetector {

    public static void main(String[] args) throws Exception {
        DashboardWebSocketServer server = new DashboardWebSocketServer(8080);
        server.start();
        System.out.println("Server is running...");

        BehaviorProfile profile = new BehaviorProfile(500);

        int packetCount = 0;

       
        while(true) {
            packetCount++;

            String sourceIP = "192.168.1." + (packetCount % 10 + 1);
            String destIP = "192.168.1.100";
            int packetSize = Constants.MIN_PACKET_SIZE + (int)(Math.random() * (Constants.MAX_PACKET_SIZE - Constants.MIN_PACKET_SIZE));
            Packet packet = new Packet("packet_data_" + packetCount, sourceIP, destIP, packetSize);

           
            double anomalyScore = packetSize > Constants.MAX_PACKET_SIZE / 2 ? Constants.HIGH_ANOMALY_SCORE : 0.1;
            if(packetSize > profile.getAvgPacketSize() * 2) anomalyScore += Constants.BEHAVIOR_SCORE_ADJUSTMENT;
            if(anomalyScore > 1.0) anomalyScore = 1.0;

            ThreatType threat = anomalyScore >= Constants.HIGH_ANOMALY_SCORE ? ThreatType.DDOS : ThreatType.NORMAL;

     
            System.out.printf("[Packet %d] %s -> %s | Size: %d | Anomaly: %.2f | Threat: %s%n",
                    packetCount, sourceIP, destIP, packetSize, anomalyScore, threat);

         
            String jsonData = String.format(
                    "{\"packetCount\": %d, \"sourceIP\": \"%s\", \"destIP\": \"%s\", \"packetSize\": %d, \"anomalyScore\": %.2f, \"threat\": \"%s\"}",
                    packetCount, sourceIP, destIP, packetSize, anomalyScore, threat
            );
            server.broadcast(jsonData);

            Thread.sleep(2000); 
        }
    }
}
