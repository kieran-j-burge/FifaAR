package FifaAR.Api;

import FifaAR.DTO.Player;
import FifaAR.Service.DataService;
import FifaAR.Tools.HttpTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Api {

    private HttpTools httpTools;
    private DataService dataService;

    @Autowired
    public Api(HttpTools httpTools, DataService dataService) {
        this.httpTools = httpTools;
        this.dataService = dataService;
    }




//    @RequestMapping(value = "/store-players", method = RequestMethod.GET)
//    public String testRoute(){
//        httpTools.getPlayers();
//        return "webpage/home";
//    }


    @RequestMapping(value = "/player/find-player/{player}", method = RequestMethod.GET)
    public String returnPlayerInfo(Model model, HttpSession session,
                                   @PathVariable("player") String player){
        List<Player> playerList = dataService.getPlayer(player);

        for (Player p : playerList){
            System.out.println(p.getName());
        }

        model.addAttribute("player_list",playerList);

       return "fragments::player-list";
   }

}
