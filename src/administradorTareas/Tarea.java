package administradorTareas;

public class Tarea {

	private String nombre;
	private String prioridad;
	private boolean estado;
	
	public Tarea() {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.prioridad = prioridad;
		this.estado = false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}	
}
