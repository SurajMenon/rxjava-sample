import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableGroupBy.GroupByObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.ResourceObserver;

public class ObserverFactory {

	public static Observer<Integer> getSlowObserver() {
		return getSlowObserver("");
	}

	// Simple Observer
	public static Observer<Integer> getSlowObserver(String name) {

		Observer<? super Integer> observer = new Observer<Integer>() {
			Disposable d;

			@Override
			public void onSubscribe(Disposable d) {
				this.d = d;
			}

			@Override
			public void onNext(Integer t) {
				System.out.println(name + t);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}

			@Override
			public void onComplete() {
				// Closes open streams for the observer. Prevents memory leak.
				// Like a hard finish. No more observers can subscribe/observe
				d.dispose();

				// NOTE: rxJava generally calls dispose() on onComplete().
				// So this is more useful when we are done with the subs
				System.out.println("Completed\n");
			}
		};

		return (Observer<Integer>) observer;
	}

	// Simple Single Observer
	public static SingleObserver<Integer> getSingleObserver() {
		SingleObserver<? super Integer> observer = new SingleObserver<Integer>() {

			@Override
			public void onSubscribe(Disposable d) {
			}

			@Override
			public void onSuccess(Integer t) {
				System.out.println(t);
			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}
		};

		return (SingleObserver<Integer>) observer;
	}

	// Simple DisposableObserver
	// NO onSubscribe()
	public static DisposableObserver<Long> getDisposableObserver() {
		DisposableObserver<? super Long> observer = new DisposableObserver<Long>() {

			@Override
			public void onNext(Long t) {
				System.out.println(t);
			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}

			@Override
			public void onComplete() {
				System.out.println("Completed\n");
			}
		};

		return (DisposableObserver<Long>) observer;
	}
	
//	public static GroupByObserver<Obj, String, Integer> getGroupByObserver(){
//		GroupByObserver<Obj, String, Integer> g = new GroupByObserver<>(actual, keySelector, valueSelector, bufferSize, delayError);
//		
//		return g;
//				
//	}
//
//	class Obj{
//		
//	}
}
