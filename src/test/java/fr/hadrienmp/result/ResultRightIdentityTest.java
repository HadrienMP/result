package fr.hadrienmp.result;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultRightIdentityTest {
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
}
