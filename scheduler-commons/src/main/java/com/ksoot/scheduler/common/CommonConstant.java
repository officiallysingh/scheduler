package com.ksoot.scheduler.common;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Application common constants.
 */
public final class CommonConstant {

	public static final String ID_PATH_VAR = "{id}";
	
	public static final String MEDIA_TYPE_ANY = "*/*";

	public static final String API_CONTEXT = "/**";

	public static final String SYSTEM_USER = "SYSTEM";

	public static final String HEADER_EXPAND = "expand";

	public static final String HEADER_LOCATION = "Location";
	
	public static final String HEADER_AUTHORIZATION = "Authorization";

	public static final String DOT = ".";
	
	public static final String SLASH = "/";
	
	// Datetime >>>
	public static final ZoneId ZONE_ID_UTC = ZoneId.of("UTC");

	public static final ZoneId ZONE_ID_IST = ZoneId.of("Asia/Kolkata");

	public static final TimeZone TIME_ZONE_UTC = TimeZone.getTimeZone(ZONE_ID_UTC);

	public static final TimeZone TIME_ZONE_IST = TimeZone.getTimeZone(ZONE_ID_IST);

	public static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

	public static final TimeZone DEFAULT_TIME_ZONE = TIME_ZONE_UTC;
	// <<< Datetime

    public static final String RESPONSE_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    
	public static final Locale DEFAULT_LOCALE = Locale.getDefault();
	
	public static final String REQUEST_DATETIME_ATTRIBUTE_NAME = "requestDateTime";
	
	public static final String API = SLASH;

	public static final String VERSION_v1 = "v1";

	public static final String VERSION_v2 = "v2";

	public static final String CURRENT_VERSION = VERSION_v1;

	private CommonConstant() {
		throw new IllegalStateException("Just a constants container, not supposed to be instantiated");
	}
}
