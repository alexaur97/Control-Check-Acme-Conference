package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Dusit;

@Component
@Transactional
public class DusitToStringConverter implements Converter<Dusit, String> {

	@Override
	public String convert(final Dusit dusit) {
		String result;
		if (dusit == null)
			result = null;
		else
			result = String.valueOf(dusit.getId());
		return result;
	}

}