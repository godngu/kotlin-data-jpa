package study.datajpa.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.MIN
        protected set

    @LastModifiedDate
    var lastModifiedDate: LocalDateTime = LocalDateTime.MIN
        protected set

    @CreatedBy
    @Column(updatable = false)
    var createdBy: String? = null
        protected set

    @LastModifiedBy
    var lastModifiedBy: String? = null
        protected set
}
