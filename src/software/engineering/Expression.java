package software.engineering;

import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 */
public class Expression {

    /**
     * Default constructor
     */
    public Expression() {
    }

    /**
     * 
     */
    protected int coefficientTerm = 0;

    /**
     * 
     */
    protected Vector<Term> terms = null;

    /**
     * @param command
     */
    void simplify(String command) {

    if(command!=""){
        String exp = this.stringFormat();
        String vars[] = command.split(" ");
        for(String var:vars){
            if(var!=""){
                exp = exp.replaceAll(var.substring(0, 1), var.substring(2, var.length()));
            }
        }
        this.saveExpression(exp);
    }
    
    for (int i = 0; i < terms.size();) {
      if (terms.get(i).vars.isEmpty()) {
        coefficientTerm += terms.get(i).coefficient;
        terms.remove(i);
      } else {
        if (terms.get(i).coefficient == 0) {
          terms.remove(i);
        } else {
          terms.get(i).simplify();
          i++;
        }
      }
    }
    }


    /**
     * @param var
     */
    String derivative(String var) {
    StringBuffer exp = new StringBuffer();
    String result;
    boolean flag = false;
    if (this.coefficientTerm != 0) {
      exp.append("");
    } else {
      if (terms.size() == 0) {
        result = "0";
        this.saveExpression(result);
        return "coefficient";
      }
    }
    for (Term t : terms) {
      int count = count(t.stringFormat(), var);
      // System.out.println(count);
      if (count != 0) {
        exp.append(t.stringFormat().replaceFirst(var, String.valueOf(count)));
        flag = true;
      }
      // else{
      // exp.append(t.stringFormat());
      // }
      exp.append("+");
    }
    if(flag==false){
        return "noVar";
    }
    exp.deleteCharAt(exp.length() - 1);
    // System.out.println(exp.toString());
    result = exp.toString();
    this.saveExpression(result);
    return "success";
  }


    /**
     * @param src 
     * @return
     */
    private String merge(String src) {
    Vector<String> group = new Vector<String>(Arrays.asList(src.split("\\+")));
    StringBuffer group2 = new StringBuffer();
    for (int i = 0; i < group.size();) {
      String term = group.get(i);
      String vars = term.replaceAll("^[0-9]*\\*", "");
      int coefficient;
      if (term.length() == vars.length()) {
        coefficient = 1;
      } else {
        coefficient = Integer.parseInt(term.substring(0, term.indexOf(vars) - 1));
      }
      for (int j = i + 1; j < group.size();) {
        String mergeterm = group.get(j);
        if (mergeterm.matches("([0-9]*\\*)?\\Q" + vars + "\\E")) {
          coefficient += mergeterm.length() == vars.length() ? 1
              : Integer.parseInt(mergeterm.substring(0, mergeterm.indexOf(vars) - 1));
          group.remove(j);
        } else {
          j++;
        }
      }
      if (coefficient == 1) {
        group2.append(vars + "+");
      } else {
        group2.append(Integer.toString(coefficient) + "*" + vars + "+");
      }
      i++;
    }
    group2.deleteCharAt(group2.length() - 1);
    return group2.toString();
    }

    /**
     * @return
     */
    public String stringFormat() {
    StringBuffer exp = new StringBuffer();

    if (this.coefficientTerm != 0) {
      exp.append(this.coefficientTerm);
      exp.append("+");
    } else {
      if (terms.size() == 0) {
        return "0";
      }
    }
    for (Term t : terms) {
      exp.append(t.stringFormat());
      exp.append("+");
    }

    exp.deleteCharAt(exp.length() - 1);

    return merge(exp.toString());
    }

    /**
     * @param content
     */
    public void saveExpression(String content) {
    coefficientTerm = 0;
    this.terms = new Vector<Term>();
    Matcher matcher;
    String termgroup;
    Pattern pattern = Pattern.compile("(([a-z]|[0-9]+)\\*)*([a-z]|[0-9]+)");
    matcher = pattern.matcher(content);
    while (matcher.find()) {
      termgroup = matcher.group();
      terms.add(new Term(termgroup));
    }
    this.simplify("");
    }

    /**
     * @param src 
     * @param find 
     * @return
     */
    private int count(String src, String find) {
    int count = 0;
    int index = -1;
    while ((index = src.indexOf(find, index)) > -1) {
      index++;
      count++;
    }
    return count;
    }

}