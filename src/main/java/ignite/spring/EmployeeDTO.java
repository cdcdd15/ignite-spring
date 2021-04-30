package ignite.spring;

import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class EmployeeDTO implements Serializable {
	private static final long serialVersionUID = 1145953070573742911L;

	@QuerySqlField(index = true)
	private Integer id;

	@QuerySqlField(index = true)
	private String name;

	@QuerySqlField(index = true)
	private boolean isEmployed;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEmployed() {
		return isEmployed;
	}

	public void setEmployed(boolean isEmployed) {
		this.isEmployed = isEmployed;
	}

	// getters, setters
}