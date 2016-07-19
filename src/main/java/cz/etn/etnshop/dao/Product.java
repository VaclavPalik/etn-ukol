package cz.etn.etnshop.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
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
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "serialNumber", nullable = false)
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	public String getSerialNumber(){
		return serialNumber;
	}
	
	public void setSerialNumber(String serialNumber){
		this.serialNumber=serialNumber;
	}
    
    
    
}
