import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeatureExtractorTest {
    @Test
    void testExtractFeatures() {
        FeatureExtractor extractor = new FeatureExtractor();
        Packet packet = new Packet("test_data", "192.168.1.1", "192.168.1.100", 1500);
        Features features = extractor.extract(packet);
        assertNotNull(features);
        assertEquals(1500, features.getPacketSize());
        assertEquals(0.9, features.getAnomalyScore(), 0.01);
    }

    @Test
    void testExtractFeaturesWithNullPacket() {
        FeatureExtractor extractor = new FeatureExtractor();
        assertThrows(IllegalArgumentException.class, () -> extractor.extract(null));
    }
}
