package hello.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyBatchJob {

  /*
   Cron to print at every min start
   */
  //@Scheduled(cron = "*/30 * * * * *")
  public void printText(){
    System.out.println("Ashish Ashish Ashish Ashish Ashish Ashish");
  }
}
