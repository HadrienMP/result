package fr.hadrienmp.result;

import org.junit.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    /*
     * -------------------------
     * Map
     * -------------------------
     */

    @Test
    public void mapping_a_success_result_will_the_return_a_new_result_than_contains_the_output_of_map_function() {
        String ok = "OK";
        Function<String, String> mapFunction = s -> "Mapped: " + s;

        assertThat(Result.success(ok).map(mapFunction))
                .isEqualTo(Result.success(mapFunction.apply(ok)));
    }

    @Test
    public void mapping_an_error_will_return_a_new_error_containing_the_existing_error() {
        Result<String, String> success = Result.error("KO");
        String result = success.fold(s -> "Success: " + s, error -> "Failure: " + error);
        assertThat(result).isEqualTo("Failure: KO");
    }
    /*
     * -------------------------
     * Fold
     * -------------------------
     */

    @Test
    public void folding_a_success_result_will_the_return_the_output_of_the_success_function() {
        Result<String, String> success = Result.success("OK");
        String result = success.fold(s -> "Success: " + s, error -> "Failure: " + error);
        assertThat(result).isEqualTo("Success: OK");
    }

    @Test
    public void folding_an_error_result_will_the_return_the_output_of_the_error_function() {
        Result<String, String> success = Result.error("KO");
        String result = success.fold(s -> "Success: " + s, error -> "Failure: " + error);
        assertThat(result).isEqualTo("Failure: KO");
    }

    /*
     * -------------------------
     * Boolean detection methods
     * -------------------------
     */
    @Test
    public void isSuccess_is_true_for_Success() {
        Result<String, String> success = Result.success("Success");
        assertThat(success.isSuccess()).isTrue();
    }
    @Test
    public void isSuccess_is_false_for_Error() {
        Result<String, String> success = Result.error("Error message");
        assertThat(success.isSuccess()).isFalse();
    }
    @Test
    public void isError_is_false_for_Success() {
        Result<String, String> success = Result.success("Success");
        assertThat(success.isError()).isFalse();
    }
    @Test
    public void isError_is_true_for_Error() {
        Result<String, String> success = Result.error("Error message");
        assertThat(success.isError()).isTrue();
    }
}