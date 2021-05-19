package com.khiemle.nab.presentation

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

fun createObservable(name: String): Observable<String> {
    return Observable.create { emitter ->
        name.split(" ").forEach { str ->
            if (emitter.isDisposed.not()) {
                Thread.sleep(1000)
                emitter.onNext(str)
            }
        }
        emitter.onComplete()
    }
}

fun returnObservable(name: String) : Observable<String> {
    return Observable.fromIterable(name.split(" "))
}

fun returnObservableWithList(name1: String, name2: String) : Observable<List<String>> {
    return Observable.create { emitter ->
        emitter.onNext(name1.split(" "))
        emitter.onNext(name2.split(" "))
    }
}

fun<T> subscribe(observable: Observable<T>) {
    observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            Log.d("khiemlq", it.toString())
        }
}

fun main() {
//    operatorFunc()
//    subjectsFunc()
    val s1 = " yes "
    val s2 = " yes "
    println(s1 === s2)


}

fun subjectsFunc() {
    val behaviorSubject = BehaviorSubject.create<Int>()

    behaviorSubject.subscribe {
        println("khiemlq 1st observer: $it")
    }

    behaviorSubject.onNext(1)
    behaviorSubject.onNext(2)
    behaviorSubject.onNext(3)

    behaviorSubject.subscribe {
        println("khiemlq 2nd observer: $it")
    }

    behaviorSubject.onNext(4)

    behaviorSubject.onComplete()

    behaviorSubject.subscribe {
        println("khiemlq 3rd observer: $it")
    }
}

private fun operatorFunc() {
    val stream1 = createObservable("le quang khiem")
    val stream2 = returnObservable("nguyen thi hoang sa")
    mergeFunc(stream2, stream1)
    zipFunc(stream1, stream2)
    combineLatestFunc(stream1, stream2)
    concatFunc(stream2, stream1)
    flatmapFunc()
}

private fun flatmapFunc() {
    println("==== flatmap ====")
    returnObservableWithList("le quang khiem", "nguyen thi hoang sa").flatMap {
        Observable.fromIterable(it)
    }.subscribe {
        println(it)
    }
}

private fun concatFunc(
    stream2: Observable<String>,
    stream1: Observable<String>
) {
    println("==== concat ====")
    Observable.concat(stream2, stream1).subscribe {
        println(it)
    }
}

private fun combineLatestFunc(
    stream1: Observable<String>,
    stream2: Observable<String>
) {
    println("==== combineLatest ====")
    val combineLatestDisposable = Observable.combineLatest(stream1, stream2, { t1, t2 ->
        "$t1 vs $t2"
    })
        .subscribe {
            println(it)
        }
    combineLatestDisposable.dispose()
}

private fun zipFunc(
    stream1: Observable<String>,
    stream2: Observable<String>
) {
    println("==== zip ====")
    val zipDisposable = Observable.zip(stream1, stream2, { t1, t2 ->
        "$t1 vs $t2"
    })
        .subscribe {
            println(it)
        }
    zipDisposable.dispose()
}

private fun mergeFunc(
    stream2: Observable<String>,
    stream1: Observable<String>
) {
    println("==== merge ====")
    val disposable = stream2.subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
        .mergeWith(stream1).subscribe {
            println(it)
        }
    disposable.dispose()
}
