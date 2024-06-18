package kw.kng.entities.oracle.ds1;

import java.time.LocalDateTime;

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
@Table(name="MDS_OFFERS_LUKE_V1") //Oracle ds1 scenario
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Offers 
{
	
	@Id
	@SequenceGenerator(name="gen1", sequenceName="mds_ord_se", initialValue=200, allocationSize=1)
	@GeneratedValue(generator="gen1", strategy=GenerationType.SEQUENCE)
	private Integer offerId;
	
	@NonNull
	@Column(length=20)
	private String offerName;
	
	@Column(length=20)
	@NonNull
	private String offerCode;
	
	@NonNull
	private Double discountPercentage;
	
	@NonNull
	private LocalDateTime expireyDate;

	public Offers(@NonNull String offerName, @NonNull String offerCode, @NonNull Double discountPercentage,
			@NonNull LocalDateTime expireyDate)
	{
		this.offerName = offerName;
		this.offerCode = offerCode;
		this.discountPercentage = discountPercentage;
		this.expireyDate = expireyDate;
	}
	
	
	
	
}
