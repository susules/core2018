package tr.com.sule.service;

import org.springframework.stereotype.Service;

/**
 * @author scinkir 9.10.2018
 */
@Service
public class CommonService implements ICommonService{

    private int scheduleRun=100000;

    public void loadConfigurations() {
        System.out.println("load configuration");
    }

    @Override
    public String getConfiguration(String s) {
        return String.valueOf(scheduleRun);
    }

    public void setScheduleRun(int scheduleRun) {
        this.scheduleRun = scheduleRun;
    }
}
