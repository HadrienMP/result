package fr.hadrienmp.result.monad.laws;

import fr.hadrienmp.result.Result;
import org.junit.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultLeftIdentityTest {
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
}
