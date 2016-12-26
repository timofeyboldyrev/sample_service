package ru.myCompany.siebel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myCompany.siebel.ws.getClientInfo.userInterface.GetClientInfoInput;
import ru.myCompany.siebel.ws.getClientInfo.userInterface.GetClientInfoOutput;

/**
 * Created by Timofey on 25.12.2016.
 */
@Service
public class SiebelManager {

    @Autowired
    private SiebelWsCaller siebelWsCaller;

    public GetClientInfoOutput findUserBySiebelId(String siebelId) {
        GetClientInfoInput request = new GetClientInfoInput();
        request.setClientId(siebelId);
        return siebelWsCaller.getClientInfo(request);
    }

}
