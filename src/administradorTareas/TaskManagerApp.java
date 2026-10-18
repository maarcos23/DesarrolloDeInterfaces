package administradorTareas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TaskManagerApp extends JFrame{

	private DefaultListModel<Tarea> modLista;
	private JList<Tarea> lisTarea;
	
	private JTextField nomTarea;
	private JComboBox<String> cb;
	private JButton agregar;
	private JButton eliminar;
	
	public TaskManagerApp() {
		// TODO Auto-generated constructor stub
		
		setTitle("Administador de tareas");
		setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
	
	
		modLista = new DefaultListModel<>();
		lisTarea = new JList<>(modLista);
	
		//conexion de la lista con la clase que esta entre parentesis
		lisTarea.setCellRenderer(new TaskCellTRenderer());
		
		JScrollPane sp = new JScrollPane(lisTarea);
		add(sp, BorderLayout.CENTER);
		
		JPanel pnlSuperior = new JPanel();
		pnlSuperior.setLayout(new FlowLayout());
		
		nomTarea = new JTextField(15);
		
		String[] prioridad = {"Alta", "Media", "Baja"};
		cb = new JComboBox<>(prioridad);
	
		agregar = new JButton("agregar tarea");
		agregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String texto = nomTarea.getText();
		        String prioridad = (String) cb.getSelectedItem();
		        Tarea tareaA = new Tarea(texto, prioridad);
				modLista.addElement(tareaA);
				nomTarea.setText("");
			}
		});
		
		pnlSuperior.add(new JLabel("Nueva tarea"));
		pnlSuperior.add(nomTarea);
		pnlSuperior.add(new JLabel("Prioridad"));
		pnlSuperior.add(cb);
		pnlSuperior.add(agregar);
		
		add(pnlSuperior, BorderLayout.NORTH);
		
		JPanel pnlInferior = new JPanel();
        eliminar = new JButton("Eliminar Tarea");
        eliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int indice = lisTarea.getSelectedIndex();
				if(indice > -1) {
					modLista.remove(indice);
				}else {
					System.out.println("No hay nada selecionado");
				}
			}
		});
        
        pnlInferior.add(eliminar);
        
        add(pnlInferior, BorderLayout.SOUTH);
        
        lisTarea.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int indice = lisTarea.locationToIndex(e.getPoint());
                if (indice > -1) {
                    Tarea tareaClicada = modLista.getElementAt(indice);
                    tareaClicada.setEstado(!tareaClicada.isEstado());
                    lisTarea.repaint();
                }
            }
        });
        
        // Hacemos visible la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        new TaskManagerApp();
    }
}
