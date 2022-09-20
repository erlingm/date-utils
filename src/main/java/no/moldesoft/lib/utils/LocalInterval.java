package no.moldesoft.utils;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Erling Molde on 07.09.2016.
 */
public class LocalInterval implements Serializable {
    private final LocalDate startDateInclusive;
    private final LocalDate endDateExclusive;

    private LocalInterval(LocalDate startDateInclusive, LocalDate endDateExclusive) {
        this.startDateInclusive = startDateInclusive;
        this.endDateExclusive = endDateExclusive;
    }

    public static LocalInterval of(LocalDate startDateInclusive, LocalDate endDateExclusive) {
        Objects.requireNonNull(startDateInclusive, "startDateInclusive");
        Objects.requireNonNull(endDateExclusive, "endDateExclusive");
        if (!startDateInclusive.isBefore(endDateExclusive))
            throw new DateTimeException("Start date must be earlier than end date: " + startDateInclusive + "-" + endDateExclusive);
        return new LocalInterval(startDateInclusive, endDateExclusive);
    }

    public boolean contains(LocalDate localDate) {
        return localDate != null && !localDate.isBefore(startDateInclusive) && localDate.isBefore(endDateExclusive);
    }

    public LocalDate getStartDateInclusive() {
        return startDateInclusive;
    }

    public LocalDate getEndDateExclusive() {
        return endDateExclusive;
    }

    public boolean isBefore(LocalDate localDate) {
        return localDate.isBefore(startDateInclusive);
    }

    public boolean isAfter(LocalDate localDate) {
        return !localDate.isBefore(getEndDateExclusive());
    }

}
