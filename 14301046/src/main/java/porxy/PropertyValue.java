package porxy;


public class PropertyValue {
	public PropertyValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	private String name;
	
	private Object value;
	
	private String ref;
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
