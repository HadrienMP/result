package fr.hadrienmp.result;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Result<S, E> {
    static <S, E> Result<S, E> success(S result) {
        return new Success<>(result);
    }

    static <S, E> Result<S, E> success(S result, Class<E> errorClass) {
        return new Success<>(result);
    }

    static <S, E> Result<S, E> error(E error) {
        return new Error<>(error);
    }

    static <E> Result<Void, E> success() {
        return success(null);
    }

    static Result<Void, Throwable> ofFailable(FailableRunnable runnable) {
        try {
            runnable.run();
            return success();
        } catch (Throwable exception) {
            return error(exception);
        }
    }

    static Result<Void, RuntimeException> ofFailable(Runnable runnable) {
        try {
            runnable.run();
            return success();
        } catch (RuntimeException e) {
            return error(e);
        }
    }

    static <S> Result<S, Throwable> ofFailable(FailableSupplier<S, Throwable> supplier) {
        try {
            return success(supplier.get());
        } catch (Throwable e) {
            return error(e);
        }
    }

    static <S> Result<S, RuntimeException> ofFailableJavaSupplier(Supplier<S> supplier) {
        try {
            return success(supplier.get());
        } catch (RuntimeException e) {
            return error(e);
        }
    }

    S orElseThrow(Function<E, ? extends RuntimeException> function);

    default boolean isError() {
        return !isSuccess();
    }

    boolean isSuccess();

    <T> T fold(Function<S, T> successFunction, Function<E, T> errorFunction);

    Optional<S> getSuccess();

    Optional<E> getError();

    <T> Result<T, E> map(Function<S, T> successFunction);

    Result<S, E> onSuccess(Consumer<S> successFunction);

    void onError(Consumer<E> errorFunction);

    <T> Result<T, E> flatMap(Function<S, Result<T, E>> mapFunction);

    interface FailableSupplier<S, E extends Throwable> {
        S get() throws E;
    }

    interface FailableRunnable {
        void run() throws Throwable;
    }
}
