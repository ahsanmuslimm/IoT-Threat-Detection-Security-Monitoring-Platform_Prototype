import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ThreatDetectorTest {
    @Test
    void testDetectDDoS() {
        ThreatDetector detector = new ThreatDetector();
        Features features = new Features(2000, 0.9);
        assertEquals(ThreatType.DDOS, detector.detect(features));
    }

    @Test
    void testDetectNormal() {
        ThreatDetector detector = new ThreatDetector();
        Features features = new Features(500, 0.1);
        assertEquals(ThreatType.NORMAL, detector.detect(features));
    }

    @Test
    void testDetectWithNullFeatures() {
        ThreatDetector detector = new ThreatDetector();
        assertThrows(IllegalArgumentException.class, () -> detector.detect(null));
    }
}
