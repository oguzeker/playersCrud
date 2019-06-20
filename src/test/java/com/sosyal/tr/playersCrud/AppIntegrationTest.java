package com.sosyal.tr.playersCrud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.sosyal.tr.playersCrud.entity.Contract;
import com.sosyal.tr.playersCrud.entity.Player;
import com.sosyal.tr.playersCrud.entity.Team;
import com.sosyal.tr.playersCrud.service.ContractService;
import com.sosyal.tr.playersCrud.service.PlayerService;
import com.sosyal.tr.playersCrud.service.TeamService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=RANDOM_PORT, classes = PlayersCrudApplication.class)
@ActiveProfiles("test")
public class AppIntegrationTest {

    private static final String TEAM = "/team";
    private static final String PLAYER = "/player";
    private static final String CONTRACT = "/contract";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(PlayersCrudAppConfig.dateFormat);  

	private static final String HTTP_LOCALHOST = "http://localhost:";

	private final ObjectMapper mapper = new ObjectMapper();

	@LocalServerPort
	private int port;
	
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
	private PlayerService playerService;
	@Autowired
	private TeamService teamService;
	@Autowired
    private ContractService contractService;
	
	@Before
    public void testCreateContract() throws Exception {
		Optional<Player> player = playerService.getPlayerById(1l);
		Optional<Team> team = teamService.getTeamById(1l);
		
		Contract contract = new Contract();
		contract.setPlayer(player.get());
		contract.setTeam(team.get());
		contract.setSeason(2019l);
		contract = contractService.addOrUpdateContract(contract);

		assertEquals(contract.getPlayer().getPlayerId(), player.get().getPlayerId());
		assertEquals(contract.getTeam().getTeamId(), team.get().getTeamId());
		
		// The following values change by current time of testing.
		// So we need to compare.
		assertTrue(contract.getTransferPrice().longValue() > 339393);
		assertTrue(contract.getTeamCommission().longValue() > 33939);
		assertTrue(contract.getTotalAmount().longValue() > 373332);
    }
	
	@Test
	public void testGetAllContracts() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(createMainURL(CONTRACT), String.class);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		List<Contract> contracts = mapper.readValue(response.getBody(), new TypeReference<List<Contract>>(){});
		assertEquals(contracts.size(), 1);
	}

    @Test
    public void testGetContractById() throws Exception {
    	ResponseEntity<String> response = restTemplate.getForEntity(createMainURL(CONTRACT) + "/1", String.class);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		Contract contract = mapper.readValue(response.getBody(), new TypeReference<Contract>(){});
		
		assertTrue(contract.getTransferPrice().longValue() > 339393);
		assertTrue(contract.getTeamCommission().longValue() > 33939);
		assertTrue(contract.getTotalAmount().longValue() > 373332);
        assertEquals(contract.getPlayer().getPlayerId().longValue(), 1l);
        assertEquals(contract.getTeam().getTeamId().longValue(), 1l);
    }
    
    @Test
    public void testPlayersOfTeam_WithId_200() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(createMainURL(TEAM) + "/getPlayers?teamId=1", String.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());

        List<Player> players = mapper.readValue(response.getBody(), new TypeReference<List<Player>>(){});
        assertEquals(players.size(), 1);
    }
    
    @Test
    public void testPlayersOfTeam_WithIdAndSeason_200() throws Exception {
    	ResponseEntity<String> response = restTemplate.getForEntity(createMainURL(TEAM) + "/getPlayers?teamId=1&season=2019", String.class);
    	assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    	
    	List<Player> players = mapper.readValue(response.getBody(), new TypeReference<List<Player>>(){});
    	assertEquals(players.size(), 1);
    }
    
    @Test
    public void testPlayersOfTeam_WithIdAndSeason_404() throws Exception {
    	ResponseEntity<String> response = restTemplate.getForEntity(createMainURL(TEAM) + "/getPlayers?teamId=1&season=1900", String.class);
    	assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
    	
    	assertTrue(mapper.writeValueAsString(response).contains("PLAYERS OF TEAM not found with id : '1'"));
    }
    
    @Test
    public void testTeamsOfPlayer_200() throws Exception {
    	ResponseEntity<String> response = restTemplate.getForEntity(createMainURL(PLAYER) + "/getTeams?playerId=1", String.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());

        List<Team> teams = mapper.readValue(response.getBody(), new TypeReference<List<Team>>(){});
        assertEquals(teams.size(), 1);
    }
    
    @Test(expected = MismatchedInputException.class)
    public void testTeamsOfPlayer_404() throws Exception {
    	ResponseEntity<String> response = restTemplate.getForEntity(createMainURL(PLAYER) + "/getTeams?playerId=2", String.class);
    	assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
    	
    	mapper.readValue(response.getBody(), new TypeReference<List<Team>>(){});
    }
    
    @Test
    public void testGetAllTeams() throws Exception {
		List<Team> allTeams = teamService.getAllTeams();
		
		assertEquals(allTeams.size(), 3);
		
		assertEquals(allTeams.get(0).getTeamId().longValue(), 1l);
        assertEquals(allTeams.get(0).getCurrency(), "TRY");
        assertEquals(allTeams.get(0).getName(), "Galatasaray");
        
        assertEquals(allTeams.get(1).getTeamId().longValue(), 2l);
        assertEquals(allTeams.get(1).getCurrency(), "EUR");
        assertEquals(allTeams.get(1).getName(), "Besiktas");
        
        assertEquals(allTeams.get(2).getTeamId().longValue(), 3l);
        assertEquals(allTeams.get(2).getCurrency(), "USD");
        assertEquals(allTeams.get(2).getName(), "Fenerbahce");
    }
	
	@Test
    public void testPlayersOfTeam_WithId_Success() throws Exception {
		List<Player> players = teamService.getPlayersOfTeam(1l, null);
        assertEquals(players.size(), 1);
        
        assertEquals(players.get(0).getPlayerId().longValue(), 1l);
        assertEquals(players.get(0).getName(), "Santorini");
        assertEquals(DATE_FORMAT.format(players.get(0).getBirthdate()), "1986-03-30");
        assertEquals(DATE_FORMAT.format(players.get(0).getCareerStartDate()), "2010-01-20");
    }
    
    @Test
    public void testPlayersOfTeam_WithIdAndSeason_Empty() throws Exception {
    	List<Player> players = teamService.getPlayersOfTeam(1l, 1900l);
        assertTrue(CollectionUtils.isEmpty(players));
    }
    
    @Test
    public void testGetAllPlayers() throws Exception {
        List<Player> allPlayers = playerService.getAllPlayers();
        assertEquals(allPlayers.size(), 9);

        assertEquals(DATE_FORMAT.format(allPlayers.get(0).getBirthdate()), "1986-03-30");
        assertEquals(DATE_FORMAT.format(allPlayers.get(0).getCareerStartDate()), "2010-01-20");
    }
    
    private String createMainURL(String uri) {
        return HTTP_LOCALHOST + port + uri;
    }

}
