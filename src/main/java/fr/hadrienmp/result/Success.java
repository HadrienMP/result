package fr.hadrienmp.result;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Success<S, E> implements Result<S, E> {
    private final S result;

    Success(S result) {
        this.result = result;
    }

    @Override
    public <T> T fold(Function<S, T> successFunction, Function<E, T> errorFunction) {
        return successFunction.apply(this.result);
    }

    @Override
    public Optional<S> getResult() {
        return Optional.ofNullable(this.result);
    }

    @Override
    public Optional<E> getError() {
        return Optional.empty();
    }

    @Override
    public <T> Result<T, E> map(Function<S, T> successFunction) {
        return Result.success(successFunction.apply(this.result));
    }

    @Override
    public <T> Result<T, E> flatMap(Function<S, Result<T, E>> successFunction) {
        return successFunction.apply(this.result);
    }

    @Override
    public Result<S, E> onSuccess(Consumer<S> successFunction) {
        successFunction.accept(this.result);
        return this;
    }

    @Override
    public void onError(Consumer<E> errorFunction) {
        // Nothing to do
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public S orElseThrow(Function<E, ? extends RuntimeException> function) {
        return this.result;
    }
}
