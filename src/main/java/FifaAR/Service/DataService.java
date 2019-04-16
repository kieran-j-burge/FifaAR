package FifaAR.Service;

import FifaAR.DTO.Player;

import java.util.List;

public interface DataService {
    void storePlayer(Integer id, String cardName, String longName, String rating);
    Integer getLastPlayerCount();
    List<Player> getPlayer(String player);
}
