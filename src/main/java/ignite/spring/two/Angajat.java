package ignite.spring.two;

import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class Angajat implements Serializable{
	
	private static final long serialVersionUID = 3085320370793610706L;
	
	@QuerySqlField(index = true)
	private Long id;
	
	@QuerySqlField(index = true)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
