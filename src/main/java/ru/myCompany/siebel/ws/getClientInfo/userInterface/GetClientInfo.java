
package ru.myCompany.siebel.ws.getClientInfo.userInterface;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "GetClientInfo", targetNamespace = "http://siebel.com/CustomUI")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
        ru.myCompany.siebel.ws.getClientInfo.userInterface.ObjectFactory.class,
        ru.myCompany.siebel.ws.getClientInfo.data.ObjectFactory.class
})
public interface GetClientInfo {


    /**
     * 
     * @param getClientInfoInput
     * @return
     *     returns com.siebel.customui.GetClientInfoOutput
     */
    @WebMethod(operationName = "GetClientInfo", action = "document/http://siebel.com/CustomUI:GetClientInfo")
    @WebResult(name = "GetClientInfo_Output", targetNamespace = "http://siebel.com/CustomUI", partName = "GetClientInfo_Output")
    public GetClientInfoOutput getClientInfo(
            @WebParam(name = "GetClientInfo_Input", targetNamespace = "http://siebel.com/CustomUI", partName = "GetClientInfo_Input")
            GetClientInfoInput getClientInfoInput);

}