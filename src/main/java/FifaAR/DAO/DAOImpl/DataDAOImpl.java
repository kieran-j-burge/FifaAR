package FifaAR.DAO.DAOImpl;

import FifaAR.DAO.DataDAO;
import FifaAR.DTO.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataDAOImpl implements DataDAO{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DataDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void storePlayer(Integer id, String cardName, String longName, String rating) {
        jdbcTemplate.update("INSERT INTO players (id,c_name,l_name,rating) VALUES (?,?,?,?)", id, cardName, longName,rating);
    }

    @Override
    public Integer getLastPlayerCount() {
        return jdbcTemplate.queryForObject("SELECT id FROM players ORDER BY id DESC LIMIT 1",Integer.class);
    }

    @Override
    public List<Player> getPlayer(String player) {
        player = "%"+player+"%";
        return jdbcTemplate.query("SELECT * FROM players WHERE c_name LIKE ?;",
                new Object[]{player},
                (rs, rowNum) -> new Player(
                        rs.getInt("id"),
                        rs.getString("c_name")
                ));

    }
}
