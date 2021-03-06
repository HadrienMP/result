package fr.hadrienmp.result;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Error<S, E> implements Result<S, E> {
    private final E error;

    Error(E error) {
        this.error = error;
    }

    @Override
    public <T> T fold(Function<S, T> successFunction, Function<E, T> errorFunction) {
        return errorFunction.apply(this.error);
    }

    @Override
    public Optional<S> getSuccess() {
        return Optional.empty();
    }

    @Override
    public Optional<E> getError() {
        return Optional.of(this.error);
    }

    @Override
    public <T> Result<T, E> map(Function<S, T> successFunction) {
        return Result.error(this.error);
    }

    @Override
    public Result<S, E> onSuccess(Consumer<S> successFunction) {
        return this;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public void onError(Consumer<E> errorFunction) {
        errorFunction.accept(this.error);
    }

    @Override
    public <T> Result<T, E> flatMap(Function<S, Result<T, E>> mapFunction) {
        return Result.error(this.error);
    }

    @Override
    public S orElseThrow(Function<E, ? extends RuntimeException> function) {
        throw function.apply(this.error);
    }

    @Override
    public String toString() {
        return "Error{" +
                "error=" + this.error +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Error)) return false;
        Error<?, ?> error1 = (Error<?, ?>) o;
        return Objects.equals(error, error1.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error);
    }
}
