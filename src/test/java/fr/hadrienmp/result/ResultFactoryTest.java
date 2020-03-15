package fr.hadrienmp.result;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultFactoryTest {

    protected static final Result.FailableRunnable<Throwable> failingRunnable = () -> {
        throw new Exception("failed");
    };
    protected static final Result.FailableRunnable<Throwable> succeedingRunnable = () -> {
        // do stuff
    };

    @Test
    public void ofFailable_java_runnable_will_return_a_null_containing_result_for_a_success() {
        Runnable runnable = () -> {
            // do stuff};
        };
        Result<Void, RuntimeException> result = Result.ofFailable(runnable);
        assertThat(result.isSuccess()).isTrue();
    }

    @Test
    public void can_be_created_from_a_java_runnable() {
        Runnable runnable = () -> {throw new RuntimeException();};
        Result<Void, RuntimeException> result = Result.ofFailable(runnable);
        assertThat(result.getError().get()).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void can_be_created_from_an_exception_throwing_runnable() {
        Result<Void, Throwable> result = Result.ofFailable(failingRunnable);
        assertThat(result.getError().get()).isInstanceOf(Exception.class);
    }

    @Test
    public void ofFailable_runnable_will_return_a_null_containing_result_for_a_success() {
        Result<Void, Throwable> result = Result.ofFailable(succeedingRunnable);
        assertThat(result.isSuccess()).isTrue();
    }

    @Test
    public void can_be_created_from_an_exception_throwing_method() {
        int i = 0;
        Result<Integer, Throwable> result = Result.ofFailable(() -> exceptionForMinus1(i));
        assertThat(result).isEqualTo(Result.success(i));
    }

    @Test
    public void ofCheckedFailable_will_return_an_error_containing_the_exception() {
        int i = -1;
        Result<Integer, Throwable> result = Result.ofFailable(() -> exceptionForMinus1(i));
        assertThat(result.getError().get()).isInstanceOf(Exception.class);
    }

    private Integer exceptionForMinus1(int i) throws Exception {
        if (i == -1) {
            throw new Exception();
        }
        return i;
    }

    @Test
    public void success_with_error_class_is_useful_to_turn_an_optional_into_a_result() {
        Result<String, String> result = Optional.of("value").map(s -> Result.success(s, String.class))
                                                .orElse(Result.error("Fail"));
        assertThat(result.orElseThrow(RuntimeException::new)).isEqualTo("value");
    }

    @Test
    public void success_without_params_is_useful_for_void_methods_that_could_fail() {
        Function<Integer, Result<Void, String>> failableVoid = i -> {
            if (i == -1) {
                return Result.error("Invalid number");
            }
            // do some side effect
            return Result.success();
        };
        Result<Void, String> result = failableVoid.apply(0);
        assertThat(result.orElseThrow(RuntimeException::new)).isNull();
    }
}
