package community.board.repository;

import community.board.domain.User;
import community.board.domain.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입_테스트")
    public void saveTest() {
        //given
        String username = "test";
        String nickname = "test12";

        userRepository.save(User.builder()
                .username(username)
                .password("1234")
                .email("test@gmail.com")
                .nickname(nickname)
                .role(Role.USER)
                .build());

        //when
        List<User> userList = userRepository.findAll();

        //then
        User user = userList.get(0);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getNickname()).isEqualTo(nickname);
    }
}
