package tr.com.sule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;
import tr.com.sule.service.CommonService;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author scinkir 9.10.2018
 */
@Service
//Using SchedulingConfigurer is necessary when implementing Trigger-based tasks, which are not supported by the @Scheduled annotation.
public class SchedulerService implements SchedulingConfigurer {

    @Autowired
    CommonService commonService;

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.setPoolSize(1);
        scheduler.initialize();
        return scheduler;
    }

//    @Override
//    public void configureTasks (ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addFixedDelayTask(() -> {
//            System.out.println("Running task : "+ LocalTime.now());
//
//        }, 500);
//    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(poolScheduler());
        //Using a Trigger you can calculate the next execution time on the fly.
        taskRegistrar.addTriggerTask(() -> commonService.loadConfigurations(), t -> {
            Calendar nextExecutionTime = new GregorianCalendar();
            Date lastActualExecutionTime = t.lastActualExecutionTime();
            nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
            nextExecutionTime.add(Calendar.MILLISECOND,
                    Integer.parseInt(commonService.getConfiguration("")));
            return nextExecutionTime.getTime();
        });
    }

   /* @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(poolScheduler());
        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                // Do not put @Scheduled annotation above this method, we don't need it anymore.
                commonService.loadConfigurations();
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                Calendar nextExecutionTime = new GregorianCalendar();
                Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
                nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
                nextExecutionTime.add(Calendar.MILLISECOND, Integer.parseInt(commonService.getConfiguration(Constants.CONFIG_KEY_REFRESH_RATE_CONFIG).getConfigValue()));
                return nextExecutionTime.getTime();
            }
        });
    }*/


}
