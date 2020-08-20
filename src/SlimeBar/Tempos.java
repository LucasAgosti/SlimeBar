package SlimeBar;

public class Tempos
{
	private long tempoBar;
	private long tempoCasa;
	
	public Tempos(long tempoBar, long tempoCasa)
	{
		this.tempoBar = tempoBar * 1000;
		this.tempoCasa = tempoCasa * 1000;
	}
	
	public void setTempoBar(long tempo) {
		this.tempoBar = tempo;
	}

	public long getTempoBar() {
		return this.tempoBar;
	}
	
	public void setTempoCasa(long tempo) {
		this.tempoCasa = tempo;
	}

	public long getTempoCasa() {
		return this.tempoCasa;
	}
}
