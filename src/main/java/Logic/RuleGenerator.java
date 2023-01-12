package Logic;

import Utils.Rule;

import java.util.List;

public interface RuleGenerator {
    List<List<Rule>> getAllRulesForThreads(int threads);
}
