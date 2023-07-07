package lambdasinaction._02stream.with.optional;

import java.util.Optional;

public class MobileService {

	public Integer getMobileScreenWidth(Optional<Mobile> mobile){
		return mobile.flatMap(mobile1 -> mobile1.getDisplayFeatures())
				//.flatMap(Mobile::getDisplayFeatures)
					 .flatMap(DisplayFeatures::getResolution)
					 .map(ScreenResolution::getWidth)
					 .orElse(0);
	}

}
