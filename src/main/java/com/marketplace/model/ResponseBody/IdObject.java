package com.marketplace.model.ResponseBody;

public class IdObject {
	
	private Long id;
	
	/**
	 * Constructor
	 * @param id
	 */
	public IdObject(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
