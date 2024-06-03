package Services;

import Interfaces.inscriptionInterface;
import Models.Inscription;

import java.sql.SQLException;
import java.util.List;

public class ServiceInscription implements inscriptionInterface<Inscription> {
    @Override
    public void addInscription(Inscription inscription) {

    }

    @Override
    public List<Inscription> getAllInscriptions() {
        return null;
    }

    @Override
    public void update(Inscription inscription) throws SQLException {

    }

    @Override
    public void delete(Inscription inscription) {

    }
}
