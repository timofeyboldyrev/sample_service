package ru.myCompany.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Timofey on 25.12.2016.
 */
@Configuration
@ComponentScan("ru.myCompany")
public class Context {

    public static final String BAEN_URL_AUTH_SERVICE_VALIDATE = "url.auth-service.validate";
    public static final String BEAN_URL_SIEBEL_GET_CLIENT_INFO = "url.siebel.getClientInfo";
    private static final String PARAMETER_AUTH_SERVICE_ADDRESS = "auth-service.address";
    private static final String PARAMETER_AUTH_SERVICE_VALIDATE = "auth-service.validate";
    private static final String PARAMETER_SIEBEL_GET_CLIENT_INFO_URL = "siebel.getClientInfo.url";

    @Bean @Qualifier(BAEN_URL_AUTH_SERVICE_VALIDATE)
    public URL urlAuthServiceValidate(
            @Value("${" + PARAMETER_AUTH_SERVICE_ADDRESS + "}") String authServiceAddress,
            @Value("${" + PARAMETER_AUTH_SERVICE_VALIDATE + "}") String validateMethod) throws MalformedURLException {
        return new URL(authServiceAddress + validateMethod);
    }

    @Bean @Qualifier(BEAN_URL_SIEBEL_GET_CLIENT_INFO)
    public URL urlSiebelGetClientInfo(
            @Value("${" + PARAMETER_SIEBEL_GET_CLIENT_INFO_URL + "}") String siebelUrlAddress) throws MalformedURLException {
        return new URL(siebelUrlAddress);
    }

}
