import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class Subjects {
	/*
	 * Subject can act as both Observer & Observable
	 */

	public static void main(String args[]) {
		publishSubject();
		replaySubject();
	}

	// Like a Hot Observable
	private static void publishSubject() {
		PublishSubject<Integer> p = PublishSubject.create();
		p.subscribe(ObserverFactory.getSlowObserver("First:"));
		p.onNext(1);
		p.onNext(2);

		p.subscribe(ObserverFactory.getSlowObserver("Second:"));
		p.onNext(3);
		p.onNext(4);

		p.onComplete();
	}

	// Like a Cold Observable
	private static void replaySubject() {

		ReplaySubject<Integer> p1 = ReplaySubject.create();
		ReplaySubject<Integer> p2 = ReplaySubject.create();

		// p2 acting as an Observable
		// p1 acting as an Observer
		p2.subscribe(p1);

		p2.onNext(10);
		p2.onNext(11);

		p1.subscribe(ObserverFactory.getSlowObserver("First:"));
		p1.onNext(1);
		p1.onNext(2);

		p1.subscribe(ObserverFactory.getSlowObserver("Second:"));
		p1.onNext(3);
		p1.onNext(4);

		p1.onComplete();
	}

	// BehaviorSubject gets : last event + new events
	// AsyncSubject just gets the last event before onComplete (irrespective of when
	// it was subscribed)}
}
