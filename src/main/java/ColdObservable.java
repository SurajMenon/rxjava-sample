import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//Cold observables are observables that run their sequence when and if they are subscribed to. They present the sequence from the start to each subscriber. 
public class ColdObservable {
	public static void main(String args[]) throws InterruptedException {

		//Generating events from 0 to 10, one vent every 100 Milliseconds
		Observable<Long> cold = Observable.intervalRange(0, 10, 0, 100, TimeUnit.MILLISECONDS);

		//First Observer receives events immediately
		cold.subscribe(i -> System.out.println("First Observer: " + i));
		Thread.sleep(500);
		
		//Second Observer receives events one First Observer has received 5 events
		cold.subscribe(i -> System.out.println("Second Observer: " + i));
		Thread.sleep(5000);
		
		//Both Observer receive all events
		System.out.println("Done");
	}

}
