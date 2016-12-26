package ru.myCompany.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.myCompany.authService.AuthServiceManager;
import ru.myCompany.request.FindUserInfoRequest;
import ru.myCompany.siebel.SiebelManager;
import ru.myCompany.siebel.ws.getClientInfo.userInterface.GetClientInfoOutput;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Timofey on 25.12.2016.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FindUserInfoProcess implements Process<GetClientInfoOutput> {

    private static final Logger log = LoggerFactory.getLogger(FindUserInfoProcess.class);
    private FindUserInfoRequest request;

    @Autowired
    private AuthServiceManager authServiceManager;
    @Autowired
    private SiebelManager siebelManager;

    public FindUserInfoProcess(FindUserInfoRequest request) {
        this.request = request;
    }

    @Override
    public GetClientInfoOutput run() {
        checkInput();
        checkUserAccess();
        return callSiebel();
    }

    private void checkInput() {
        log.trace("Check input parameters...");
        Objects.requireNonNull(request, getExceptionText("request"));
        Objects.requireNonNull(request.getSessionId(), getExceptionText("sessionId"));
        Objects.requireNonNull(request.getUserSiebelId(), getExceptionText("userSiebelId"));
        log.trace("Input parameters was checked");
    }

    private Supplier<String> getExceptionText(String parameterName) {
        return () -> "Input parameter " + parameterName + " is null";
    }

    private void checkUserAccess() {
        log.trace("Check user access...");
        List<String> roles = authServiceManager.validateAndGetRoles(request.getSessionId());
        if(!authServiceManager.isAdmin(roles)) {
            throw new RuntimeException("Access is denied. Method caller hasn't role admin");
        }
        log.trace("Access is allowed ");
    }

    private GetClientInfoOutput callSiebel() {
        log.trace("Receipt user information from siebel...");
        GetClientInfoOutput response = siebelManager.findUserBySiebelId(request.getUserSiebelId());
        log.trace("User information was obtained...");
        return response;
    }

}
