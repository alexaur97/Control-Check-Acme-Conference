
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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Dusit extends DomainEntity {

	private Conference			conference;
	private Administrator		administrator;

	private String				ticker;
	private Date				publicationMoment;
	private String				body;
	private String				picture;
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
	@Size(max=251)
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	@URL
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
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
