package software.engineering;

import static org.junit.Assert.*;
import software.engineering.Expression;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class ExpressionTestpath1 {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { "2333", "2333" }, 
            { "1096*2", "2192" }, 
            { "1234*0", "0" },
            { "1567+0+0+0", "1567" }, 
            { "1024*6+2048+2048+3960*0+5598*1543+0", "8647954" } 
        });
    }

    private String expinput;
    private String expExpected;

    public ExpressionTestpath1(String input, String expected) {
        expinput = input;
        expExpected = expected;
    }

    @Test
    public void testExpression() {
        assertEquals(expExpected, new Expression(expinput).printExpression());
    }

}
