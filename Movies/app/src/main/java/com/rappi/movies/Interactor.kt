package com.rappi.movies

import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver

internal interface Interactor<T> {

    fun execute(observer: DisposableObserver<T>)

    fun execute(observer: DisposableSingleObserver<T>)

    fun execute(observer: DisposableObserver<T>, requestValue: UseCase.RequestValue)

    fun execute(observer: DisposableSingleObserver<T>, requestValue: UseCase.RequestValue)

    fun execute(): Observable<T>
}
