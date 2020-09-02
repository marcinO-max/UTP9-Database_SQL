package zad1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
	/**
	 * 
	 */
	TravelData td ;
	List<String> kolumny;
	
	public TableModel(TravelData td) {
		this.td = td;
		kolumny= new ArrayList<String>();
		kolumny.add("Kraj");
		kolumny.add("data w");
		kolumny.add("data p");
		kolumny.add("miejsce");
		kolumny.add("cena");
		kolumny.add("waluta");
	}
	

	public String getColumnName(int columns) {
		return kolumny.get(columns);
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return kolumny.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return td.ofertyList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

		switch(columnIndex) {
		case 0:
			return td.ofertyList.get(rowIndex).getKraj();

		case 1:
			return td.ofertyList.get(rowIndex).getDate_wyjazdu();
		case 2:
			return td.ofertyList.get(rowIndex).getDate_powrotu();
		case 3:
			return td.ofertyList.get(rowIndex).getMiejsce();
		case 4:
			return td.ofertyList.get(rowIndex).getCena();
		case 5:
			return td.ofertyList.get(rowIndex).getWalutaLocale();
		default:
				return null;
		}
	}


}
