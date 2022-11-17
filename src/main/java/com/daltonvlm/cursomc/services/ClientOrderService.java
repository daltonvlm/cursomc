package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.ClientOrder;
import com.daltonvlm.cursomc.domain.ClientOrderItem;
import com.daltonvlm.cursomc.domain.PaymentByTicket;
import com.daltonvlm.cursomc.domain.enums.PaymentState;
import com.daltonvlm.cursomc.repositories.ClientOrderItemRepository;
import com.daltonvlm.cursomc.repositories.ClientOrderRepository;
import com.daltonvlm.cursomc.repositories.PaymentRepository;
import com.daltonvlm.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class ClientOrderService {
    @Autowired
    private ClientOrderRepository repository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ClientOrderItemRepository clientOrderItemRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ProductService productService;

    public ClientOrder find(Integer id) {
        Optional<ClientOrder> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + ClientOrder.class.getName()));
    }

    @Transactional
    public ClientOrder insert(ClientOrder obj) {
        obj.setId(null);
        obj.setDate(new Date());

        obj.getPayment().setPaymentState(PaymentState.PENDING);
        obj.getPayment().setOrder(obj);
        if (obj.getPayment() instanceof PaymentByTicket) {
            PaymentByTicket payment = (PaymentByTicket) obj.getPayment();
            ticketService.fillPaymentByTicket(payment, obj.getDate());
        }
        obj = repository.save(obj);
        paymentRepository.save(obj.getPayment());

        for (ClientOrderItem item : obj.getOrderItems()) {
            item.setDiscount(0.0);
            item.setPrice(productService.find(item.getProduct().getId()).getPrice());
            item.setOrder(obj);
        }
        clientOrderItemRepository.saveAll(obj.getOrderItems());

        return obj;
    }
}
