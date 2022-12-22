package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        Integer[] testArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int testNum = 5;
        int testNum2 = 6;

        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3, 4));
        int n1 = 2;
        List<Integer> expected1 = new ArrayList<>(List.of(1, 2));
        List<Integer> list2 = new ArrayList<>(List.of(7, 3, 10));
        int n2 = 8;
        List<Integer> expected2 = new ArrayList<>(List.of(7, 3, 10));

        Assertions.assertEquals(App.take(list1, n1), expected1);
        assertThat(App.take(list2, n2)).isEqualTo(expected2);

        List<Integer> result = (App.take(Arrays.asList(testArray), testNum));
        int resultSize = result.size();

        assertThat(resultSize).isEqualTo(testNum);
        // END
    }
}
