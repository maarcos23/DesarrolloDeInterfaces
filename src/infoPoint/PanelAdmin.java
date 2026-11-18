package infoPoint;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class PanelAdmin extends JPanel{
	
	List<Libro>libro;
	List<Aviso>aviso;
	
	public PanelAdmin(Libro libro, Aviso aviso) {
		this.libro= new ArrayList<Libro>();
		this.aviso= new ArrayList<Aviso>();
		
	}
	
}
