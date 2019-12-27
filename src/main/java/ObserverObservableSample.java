import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ObserverObservableSample {

	public static void main(String args[]) throws InterruptedException {

		justObservable();
		iteratorObservable();
		createdObservable();
		System.out.println("Done");
	}

	private static void justObservable() {
		// Observable from JUST
		Observable<String> justObservable = Observable.just("Pan", "Singh", "Tomar");

		// Consumer Observer
		Consumer<? super String> consumerObserver = new Consumer<String>() {
			public void accept(String t) throws Exception {
				System.out.println("Name:" + t);
			}
		};

		// Subscribing Observer to Observable
		justObservable.subscribe(consumerObserver);
	}

	private static void iteratorObservable() {
		// Observable from iterator
		List<String> sList = new ArrayList<>(Arrays.asList("Harry", "Ron", "Emma"));
		Observable<String> iteratorObservable = Observable.fromIterable(sList);

		// Observer
		Observer<? super String> observer = new Observer<String>() {

			@Override
			public void onSubscribe(Disposable d) {
			}

			@Override
			public void onNext(String t) {
				System.out.println("O:" + t);

			}

			@Override
			public void onError(Throwable e) {
			}

			@Override
			public void onComplete() {
				System.out.println("Completed\n");
			}
		};

		// Subscribing Observer to Observable
		iteratorObservable.subscribe(observer);
	}

	private static void createdObservable() {

		// Observable from create
		ObservableOnSubscribe<Integer> source = emitter -> {
			for (int i = 0; i < 5; i++)
				emitter.onNext(i);
		};
		Observable<Integer> observable = Observable.create(source);

		// Observer
		Observer<Integer> observer = ObserverFactory.getSlowObserver();
		observable.subscribe(observer);
	}
}
