package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.PaymentByTicket;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TicketService {

    public void fillPaymentByTicket(PaymentByTicket payment, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        payment.setExpirationDate(calendar.getTime());
    }
}
