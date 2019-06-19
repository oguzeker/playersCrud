package com.sosyal.tr.playersCrud;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.sosyal.tr.playersCrud.entity.Player;
import com.sosyal.tr.playersCrud.repository.PlayerRepository;

@RunWith(SpringRunner.class)
// @SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
public class DemoApplicationTests {

	@Autowired 
	private PlayerRepository playerRepository;

    @Test
    public void givenTwoImportFilesWhenFindAllShouldReturnSixUsers() {
        Collection<Player> users = playerRepository.findAll();

        assertThat(users.size()).isEqualTo(9);
    }

}
