package arslabadack.ifsc.oop2.skynet.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marketplace {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;

	private String productName;

	private String productPrice;

	private String productDescription;
	
	public Marketplace() {

	}

	public Marketplace(int productId) {
		super();
		this.productId = productId;
	}

	public Marketplace(String productName, String productPrice, String productDescription) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marketplace other = (Marketplace) obj;
		if (productId != other.productId)
			return false;
		return true;
	}

}
