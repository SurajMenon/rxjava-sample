import io.reactivex.Observable;

public class Operators {

	public static void main(String args[]) {
		map();
		filter();
		filterAndMap();
		take();
		skip();
		repeat();
		takeWhile();
		distinct();
		scan();
		groupBy();
		ifEmpty();
	}

	// map() to transform one observable to another
	private static void map() {
		Observable<Integer> justObservable = Observable.just(1, 2, 3, 4, 5);
		justObservable.map(x -> 2 * x).subscribe(ObserverFactory.getSlowObserver());
	}

	private static void filter() {
		Observable<Integer> justObservable = Observable.just(1, 2, 3, 4, 5);
		justObservable.filter(x -> x % 2 == 0).subscribe(ObserverFactory.getSlowObserver());
	}

	private static void filterAndMap() {
		Observable<Integer> justObservable = Observable.just(1, 2, 3, 4, 5);
		justObservable.filter(x -> x % 2 == 0).map(x -> x * 2).subscribe(ObserverFactory.getSlowObserver());
	}

	private static void take() {
		Observable<Integer> justObservable = Observable.just(1, 2, 3, 4, 5);

		// gets the first 3 elements
		justObservable.take(3).subscribe(ObserverFactory.getSlowObserver());
	}

	private static void skip() {
		Observable<Integer> justObservable = Observable.just(1, 2, 3, 4, 5);

		// skips the first 3 elements
		justObservable.skip(3).subscribe(ObserverFactory.getSlowObserver());
	}

	private static void takeWhile() {
		Observable<Integer> justObservable = Observable.just(1, 2, 3, 4, 5, 6);

		// takes elements till the first elements that violates the condition
		// similarly we have skipWhile
		justObservable.takeWhile(x -> x % 3 != 0).subscribe(ObserverFactory.getSlowObserver());
	}

	private static void repeat() {
		Observable<Integer> justObservable = Observable.just(1, 2, 3);

		// repeats the stream twice
		justObservable.repeat(2).subscribe(ObserverFactory.getSlowObserver());
	}

	private static void distinct() {
		Observable<Integer> justObservable = Observable.just(1, 1, 2, 3, 2, 2, 4, 6, 5, 6);

		// emits distinct elements
		justObservable.distinct().subscribe(ObserverFactory.getSlowObserver());

		// We can have custom definitions of what is distinct
		// Here I have defined odd/even as distinct
		justObservable.distinct(x -> x % 2 == 0).subscribe(ObserverFactory.getSlowObserver());

		// emits elements when they change
		justObservable.distinctUntilChanged().subscribe(ObserverFactory.getSlowObserver());
	}

	private static void scan() {
		Observable<Integer> justObservable = Observable.just(1, 2, 3, 4, 5);

		// sort of like maintaining state while iterating
		justObservable.scan((result, x) -> result + x).subscribe(ObserverFactory.getSlowObserver());
	}

	private static void groupBy() {

	}

	private static void ifEmpty() {
		//defaultIfEmpty()
		//switchIfEmpty()
	}

}
