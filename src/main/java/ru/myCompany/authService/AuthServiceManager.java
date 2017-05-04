package ru.myCompany.authService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.myCompany.config.Context;
import ru.myCompany.http.HttpApiCaller;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Timofey on 25.12.2016.
 */
@Service
public class AuthServiceManager {

    private static final String HEADER_SESSION_ID = "sessionID";
    private static final String ADMIN_ROLE = "admin";
    @Autowired
    private HttpApiCaller httpApiCaller;
    @Autowired @Qualifier(Context.BEAN_URL_AUTH_SERVICE_VALIDATE)
    private URL authServiceUrl;

    public List<String> validateAndGetRoles(String sessionId) {
        ValidateResponse validateResponse;
        try {
            Map<String, String> header = Collections.singletonMap(HEADER_SESSION_ID, sessionId);
            validateResponse = httpApiCaller.sendGet(authServiceUrl, header, ValidateResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while check user validation", e);
        }
        if(validateResponse == null || !validateResponse.isSuccess()) {
            throw new RuntimeException("The user didn't pass the validation. " +
                    "Server response: " + validateResponse + ". SessionId: " + sessionId);
        }
        return validateResponse.getRoles()==null? Collections.emptyList() : Arrays.asList(validateResponse.getRoles());
    }

    public boolean isAdmin(List<String> roles) {
        return roles!=null && roles.stream().anyMatch(ADMIN_ROLE::equals);
    }

}
