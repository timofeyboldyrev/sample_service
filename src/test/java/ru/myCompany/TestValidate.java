package ru.myCompany;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.myCompany.authService.AuthServiceManager;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by t.boldyrev on 26.12.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class)
public class TestValidate {

    @Autowired
    private AuthServiceManager authServiceManager;

    @Test(expected=RuntimeException.class)
    public void fallValidate() {
        authServiceManager.validateAndGetRoles("failSessionId");
    }

    @Test
    public void isAdmin() {
        assertFalse(authServiceManager.isAdmin(null));
        assertFalse(authServiceManager.isAdmin(Collections.emptyList()));
        assertFalse(authServiceManager.isAdmin(Collections.singletonList("user1")));
        assertFalse(authServiceManager.isAdmin(Arrays.asList("user1", "user2")));
        assertTrue(authServiceManager.isAdmin(Collections.singletonList("admin")));
        assertTrue(authServiceManager.isAdmin(Arrays.asList("user1", "user2", "admin")));
    }

}
