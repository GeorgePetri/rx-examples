package net.essensys;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RxExamples {

    public RxExamples() {
//        oneItem();
//        multipleItems();
//        transform();
//        async();
//        flatMap();
//        combine();
//        scan();
    }

    private void oneItem() {
        Observable.just("Hello World")
                .subscribe(value -> System.out.print("\n" + value));
    }

    private void multipleItems() {
        Observable.fromArray(1, 2, 3)
                .subscribe(printFunction());
    }

    private void transform() {
        Observable.fromIterable(getNumberList())
                .skip(10)
                .take(5)
                .map(integer -> integer.toString() + " string")
                .subscribe(string -> System.out.print("\n" + string));
    }

    private void async() {
        Observable.just("Hello World late")
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .subscribe(printFunction());

        oneItem();
    }

    private void flatMap() {
        Observable.fromIterable(getNumberList())
                .take(6)
                .flatMap(integer -> Observable.range(1, integer))
                .subscribe(printFunction());
    }

    private void combine() {
        getIntegerObservable()
                .flatMap(number -> get12Observable())
                .subscribe(printFunction());
    }

    private void scan() {
        getIntegerObservable()
                .scan(1, (oldValue, newValue) -> oldValue + newValue)
                .subscribe(printFunction());
    }

    private List<Integer> getNumberList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        return list;
    }

    private Observable<Integer> getIntegerObservable() {
        return Observable.fromIterable(getNumberList())
                .take(5);
    }

    private Observable<Integer> get12Observable() {
        return Observable.range(1, 2);
    }

    private Consumer<? super Object> printFunction() {
        return value -> System.out.print("\n" + value);
    }
}
