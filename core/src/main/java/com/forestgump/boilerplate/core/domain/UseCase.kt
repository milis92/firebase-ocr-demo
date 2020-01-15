package com.forestgump.boilerplate.core.domain

import androidx.annotation.CallSuper
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.net.UnknownHostException
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber

/**
 * This abstraction represents an execution unit for different use cases
 * (this means that every use case in the application should implement this or one of
 * ReactiveUseCase contracts).
 *
 * Default implementation executes the job in on the current thread in a blocking fashion.
 */
abstract class UseCase<out Type, in Params> {

    @VisibleForTesting(otherwise = PRIVATE)
    abstract fun run(params: Params): Type

    operator fun invoke(params: Params) = run(params = params)

    /**
     * This type represents [Nothing] for use-case without parameters
     */
    object None
}

/**
 * By android threading convention each [ReactiveUseCase] implementation should run its job in a
 * background thread and post result back to the UI thread
 *
 * If underlying implementation has no [Params] pass [UseCase.None]
 *
 * @see [ReactiveUseCase]
 */
abstract class ReactiveUseCase<out Type, in Params> : UseCase<Type, Params>() {

    protected abstract val ioScheduler: Scheduler
    protected abstract val mainScheduler: Scheduler

    @VisibleForTesting(otherwise = PRIVATE)
    val disposables = CompositeDisposable()

    /**
     * Empty stub for success events
     */
    protected val onNextStub: (Any) -> Unit = {}

    /**
     * Default stub for errors events
     */
    protected val onErrorStub: (Failure) -> Unit = {}

    /**
     * Default stub for completion events
     */
    protected val onCompleteStub: () -> Unit = {}

    /**
     * Default error  mapper
     */
    open fun errorMapper(throwable: Throwable): Failure {
        Timber.e(throwable)
        return when (throwable) {
            is UnknownHostException -> NetworkFailure
            is HttpException -> ServerFailure(
                    throwable.code(),
                    JSONObject(
                            throwable.response()?.errorBody()?.string() ?: ""
                    ).getString("error")
            )
            else -> Failure(throwable)
        }
    }

    /**
     * Cancels running jobs and clears the disposable in this execution context
     */
    @CallSuper
    fun clear() = disposables.dispose()
}

/**
 * This abstraction represents execution unit for all [Single] streams
 *
 * @see [ReactiveUseCase]
 */
abstract class SingleUseCase<Type : Any, in Params> : ReactiveUseCase<Single<Type>, Params>() {

    /**
     * Builds [Single] producer for this use-case
     *
     * @param params - specific parameters for this use-case
     * @return [Single] with provided scheduler
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    abstract override fun run(params: Params): Single<Type>

    /**
     * Executes the current use-case
     *
     * This implementation provides default stubs for onSuccess and onError handlers, so implementations
     * can pass only the lambdas they intend to use
     *
     * This function is exposed as kotlin operator so you can invoke Class-as-method
     * Example usage:
     * ```
     * val useCase = SingleUseCase()
     *
     * val disposable : Disposable = useCase(params = Nothing())
     * ```
     * @param params - specific parameters for this use-case
     * @param onSuccess - Success handler with default empty stub
     * @param onError - Error handler with default empty stub
     *
     * @return Disposable for convenience
     */
    operator fun invoke(
        params: Params,
        onSuccess: (Type) -> Unit = onNextStub,
        onError: (Failure) -> Unit = onErrorStub
    ): Disposable {
        return run(params)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeBy(
                        onSuccess = onSuccess,
                        onError = {
                            onError(errorMapper(it))
                        }
                )
                .addTo(disposables)
    }
}

/**
 * This abstraction represents execution unit for all [Maybe] streams
 *
 * @see [ReactiveUseCase]
 */
abstract class MaybeUseCase<Type : Any, in Params> : ReactiveUseCase<Maybe<Type>, Params>() {

    /**
     * Builds [Maybe] producer for this use-case
     *
     * @param params - specific parameters for this use-case
     * @return [Maybe] with provided scheduler
     */
    abstract override fun run(params: Params): Maybe<Type>

    /**
     * Executes the current use-case
     *
     * This implementation provides default stubs for onError, onComplete and onSuccess handlers, so implementations
     * can pass only the lambdas they intend to use
     *
     * This function is exposed as kotlin operator so you can invoke Class-as-method
     * Example usage:
     * ```
     * val useCase = MaybeUseCase()
     *
     * val disposable : Disposable = useCase(params = Nothing())
     * ```
     * @param params - specific parameters for this use-case
     * @param onSuccess - Success handler with default empty stub
     * @param onError - Error handler with default empty stub
     * @param onComplete - Completion handler with default empty stub
     *
     * @return Disposable for convenience
     */
    operator fun invoke(
        params: Params,
        onError: (Failure) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onSuccess: (Type) -> Unit = onNextStub
    ): Disposable {
        return run(params)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeBy(
                        onSuccess = onSuccess,
                        onError = {
                            onError(errorMapper(it))
                        },
                        onComplete = onComplete
                )
                .addTo(disposables)
    }
}

/**
 * This abstraction represents execution unit for all [Flowable] streams
 *
 * @see [ReactiveUseCase]
 */
abstract class FlowableUseCase<Type : Any, in Params> : ReactiveUseCase<Flowable<Type>, Params>() {

    /**
     * Builds [Flowable] producer for this use-case
     *
     * @param params - specific parameters for this use-case
     * @return [Flowable] with provided scheduler
     */
    abstract override fun run(params: Params): Flowable<Type>

    /**
     * Executes the current use-case
     *
     * This implementation provides default stubs for onError, onComplete and onSuccess handlers, so implementations
     * can pass only the lambdas they intend to use
     *
     * This function is exposed as kotlin operator so you can invoke Class-as-method
     * Example usage:
     * ```
     * val useCase = FlowableUseCase()
     *
     * val disposable : Disposable = useCase(params = Nothing())
     * ```
     * @param params - specific parameters for this use-case
     * @param onError - Error handler with default empty stub
     * @param onComplete - Completion handler with default empty stub
     * @param onNext - Success handler with default empty stub
     *
     * @return Disposable for convenience
     */
    operator fun invoke(
        params: Params,
        onError: (Failure) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onNext: (Type) -> Unit = onNextStub
    ): Disposable {
        disposables.clear()
        return run(params)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeBy(
                        onNext = onNext,
                        onError = {
                            onError(errorMapper(it))
                        },
                        onComplete = onComplete
                )
                .addTo(disposables)
    }
}

/**
 * This abstraction represents execution unit for all [Completable] streams
 *
 * @see [ReactiveUseCase]
 */
abstract class CompletableUseCase<in Params> : ReactiveUseCase<Completable, Params>() {

    /**
     * Builds [Completable] producer for this use-case
     *
     * @param params - specific parameters for this use-case
     * @return [Completable] with provided scheduler
     */
    abstract override fun run(params: Params): Completable

    /**
     * Executes the current use-case
     *
     * This implementation provides default stubs for onError and onComplete handlers, so implementations
     * can pass only the lambdas they intend to use
     *
     * This function is exposed as kotlin operator so you can invoke Class-as-method
     * Example usage:
     * ```
     * val useCase = CompletableUseCase()
     *
     * val disposable : Disposable = useCase(params = Nothing())
     * ```
     * @param params - specific parameters for this use-case
     * @param onError - Error handler with default empty stub
     * @param onComplete - Completion handler with default empty stub
     *
     * @return Disposable for convenience
     */
    operator fun invoke(
        params: Params,
        onError: (Failure) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
    ): Disposable {
        return run(params).subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeBy(
                        onError = {
                            onError(errorMapper(it))
                        },
                        onComplete = onComplete
                )
                .addTo(disposables)
    }
}
