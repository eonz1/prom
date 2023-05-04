package com.cgram.prom.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cgram.prom.user.domain.User;
import com.cgram.prom.user.exception.UserException;
import com.cgram.prom.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository);
        user = User.builder()
            .email("a@gmail.com")
            .password("test1234!")
            .build();
    }

    @Test
    @DisplayName("이메일이 중복되면 회원가입 할 수 없다.")
    public void registerDuplicatedEmail() throws Exception {
        // given
        Optional<User> returnUser = Optional.of(new User());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(returnUser);

        // expected
        UserException exception = assertThrows(UserException.class, () -> {
            userService.register(user);
        });
        assertEquals(exception.getExceptionType().getStatus(), HttpStatus.CONFLICT);
        verify(userRepository, never()).save(user);
    }

    @Test
    @DisplayName("이메일이 중복되지 않으면 회원가입에 성공한다.")
    public void registerEmail() throws Exception {
        // given
        Optional<User> returnUser = Optional.empty();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(returnUser);

        // when
        userService.register(user);

        // then
        verify(userRepository, times(1)).save(user);
    }
}