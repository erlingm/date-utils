package no.moldesoft.lib.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

public record LocalDateInterval(LocalDate startDateInclusive, LocalDate endDateExclusive) {

    public static LocalDateInterval of(LocalDate startInclusive, LocalDate endExclusive) {
        Objects.requireNonNull(startInclusive, "startDateInclusive");
        Objects.requireNonNull(endExclusive, "endDateExclusive");
        if (!startInclusive.isBefore(endExclusive)) {
            throw new DateTimeException("Start date must be earlier than end date: " + startInclusive + "-" + endExclusive);
        }
        return new LocalDateInterval(startInclusive, endExclusive);
    }

    public boolean contains(LocalDate localDate) {
        return localDate != null && !localDate.isBefore(startDateInclusive) && localDate.isBefore(endDateExclusive);
    }

    public boolean isBefore(LocalDate localDate) {
        return localDate.isBefore(startDateInclusive);
    }

    public boolean isAfter(LocalDate localDate) {
        return !localDate.isBefore(endDateExclusive);
    }

}
