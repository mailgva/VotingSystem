package com.voting.util;


import com.voting.model.AbstractBaseEntity;
import com.voting.model.Vote;
import com.voting.util.exception.NotFoundException;
import com.voting.util.exception.TooLateEcxeption;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class ValidationUtil {

    private static Calendar setTimeTo(Calendar calendar, int hours, int minutes, int seconds, int milliseconds) {
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE,minutes);
        calendar.set(Calendar.SECOND,seconds);
        calendar.set(Calendar.MILLISECOND,milliseconds);
        return calendar;
    }

    public static void checkTooLate(Vote vote) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Calendar voteDate = Calendar.getInstance();
        voteDate.setTime(vote.getDate());

        Calendar limitDate = Calendar.getInstance();
        limitDate = setTimeTo(limitDate, 11,0,0,0);


        if(voteDate.before(limitDate))
            throw new TooLateEcxeption(sdf.format(vote.getDate()) + " - it's to late to select restaurant");

    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }


    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }
}