package Services;

import Models.Formation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class ServiceFormation {
    private final ObservableList<Formation> formations = FXCollections.observableArrayList();

    public ServiceFormation() {
        // Initialize with some sample data or load from database
        formations.add(new Formation(1, "Formation Java", "Description de la formation Java"));
        formations.add(new Formation(2, "Formation Python", "Description de la formation Python"));
    }

    public ObservableList<Formation> getAllFormations() {
        return formations;
    }

    public Optional<Formation> getFormationById(int id) {
        return formations.stream()
                .filter(formation -> formation.getId() == id)
                .findFirst();
    }

    public void addFormation(Formation formation) {
        formations.add(formation);
    }

    public boolean updateFormation(int id, Formation updatedFormation) {
        Optional<Formation> existingFormation = getFormationById(id);
        if (existingFormation.isPresent()) {
            existingFormation.get().setTitre(updatedFormation.getTitre());
            existingFormation.get().setDescription(updatedFormation.getDescription());
            return true;
        }
        return false;
    }

    public boolean deleteFormation(int id) {
        Optional<Formation> formationToDelete = getFormationById(id);
        return formationToDelete.map(formations::remove).orElse(false);
    }
}
