
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.Administrator;
import domain.Dusit;
import repositories.DusitRepository;

@Service
@Transactional
public class DusitService {

	@Autowired
	private DusitRepository dusitRepository;

	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private Validator			validator;

	public Dusit create() {
		return new Dusit();
	}

	public Collection<Dusit> findDusitsByPrincipal() {
		Administrator principal = this.administratorService.findByPrincipal();
		return this.dusitRepository.findDusitsByPrincipal(principal.getId());
	}

	public Dusit findOne(int dusitId) {
		return this.dusitRepository.findOne(dusitId);
	}

	public Dusit reconstruct(Dusit dusit, BindingResult binding) {
		Dusit result = dusit;
		if (dusit.getId() != 0) {
			Dusit ret = this.dusitRepository.findOne(dusit.getId());
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
		
		this.validator.validate(result, binding);
		
		return result;
	}

	private String generateTicker(Date moment) {
		final Integer year = moment.getYear() % 100;
		final Integer month = moment.getMonth() + 1;
		final Integer day = moment.getDate();

		int numero1 = (int) (Math.random() * 9) + 1;
		int numero2 = (int) (Math.random() * 9) + 1;
		int numero3 = (int) (Math.random() * 9) + 1;
		int numero4 = (int) (Math.random() * 9) + 1;
		int numero5 = (int) (Math.random() * 9) + 1;
		int numero6 = (int) (Math.random() * 9) + 1;
		
		String n1 = numero1+"";
		String n2 = numero2+"";
		String n3 = numero3+"";
		String n4 = numero4+"";
		String n5 = numero5+"";
		String n6 = numero6+"";
		
		String yy = "" + year;
		String mm = "" + month;
		String dd = "" + day;

		if (year < 10)
			yy = "0" + yy;

		if (month < 10)
			mm = "0" + mm;
		if (day < 10)
			dd = "0" + dd;
		final String result = n1+n2+n3+n4+n5+n6+":"+yy+":"+mm +":"+ dd;
		return result;
	}

	public Dusit save(Dusit dusit) {
		this.administratorService.findByPrincipal();
		return this.dusitRepository.save(dusit);
	}

	public void delete(Dusit dusit) {
		this.administratorService.findByPrincipal();
		this.dusitRepository.delete(dusit);
	}

	public Collection<Dusit> findAll() {
		return this.dusitRepository.findAll();
	}

	public Collection<Dusit> findDusitsFinal() {
		return this.dusitRepository.findDusitsFinal();
	}

	public Collection<Dusit> findDusitsByConference(int conferenceId) {
		return this.dusitRepository.findDusitsByConference(conferenceId);
	}

	public Collection<Double> statsNumberDusit() {
		final Collection<Double> result = this.dusitRepository.statsNumberDusits();
		Assert.notNull(result);
		return result;
	}

	public Double publishedRatio() {
		final Double result = this.dusitRepository.publishedRatio();
		Assert.notNull(result);
		return result;
	}

	public Double unpublishedRatio() {
		final Double result = this.dusitRepository.unpublishedRatio();
		Assert.notNull(result);
		return result;
	}

}
