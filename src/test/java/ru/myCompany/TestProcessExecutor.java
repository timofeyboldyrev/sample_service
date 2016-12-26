package ru.myCompany;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.myCompany.process.*;
import ru.myCompany.process.Process;

/**
 * Created by t.boldyrev on 26.12.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class)
public class TestProcessExecutor {

    private static final String TEST_RESPONSE = "Test response";

    @Autowired
    private ProcessExecutor processExecutor;

    @Test
    public void run() {
        String response = processExecutor.execute("test", TestProcess.class, new Object());
        assertEquals(response, TEST_RESPONSE);
    }

    @Service
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class TestProcess implements Process<String> {

        private Object request;

        public TestProcess(Object request) {
            this.request = request;
        }

        @Override
        public String run() {
            return TEST_RESPONSE;
        }
    }
}
