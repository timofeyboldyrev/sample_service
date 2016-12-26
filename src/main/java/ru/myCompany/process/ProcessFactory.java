package ru.myCompany.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by Timofey on 25.12.2016.
 */
@Service
public class ProcessFactory {

    @Autowired
    private ApplicationContext context;

    /**
     * @param <P> Process
     * @param <R> Response
     * @return
     */
    public <P extends Process,R> P getProcess(Class<P> processClass, R request) {
        try {
            return context.getBean(processClass, request);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while trying to create bean of " + processClass.getName(), e);
        }
    }
}
