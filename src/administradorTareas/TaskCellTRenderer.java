package administradorTareas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class TaskCellTRenderer extends JPanel implements ListCellRenderer<Tarea>{

	private JLabel lblIcono;//El icono es para la prioridad
	private JLabel lblTexto;
	
	public TaskCellTRenderer() {

		setLayout(new BorderLayout(10, 10));
		
	lblIcono = 	new JLabel();
	lblIcono.setOpaque(true);
	lblTexto = new JLabel();
	
		add(lblIcono, BorderLayout.WEST);
		add(lblTexto, BorderLayout.CENTER);
		
		setOpaque(true);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Tarea> list, Tarea value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		
		lblTexto.setText(value.getNombre());
		
		if(value.getPrioridad().equals("Alta")) {
			lblIcono.setBackground(Color.red);
			lblIcono.setText("  ");
			}else if(value.getPrioridad().equals("Media")) {
				lblIcono.setBackground(Color.YELLOW);
				lblIcono.setText("  ");
			}else if(value.getPrioridad().equals("Baja")) {
				lblIcono.setBackground(Color.GREEN);
				lblIcono.setText("  ");
			}
		
		if(isSelected) {
			setBackground(list.getSelectionBackground());
			lblTexto.setForeground(list.getSelectionForeground());
			}else {
				setBackground(list.getBackground());
			
			if(value.isEstado()) {
				setBackground(new Color(230, 230, 230));
				lblTexto.setForeground(Color.GRAY);
			}else {
				setBackground(list.getBackground());
				lblTexto.setForeground(Color.BLACK);
			}
		}
		
		
		return this;
	}

}
