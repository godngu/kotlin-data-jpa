package study.datajpa.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class EmailAddressTest {

    @Test
    internal fun check_valid() {
        // given
        val emailString = "kiljun@kurlycorp.com"

        // when
        val emailAddress = EmailAddress(emailString)

        // then
        assertThat(emailAddress.getId()).isEqualTo("kiljun")
        assertThat(emailAddress.getDomain()).isEqualTo("kurlycorp.com")
    }

    @Test
    internal fun check_invalid() {
        // given
        val emailString = "kiljun@kurlycorp_com"

        // when
        val assertThrows = Assertions.assertThrows(IllegalArgumentException::class.java) {
            EmailAddress(emailString)
        }

        // then
        assertThat(assertThrows.message).isEqualTo("올바르지 않은 이메일 규칙입니다.")
    }
}
