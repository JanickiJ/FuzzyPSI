import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

import java.util.List;

public class Fuzzy {
    private final FIS fis;
    private final FuzzyRuleSet fuzzyRuleSet;
    private final List<String> sensorsNames = List.of("x", "y", "vx", "vy", "ax", "ay");

    public Fuzzy() {
        String fileName = "src/main/resources/fuzzy_rocket.fcl";
        this.fis = FIS.load(fileName, false);
        fuzzyRuleSet = fis.getFuzzyRuleSet();
    }


    public List<Double> evaluate(List<Double> sensors) {
        for (int i = 0; i < sensorsNames.size(); i++) {
            fuzzyRuleSet.setVariable(sensorsNames.get(i), sensors.get(i));
        }
        fuzzyRuleSet.evaluate();

        System.out.println(fuzzyRuleSet.getVariable("axChange").getLatestDefuzzifiedValue());

        fuzzyRuleSet.getRules().forEach(System.out::println);
        return List.of(fuzzyRuleSet.getVariable("axChange").getLatestDefuzzifiedValue(), fuzzyRuleSet.getVariable("ayChange").getLatestDefuzzifiedValue());
    }

    public FuzzyRuleSet getFuzzyRuleSet() {
        return fuzzyRuleSet;
    }
}
