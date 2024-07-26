package rjr.studio.passgate.conf.mapping.converter;

import com.github.dozermapper.core.DozerConverter;

public class ObscurePasswordConverter extends DozerConverter<String, String> {

	public ObscurePasswordConverter() {
		super(String.class, String.class);
	}

	@Override
	public String convertTo(String source, String destination) {
		return null;
	}

	@Override
	public String convertFrom(String source, String destination) {
		if (null == source || source.isEmpty())
			destination = null;
		else {
			destination = "********";
		}
		return destination;
	}

}