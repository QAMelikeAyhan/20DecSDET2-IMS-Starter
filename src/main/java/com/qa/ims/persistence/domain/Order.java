package com.qa.ims.persistence.domain;

public class Order {

	private Long id;
	private Long fk_cid;
	
	public Order(Long id, Long fk_cid) {
		this.setId(id);
		this.setId(fk_cid);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFk_cid() {
		return fk_cid;
	}

	public void setFk_cid(Long fk_cid) {
		this.fk_cid = fk_cid;
	}

	@Override
	public String toString() {
		return "id: " + id + ", fk_cid=" + fk_cid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		double result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((fk_cid == null) ? 0 : fk_cid.hashCode());
		return (int) result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
		}
}
