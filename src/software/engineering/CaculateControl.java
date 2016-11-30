package software.engineering;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CaculateControl {
    public Expression expression;
    
    public boolean inputValid(String exp){
        Matcher matcher;
        Pattern expressionRegex = Pattern.compile("^((([a-z]|[0-9]+)\\*)*([a-z]|[0-9]+)\\+)*(([a-z]|[0-9]+)\\*)*([a-z]|[0-9]+)$");
        matcher = expressionRegex.matcher(exp);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
    
    
    public String inputExpression(String exp){
        expression = new Expression();
        expression.saveExpression(exp);
        if(inputValid(exp)){
            return expression.stringFormat();
        }else{
            return "";
        }
    }
    
    public String outputExpression(){
        return expression.stringFormat();
    }
    
    public String simplifyExpression(String command){
        Pattern simplifyRegex = Pattern.compile("^([a-z]=[0-9]+ )*([a-z]=[0-9]+)$");
        Matcher matcher = simplifyRegex.matcher(command);
        if(matcher.matches()){
            expression.simplify(command);
            return "success"; 
        }else{
            return "formatError";
        }
    }
    
    public String derivativeExpression(String command){
        Pattern derivativeRegex = Pattern.compile("^[a-z]$");
        Matcher matcher = derivativeRegex.matcher(command);
        if(matcher.matches()){
            return expression.derivative(command);
        }else{
            return "formatError";
        }
    }
}
