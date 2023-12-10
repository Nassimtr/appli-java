package graphique;
import kernel.Club;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClubTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private final List<Club> clubs;
    private final String[] columnNames = {"ID", "Nom", "Adresse", "Contact", "Téléphone", "Email", "Site Web"};

    public ClubTableModel(List<Club> clubs) {
        this.clubs = clubs;
    }

    @Override
    public int getRowCount() {
        return clubs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Club club = clubs.get(rowIndex);
        switch (columnIndex) {
            case 0: return club.getId();
            case 1: return club.getNom();
            case 2: return club.getAdresse();
            case 3: return club.getContact();
            case 4: return club.getTel();
            case 5: return club.getMail();
            case 6: return club.getSite();
            default: return null;
        }
    }
}

