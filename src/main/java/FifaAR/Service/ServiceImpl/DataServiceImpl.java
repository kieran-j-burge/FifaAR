package FifaAR.Service.ServiceImpl;

import FifaAR.DAO.DataDAO;
import FifaAR.DTO.Player;
import FifaAR.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService{

    private DataDAO dataDAO;

    @Autowired
    public DataServiceImpl(DataDAO dataDAO) {
        this.dataDAO = dataDAO;
    }

    @Override
    public void storePlayer(Integer id, String cardName, String longName, String rating) {
        dataDAO.storePlayer(id,cardName,longName,rating);
    }

    @Override
    public Integer getLastPlayerCount() {
        return dataDAO.getLastPlayerCount();
    }

    @Override
    public List<Player> getPlayer(String player) {
        return dataDAO.getPlayer(player);
    }
}
