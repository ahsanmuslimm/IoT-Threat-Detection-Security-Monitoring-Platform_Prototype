import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlertManagerTest {
    @Test
    void testGenerateAlert() {
        AlertManager manager = new AlertManager();
        assertTrue(manager.generateAlert("Test alert", ThreatType.DDOS));
    }

    @Test
    void testGenerateAlertWithNullMessage() {
        AlertManager manager = new AlertManager();
        assertFalse(manager.generateAlert(null, ThreatType.DDOS));
    }

    @Test
    void testGenerateAlertWithNullThreat() {
        AlertManager manager = new AlertManager();
        assertFalse(manager.generateAlert("Test alert", null));
    }
}
