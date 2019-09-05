
package controllers.all;

import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.DusitService;
import controllers.AbstractController;
import domain.Dusit;

@Controller
@RequestMapping("/dusit")
public class DusitController extends AbstractController {

	// controlcheck -----------------------------------
	
	@Autowired
	private DusitService dusitService;

	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int dusitId) {

		ModelAndView result;

		try {
			final Collection<Dusit> dusits = this.dusitService.findDusitsFinal();
			Dusit dusit = this.dusitService.findOne(dusitId);
			Assert.isTrue(dusits.contains(dusit));

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
	
	// -----------------------------------
}
