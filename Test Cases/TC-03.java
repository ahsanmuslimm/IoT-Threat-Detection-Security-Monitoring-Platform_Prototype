import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BehaviorAnalyzerTest {
    @Test
    void testAnalyzeBehavior() {
        BehaviorAnalyzer analyzer = new BehaviorAnalyzer();
        BehaviorProfile profile = new BehaviorProfile(500, 10.0, 0.8);
        Features features = new Features(1200, 0.5);
        double score = analyzer.analyze(features, profile);
        assertEquals(0.8, score, 0.01);
    }

    @Test
    void testAnalyzeWithNullFeatures() {
        BehaviorAnalyzer analyzer = new BehaviorAnalyzer();
        BehaviorProfile profile = new BehaviorProfile(500, 10.0, 0.8);
        assertThrows(IllegalArgumentException.class, () -> analyzer.analyze(null, profile));
    }

    @Test
    void testAnalyzeWithNullProfile() {
        BehaviorAnalyzer analyzer = new BehaviorAnalyzer();
        Features features = new Features(1200, 0.5);
        assertThrows(IllegalArgumentException.class, () -> analyzer.analyze(features, null));
    }
}
