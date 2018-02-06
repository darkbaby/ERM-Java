
public class Test1 implements Cloneable{
	private String attr;

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}
	  @Override
	  protected Object clone() throws CloneNotSupportedException {
	        return super.clone();
	}
}
