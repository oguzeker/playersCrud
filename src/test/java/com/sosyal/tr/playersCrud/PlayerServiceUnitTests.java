//package com.sosyal.tr.playersCrud;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.sosyal.tr.playersCrud.entity.Team;
//import com.sosyal.tr.playersCrud.repository.impl.PlayerRepositoryImpl;
//import com.sosyal.tr.playersCrud.service.PlayerService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PlayerServiceUnitTests {
//
//	@Autowired
//    private PlayerService playerService;
//	
////	@MockBean
////    private PlayerRepository playerRepository;
//	
//	@MockBean
//	private PlayerRepositoryImpl playerRepositoryImpl;
//	
//	@Test
//    public void testGetTeamsOfPlayer() throws Exception {
//  
//        List<Team> optList = new ArrayList<Team>();
//        when(playerRepositoryImpl.getTeamsOfPlayer(1l)).thenReturn(optList);
//
//        assertEquals(playerService.getTeamsOfPlayer(1l).size(), 0);
//    }
//
//}
