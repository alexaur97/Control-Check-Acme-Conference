
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Quolet extends DomainEntity {

	private Conference			conference;
	private Administrator		administrator;

	private String				ticker;
	private Date				publicationMoment;
	private String				x1;
	private String				x2;
	private String				x3;
	private String 				mode;
	
	
	@NotNull
	@ManyToOne(optional=false)
	public Conference getConference() {
		return conference;
	}
	
	public void setConference(Conference conference) {
		this.conference = conference;
	}
	
	@NotNull
	@ManyToOne(optional = false)
	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(final Administrator administrator) {
		this.administrator = administrator;
	}
	
	@NotBlank
	public String getTicker() {
		return ticker;
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	public Date getPublicationMoment() {
		return publicationMoment;
	}
	
	public void setPublicationMoment(Date publicationMoment) {
		this.publicationMoment = publicationMoment;
	}
	
	@NotBlank
	public String getX1() {
		return x1;
	}
	
	public void setX1(String x1) {
		this.x1 = x1;
	}
	
	@NotBlank
	public String getX2() {
		return x2;
	}
	
	public void setX2(String x2) {
		this.x2 = x2;
	}
	
	@NotBlank
	public String getX3() {
		return x3;
	}
	
	public void setX3(String x3) {
		this.x3 = x3;
	}
	
	@NotBlank
	@Pattern(regexp="^(DRAFT|FINAL)$")
	public String getMode() {
		return mode;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}

	
	
}
