package rest.service.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rest.entity.User;
import rest.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceLogicTest {

    @Autowired
    private UserService userService;

    private User aaa;

    private User bbb;

    @BeforeEach
    public void init() {
        this.aaa = new User("aaa", "aaa@aaa.co.kr");
        this.bbb = new User("bbb", "bbb@bbb.co.kr");
        this.userService.register(aaa);
        this.userService.register(bbb);
    }

    @Test
    public void registerTest() {

        User user = User.sample();
        assertThat(this.userService.register(user)).isEqualTo(user.getId());
        assertThat(this.userService.findAll().size()).isEqualTo(3);
        this.userService.remove(user.getId());
    }

    @Test
    public void find() {
        assertThat(this.userService.find(bbb.getId())).isNotNull();
        assertThat(this.userService.find(bbb.getId()).getEmail()).isEqualTo(bbb.getEmail());
    }

    @AfterEach
    public void clean() {
        this.userService.remove(aaa.getId());
        this.userService.remove(bbb.getId());
    }
}
