
package controllers.administrator;

import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.ConferenceService;
import services.DusitService;
import controllers.AbstractController;
import domain.Conference;
import domain.Dusit;

@Controller
@RequestMapping("/dusit/administrator")
public class DusitAdministratorController extends AbstractController {

	// controlcheck -----------------------------------
	
	@Autowired
	private DusitService dusitService;

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private ConferenceService conferenceService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			Collection<Dusit> myDusits = this.dusitService.findDusitsByPrincipal();
			Collection<Dusit> dusits = this.dusitService.findAll();
			result = new ModelAndView("dusit/list");
			result.addObject("dusits",dusits);
			result.addObject("myDusits",myDusits);
			result.addObject("requestURI","dusit/administrator/list.do");
			
			final Date fecha = new Date();
			final Long date2 = fecha.getTime();
			
			result.addObject("date2", date2);
			
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {

			this.administratorService.findByPrincipal();

			Dusit dusit = this.dusitService.create();
			result = this.createEditModelAndView(dusit);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int dusitId) {

		ModelAndView result;

		try {
			Dusit dusit = this.dusitService.findOne(dusitId);
			Assert.isTrue(dusit.getMode().equals("DRAFT"));
			result = this.createEditModelAndView(dusit);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");

		}

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int dusitId) {

		ModelAndView result;

		try {
			Dusit dusit = this.dusitService.findOne(dusitId);

			result = new ModelAndView("dusit/show");
			result.addObject("dusit",dusit);
			
			final Locale l = LocaleContextHolder.getLocale();
			final String lang = l.getLanguage();
			result.addObject("lang",lang);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params="save")
	public ModelAndView save(@ModelAttribute("dusit") Dusit dusit, final BindingResult binding) {

		ModelAndView result;
		dusit = this.dusitService.reconstruct(dusit, binding);

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(dusit);
		} else
			try {
				Dusit saved = this.dusitService.save(dusit);
				result = new ModelAndView("redirect:/dusit/administrator/show.do?dusitId="+saved.getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(dusit, "dusit.commit.error");
			}
		return result;

	}

	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Dusit dusit) {
		ModelAndView result;
		try {
			this.dusitService.delete(dusit);
			result = new ModelAndView("redirect:/dusit/administrator/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(dusit, "dusit.commit.error");
		}
		return result;
	}
	
	// ######################################################################################
	// ############################### Métodos auxiliares ###################################
	// ######################################################################################

	protected ModelAndView createEditModelAndView(Dusit dusit) {
		return this.createEditModelAndView(dusit, null);
	}

	protected ModelAndView createEditModelAndView(Dusit dusit, String messageCode) {
		ModelAndView result;
		if (dusit.getId() == 0) {
			result = new ModelAndView("dusit/create");
		} else {
			result = new ModelAndView("dusit/edit");
		}
		result.addObject("dusit", dusit);
		result.addObject("message", messageCode);
		
		Collection<Conference> conferences = this.conferenceService.findConferencesFinal();
		result.addObject("conferences", conferences);

		return result;
	}
	
	// -----------------------------------

}
