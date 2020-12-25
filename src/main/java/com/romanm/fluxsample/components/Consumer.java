package com.romanm.fluxsample.components;

import com.romanm.fluxsample.entities.SimpleTask;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.FlowAdapters;
import org.springframework.stereotype.Component;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
@SuppressWarnings("deprecation")
@Component
public class Consumer {
    private EmitterProcessor<SimpleTask> emitterProcessor;
    private Flux<SimpleTask> stream;

    public Consumer(EmitterProcessor<SimpleTask> emitterProcessor) {
        this.emitterProcessor = emitterProcessor;
        this.stream = emitterProcessor;

       // this.stream.doOnNext(simpleTask -> {log.info("<< I'v got some data: "+simpleTask);});

        this.Consume2();
    }

    private void Consume() {
        log.info("Consuming...");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("Subscribing...");
                stream.doOnNext(s -> {
                            log.info(s.getDescription());
                        }).subscribe();
            }
        });
        thread.start();
    }

    private void Consume2() {
        log.info("Consuming...");
        stream.delayElements(Duration.ofMillis(3000))
                        .doOnNext(s -> {
                            log.info(s.getDescription());
                        }).subscribe();
    }

}
