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

    static Result<Void, RuntimeException> ofFailableRunnable(Runnable runnable) {
        try {
            runnable.run();
            return success();
        } catch (RuntimeException exception) {
            return error(exception);
        }
    }

    static <T> Result<T, RuntimeException> ofFailable(Supplier<T> runnable) {
        try {
            return success(runnable.get());
        } catch (RuntimeException exception) {
            return error(exception);
        }
    }

    static <S> Result<S, Throwable> ofCheckedFailable(FailableSupplier<S, Throwable> supplier) {
        try {
            return success(supplier.get());
        } catch (Throwable e) {
            return error(e);
        }
    }

    S orElseThrow(Function<E, ? extends RuntimeException> function);


    default boolean isError() {
        return !isSuccess();
    }

    boolean isSuccess();

    <T> T fold(Function<S, T> successFunction, Function<E, T> errorFunction);

    Optional<S> getResult();

    Optional<E> getError();

    <T> Result<T, E> map(Function<S, T> successFunction);

    <T> Result<T, E> flatMap(Function<S, Result<T, E>> successFunction);

    Result<S, E> onSuccess(Consumer<S> successFunction);

    void onError(Consumer<E> errorFunction);

    interface FailableSupplier<S, E extends Throwable> {
        S get() throws E;
    }
}
