
package software.engineering;

/**
 * 
 */
public class Var {

    /**
     * Default constructor
     */
    public Var(String name,final int power) {
    this.name = name;
    this.power = power;
    }

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private int power = 1;

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public int getPower() {
    return power;
    }

    /**
     * @param power
     */
    public void setPower(int power) {
    this.power = power;
    }

    /**
     * @return
     */
    protected String stringFormat() {
    StringBuffer x1 = new StringBuffer();
    for (int i = 0; i < this.power; i++) {
      x1.append(name);
      x1.append("*");
    }
    x1.deleteCharAt(x1.length() - 1);
    return x1.toString();
    }


  /*
 ¡¡¡¡* compareTo The method inherit from the interface "Comparable" Compare the
 ¡¡¡¡* <Var> type by name of the variable
  */


    /**
     * @param o 
     * @return
     */
    public int compareTo(Var o) {
      return Character.compare(this.name.charAt(0),o.name.charAt(0));
    }

}