package SlimeBar;

import java.util.LinkedList;
import java.util.List;

public class Fila
{
	private List<Cliente> filaClientes = new LinkedList<Cliente>();
	private int cadeira = 0;

	  public void insereCliente(Cliente cliente) {
		  this.filaClientes.add(cliente);
	  }
	
	  public Cliente removeCliente() {
		  return this.filaClientes.remove(0);
	  }
	  
	  public String infoClientes() {
		  return filaClientes.toString();
	  }
	  
	  public boolean vazia() {
		  return this.filaClientes.size() == 0;
	  }
	  
	  public int getIndex(Cliente cliente) {
		  return filaClientes.indexOf(cliente);
	  }
	  
	  public boolean inQueue(Cliente cliente) {
		  return filaClientes.contains(cliente);
	  }
		
	  public void setCadeira(int i) {
		  this.cadeira = i;
	  }

	  public int getCadeira() {
		  return this.cadeira;
	  }
}
