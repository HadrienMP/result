package fr.hadrienmp.result;

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
    public Optional<S> getResult() {
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
    public <T> Result<T, E> flatMap(Function<S, Result<T, E>> successFunction) {
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
    public String toString() {
        return "Error{" +
               "error=" + this.error +
               '}';
    }

    @Override
    public S orElseThrow(Function<E, ? extends RuntimeException> function) {
        throw function.apply(this.error);
    }
}
