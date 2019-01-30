package com.cristikang.summer.schedual.impl;

import com.cristikang.summer.schedual.ISchedualService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: sunkang
 * @CreateTime: 2019-01-30 15:14
 * @Description: ${Description}
 */
@Component
@EnableScheduling
@Slf4j
@Service("schedualService")
public class SchedualService implements ISchedualService {
    @Override
    @Scheduled(cron = "0 0/5 * * * ? ")
    public void testSchedual() {

    }
}
