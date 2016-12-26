package ru.myCompany.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myCompany.exception.BusinessException;

/**
 * Created by Timofey on 25.12.2016.
 */
@Service
public class ProcessExecutor {

    private static final Logger log = LoggerFactory.getLogger(ProcessExecutor.class);
    @Autowired
    private ProcessFactory processFactory;

    /**
     * @param <P> Process
     * @param <T> Request
     * @param <R> Response
     */
    public <P extends Process<R>,T,R> R execute(String methodName, Class<P> processClass, T request) {
        try {
            log.debug(methodName + " called. Request: " + request);
            P process = processFactory.getProcess(processClass, request);
            R response = process.run();
            log.debug(methodName + " finished. Response: " + response);
            return response;
        } catch (Exception e) {
            log.error("An error occurred while executing method " + methodName + ". Request: " + request, e);
            throw new BusinessException("Ошибка работы сервера. Обратитесь в службу технической поддержки");
        }
    }
}
