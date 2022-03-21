package study.datajpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.stereotype.Component
import java.util.Optional
import java.util.UUID

@EnableJpaAuditing
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@Component
class AuditorProvider : AuditorAware<String> {
    override fun getCurrentAuditor() = Optional.of(UUID.randomUUID().toString())
}
