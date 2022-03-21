package study.datajpa.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import study.datajpa.entity.DeliveryPrice
import study.datajpa.entity.DeliveryPriceRepository
import study.datajpa.entity.Order
import study.datajpa.entity.OrderRepository

@Transactional
@SpringBootTest
@Rollback(false)
class OrderRepositoryTest {

    @Autowired lateinit var orderRepository: OrderRepository
    @Autowired lateinit var deliveryPriceRepository: DeliveryPriceRepository

//    @Test
//    fun name() {
//        val order = Order("order1")
//        val deliveryPrice = DeliveryPrice(100)
//        order.deliveryPrice = deliveryPrice
//
//        orderRepository.save(order)
//    }

    //    @Test
//    fun createOrder() {
//        val order = Order("order1")
//        orderRepository.save(order)
//    }
//
    @Test
    fun name() {
        val order = Order("order1")

        val deliveryPrice = DeliveryPrice(100)
        deliveryPrice.order = order

        deliveryPriceRepository.save(deliveryPrice)
    }
}