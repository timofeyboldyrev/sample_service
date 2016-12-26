package ru.myCompany.siebel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.myCompany.config.Context;
import ru.myCompany.siebel.ws.getClientInfo.userInterface.GetClientInfo;
import ru.myCompany.siebel.ws.getClientInfo.userInterface.GetClientInfoInput;
import ru.myCompany.siebel.ws.getClientInfo.userInterface.GetClientInfoOutput;
import ru.myCompany.siebel.ws.getClientInfo.userInterface.SpcIntegration;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.function.Function;

/**
 * Created by Timofey on 25.12.2016.
 */
@Service
public class SiebelWsCaller {

    private static final Logger log = LoggerFactory.getLogger(SiebelWsCaller.class);

    @Autowired @Qualifier(Context.BEAN_URL_SIEBEL_GET_CLIENT_INFO)
    private URL urlSiebelGetClientInfo;
    private SpcIntegration getClientInfoService;

    @PostConstruct
    public void initGetClientInfoService() {
        getClientInfoService = new SpcIntegration(urlSiebelGetClientInfo);
    }

    public GetClientInfoOutput getClientInfo(GetClientInfoInput request) {
        return call("GetClientInfo", createGetClientInfoServicePort()::getClientInfo, request);
    }

    private GetClientInfo createGetClientInfoServicePort() {
        return getClientInfoService.getGetClientInfo();
    }

    private <T, R> R call(String method, Function<T, R> function, T request) {
        try {
            log.trace("Call Siebel API " + method + "... Request: " + request);
            R response = function.apply(request);
            log.trace("Call Siebel API " + method + " was successful. Response: " + response + ". ");
            return response;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while calling Siebel API " + method +
                    ". Request: " + request, e);
        }
    }

}
