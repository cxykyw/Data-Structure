package com.kyw.productAndcustomer;

/**
 * 容器数据类型
 * @author MR.W
 *
 */

public class PCData {

	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PCData(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "PCData [id=" + id + ", name=" + name + "]";
	}
	
	
}
