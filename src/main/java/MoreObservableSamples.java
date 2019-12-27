import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;

public class MoreObservableSamples {

	static int count = 3;

	public static void main(String args[]) {
		emptyObservable();
		rangeObservable();
		deferObservable();
		fromCallableObservable();
		singleObservable();
		System.out.println("Done");
	}

	// empty() returns an Observable that emits no items & immediately invokes
	// onComplete().
	private static void emptyObservable() {
		System.out.println("Inside " + new Throwable().getStackTrace()[0].getMethodName());
		Observable<Integer> o = Observable.empty();
		Observer<Integer> obs = ObserverFactory.getSlowObserver();
		o.subscribe(obs);

	}

	// range() returns an Observable that emits a sequence of Integers within a
	// specified range.
	private static void rangeObservable() {
		System.out.println("Inside " + new Throwable().getStackTrace()[0].getMethodName());
		Observable<Integer> o = Observable.range(5, 3);
		Observer<Integer> obs = ObserverFactory.getSlowObserver();
		o.subscribe(obs);
	}

	private static void deferObservable() {

		System.out.println("Inside " + new Throwable().getStackTrace()[0].getMethodName());

		// Using callable to 'sort of' lazily initialize Observable
		Callable<Observable<Integer>> callable = new Callable<Observable<Integer>>() {
			@Override
			public Observable<Integer> call() throws Exception {
				System.out.println("Creating observable with count " + count);
				return Observable.range(5, count);
			}
		};

		Observable<Integer> o = Observable.defer(callable);

		Observer<Integer> obs = ObserverFactory.getSlowObserver();
		o.subscribe(obs);

		// Change in value of variable is reflected in the new observer
		count = 4;
		Observer<Integer> obs2 = ObserverFactory.getSlowObserver();
		o.subscribe(obs2);
	}

	private static void fromCallableObservable() {
		System.out.println("Inside " + new Throwable().getStackTrace()[0].getMethodName());

		// Using callable to lazily initialize "the value" of Observable

		// Real life use case - convert an Object created by invoking a DB call to
		// Observable
		// (Another way of creating the above Observable could be using .just() but then
		// the DB call would be sync )
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("Making a DB call");
				Thread.sleep(1000);
				return 1;
			}
		};

		Observable<Integer> o = Observable.fromCallable(callable);

		Observer<Integer> obs = ObserverFactory.getSlowObserver();
		o.subscribe(obs);

	}

	// Single observable emits a single object
	private static void singleObservable() {
		System.out.println("Inside " + new Throwable().getStackTrace()[0].getMethodName());

		Single<Integer> o = Single.just(1);

		// Needs Single Observer. SingleObserver does not have onComplete method()
		SingleObserver<Integer> obs = ObserverFactory.getSingleObserver();
		o.subscribe(obs);
	}

	private static void mayBeObservable() {
		// 'sort of' like Optional
		Maybe<Integer> o1 = Maybe.just(1);
		Maybe<Integer> o2 = Maybe.empty();

		// TODO finish
	}

	private static void completableObservable() {
		// Just sends 'onComplete' . No actual event will be sent
		Completable.fromSingle(Single.just(1));

		// TODO finish
	}
}
