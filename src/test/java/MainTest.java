import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class MainTest {
    Main main;

    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @ParameterizedTest
    @CsvSource({"0.4, 4.312", "1.4, 3.64903648198" , "2.4, 0.7615384615384"})
    void testFunc(double x, double expectedY) {
        assertThat(main.func(x))
                .isCloseTo(expectedY, Percentage.withPercentage((Main.err)));
    }

    @Test
    void testGetSteps() {
        assertThat(main.getSteps(0.0, 2.0, 0.002)).isEqualTo(1001);
    }

    @ParameterizedTest
    @CsvSource({"0, 0.0 ", "700, 1.4", "1000, 2.0"})
    void testGetArrayOfX(int index, double expectedX) {
        double[] bufferedArrayOfX = main.getArrayOfX(0.0, 2.0, 0.002);
        assertThat(bufferedArrayOfX[index]).
                isCloseTo(expectedX, Percentage.withPercentage((Main.err)));
    }

    @ParameterizedTest
    @CsvSource({"0, 4.0 ", "700, 3.6490364819799535", "1000, 0.93914855055"})
    void testGetArrayOfY(int index, double expectedY) {
        double[] bufferedArrayOfY = main.getArrayOfY(main.getArrayOfX(0.0, 2.0, 0.002));
        assertThat(bufferedArrayOfY[index]).
                isCloseTo(expectedY, Percentage.withPercentage((Main.err)));
    }


    @Test
    void testGetMaxY() {
        assertThat(main.getMaxY(main.getArrayOfY
                (main.getArrayOfX(0.0, 2.0, 0.002)))).
                isCloseTo(8.857490800000008,Percentage.withPercentage((Main.err)));
    }

    @Test
    void testGetMinY() {
        assertThat(main.getMinY(main.getArrayOfY
                (main.getArrayOfX(0.0, 2.0, 0.002)))).
                isCloseTo(0.9391485505499111,Percentage.withPercentage((Main.err)));
    }

    @Test
    void testGetSumY() {
        assertThat(main.getSumY(main.getArrayOfY
                (main.getArrayOfX(0.0, 2.0, 0.002)))).
                isCloseTo(4224.112998155183,Percentage.withPercentage((Main.err)));
    }

    @Test
    void testGetAverageY() {
        assertThat(main.getAverageY((main.getArrayOfY
                (main.getArrayOfX(0.0, 2.0, 0.002))))).
                isCloseTo(4.219893105050133,Percentage.withPercentage((Main.err)));
    }
}