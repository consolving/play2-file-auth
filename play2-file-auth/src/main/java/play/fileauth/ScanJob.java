/**
 * ScanJob
 * 31.07.2012
 * @author Philipp Haussleiter
 *
 */
package play.fileauth;

import java.util.concurrent.TimeUnit;

import play.libs.Akka;
import scala.concurrent.duration.Duration;

/**
 * Periodically Scan of user/group files.
 * 
 * @author Philipp Haußleiter
 */

public class ScanJob implements Runnable {
	@Override
	public void run() {
		FileAuth.scanUsers();
		FileAuth.scanGroups();
	}

	public static void schedule() {
		ScanJob job = new ScanJob();
		Akka.system()
				.scheduler()
				.schedule(Duration.create(200, TimeUnit.MILLISECONDS),
						Duration.create(5, TimeUnit.MINUTES), // run job every 5
						job, Akka.system().dispatcher());
	}
}
