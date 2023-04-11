package ra.controller.oder;

import ra.model.entity.Oder;
import ra.model.service.oder.IOderService;
import ra.model.service.oder.OderServiceIMPL;

import java.util.List;

public class ControllerOder {
    private static IOderService iOderService = new OderServiceIMPL();
    public Oder findById(int id){
        return iOderService.findById(id);
    }
    public void save(Oder oder){
        iOderService.save(oder);
    }
    public void deleteOder(int id){
        iOderService.delete(id);
    }
    public void saveOderHistory(Oder oder){
        iOderService.saveHistory(oder);
    }
    public Oder showOderHistory(int id){
        return iOderService.findOderHistory(id);
    }
    public List<Oder> showAllOderHistory(){
        return iOderService.showAllOderHistory();
    }
}
