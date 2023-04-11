package ra.model.service.oder;

import ra.model.entity.Oder;
import ra.model.service.IGenericService;

import java.util.List;

public interface IOderService extends IGenericService<Oder> {
    void saveHistory(Oder oder);
    Oder findOderHistory(int id);
    List<Oder> showAllOderHistory();
}
