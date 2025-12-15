import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

class RuleEngineTest {
    @Test
    void testEvaluateRuleTriggered() {
        RuleEngine engine = new RuleEngine();
        Rule rule = new Rule("DDoS_Rule", 0.8);
        List<Rule> rules = Arrays.asList(rule);
        Features features = new Features(1500, 0.9);
        assertTrue(engine.evaluate(features, rules));
    }

    @Test
    void testEvaluateRuleNotTriggered() {
        RuleEngine engine = new RuleEngine();
        Rule rule = new Rule("DDoS_Rule", 0.8);
        List<Rule> rules = Arrays.asList(rule);
        Features features = new Features(500, 0.1);
        assertFalse(engine.evaluate(features, rules));
    }

    @Test
    void testEvaluateWithNullFeatures() {
        RuleEngine engine = new RuleEngine();
        Rule rule = new Rule("DDoS_Rule", 0.8);
        List<Rule> rules = Arrays.asList(rule);
        assertFalse(engine.evaluate(null, rules));
    }

    @Test
    void testEvaluateWithNullRules() {
        RuleEngine engine = new RuleEngine();
        Features features = new Features(500, 0.1);
        assertFalse(engine.evaluate(features, null));
    }
}
