package com.ksoot.scheduler.common;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Rajveer Singh
 */
public class DateTimeHelper {

	public static final LocalTime LOCAL_TIME_MIN = LocalTime.MIN.truncatedTo(ChronoUnit.MINUTES);

	public static final LocalTime LOCAL_TIME_MAX = LocalTime.MAX.truncatedTo(ChronoUnit.MINUTES);

	public final static ZoneId ZONE_ID_UTC = ZoneId.of("UTC");

	// Indian Standard Time Zone
	public final static ZoneId ZONE_ID_IST = ZoneId.of("Asia/Kolkata");

	public final static TimeZone TIME_ZONE_UTC = TimeZone.getTimeZone(ZONE_ID_UTC);

	public final static TimeZone TIME_ZONE_IST = TimeZone.getTimeZone(ZONE_ID_IST);

	public final static Locale DEFAULT_LOCALE = Locale.getDefault();

	public final static ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

	public final static TimeZone DEFAULT_TIME_ZONE = TIME_ZONE_UTC;

	public static String formatDate(final LocalDate date) {
		return date.format(DateTimeFormatter.ISO_DATE);
	}

	public static String formatDate(final LocalDate date, final String pattern) {
		return date.format(DateTimeFormatter.ofPattern(pattern));
	}

	public static String formatDate(final LocalDate date, final DateTimeFormatter formatter) {
		return date.format(formatter);
	}

	public static String formatDateTime(final LocalDateTime datetime) {
		return datetime.format(DateTimeFormatter.ISO_DATE_TIME);
	}

	public static String formatDateTime(final LocalDateTime datetime, final String pattern) {
		return datetime.format(DateTimeFormatter.ofPattern(pattern));
	}

	public static String formatDateTime(final LocalDateTime datetime, final DateTimeFormatter formatter) {
		return datetime.format(formatter);
	}

	public static String formatZonedDateTime(final ZonedDateTime zonedDateTime) {
		return zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
	}

	public static String formatZonedDateTime(final ZonedDateTime zonedDateTime, final String pattern) {
		return zonedDateTime.format(DateTimeFormatter.ofPattern(pattern));
	}

	public static String formatZonedDateTime(final ZonedDateTime zonedDateTime, final DateTimeFormatter formatter) {
		return zonedDateTime.format(formatter);
	}

	public static ZonedDateTime nowZonedDateTimeUTC() {
		return ZonedDateTime.now(ZONE_ID_UTC);
	}

	public static ZonedDateTime nowZonedDateTime(final ZoneId zoneId) {
		return ZonedDateTime.now(zoneId);
	}

	public static LocalDateTime nowLocalDateTimeUTC() {
		return LocalDateTime.now(ZONE_ID_UTC);
	}

	public static LocalDateTime nowLocalDateTime(final ZoneId zoneId) {
		return LocalDateTime.now(zoneId);
	}

	public static ZonedDateTime convertZonedDateTime(final ZonedDateTime sourceZoneDatetime,
			final ZoneId targetZoneId) {
		return sourceZoneDatetime.getZone() == targetZoneId ? sourceZoneDatetime
				: sourceZoneDatetime.withZoneSameInstant(targetZoneId);
	}

	public static String convertAndFormatZonedDateTime(final ZonedDateTime sourceZoneDatetime,
			final ZoneId targetZoneId) {
		return sourceZoneDatetime.getZone() == targetZoneId ? formatZonedDateTime(sourceZoneDatetime)
				: formatZonedDateTime(sourceZoneDatetime.withZoneSameInstant(targetZoneId));
	}

	public static OffsetDateTime nowOffsetDateTimeUTC() {
		return OffsetDateTime.now(ZONE_ID_UTC);
	}

	public static OffsetDateTime nowOffsetDateTime(final ZoneId zoneId) {
		return OffsetDateTime.now(zoneId);
	}

	public static OffsetDateTime offsetDateTime(final Date date) {
		return date.toInstant().atOffset(ZoneOffset.UTC);
	}

	public static OffsetDateTime offsetDateTime(final Date date, ZoneOffset zoneOffset) {
		return date.toInstant().atOffset(zoneOffset);
	}

	// Truncate to minutes also
	public static LocalTime covertLocalTimeHHmm(final LocalTime time, final ZoneId sourceZoneId,
			final ZoneId targetZoneId) {
		if (sourceZoneId == targetZoneId) {
			return time;
		}
		ZonedDateTime sourceTime = ZonedDateTime.of(LocalDate.now(), time, sourceZoneId);
		ZonedDateTime converted = sourceTime.withZoneSameInstant(targetZoneId);
		return LocalTime.of(converted.getHour(), converted.getMinute());
	}

	public static String formatTime(final LocalTime time, final DateTimeFormatter formatter) {
		return time.format(formatter);
	}

	public static String formatTime(final LocalTime time) {
		return time.format(DateTimeFormatter.ISO_DATE_TIME);
	}

	public static boolean timeLe(final LocalTime candidate, final LocalTime reference) {
		return !candidate.isAfter(reference);
	}

	public static boolean timeGe(final LocalTime candidate, final LocalTime reference) {
		return !candidate.isBefore(reference);
	}

	public static boolean timeBetween(final LocalTime candidate, final LocalTime from, final LocalTime till) {
		return timeGe(candidate, from) && timeLe(candidate, till);
	}

	public static LocalTime nowLocalTimeHHmm() {
		return LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
	}

	public static String nowOffsetDateTimeString() {
		return OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}
}
