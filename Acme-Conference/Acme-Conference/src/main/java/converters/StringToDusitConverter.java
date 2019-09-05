package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.DusitRepository;
import domain.Dusit;

@Component
@Transactional
public class StringToDusitConverter implements Converter<String, Dusit> {

	@Autowired
	DusitRepository	dusitRepository;


	@Override
	public Dusit convert(final String text) {
		Dusit result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.dusitRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}