import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.observers.ResourceSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class DisposableSample {
	public static void main(String args[]) throws InterruptedException {

		outsideDisposal();
		compositeDisposal();
		System.out.println("Done");

	}

	private static void outsideDisposal() throws InterruptedException {
		Observable<Long> o = Observable.intervalRange(0, 100, 0, 100, TimeUnit.MILLISECONDS);

		// ResourceObserver for handling dispose() outside the Observer scope
		DisposableObserver<Long> r = ObserverFactory.getDisposableObserver();
		o.subscribeWith(r);
		Thread.sleep(1000);

		// No events are read after this [10 events]
		r.dispose();
		Thread.sleep(1000);

		System.out.println("\n");
	}

	private static void compositeDisposal() throws InterruptedException {
		
		Observable<Long> o = Observable.intervalRange(0, 100, 0, 100, TimeUnit.MILLISECONDS);
		
		//Collection of disposable - used to dispose all of them together
		CompositeDisposable c = new CompositeDisposable();
		
		DisposableObserver<Long> r1 = ObserverFactory.getDisposableObserver();
		DisposableObserver<Long> r2 = ObserverFactory.getDisposableObserver();
		
		Disposable d1 = o.subscribeWith(r1);
		Disposable d2 = o.subscribeWith(r2);
		
		//Create an CompositeDisposable, which holds all disposable
		c.addAll(d1,d2);
		Thread.sleep(1000);
		
		// Disposing all observers. No events are read after this [10 events]
		c.dispose();
		Thread.sleep(1000);


	}

}
