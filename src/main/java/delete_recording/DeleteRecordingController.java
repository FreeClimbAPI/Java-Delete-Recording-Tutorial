/* 
 * AFTER RUNNING PROJECT WITH COMMAND: 
 * `gradle build && java -Dserver.port=0080 -jar build/libs/gs-spring-boot-0.1.0.jar`
 * RUN CURL COMMAND:
 * `curl http://kohearn-ltm.wifi.cl.vail/deleteRecording`
 * EXPECT NOTHING TO BE RETURNED & THE OLD RECORDING SHOULD BE DELETED UNDER YOUR PERSEPHONY ACCOUNT, WHICH CAN BE FOUND VISUALLY IN PERSEPHONY DASHBOARD
*/

package main.java.delete_recording;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vailsys.persephony.api.PersyClient;
import com.vailsys.persephony.api.PersyException;

@RestController
public class DeleteRecordingController {

  @RequestMapping("/deleteRecording")
  public void deleteRecording() {
    PersyClient client;

    try {
      // Create PersyClient object
      String accountId = System.getenv("ACCOUNT_ID");
      String authToken = System.getenv("AUTH_TOKEN");
      client = new PersyClient(accountId, authToken);

      String recordingId = "RErecordingId"; // desired recordingId to be deleted
      client.recordings.delete(recordingId);
    } catch (PersyException pe) {
      System.out.println(pe.getMessage());
    }
  }
}