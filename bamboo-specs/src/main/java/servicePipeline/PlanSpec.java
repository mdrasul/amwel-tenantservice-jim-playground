package servicePipeline;

import ampPipeline.*;
import com.atlassian.bamboo.specs.api.BambooSpec;

@BambooSpec
public class PlanSpec {
    public static void main(final String[] args) throws Exception {
        new ampPipeline.Pipeline().run();
    }
}
