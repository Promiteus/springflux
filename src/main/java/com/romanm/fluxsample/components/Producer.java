package com.romanm.fluxsample.components;

import com.romanm.fluxsample.entities.SimpleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.emitter.Emitter;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@SuppressWarnings("deprecation")
@Component
public class Producer {
    private EmitterProcessor<SimpleTask> emitterProcessor;


    public Producer(EmitterProcessor<SimpleTask> emitterProcessor) {
        this.emitterProcessor = emitterProcessor;

    }

    public void sendData(List<SimpleTask> tasks) {

        tasks.forEach(simpleTask ->  {
            log.info(">> Sending messages: "+simpleTask);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            emitterProcessor.onNext(simpleTask);
        });
    }
}
