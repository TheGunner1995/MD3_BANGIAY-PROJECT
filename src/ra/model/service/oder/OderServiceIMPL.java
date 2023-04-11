package ra.model.service.oder;

import ra.config.Config;
import ra.model.entity.Cart;
import ra.model.entity.Oder;
import ra.model.entity.User;
import ra.model.service.cart.CartServiceIMPL;
import ra.model.service.user.UserService;

import java.util.List;

public class OderServiceIMPL implements IOderService {
    private static List<Oder> oderList = new Config<Oder>().readFromFile(Config.PATH_ODER);
    private static List<Oder> oderHistory = new Config<Oder>().readFromFile(Config.PATH_ODER_HISTORY);
    User userlogin = new UserService().getCurrentLogin();
    Cart cartOder = new CartServiceIMPL().findById(userlogin.getUserId());
    @Override
    public List<Oder> findAll() {
        return oderList;
    }

    @Override
    public void save(Oder oder) {
        oderList.add(oder);
        new Config<Oder>().writeToFile(Config.PATH_ODER, oderList);

    }

    @Override
    public Oder findById(int id) {
        for (Oder oder : oderList) {
           if (oder.getOrderUser().getUserId()==id){
               return oder;
           }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        for (Oder oder : oderList) {
         if ( oder.getOrderUser().getUserId()==id){
             oderList.remove(oder);
             break;
         }
        }
        new Config<Oder>().writeToFile(Config.PATH_ODER, oderList);
    }

    @Override
    public List<Oder> searchByName(String searchName){
        return null;
    }
    public  void saveHistory(Oder oder){
        oderHistory.add(oder);
        new Config<Oder>().writeToFile(Config.PATH_ODER_HISTORY, oderHistory);
    }
    public Oder findOderHistory(int id){
        List<Oder> oders = new Config<Oder>().readFromFile(Config.PATH_ODER_HISTORY);
        for (Oder inv: oders) {
            if (inv.getOrderUser().getUserId()==id){
               return inv;
            }
        }
        return null;
    }

    @Override
    public List<Oder> showAllOderHistory() {
        List<Oder> oders = new Config<Oder>().readFromFile(Config.PATH_ODER_HISTORY);
        return oders;
    }

}
