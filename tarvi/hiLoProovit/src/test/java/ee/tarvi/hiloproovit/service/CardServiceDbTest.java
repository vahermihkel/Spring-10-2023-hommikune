package ee.tarvi.hiloproovit.service;

import ee.tarvi.hiloproovit.entity.Player;
import ee.tarvi.hiloproovit.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class CardServiceDbTest {

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    CardService cardService;

    @BeforeEach
    void setUp() {
    }
    @Test
    void savesPlayerToDb() {
        cardService.getPlayer("Pets");

        when(playerRepository.findById("Pets")).thenReturn(Optional.of(new Player("Pets", new Date(), 3)));
        Optional<Player> player = playerRepository.findById("Pets");

        assertTrue(player.isPresent());
    }

    @Test
    void getAllReturnsThreePlayers() {
        when(playerRepository.findAll()).thenReturn(Arrays.asList(new Player(), new Player(), new Player()));

        List<Player> players = cardService.getAll();

        assertEquals(3, players.size());
    }

}
