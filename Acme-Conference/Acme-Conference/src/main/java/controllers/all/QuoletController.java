
package controllers.all;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.QuoletService;
import controllers.AbstractController;
import domain.Quolet;

@Controller
@RequestMapping("/quolet")
public class QuoletController extends AbstractController {

	// controlcheck -----------------------------------
	
	@Autowired
	private QuoletService quoletService;

	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int quoletId) {

		ModelAndView result;

		try {
			final Collection<Quolet> quolets = this.quoletService.findQuoletsFinal();
			Quolet quolet = this.quoletService.findOne(quoletId);
			Assert.isTrue(quolets.contains(quolet));

			result = new ModelAndView("quolet/show");
			result.addObject("quolet",quolet);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	
	// -----------------------------------
}
