import exception.LandscapeValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class WaterCalculatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private WaterCalculator waterCalculator = new WaterCalculator();

    @Test
    public void shouldCountCorrectWaterAmount() {
        assertEquals(waterCalculator.calculateWaterAmount(new int[]{5,2,3,4,5,4,0,3,1}), 9);
        assertEquals(waterCalculator.calculateWaterAmount(new int[]{5,4,3,2,1}), 0);
        assertEquals(waterCalculator.calculateWaterAmount(new int[]{5,6,25,30,31,31,35}), 0);
    }

    @Test
    public void shouldThrowAnExceptionIfLandscapeIsTooLong() {
        thrown.expect(LandscapeValidationException.class);
        int[] badLandscape = new int[32001];
        for(int i = 0; i < 32001; i++) {
            badLandscape[i] = i;
        }
        waterCalculator.calculateWaterAmount(badLandscape);
    }

    @Test
    public void shouldThrowAnExceptionIfLandscapeIsTooHigh() {
        thrown.expect(LandscapeValidationException.class);
        int[] badLandscape = {1,2,3,33000,4,90};
        waterCalculator.calculateWaterAmount(badLandscape);
    }

    @Test
    public void shouldThrowAnExceptionIfLandscapeIsBelowZero() {
        thrown.expect(LandscapeValidationException.class);
        int[] badLandscape = {1,2,3,-7,4,90};
        waterCalculator.calculateWaterAmount(badLandscape);
    }
}
