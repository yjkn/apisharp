package com.apisharp.actuator.jmeter;

import com.apisharp.core.model.TestStep;
import org.springframework.stereotype.Component;

@Component
public class JMeterPlanBuilder {
    public String buildPlan(TestStep step, int virtualUsers, int durationSeconds) {
        return "<jmeterTestPlan version=\"1.2\" properties=\"5.0\" jmeter=\"APISharp\">"
                + "<hashTree><TestPlan testname=\"" + escape(step.name()) + "\" enabled=\"true\"/>"
                + "<ThreadGroup testname=\"APISharp Thread Group\" enabled=\"true\">"
                + "<stringProp name=\"ThreadGroup.num_threads\">" + Math.max(1, virtualUsers) + "</stringProp>"
                + "<stringProp name=\"ThreadGroup.duration\">" + Math.max(1, durationSeconds) + "</stringProp>"
                + "</ThreadGroup>"
                + "<HTTPSamplerProxy testname=\"" + escape(step.name()) + "\" enabled=\"true\">"
                + "<stringProp name=\"HTTPSampler.method\">" + step.method() + "</stringProp>"
                + "<stringProp name=\"HTTPSampler.path\">" + escape(step.url()) + "</stringProp>"
                + "</HTTPSamplerProxy></hashTree></jmeterTestPlan>";
    }

    private String escape(String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}
