package cz.etn.etnshop.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product implements Serializable {
	
 	private static final long serialVersionUID = -2739622030641073946L;

	private int id;
 
    private String name;
    private String serialNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    @Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "serialNumber", nullable = false)
	public String getSerialNumber(){
		return serialNumber;
	}
	
	public void setSerialNumber(String serialNumber){
		this.serialNumber=serialNumber;
	}
    
    
    
}
