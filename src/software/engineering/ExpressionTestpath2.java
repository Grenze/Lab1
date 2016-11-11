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
public class ExpressionTestpath2 {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { "x", "x" }, 
            { "y+x", "y+x" }, 
            { "x*y*z+a*c*b", "x*y*z+a*b*c" },
            { "a*c*m+m*a*c+x*y*p+5", "5+2*a*c*m+p*x*y" }, 
            { "x*y*z*t*a*v*d*t*y*h*0", "0" } 
        });
    }

    private String expinput;
    private String expExpected;

    public ExpressionTestpath2(String input, String expected) {
        expinput = input;
        expExpected = expected;
    }

    @Test
    public void testExpression() {
        assertEquals(expExpected, new Expression(expinput).printExpression());
    }

}
