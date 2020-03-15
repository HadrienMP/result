package fr.hadrienmp.result;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ResultExtractionTest {

    /*
     * -------------------------------
     * orElseThrow
     * -------------------------------
     */

    @Test
    public void the_success_value_can_be_extracted_with_orElseThrow() {
        Result<String, String> ok = Result.success("OK");
        String result = ok.orElseThrow(s -> new RuntimeException("Error: " + s));
        assertThat(result).isEqualTo("OK");
    }

    @Test
    public void orElseThrow_will_throw_the_specified_exception_for_an_error_result() {
        Result<String, String> ok = Result.error("KO");
        assertThatThrownBy(() -> ok.orElseThrow(RuntimeException::new))
                .isInstanceOf(RuntimeException.class);
    }

    /*
     * -------------------------------
     * getSuccess
     * -------------------------------
     */

    @Test
    public void getSuccess_will_return_an_optional_containing_the_success_value_for_a_success() {
        Result<String, String> ok = Result.success("OK");
        Optional<String> result = ok.getSuccess();
        assertThat(result).contains("OK");
    }

    @Test
    public void getSuccess_will_return_an_empty_optional_for_an_error() {
        Result<String, String> ko = Result.error("KO");
        Optional<String> result = ko.getSuccess();
        assertThat(result).isEmpty();
    }

    /*
     * -------------------------------
     * getError
     * -------------------------------
     */

    @Test
    public void getError_will_return_an_optional_containing_the_error_for_an_error() {
        Result<String, String> ok = Result.error("KO");
        Optional<String> result = ok.getError();
        assertThat(result).contains("KO");
    }

    @Test
    public void getError_will_return_an_empty_optional_for_a_success() {
        Result<String, String> ko = Result.success("OK");
        Optional<String> result = ko.getError();
        assertThat(result).isEmpty();
    }
}
