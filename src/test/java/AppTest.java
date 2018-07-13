import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.expectNew;
import static org.powermock.api.easymock.PowerMock.replay;

@RunWith(PowerMockRunner.class)
@PrepareForTest(App.class)
public class AppTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream origOutput = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void restore() {
        System.setOut(origOutput);
    }

    @Test
    public void shouldCountWaterFromUserInput() throws Exception {
        WaterCalculator mockCalc = createMock(WaterCalculator.class);
        expect(mockCalc.calculateWaterAmount(new int[]{5,2,3,4,5,4,0,3,1})).andReturn(1L);
        expectNew(WaterCalculator.class).andReturn(mockCalc);
        replay(mockCalc, WaterCalculator.class);
        System.setIn(new ByteArrayInputStream("5,2,3,4,5,4,0,3,1\nq".getBytes()));
        App.main(null);
        assertTrue(output.toString().contains("This landscape can collect 1 water units."));
    }

    @Test
    public void shouldReturnErrorMessageWhenGetsWrongUserInputs() {
        System.setIn(new ByteArrayInputStream("5 2 3,4,5,4,0,3,1\nq".getBytes()));
        App.main(null);
        assertTrue(output.toString().contains("Error. Can't read landscape data!"));
    }

    @Test
    public void shouldReturnErrorMessageWhenGetsIncorrectLandscape() {
        System.setIn(new ByteArrayInputStream("5,5,4,33000,3,1\nq".getBytes()));
        App.main(null);
        assertTrue(output.toString().contains("Error. Landscape is incorrect!"));
    }
}
