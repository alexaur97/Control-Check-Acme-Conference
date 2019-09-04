
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import domain.Administrator;
import domain.Quolet;
import repositories.QuoletRepository;

@Service
@Transactional
public class QuoletService {

	@Autowired
	private QuoletRepository quoletRepository;

	@Autowired
	private AdministratorService administratorService;

	public Quolet create() {
		return new Quolet();
	}

	public Collection<Quolet> findQuoletsByPrincipal() {
		Administrator principal = this.administratorService.findByPrincipal();
		return this.quoletRepository.findQuoletsByPrincipal(principal.getId());
	}

	public Quolet findOne(int quoletId) {
		return this.quoletRepository.findOne(quoletId);
	}

	public Quolet reconstruct(Quolet quolet, BindingResult binding) {
		Quolet result = quolet;
		if (quolet.getId() != 0) {
			Quolet ret = this.quoletRepository.findOne(quolet.getId());
			Assert.isTrue(ret.getMode().equals("DRAFT"));
			
			result.setPublicationMoment(ret.getPublicationMoment());
			result.setTicker(ret.getTicker());
			result.setAdministrator(ret.getAdministrator());
		} else {
			Date moment = new Date();
			result.setPublicationMoment(moment);
			Administrator principal = this.administratorService.findByPrincipal();
			result.setAdministrator(principal);
			String ticker = this.generateTicker(moment);
			result.setTicker(ticker);
		}
		return result;
	}

	private String generateTicker(Date moment) {
		final Integer year = moment.getYear() % 100;
		final Integer month = moment.getMonth() + 1;
		final Integer day = moment.getDate();

		String yy = "" + year;
		String mm = "" + month;
		String dd = "" + day;

		if (year < 10)
			yy = "0" + yy;

		if (month < 10)
			mm = "0" + mm;
		if (day < 10)
			dd = "0" + dd;
		final String result = yy + mm + dd;
		return result;
	}

	public Quolet save(Quolet quolet) {
		this.administratorService.findByPrincipal();
		return this.quoletRepository.save(quolet);
	}

	public void delete(Quolet quolet) {
		this.administratorService.findByPrincipal();
		this.quoletRepository.delete(quolet);
	}

	public Collection<Quolet> findAll() {
		return this.quoletRepository.findAll();
	}

	public Collection<Quolet> findQuoletsFinal() {
		return this.quoletRepository.findQuoletsFinal();
	}

	public Collection<Quolet> findQuoletsByConference(int conferenceId) {
		return this.quoletRepository.findQuoletsByConference(conferenceId);
	}

}
