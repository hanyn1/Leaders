package Services;

import Models.Formation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceFormation {
    private final List<Formation> formations = new ArrayList<>();

    public List<Formation> getAllFormations() {
        return new ArrayList<>(formations);
    }

    public Optional<Formation> getFormationById(int id) {
        return formations.stream().filter(f -> f.getId() == id).findFirst();
    }

    public void addFormation(Formation formation) {
        formations.add(formation);
    }

    public boolean updateFormation(int id, Formation updatedFormation) {
        Optional<Formation> existingFormationOpt = getFormationById(id);
        if (existingFormationOpt.isPresent()) {
            Formation existingFormation = existingFormationOpt.get();
            existingFormation.setTitre(updatedFormation.getTitre());
            existingFormation.setDescription(updatedFormation.getDescription());
            return true;
        }
        return false;
    }

    public boolean deleteFormation(int id) {
        return formations.removeIf(f -> f.getId() == id);
    }
}
