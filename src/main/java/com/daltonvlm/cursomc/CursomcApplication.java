package com.daltonvlm.cursomc;

import com.daltonvlm.cursomc.domain.*;
import com.daltonvlm.cursomc.domain.enums.ClientType;
import com.daltonvlm.cursomc.domain.enums.PaymentState;
import com.daltonvlm.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientOrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ClientOrderItemRepository orderItemRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category informatics = new Category(null, "Informatics");
        Category office = new Category(null, "Office");
        Category householdLinen = new Category(null, "Household linen");
        Category electronics = new Category(null, "Electronics");
        Category gardening = new Category(null, "Gardening");
        Category decoration = new Category(null, "Decoration");
        Category perfumery = new Category(null, "Perfumery");

        Product computer = new Product(null, "Computer", 2000.00);
        Product printer = new Product(null, "Printer", 800.00);
        Product mouse = new Product(null, "Mouse", 80.00);

        informatics.getProducts().addAll(Arrays.asList(computer, printer, mouse));
        office.getProducts().addAll(Arrays.asList(printer));

        computer.getCategories().addAll(Arrays.asList(informatics));
        printer.getCategories().addAll(Arrays.asList(informatics, office));
        mouse.getCategories().addAll(Arrays.asList(informatics));

        categoryRepository.saveAll(Arrays.asList(informatics, office, householdLinen, electronics, gardening, decoration, perfumery));
        productRepository.saveAll(Arrays.asList(computer, printer, mouse));

        State minasGerais = new State(null, "Minas Gerais");
        State saoPaulo = new State(null, "São Paulo");

        City uberlandia = new City(null, "Uberlândia", minasGerais);
        City saoPauloCity = new City(null, "São Paulo", saoPaulo);
        City campinas = new City(null, "Campinas", saoPaulo);

        minasGerais.getCities().addAll(Arrays.asList(uberlandia));
        saoPaulo.getCities().addAll(Arrays.asList(saoPauloCity, campinas));

        stateRepository.saveAll(Arrays.asList(minasGerais, saoPaulo));
        cityRepository.saveAll(Arrays.asList(uberlandia, saoPauloCity, campinas));

        Client mariaSilva = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.NATURAL_PERSON);
        mariaSilva.getPhones().addAll(Arrays.asList("27363323", "93838393"));

        Address address1 = new Address(
                null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", mariaSilva, uberlandia);
        Address address2 = new Address(
                null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", mariaSilva, saoPauloCity);

        mariaSilva.getAddresses().addAll(Arrays.asList(address1, address2));

        clientRepository.saveAll(Arrays.asList(mariaSilva));
        addressRepository.saveAll(Arrays.asList(address1, address2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        ClientOrder order1 = new ClientOrder(null, sdf.parse("30/09/2017 10:32"), mariaSilva, address1);
        ClientOrder order2 = new ClientOrder(null, sdf.parse("10/10/2017 19:35"), mariaSilva, address2);

        Payment payment1 = new PaymentByCard(null, PaymentState.PAID, order1, 6);
        order1.setPayment(payment1);

        Payment payment2 = new PaymentByTicket(null, PaymentState.PENDING, order2, sdf.parse("20/10/2017 00:00"), null);
        order2.setPayment(payment2);

        mariaSilva.getOrders().addAll(Arrays.asList(order1, order2));

        orderRepository.saveAll(Arrays.asList(order1, order2));
        paymentRepository.saveAll(Arrays.asList(payment1, payment2));

        ClientOrderItem orderItem1 = new ClientOrderItem(order1, computer, 0.00, 1, 2000.00);
        ClientOrderItem orderItem2 = new ClientOrderItem(order1, mouse, 0.00, 2, 80.00);
        ClientOrderItem orderItem3 = new ClientOrderItem(order2, printer, 100.00, 1, 800.00);

        order1.getOrderItems().addAll(Arrays.asList(orderItem1, orderItem2));
        order2.getOrderItems().addAll(Arrays.asList(orderItem3));

        computer.getOrderItems().addAll(Arrays.asList(orderItem1));
        mouse.getOrderItems().addAll(Arrays.asList(orderItem2));
        printer.getOrderItems().addAll(Arrays.asList(orderItem3));

        orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));
    }
}
