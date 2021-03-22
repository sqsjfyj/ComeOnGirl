package com.qwj.girl.scheduling;

import com.qwj.girl.crawler.Actresses;
import com.qwj.girl.crawler.UncensoredActresses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ActressesScheduled {

    @Autowired
    private Actresses actresses;
    @Autowired
    private UncensoredActresses uncensoredActresses;

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 6)
    public void ActressesCome() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            final int index = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    if (index == 0) {
                        actresses.startGo();
                    } else {
                        uncensoredActresses.startGo();
                    }
                }
            });
        }
    }

}
