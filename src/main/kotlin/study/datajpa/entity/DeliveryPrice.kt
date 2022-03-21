package study.datajpa.entity

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
class DeliveryPrice(price: Long) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_price_id")
    var id: Long = 0

    @Column(name = "price")
    val price: Long = price

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "order_no")
    var order: Order? = null
}
interface DeliveryPriceRepository : JpaRepository<DeliveryPrice, Long>
