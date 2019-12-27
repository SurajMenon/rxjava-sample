import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

// (??) Hot Observable - Observable decides the speed. Stored in buffer .. so need more memory
/** 
 * Hot observables emit values independent of individual subscriptions. 
 * They have their own timeline and events occur whether someone is listening or not. 
 */
public class HotObservable {

	public static void main(String args[]) throws InterruptedException {

		//publish() converts Cold observable to Hot
		ConnectableObservable<Long> cold = Observable.intervalRange(0, 10, 0, 100, TimeUnit.MILLISECONDS).publish();
		
		//ConnectableObservable only starts emmiting after connect() is called.
		//Hot Observable have single state.
		cold.connect();

		cold.subscribe(i -> System.out.println("First: " + i));
		Thread.sleep(500);

		//Second will miss the first 5 events
		cold.subscribe(i -> System.out.println("Second: " + i));
		
		Thread.sleep(5000);
		System.out.println("Done");
		
	}

}
