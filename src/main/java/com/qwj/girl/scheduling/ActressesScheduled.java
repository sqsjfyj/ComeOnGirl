package com.qwj.girl.scheduling;

import com.qwj.girl.crawler.mongo.GirlActresses;
import com.qwj.girl.crawler.mongo.GirlUncensoredActresses;
import com.qwj.girl.crawler.mysql.Actresses;
import com.qwj.girl.crawler.mysql.UncensoredActresses;
import com.qwj.girl.crawler.oracle.HookerActresses;
import com.qwj.girl.crawler.oracle.HookerUncensoredActresses;
import com.qwj.girl.crawler.redis.TeacherActresses;
import com.qwj.girl.crawler.redis.TeacherUncensoredActresses;
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
    @Autowired
    private GirlActresses girlActresses;
    @Autowired
    private GirlUncensoredActresses girlUncensoredActresses;
    @Autowired
    private TeacherActresses teacherActresses;
    @Autowired
    private TeacherUncensoredActresses teacherUncensoredActresses;
    @Autowired
    private HookerActresses hookerActresses;
    @Autowired
    private HookerUncensoredActresses hookerUncensoredActresses;

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 6)
    public void ActressesCome() {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 8; i++) {
            final int index = i;
            executorService.submit(() -> {
                if (index == 0) {
                    actresses.startGo();
                } else if (index == 1) {
                    uncensoredActresses.startGo();
                } else if (index == 2) {
                    girlActresses.startGo();
                } else if (index == 3) {
                    girlUncensoredActresses.startGo();
                } else if (index == 4) {
                    teacherActresses.startGo();
                } else if (index == 5) {
                    teacherUncensoredActresses.startGo();
                } else if (index == 6) {
                    hookerActresses.startGo();
                } else {
                    hookerUncensoredActresses.startGo();
                }
            });
        }
    }

}
