package fr.hadrienmp.result;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultFactoryTest {

    private static final Result.FailableRunnable failingRunnable = () -> {
        throw new Exception("failed");
    };
    private static final Result.FailableRunnable succeedingRunnable = () -> {
        // do stuff
    };

    /*
     * -------------------------------
     * ofFailable checked exception runnable
     * -------------------------------
     */

    @Test
    public void can_be_created_from_an_exception_throwing_runnable_as_a_lambda() {
        Stub stub = new Stub();
        Result<Void, Throwable> result = Result.ofFailable(stub::doStuff);
        assertThat(result.getError().get()).isInstanceOf(Exception.class);
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

    /*
     * -------------------------------
     * ofFailable java runtime supplier
     * -------------------------------
     */

    @Test
    public void can_be_created_from_a_java_supplier() {
        Supplier<String> supplier = () -> {
            throw new RuntimeException();
        };
        Result<String, RuntimeException> result = Result.ofFailableJavaSupplier(supplier);
        assertThat(result.isError());
    }

    /*
     * -------------------------------
     * ofFailable checked exception supplier
     * -------------------------------
     */

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

    /*
     * -------------------------------
     * Other factories
     * -------------------------------
     */

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

    private static final class Stub {
        private void doStuff() {
            // do some stuff
        }
    }
}
