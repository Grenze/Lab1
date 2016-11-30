package software.engineering;

import java.util.Vector;

/**
 * 
 */
public class Term {

    /**
     * Default constructor
     */
    public Term() {
    }

    /**
     * 
     */
    protected int coefficient = 1;

    /**
     * 
     */
    protected Vector<Var> vars = null;

    /**
     * @return
     */
    protected String stringFormat() {
    StringBuffer x1 = new StringBuffer();
    if (coefficient != 1) {
      x1.append(coefficient);
      x1.append("*");
    }
    for (Var v : vars) {
      x1.append(v.stringFormat());
      x1.append("*");
    }
    x1.deleteCharAt(x1.length() - 1);
    return x1.toString();
  }

    /**
     * @param var 
     * @return
     */
    protected int find(String var) {
    int i1 = 0;
    for (Var v : vars) {
      if (var.equals(v.getName())) {
        return i1;
      }
      i1++;
    }
    return -1;
    }

    /**
     * @param content
     */
    Term(String content) {
    this.coefficient = 1;
    this.vars = new Vector<Var>();
    String[] vars = content.split("\\*");
    int index;
    for (String v : vars) {
      if (Character.isDigit(v.charAt(0))) {
        this.coefficient *= Integer.parseInt(v);
      } else {
        index = this.find(v);
        if (index == -1) {
          this.vars.add(new Var(v, 1));
        } else {
          this.vars.get(index).setPower(this.vars.get(index).getPower() + 1);
        }
      }
    }
    }

    /**
     * 
     */
    void simplify() {
    for (int i1 = 0; i1 < vars.size();) {
      if (vars.get(i1).getPower() == 0) {
        vars.remove(i1);
      } else {
        i1++;
      }
    }
    vars.sort(null);
    }

}