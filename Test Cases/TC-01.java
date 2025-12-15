import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PacketListenerTest {
    @Test
    void testReceiveValidPacket() {
        PacketListener listener = new PacketListener();
        Packet packet = new Packet("test_data", "192.168.1.1", "192.168.1.100", 500);
        assertTrue(listener.receive(packet));
    }

    @Test
    void testReceiveNullPacket() {
        PacketListener listener = new PacketListener();
        assertFalse(listener.receive(null));
    }

    @Test
    void testReceivePacketWithNullData() {
        PacketListener listener = new PacketListener();
        Packet packet = new Packet(null, "192.168.1.1", "192.168.1.100", 500);
        assertFalse(listener.receive(packet));
    }
}
