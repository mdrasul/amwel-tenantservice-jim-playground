package notificationServicePipeline;

import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.util.EntityPropertiesBuilders;
import org.junit.Test;
import ampPipeline.Pipeline;

public class PlanSpecTest {
    @Test
    public void checkYourPlanOffline() throws PropertiesValidationException {
        Plan plan = new Pipeline().createPlan();
        EntityPropertiesBuilders.build(plan);
    }
}
