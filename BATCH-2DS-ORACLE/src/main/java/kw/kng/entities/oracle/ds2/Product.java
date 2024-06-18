package kw.kng.entities.oracle.ds2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="MDS_PRODUCT_LUKE_V1") //Oracle ds2 scenario
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product 
{
	@Id
	@SequenceGenerator(name="gen1", sequenceName="mds_prod_se", initialValue=200, allocationSize=1)
	@GeneratedValue(generator="gen1", strategy=GenerationType.SEQUENCE)
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pid;
	
	@NonNull
	@Column(length =20)
	private String pname;
	
	@NonNull
	private Double qty;
	
	@NonNull
	private Double price;

	public Product( @NonNull String pname, @NonNull Double qty, @NonNull Double price) 
	{
		this.pname = pname;
		this.qty = qty;
		this.price = price;
	}
	
	
}


/*
create sequence mds_prod_seq
start with 200
increment by 1
NOMAXVALUE;
 */