package tr.com.sule.service;

/**
 * @author scinkir 9.10.2018
 */
public interface ICommonService {

    public void setScheduleRun(int scheduleRun);

    public void loadConfigurations();

    String getConfiguration(String s);
}
