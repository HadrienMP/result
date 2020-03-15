package fr.hadrienmp.result;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCallbackTest {

    private Mock mock;

    @Before
    public void setUp() {
        mock = new Mock();
    }

    /*
     * -------------------------------
     * onSuccess
     * -------------------------------
     */

    @Test
    public void the_success_callback_will_be_given_the_success_value() {
        Result<String, Object> result = Result.success("OK");
        result.onSuccess(mock::call);
        assertThat(mock.called).isTrue();
    }

    @Test
    public void the_success_callback_will_not_be_called_for_an_error() {
        Result<String, Object> ok = Result.error("KO");
        ok.onSuccess(mock::call);
        assertThat(mock.called).isFalse();
    }

    /*
     * -------------------------------
     * onError
     * -------------------------------
     */

    @Test
    public void the_error_callback_will_be_given_the_error_value() {
        Result<String, Object> result = Result.error("KO");
        result.onError(mock::call);
        assertThat(mock.called).isTrue();
    }

    @Test
    public void the_error_callback_will_not_be_called_for_a_success() {
        Result<String, Object> ok = Result.success("OK");
        ok.onError(mock::call);
        assertThat(mock.called).isFalse();
    }

    private static final class Mock {
        private boolean called = false;
        private <T> void call(T input) {
            called = true;
        }
    }
}
