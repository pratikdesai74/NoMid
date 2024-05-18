package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringJUnit4ClassRunner.class)

//@ExtendWith(SpringExtension.class)
//@Transactional

//@DataJpaTest
//@ActiveProfiles("test")
//@TestPropertySource("classpath:application-test.yaml")

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"}) // testdata profil for images in Database
@DirtiesContext
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    @Test
    public void givenOrder_whenSave_thenGetOk() {
        Order order = Order.builder()
                .productId(1)
                .quantity(10)
                .totalAmount(555)
                .build();

        Order savedOrder=repository.save(order);

        Order retrivedOrder = repository.findById(savedOrder.getId()).get();
        assertEquals(retrivedOrder.getTotalAmount(),order.getTotalAmount() );
    }
}