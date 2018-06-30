package com.kentoapps.ministagram.ui.signup

import com.kentoapps.ministagram.data.source.user.UserRepository
import com.kentoapps.ministagram.ui.account.AccountViewModel
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.then
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SignUpViewModelTest {

    @InjectMocks private lateinit var sut: AccountViewModel
    @Mock private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test fun onSignUpClick_success() {
        // GIVEN
        given(repository.signUp(USER_NAME, EMAIL, PASSWORD)).willReturn(Completable.complete())
        sut.userName.set(USER_NAME)
        sut.email.set(EMAIL)
        sut.password.set(PASSWORD)
        sut.confirm.set(PASSWORD)

        // WHEN
        sut.onSignUpClick()

        // THEN
        then(repository).should().signUp(USER_NAME, EMAIL, PASSWORD)
    }

    @Test fun onSignUpClick_fail_userNameIsEmplty() {
        // GIVEN
        given(repository.signUp(EMPTY, EMAIL, PASSWORD)).willReturn(Completable.complete())
        sut.userName.set(EMPTY)
        sut.email.set(EMAIL)
        sut.password.set(PASSWORD)
        sut.confirm.set(PASSWORD)

        // WHEN
        sut.onSignUpClick()

        // THEN
        assert(sut.errorMessage.get()?.isNotEmpty() == true)
        then(repository).should(never()).signUp(EMPTY, EMAIL, PASSWORD)
    }

    @Test fun onSignUpClick_fail_emailIsEmplty() {
        // GIVEN
        given(repository.signUp(USER_NAME, EMPTY, PASSWORD)).willReturn(Completable.complete())
        sut.userName.set(USER_NAME)
        sut.email.set(EMPTY)
        sut.password.set(PASSWORD)
        sut.confirm.set(PASSWORD)

        // WHEN
        sut.onSignUpClick()

        // THEN
        assert(sut.errorMessage.get()?.isNotEmpty() == true)
        then(repository).should(never()).signUp(USER_NAME, EMPTY, PASSWORD)
    }

    @Test fun onSignUpClick_fail_passwordIsEmplty() {
        // GIVEN
        given(repository.signUp(USER_NAME, EMAIL, EMPTY)).willReturn(Completable.complete())
        sut.userName.set(USER_NAME)
        sut.email.set(EMAIL)
        sut.password.set(EMPTY)
        sut.confirm.set(PASSWORD)

        // WHEN
        sut.onSignUpClick()

        // THEN
        assert(sut.errorMessage.get()?.isNotEmpty() == true)
        then(repository).should(never()).signUp(USER_NAME, EMAIL, EMPTY)
    }

    @Test fun onSignUpClick_fail_confirmIsEmplty() {
        // GIVEN
        given(repository.signUp(USER_NAME, EMAIL, PASSWORD)).willReturn(Completable.complete())
        sut.userName.set(USER_NAME)
        sut.email.set(EMAIL)
        sut.password.set(PASSWORD)
        sut.confirm.set(EMPTY)

        // WHEN
        sut.onSignUpClick()

        // THEN
        assert(sut.errorMessage.get()?.isNotEmpty() == true)
        then(repository).should(never()).signUp(USER_NAME, EMAIL, PASSWORD)
    }

    @Test fun onSignUpClick_fail_emailWithoutAt() {
        // GIVEN
        given(repository.signUp(USER_NAME, EMAIL_WITHOUT_AT, PASSWORD)).willReturn(Completable.complete())
        sut.userName.set(USER_NAME)
        sut.email.set(EMAIL_WITHOUT_AT)
        sut.password.set(PASSWORD)
        sut.confirm.set(PASSWORD)

        // WHEN
        sut.onSignUpClick()

        // THEN
        assert(sut.errorMessage.get()?.isNotEmpty() == true)
        then(repository).should(never()).signUp(USER_NAME, EMAIL_WITHOUT_AT, PASSWORD)
    }

    @Test fun onSignUpClick_fail_confirmIsDifferent() {
        // GIVEN
        given(repository.signUp(USER_NAME, EMAIL_WITHOUT_AT, PASSWORD)).willReturn(Completable.complete())
        sut.userName.set(USER_NAME)
        sut.email.set(EMAIL_WITHOUT_AT)
        sut.password.set(PASSWORD)
        sut.confirm.set(CONFIRM_DIFFERENT)

        // WHEN
        sut.onSignUpClick()

        // THEN
        assert(sut.errorMessage.get()?.isNotEmpty() == true)
        then(repository).should(never()).signUp(USER_NAME, EMAIL_WITHOUT_AT, PASSWORD)
    }

    companion object {
        private const val EMPTY = ""
        private const val USER_NAME = "test"
        private const val EMAIL = "test@test.com"
        private const val PASSWORD = "test12345"
        private const val EMAIL_WITHOUT_AT = "testtest.com"
        private const val CONFIRM_DIFFERENT = "test123455"
    }
}