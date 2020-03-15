package fr.hadrienmp.result.monad.laws;

import fr.hadrienmp.result.Result;
import org.junit.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultAssociativityTest {
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
}
