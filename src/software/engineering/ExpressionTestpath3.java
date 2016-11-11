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
public class ExpressionTestpath3 {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { 
            { "a*b*c*c*b*a", "a*a*b*b*c*c" }, 
            { "5+89*x*2*y*x*z*y*b+c*b*c*0", "5+178*b*x*x*y*y*z" },
            { "m*c*c+c*c*m+5*c*m*c", "7*c*c*m" }
        });
    }

    private String expinput;
    private String expExpected;

    public ExpressionTestpath3(String input, String expected) {
        expinput = input;
        expExpected = expected;
    }

    @Test
    public void testExpression() {
        assertEquals(expExpected, new Expression(expinput).printExpression());
    }

}
