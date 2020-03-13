package fr.hadrienmp.result;

import org.junit.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    /*
     * -------------------------
     * Monad law - Associativity
     * (a + b) + c = a + (b + c)
     * -------------------------
     */

    // TODO HMP: 13/03/2020 parameter test to test with error functions too
    @Test
    public void flat_mapping_two_functions_is_the_same_as_flat_mapping_the_composition_of_the_two() {
        Function<String, Result<String, String>> f = s -> Result.success("f(" + s + ")");
        Function<String, Result<String, String>> g = s -> Result.success("g(" + s + ")");

        Result<String, String> result = Result.success("OK");

        assertThat(result.flatMap(f).flatMap(g))
                .isEqualTo(result.flatMap(s -> f.apply(s).flatMap(g)));
    }

    /*
     * -------------------------
     * Monad law - Right side identity
     * -------------------------
     */

    @Test
    public void monad_law_right_side_identity_for_success() {
        Result<String, Integer> result = Result.success("OK");
        assertThat(result.flatMap(Result::success)).isEqualTo(result);
    }

    @Test
    public void monad_law_right_side_identity_for_error() {
        Result<String, Integer> result = Result.error(1);
        assertThat(result.flatMap(Result::success)).isEqualTo(result);
    }

    /*
     * -------------------------
     * Monad law - Left side identity
     * Flat map (bind)
     * -------------------------
     */

    // TODO HMP: 13/03/2020 test with mapFunction error (parameter tests ?)
    @Test
    public void monad_law_left_side_identity_for_success() {
        String ok = "OK";
        Function<String, Result<String, String>> mapFunction = s -> Result.success("Mapped: " + s);
        Result<String, String> result = Result.success(ok);

        assertThat(result.flatMap(mapFunction))
                .isEqualTo(mapFunction.apply(ok));
    }

    @Test
    public void monad_law_left_side_identity_for_error() {
        Function<String, Result<String, String>> mapFunction = s -> Result.success("Mapped: " + s);

        Result<String, String> result = Result.error("KO");

        assertThat(result.flatMap(mapFunction)).isEqualTo(result);
    }

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