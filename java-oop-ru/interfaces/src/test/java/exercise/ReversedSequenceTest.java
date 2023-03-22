package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
public class ReversedSequenceTest {

    @Test
    public void reverseTest() {
        ReversedSequence testSeq = new ReversedSequence("abcdef");
        assertThat(testSeq.toString()).isEqualTo("fedcba");
        assertThat(testSeq.charAt(1)).isEqualTo('e');
        assertThat(testSeq.length()).isEqualTo(6);
        assertThat(testSeq.subSequence(1,4).toString()).isEqualTo("edc");
    }
}
