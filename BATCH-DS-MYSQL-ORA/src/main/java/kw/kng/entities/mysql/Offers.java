package kw.kng.entities.mysql;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="MDS_OFFERS") //Mysql
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Offers 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
