package community.board.repository;

import community.board.domain.User;
import community.board.domain.UserLevel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    @Rollback
    void findUser() throws Exception {
        //given
        String username = "james";
        String password = "james12!@";
        String encPassword = encoder.encode(password);
        userRepository.save(User.builder()
                .username(username)
                .password(encPassword)
                .nickname("james")
                .email("james@naver.com")
                .userLevel(UserLevel.USER)
                .build());
        //when
        List<User> userList = userRepository.findAll();
        User findUser = userList.get(0);
        //then
        assertThat(findUser.getPassword()).isEqualTo(encPassword);
    }
}