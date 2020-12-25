package com.romanm.fluxsample;

import com.romanm.fluxsample.components.Producer;
import com.romanm.fluxsample.entities.SimpleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class RxConfig {

  @SuppressWarnings("deprecation")
  @Bean
  EmitterProcessor<SimpleTask> processor() {
      EmitterProcessor<SimpleTask> stream = EmitterProcessor.create();
      stream.doOnError(c -> {log.error(c.getCause().getMessage());}).collectList();
      stream.doOnComplete(new Runnable() {
          @Override
          public void run() {
              log.warn("Transmittion was completed");
          }
      });
      return stream;
  }

  @Bean
  CommandLineRunner runFluxExample(Producer producer) {
      return args -> {
          List<SimpleTask> data = new ArrayList<SimpleTask>();
          data.add(new SimpleTask("Task-1"));
          data.add(new SimpleTask("Task-2"));
          data.add(new SimpleTask("Task-3"));
          data.add(new SimpleTask("Task-4"));
          data.add(new SimpleTask("Task-5"));
          data.add(new SimpleTask("Task-6"));
          producer.sendData(data);

          Thread.sleep(15000);
      };
  }

}
