
package controllers.administrator;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
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
import services.QuoletService;
import controllers.AbstractController;
import domain.Conference;
import domain.Quolet;

@Controller
@RequestMapping("/quolet/administrator")
public class QuoletAdministratorController extends AbstractController {

	// controlcheck -----------------------------------
	
	@Autowired
	private QuoletService quoletService;

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private ConferenceService conferenceService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			Collection<Quolet> myQuolets = this.quoletService.findQuoletsByPrincipal();
			Collection<Quolet> quolets = this.quoletService.findAll();
			result = new ModelAndView("quolet/list");
			result.addObject("quolets",quolets);
			result.addObject("myQuolets",myQuolets);
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

			Quolet quolet = this.quoletService.create();
			result = this.createEditModelAndView(quolet);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int quoletId) {

		ModelAndView result;

		try {
			Quolet quolet = this.quoletService.findOne(quoletId);
			Assert.isTrue(quolet.getMode().equals("DRAFT"));
			result = this.createEditModelAndView(quolet);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");

		}

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int quoletId) {

		ModelAndView result;

		try {
			Quolet quolet = this.quoletService.findOne(quoletId);

			result = new ModelAndView("quolet/show");
			result.addObject("quolet",quolet);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params="save")
	public ModelAndView save(@ModelAttribute("quolet") Quolet quolet, final BindingResult binding) {

		ModelAndView result;
		quolet = this.quoletService.reconstruct(quolet, binding);

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(quolet);
		} else
			try {
				Quolet saved = this.quoletService.save(quolet);
				result = new ModelAndView("redirect:/quolet/administrator/show.do?quoletId="+saved.getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(quolet, "quolet.commit.error");
			}
		return result;

	}

	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Quolet quolet) {
		ModelAndView result;
		try {
			this.quoletService.delete(quolet);
			result = new ModelAndView("redirect:/quolet/administrator/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(quolet, "quolet.commit.error");
		}
		return result;
	}
	
	// ######################################################################################
	// ############################### Métodos auxiliares ###################################
	// ######################################################################################

	protected ModelAndView createEditModelAndView(Quolet quolet) {
		return this.createEditModelAndView(quolet, null);
	}

	protected ModelAndView createEditModelAndView(Quolet quolet, String messageCode) {
		ModelAndView result;
		if (quolet.getId() == 0) {
			result = new ModelAndView("quolet/create");
		} else {
			result = new ModelAndView("quolet/edit");
		}
		result.addObject("quolet", quolet);
		result.addObject("message", messageCode);
		
		Collection<Conference> conferences = this.conferenceService.findConferencesFinal();
		result.addObject("conferences", conferences);

		return result;
	}
	
	// -----------------------------------

}
