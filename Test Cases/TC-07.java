import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class DashboardWebSocketServerTest {
    @Test
    void testBroadcast() {
        DashboardWebSocketServer server = new DashboardWebSocketServer(new InetSocketAddress("localhost", 8080));
        WebSocket mockSocket = new MockWebSocket();
        server.connections.add(mockSocket);
        server.broadcast("test data");
        assertTrue(((MockWebSocket) mockSocket).messageSent);
    }

    private static class MockWebSocket implements WebSocket {
        public boolean messageSent = false;

        @Override
        public void send(String text) {
            messageSent = true;
        }

        // Mock implementations for other WebSocket methods
        @Override public void close() {}
        @Override public void close(int code) {}
        @Override public void close(int code, String reason) {}
        @Override public void send(byte[] data) {}
        @Override public void sendFrame(WebSocketFrame frame) {}
        @Override public InetSocketAddress getRemoteSocketAddress() { return null; }
        @Override public InetSocketAddress getLocalSocketAddress() { return null; }
        @Override public String getResourceDescriptor() { return null; }
        @Override public boolean isOpen() { return true; }
        @Override public boolean isClosing() { return false; }
        @Override public boolean isFlushAndClose() { return false; }
        @Override public boolean isClosed() { return false; }
    }
}
