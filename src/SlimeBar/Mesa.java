package SlimeBar;

public class Mesa
{
	private boolean mesaLotada;
	private int quantidadeCadeiras;
	private int cadeirasLivres;
	
	public Mesa(int quantidade){
		this.mesaLotada = false;

		this.quantidadeCadeiras = quantidade;
		this.cadeirasLivres     = quantidade;
	}
	
	public void setMesaLotada(boolean mesaLotada) {
		this.mesaLotada = mesaLotada;
	}
	
	public boolean getMesaLotada(){
		return this.mesaLotada;
	}
	
	public void setQuantidadeCadeiras(int quantidade) {
		this.quantidadeCadeiras = quantidade;
	}
	
	public int getQuantidadeCadeiras() {
		return this.quantidadeCadeiras;
	}

	public void setCadeirasLivres(int quantidade) {
		this.cadeirasLivres = quantidade;
	}
	
	public int getCadeirasLivres() {
		return this.cadeirasLivres;
	}
		
	public boolean inserirPessoa()
	{
		if(cadeirasLivres == 0) {
			return false;
		}
		
		this.cadeirasLivres--;
		if(cadeirasLivres == 0) {
			setMesaLotada(true);
		}
		return true;
	}
	
	public boolean retirarPessoa()
	{
		this.cadeirasLivres++;
		
		if(cadeirasLivres == quantidadeCadeiras) {
			setMesaLotada(false);
		}
		
		if(cadeirasLivres == quantidadeCadeiras) {
			return false;
		}
		
		return true;
	}
	
	public boolean mesaVazia() {
		return cadeirasLivres == quantidadeCadeiras ? true : false;
	}
}


