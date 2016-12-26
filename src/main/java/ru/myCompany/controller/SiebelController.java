package ru.myCompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.myCompany.process.FindUserInfoProcess;
import ru.myCompany.process.ProcessExecutor;
import ru.myCompany.request.FindUserInfoRequest;
import ru.myCompany.siebel.ws.getClientInfo.userInterface.GetClientInfoOutput;

/**
 * Created by Timofey on 25.12.2016.
 */
@RestController
@RequestMapping("siebel")
public class SiebelController {

    private static final String METHOD_FIND_USER_INFO = "/findUserInfo";
    @Autowired
    private ProcessExecutor processExecutor;

    @RequestMapping(value = METHOD_FIND_USER_INFO, method = RequestMethod.POST)
    public GetClientInfoOutput findUserInfo(@RequestBody FindUserInfoRequest request) {
        return processExecutor.execute(METHOD_FIND_USER_INFO, FindUserInfoProcess.class, request);
    }
}
