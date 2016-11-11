package software.engineering;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExpressionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDerivative() {
		String express = "3*x*x*y*y*z*z+4*x*y+5*y*z+6*z*x+7*x+8*y+9*z+10";	
		Expression exp = new Expression(express);
		String express1 = exp.printExpression();
		System.out.println(express1);		
		Expression exp1 = null;
		String command = "!d/d x     ";
		String command_re = null;
		String result = null;		
		command_re = exp.command(command);		
		if(command_re != "0"){
			if(exp.count(express1, String.valueOf(command.charAt(5))) == 0){
				result = "Error, no variable!";
			}else{
				exp1 = new Expression(command_re);
				result = exp1.printExpression();				
			}
		}else{
			result = "Error, invalid command!";
		}
		System.out.println(result);
		assertEquals("7+6*x*y*y*z*z+4*y+6*z",result);
	}

}
