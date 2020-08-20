package SlimeBar;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cliente extends Thread
{
	private int numCadeiras, iSprite;
	private Semaphore qnt_clientes, mutex_fila, mutex_bar;
	private Fila fila;
	private Mesa mesa;
	private Tempos tempos;
	
	int filaCoordenadas[] = {100, 170, 240, 310, 380, 450, 520, 590, 660, 730, 800, 870, 940, 1010, 1080, 1150};
	
	String nome = "";
	
	JFrame bar;
	JLabel fundo;
	JLabel sprite;
	
	int flag = 1;
	
	ImageIcon sp1; //andando
	ImageIcon sp2;
	ImageIcon sp3;
	ImageIcon sp4;
	ImageIcon sp5;
	ImageIcon sp6;
	ImageIcon sp7;
	ImageIcon sp8;
	ImageIcon sp9;
	ImageIcon sp10;
	ImageIcon sp11;
	ImageIcon sp12;
	ImageIcon bb1; //bebendo
	ImageIcon bb2;
	ImageIcon pt1; //portal
	ImageIcon pt2;
	
	JFrame casa;
	JLabel sala;
	
	public Cliente(JFrame bar, JLabel fundo, Semaphore qnt_clientes, Semaphore mutex_fila, Semaphore mutex_bar, Fila fila, Mesa mesa, String nome, int tempoBar, int tempoCasa)
	{
		Random gerador = new Random();
		iSprite = gerador.nextInt(3);
		
		setSprite(iSprite);
		
		sprite = new JLabel(sp2);
		
		pt1 = criarImageIcon("../imgs/andando_na_fila/efeito_portal_1.png", "Portal 1");
		pt2 = criarImageIcon("../imgs/andando_na_fila/efeito_portal_2.png", "Portal 2");
		
		sprite.setBounds(530, 500, 100, 100);
		
		this.numCadeiras = mesa.getQuantidadeCadeiras();
		this.fila = fila;
		this.mesa = mesa;
		this.nome = nome;
		this.bar = bar;
		
		bar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bar.remove(fundo);
		bar.add(sprite);
		
		this.fundo = fundo;
		
		SwingUtilities.updateComponentTreeUI(bar);
		bar.add(fundo);
		
		this.qnt_clientes = qnt_clientes;
		this.mutex_fila = mutex_fila;
		this.mutex_bar = mutex_bar;
		
		this.tempos = new Tempos(tempoBar, tempoCasa);
		this.casa = new JFrame("Casa");
		
		casa.setLayout(null);
		sala = new JLabel(criarImageIcon("../imgs/locais/casa.png", "Casa"));
		sala.setBounds(0, 0, 640, 480);
		casa.setSize(640, 480);
	}
	
	public void setSprite(int sprite)
	{
		switch(sprite)
		{
			case 0:
				sp1 = criarImageIcon("../imgs/andando_na_fila/red_1.png","Sprite 1");
				sp2 = criarImageIcon("../imgs/andando_na_fila/red_2.png","Sprite 2");
				sp3 = criarImageIcon("../imgs/andando_na_fila/red_3.png","Sprite 3");
				sp4 = criarImageIcon("../imgs/andando_na_fila/red_1_down.png","Sprite 4");
				sp5 = criarImageIcon("../imgs/andando_na_fila/red_2_down.png","Sprite 5");
				sp6 = criarImageIcon("../imgs/andando_na_fila/red_3_down.png","Sprite 5");
				sp7 = criarImageIcon("../imgs/andando_na_fila/red_1_dir.png","Sprite 1");
				sp8 = criarImageIcon("../imgs/andando_na_fila/red_2_dir.png","Sprite 2");
				sp9 = criarImageIcon("../imgs/andando_na_fila/red_3_dir.png","Sprite 3");
				sp10 = criarImageIcon("../imgs/andando_na_fila/red_1_esq.png","Sprite 4");
				sp11 = criarImageIcon("../imgs/andando_na_fila/red_2_esq.png","Sprite 5");
				sp12 = criarImageIcon("../imgs/andando_na_fila/red_3_esq.png","Sprite 5");
				break;
			case 1:
				sp1 = criarImageIcon("../imgs/andando_na_fila/green_1.png","Sprite 1");
				sp2 = criarImageIcon("../imgs/andando_na_fila/green_2.png","Sprite 2");
				sp3 = criarImageIcon("../imgs/andando_na_fila/green_3.png","Sprite 3");
				sp4 = criarImageIcon("../imgs/andando_na_fila/green_1_down.png","Sprite 4");
				sp5 = criarImageIcon("../imgs/andando_na_fila/green_2_down.png","Sprite 5");
				sp6 = criarImageIcon("../imgs/andando_na_fila/green_3_down.png","Sprite 5");
				sp7 = criarImageIcon("../imgs/andando_na_fila/green_1_dir.png","Sprite 1");
				sp8 = criarImageIcon("../imgs/andando_na_fila/green_2_dir.png","Sprite 2");
				sp9 = criarImageIcon("../imgs/andando_na_fila/green_3_dir.png","Sprite 3");
				sp10 = criarImageIcon("../imgs/andando_na_fila/green_1_esq.png","Sprite 4");
				sp11 = criarImageIcon("../imgs/andando_na_fila/green_2_esq.png","Sprite 5");
				sp12 = criarImageIcon("../imgs/andando_na_fila/green_3_esq.png","Sprite 5");
				break;
			case 2:
				sp1 = criarImageIcon("../imgs/andando_na_fila/yellow_1.png","Sprite 1");
				sp2 = criarImageIcon("../imgs/andando_na_fila/yellow_2.png","Sprite 2");
				sp3 = criarImageIcon("../imgs/andando_na_fila/yellow_3.png","Sprite 3");
				sp4 = criarImageIcon("../imgs/andando_na_fila/yellow_1_down.png","Sprite 4");
				sp5 = criarImageIcon("../imgs/andando_na_fila/yellow_2_down.png","Sprite 5");
				sp6 = criarImageIcon("../imgs/andando_na_fila/yellow_3_down.png","Sprite 5");
				sp7 = criarImageIcon("../imgs/andando_na_fila/yellow_1_dir.png","Sprite 1");
				sp8 = criarImageIcon("../imgs/andando_na_fila/yellow_2_dir.png","Sprite 2");
				sp9 = criarImageIcon("../imgs/andando_na_fila/yellow_3_dir.png","Sprite 3");
				sp10 = criarImageIcon("../imgs/andando_na_fila/yellow_1_esq.png","Sprite 4");
				sp11 = criarImageIcon("../imgs/andando_na_fila/yellow_2_esq.png","Sprite 5");
				sp12 = criarImageIcon("../imgs/andando_na_fila/yellow_3_esq.png","Sprite 5");
				break;
		}
	}
	
	public void clienteBebe()
	{
		long t1, t2;
		int i = 0;
		
		t1 = System.currentTimeMillis();
		t2 = System.currentTimeMillis();
		
		while(i < 2)
		{
			if(t2 - t1 > 500)
			{
				switch(flag)
				{
					case 5:
						sprite.setIcon(bb1);
						flag=6;
						break;
						
					case 6: 
						sprite.setIcon(bb2); 
						flag=5;
						break;
						
				}
				t1 = System.currentTimeMillis();
				i++;
			}
			t2 = System.currentTimeMillis();
		}
	}
	
	public void clienteCasa(boolean orientacao, int scale)
	{
		ImageIcon nextImg = null;
		
		long t1, t2;
		int i = 0;
		flag = 1;
		
		t1 = System.currentTimeMillis();
		t2 = System.currentTimeMillis();
		
		int x = (scale == 1 ? 5 : 0);
		int y = (scale == 1 ? 0 : 5);		

		while(i < 20)
		{
			if(t2 - t1 > 25)
			{
				if(orientacao)
				{
					sprite.setLocation(sprite.getX()+x, sprite.getY()-y);
					switch(flag)
					{
						case 1:			
							nextImg = (scale == 1 ? sp8 : sp2);
							sprite.setIcon(nextImg);
							flag++;
							break;
							
						case 2: 
							nextImg = (scale == 1 ? sp7 : sp1);
							sprite.setIcon(nextImg);
							flag++;
							break;
							
						case 3: 
							nextImg = (scale == 1 ? sp8 : sp2);
							sprite.setIcon(nextImg);
							flag++;
							break;
							
						case 4: 
							nextImg = (scale == 1 ? sp9 : sp3);
							sprite.setIcon(nextImg);
							flag=1;
							break;
							
					}
					t1 = System.currentTimeMillis();
					i++;
				} 
				
				else
				{
					sprite.setLocation(sprite.getX()-x, sprite.getY()+y);
					switch(flag)
					{
						case 1:
							nextImg = (scale == 1 ? sp11 : sp5);
							sprite.setIcon(nextImg);
							flag++;
							break;
							
						case 2:
							nextImg = (scale == 1 ? sp10 : sp4);
							sprite.setIcon(nextImg);
							flag++;
							break;
							
						case 3:
							nextImg = (scale == 1 ? sp11 : sp5);
							sprite.setIcon(nextImg);
							flag++;
							break;
							
						case 4:
							nextImg = (scale == 1 ? sp12 : sp6);
							sprite.setIcon(nextImg);
							flag = 1;
							break;
							
					}
					t1 = System.currentTimeMillis();
					i++;
				}
			}
			t2 = System.currentTimeMillis();
		}
		flag = 1;
	}
	
	public void entraPortal(int qnt_pisca)
	{
		long t1, t2;
		int troca_cor = 2;

		flag = 5;
		t1 = System.currentTimeMillis();
		t2 = System.currentTimeMillis();
		
		while(troca_cor > 0) {
			if(t2 - t1 > 400) {
				switch(flag) {
					case 5:
						sprite.setIcon(pt1);
						flag = 6;
						break;
						
					case 6:
						sprite.setIcon(pt2);
						flag = 5;
						break;
						
				}
				t1 = System.currentTimeMillis();
				troca_cor--;
			}
			t2 = System.currentTimeMillis();
		}
		
		qnt_pisca--;
		
		if(qnt_pisca > 0) {
			entraPortal(qnt_pisca);
		}
	}
	
	public void irParaCasa()
	{
		long t1 ,t2;
		int segundos = 1;
		
		boolean orientacaoX = true;
		boolean orientacaoY = true;
		
		bar.remove(sprite);
		SwingUtilities.updateComponentTreeUI(bar);
		sprite.setIcon(sp2);
		sprite.setBounds(250, 300, 100, 100);
		casa.add(sprite);
		casa.add(sala);
		casa.setVisible(true);
		
		t1 = System.currentTimeMillis();
		t2 = System.currentTimeMillis();
		
		while(!checaTempoCasa(t2 - t1))
		{
			clienteCasa(orientacaoX, 1);
			clienteCasa(orientacaoY, 2);
			
			System.out.println(nome + " passou " + segundos + " segundos em casa.");
			segundos++;
			
			orientacaoX = !orientacaoX;
			orientacaoY = !orientacaoY;
			
			t2 = System.currentTimeMillis();
		}
		
		entraPortal(3);
		
		System.out.println(nome + " cansou de ficar em casa, teleportando para o bar.");
		casa.setVisible(false);
		fila.insereCliente(this);
		bar.remove(fundo);
		sprite.setIcon(sp2);
		flag = 1;
		sprite.setBounds(530, 500, 100, 100);
		bar.add(sprite);
		SwingUtilities.updateComponentTreeUI(bar);
		bar.add(fundo);
	}
	
	public void run() //entra fila
	{
		long t1, t2;
		int index = 0;
		
		boolean filaLotada = true;
		
		while(true)
		{
			if(fila.inQueue(this))
			{
				t1 = System.currentTimeMillis();
				t2 = System.currentTimeMillis();
				
				try
				{
					mutex_fila.acquire();
					index = filaCoordenadas[fila.getIndex(this)];
					mutex_fila.release();
					
					if (index == 450)
					{
						if (filaLotada == true)
						{
							JOptionPane.showMessageDialog(bar, "Fila Lotada! Espere do lado de fora do bar");
							filaLotada = false;
						}
					}
					
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				while(sprite.getY() > index)
				{
					if(t2 - t1 > 250)
					{
						sprite.setLocation(sprite.getX(), sprite.getY() - 10);
						switch(flag) {
							case 1:
								sprite.setIcon(sp2);
								flag++;
								break;
								
							case 2:
								sprite.setIcon(sp1);
								flag++;
								break;
								
							case 3:
								sprite.setIcon(sp2);
								flag++;
								break;
								
							case 4: 
								sprite.setIcon(sp3);
								flag = 1;
								break;
								
						}
						t1 = System.currentTimeMillis();
					}
					t2 = System.currentTimeMillis();
				}
				sprite.setIcon(sp2);
				
				try
				{
					if (fila.getIndex(this) == 0 && sprite.getY() == 100)
					{
						mutex_bar.acquire();
						
						bar();
						filaLotada = true;	
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
		
	public void bar()
	{
		long t1, t2;
		int segundos = 1;
		
		try
		{
			this.qnt_clientes.acquire();
			
			//------------------- ANTIGO -------------------
			
//			while (mesa.getMesaLotada()) //antigo
//			{
//				mutex_bar.acquire();
//				System.out.println("Esperando esvaziar mesas.");
//			}
			
			//----------------------------------------------
			
			t1 = System.currentTimeMillis();
			t2 = System.currentTimeMillis();
			
			while(sprite.getY() > 15)
			{
				if(t2 - t1 > 250)
				{
					sprite.setLocation(sprite.getX(), sprite.getY() - 10);	
					switch(flag)
					{
						case 1: 
							sprite.setIcon(sp2);
							flag++;
							break;
							
						case 2: 
							sprite.setIcon(sp1);
							flag++;
							break;
							
						case 3: 
							sprite.setIcon(sp2);
							flag++;
							break;
							
						case 4: 
							sprite.setIcon(sp3);
							flag = 1;
							break;
							
					}
					t1 = System.currentTimeMillis();
				}
				t2 = System.currentTimeMillis();
			}
			
			sprite.setIcon(sp2);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		mutex_bar.release();
		
		entraPortal(1);
			
		fila.removeCliente();
			
		System.out.println(nome + " teleportando para cadeira");
		irParaCadeira(fila.getCadeira(), numCadeiras);

		mesa.inserirPessoa();
		clienteBebe();

		t1 = System.currentTimeMillis();
		t2 = System.currentTimeMillis();
		
		while(!checaTempoBar(t2 - t1))
		{
			clienteBebe();
			System.out.println(nome + " esta há " + segundos + " segundos bebendo.");
			t2 = System.currentTimeMillis();
			segundos++;
		}
		
		System.out.println(nome + " estorou seu tempo no bar, teleportando para casa.");
		
		entraPortal(3);
		
		mesa.retirarPessoa();
		mutex_bar.release();
		
		//-------------------- NOVO --------------------
		
		if (mesa.mesaVazia()) {
			this.qnt_clientes.release(numCadeiras);
		}
		
		//----------------------------------------------
		
		//------------------- ANTIGO -------------------
		
//		this.qnt_clientes.release(); //antigo

		//----------------------------------------------
		
		irParaCasa();
	}

	private boolean checaTempoBar(long tempo) {
		return tempo >= tempos.getTempoBar();
	}

	private boolean checaTempoCasa(long tempo) {
		return tempo >= tempos.getTempoCasa();
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void irParaCadeira(int i, int qntCadeiras)
	{
        switch(qntCadeiras)
        {
        	case 1:
        		switch(i)
        		{
        			case 0:
		            	sprite.setLocation(65, 260);
		            	if(iSprite == 0) {
		            		bb1 = criarImageIcon("../imgs/bebendo/red/red_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/red/red_direita_2.png","Sprite 5");}
		            	if(iSprite == 1) {	
		            		bb1 = criarImageIcon("../imgs/bebendo/green/green_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/green/green_direita_2.png","Sprite 5");}
		            	if(iSprite == 2) {	
		            		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_2.png","Sprite 5");}
        			
		            	fila.setCadeira(0);
		            	break;
		            	
        		}
        		break;
            
    		case 2:
    			switch(i)
    			{
	            	case 0:
		            	sprite.setLocation(65, 260);
		            	if(iSprite == 0) {
		            		bb1 = criarImageIcon("../imgs/bebendo/red/red_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/red/red_direita_2.png","Sprite 5");}
		            	if(iSprite == 1) {	
		            		bb1 = criarImageIcon("../imgs/bebendo/green/green_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/green/green_direita_2.png","Sprite 5");}
		            	if(iSprite == 2) {	
		            		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_2.png","Sprite 5");}
		            	
		            	fila.setCadeira(1);
		            	break;
		            	
	            	case 1:
		            	sprite.setLocation(210, 260);
		            	if(iSprite == 0) {
		            		bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
		            	if(iSprite == 1) {	
		            		bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
		            	if(iSprite == 2) {	
		            		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
		            	
		            	fila.setCadeira(0);
		            	break;
				
    			}
    			break;
        
        	case 3:
                switch(i)
                {
	                case 0:
	                	sprite.setLocation(65, 260);
	                	if(iSprite == 0) {
	                		bb1 = criarImageIcon("../imgs/bebendo/red/red_direita_1.png","Sprite 4");
	    					bb2 = criarImageIcon("../imgs/bebendo/red/red_direita_2.png","Sprite 5");}
	                	if(iSprite == 1) {	
	                		bb1 = criarImageIcon("../imgs/bebendo/green/green_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/green/green_direita_2.png","Sprite 5");}
	                	if(iSprite == 2) {	
	                		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_2.png","Sprite 5");}
	                	
	                	fila.setCadeira(1);
	                	break;
                
	                case 1:
	                	sprite.setLocation(210, 260);
	                	if(iSprite == 0) {	
	                		bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	    					bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                	if(iSprite == 1) {	
	                		bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	                		bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                	if(iSprite == 2) {	
	                		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	
	                	fila.setCadeira(2);
	                	break;
	                
	                case 2:
	                	sprite.setLocation(25, 80);
	                	if(iSprite == 0){	
	                		bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	    					bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                	if(iSprite == 1){	
	                		bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                	if(iSprite == 2){	
	                		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	
	                	fila.setCadeira(0);
	                	break;
                	
                }
            	break;
	            
	            case 4:
	                switch(i)
	                {
	                	case 0:
	                		sprite.setLocation(65, 260);
	                		if(iSprite == 0) {	
		                 		bb1 = criarImageIcon("../imgs/bebendo/red/red_direita_1.png","Sprite 4");
		     					bb2 = criarImageIcon("../imgs/bebendo/red/red_direita_2.png","Sprite 5");}
	                		if(iSprite == 1) {	
		                 		bb1 = criarImageIcon("../imgs/bebendo/green/green_direita_1.png","Sprite 4");
		 						bb2 = criarImageIcon("../imgs/bebendo/green/green_direita_2.png","Sprite 5");}
	                		if(iSprite == 2) {	
		                 		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_1.png","Sprite 4");
		                 		bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_2.png","Sprite 5");}
	                		
	                		fila.setCadeira(1);
	                		break;
                 
	                 case 1:
	                	 sprite.setLocation(210, 260);
	                 	 if(iSprite == 0) {
	                 		 bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	     					 bb2 = criarImageIcon("../imgs/bebendo/red/red_direita_2.png","Sprite 5");}
	                 	 if(iSprite == 1) {
	                 		 bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	                 		 bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                 	 if(iSprite == 2) {	
	                 		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	 						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                 	 fila.setCadeira(2);
	                	 break;
	                 
	                 case 2:
	                	 sprite.setLocation(25, 80);
	                	 if(iSprite == 0) {	
	                		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	      					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                	 if(iSprite == 1) {	
	                  		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}	
	                	 if(iSprite == 2) {		
	                  		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	                  		 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                	 fila.setCadeira(3);
	                	 break;
	                 
	                 case 3:
	                	 sprite.setLocation(90, 80);
	                  	 if(iSprite == 0) {	
	                  		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	      					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 1) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 2) {	
	                  		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                  	 fila.setCadeira(0);
	                	 break;
	                
                }
                break;
            
            case 5:
                switch(i)
                {
                	case 0:
                		sprite.setLocation(65, 260);
		             	if(iSprite == 0) {	
		             		bb1 = criarImageIcon("../imgs/bebendo/red/red_direita_1.png","Sprite 4");
		 					bb2 = criarImageIcon("../imgs/bebendo/red/red_direita_2.png","Sprite 5");}
		             	if(iSprite == 1) {	
		             		bb1 = criarImageIcon("../imgs/bebendo/green/green_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/green/green_direita_2.png","Sprite 5");}
		             	if(iSprite == 2) {
		             		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_2.png","Sprite 5");}
		             	
		             	fila.setCadeira(1);
		             	break;
                 
	                 case 1:
	                	 sprite.setLocation(210, 260);
	                  	if(iSprite == 0) {	
	                  		bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	      					bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                  	if(iSprite == 1) {
	                  		bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	  						bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                  	if(iSprite == 2) {
	                  		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	  						bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                  	fila.setCadeira(2);
	                  	break;
	                
	                 case 2:
	                	 sprite.setLocation(25, 80);
                   		 if(iSprite == 0) {	
                   			 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	       					 bb2 = criarImageIcon("../imgs/bebendo/red/red_baixo_direita_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {	
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {	
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(3);
	                	 break;
	                 
	                 case 3:
	                	 sprite.setLocation(90, 80);
	                 	 if(iSprite == 0) {	
	                 		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	     					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                 	 if(iSprite == 1) {
	                 		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	 						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                 	 if(iSprite == 2) {
	                 		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	 						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                 	 fila.setCadeira(4);
	                	 break;
	                 
	                 case 4:
	                	 sprite.setLocation(155, 80);
	                 	 if(iSprite == 0) {
	                 		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	     					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                 	 if(iSprite == 1) {
	                 		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	 						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                 	 if(iSprite == 2) {	
	                 		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	 						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                 	 fila.setCadeira(0);
	                	 break;
	                	 
                }
	            break;
            
            case 6:
                switch(i)
                {
                	case 0:
                		sprite.setLocation(65, 260);
		             	if(iSprite == 0) {
		             		bb1 = criarImageIcon("../imgs/bebendo/red/red_direita_1.png","Sprite 4");
		 					bb2 = criarImageIcon("../imgs/bebendo/red/red_direita_2.png","Sprite 5");}
		             	if(iSprite == 1) {
		             		bb1 = criarImageIcon("../imgs/bebendo/green/green_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/green/green_direita_2.png","Sprite 5");}
		             	if(iSprite == 2) {
		             		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_2.png","Sprite 5");}
		            	 
		             	fila.setCadeira(1);
		            	break;
                 
	                 case 1:
	                	 sprite.setLocation(210, 260);
                   		 if(iSprite == 0){ 
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 2 ) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(2);
	                	 break;
	                
	                 case 2:
	                	 sprite.setLocation(25, 80);
                    	 if(iSprite == 0) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	        				 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
                    	 if(iSprite == 1) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
                    		 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
                    	 if(iSprite == 2) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
    						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
                    	 fila.setCadeira(3);
                    	 break;
	                 
	                 case 3:
	                	 sprite.setLocation(90, 80);
	                  	 if(iSprite == 0) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	      					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 1) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 2) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                  	 fila.setCadeira(4);
	                	 break;
	                 
	                 case 4:
	                	 sprite.setLocation(155, 80);
	                  	 if(iSprite == 0) {	
	                  		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	      					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 1) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	                  		 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 2) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                  	 fila.setCadeira(5);
	                	 break;
	                 
	                 case 5:
	                	 sprite.setLocation(220, 80);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(0);
	                	 break;
	                	 
                }
	            break;
	            
            case 7:
                switch(i)
                {
                	case 0:
                		sprite.setLocation(65, 260);
		             	if(iSprite == 0) {
		             		bb1 = criarImageIcon("../imgs/bebendo/red/red_direita_1.png","Sprite 4");
		 					bb2 = criarImageIcon("../imgs/bebendo/red/red_direita_2.png","Sprite 5");}
		             	if(iSprite == 1) {
		             		bb1 = criarImageIcon("../imgs/bebendo/green/green_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/green/green_direita_2.png","Sprite 5");}
		             	if(iSprite == 2) {
		             		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_2.png","Sprite 5");}
		            	 
		             	fila.setCadeira(1);
		            	break;
                 
	                 case 1:
	                	 sprite.setLocation(210, 260);
                   		 if(iSprite == 0){ 
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 2 ) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(2);
	                	 break;
	                
	                 case 2:
	                	 sprite.setLocation(25, 80);
                    	 if(iSprite == 0) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	        				 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
                    	 if(iSprite == 1) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
                    		 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
                    	 if(iSprite == 2) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
    						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
                    	 fila.setCadeira(3);
                    	 break;
	                 
	                 case 3:
	                	 sprite.setLocation(90, 80);
	                  	 if(iSprite == 0) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	      					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 1) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 2) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                  	 fila.setCadeira(4);
	                	 break;
	                 
	                 case 4:
	                	 sprite.setLocation(155, 80);
	                  	 if(iSprite == 0) {	
	                  		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	      					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 1) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	                  		 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 2) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                  	 fila.setCadeira(5);
	                	 break;
	                 
	                 case 5:
	                	 sprite.setLocation(220, 80);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(6);
	                	 break;
	                	 
	                 case 6:
	                	 sprite.setLocation(280, 30);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(0);
	                	 break;
	                	 
                }
	            break;
	            
            case 8:
                switch(i)
                {
                	case 0:
                		sprite.setLocation(65, 260);
		             	if(iSprite == 0) {
		             		bb1 = criarImageIcon("../imgs/bebendo/red/red_direita_1.png","Sprite 4");
		 					bb2 = criarImageIcon("../imgs/bebendo/red/red_direita_2.png","Sprite 5");}
		             	if(iSprite == 1) {
		             		bb1 = criarImageIcon("../imgs/bebendo/green/green_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/green/green_direita_2.png","Sprite 5");}
		             	if(iSprite == 2) {
		             		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_2.png","Sprite 5");}
		            	 
		             	fila.setCadeira(1);
		            	break;
                 
	                 case 1:
	                	 sprite.setLocation(210, 260);
                   		 if(iSprite == 0){ 
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 2 ) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(2);
	                	 break;
	                
	                 case 2:
	                	 sprite.setLocation(25, 80);
                    	 if(iSprite == 0) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	        				 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
                    	 if(iSprite == 1) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
                    		 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
                    	 if(iSprite == 2) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
    						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
                    	 fila.setCadeira(3);
                    	 break;
	                 
	                 case 3:
	                	 sprite.setLocation(90, 80);
	                  	 if(iSprite == 0) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	      					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 1) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 2) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                  	 fila.setCadeira(4);
	                	 break;
	                 
	                 case 4:
	                	 sprite.setLocation(155, 80);
	                  	 if(iSprite == 0) {	
	                  		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	      					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 1) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	                  		 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 2) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                  	 fila.setCadeira(5);
	                	 break;
	                 
	                 case 5:
	                	 sprite.setLocation(220, 80);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(6);
	                	 break;
	                	 
	                 case 6:
	                	 sprite.setLocation(280, 30);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(7);
	                	 break;
	                	 
	                 case 7:
	                	 sprite.setLocation(280, -35);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(0);
	                	 break;
	                	 
                }
	            break;
	            
            case 9:
                switch(i)
                {
                	case 0:
                		sprite.setLocation(65, 260);
		             	if(iSprite == 0) {
		             		bb1 = criarImageIcon("../imgs/bebendo/red/red_direita_1.png","Sprite 4");
		 					bb2 = criarImageIcon("../imgs/bebendo/red/red_direita_2.png","Sprite 5");}
		             	if(iSprite == 1) {
		             		bb1 = criarImageIcon("../imgs/bebendo/green/green_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/green/green_direita_2.png","Sprite 5");}
		             	if(iSprite == 2) {
		             		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_2.png","Sprite 5");}
		            	 
		             	fila.setCadeira(1);
		            	break;
                 
	                 case 1:
	                	 sprite.setLocation(210, 260);
                   		 if(iSprite == 0){ 
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 2 ) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(2);
	                	 break;
	                
	                 case 2:
	                	 sprite.setLocation(25, 80);
                    	 if(iSprite == 0) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	        				 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
                    	 if(iSprite == 1) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
                    		 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
                    	 if(iSprite == 2) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
    						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
                    	 fila.setCadeira(3);
                    	 break;
	                 
	                 case 3:
	                	 sprite.setLocation(90, 80);
	                  	 if(iSprite == 0) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	      					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 1) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 2) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                  	 fila.setCadeira(4);
	                	 break;
	                 
	                 case 4:
	                	 sprite.setLocation(155, 80);
	                  	 if(iSprite == 0) {	
	                  		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	      					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 1) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	                  		 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 2) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                  	 fila.setCadeira(5);
	                	 break;
	                 
	                 case 5:
	                	 sprite.setLocation(220, 80);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(6);
	                	 break;
	                	 
	                 case 6:
	                	 sprite.setLocation(280, 30);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(7);
	                	 break;
	                	 
	                 case 7:
	                	 sprite.setLocation(280, -35);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(8);
	                	 break;
	                	 
	                 case 8:
	                	 sprite.setLocation(135, 190);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_frente_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_frente_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_frente_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_frente_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_frente_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_frente_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(0);
	                	 break;
	                	 
                }
	            break;
	            
            case 10:
                switch(i)
                {
                	case 0:
                		sprite.setLocation(65, 260);
		             	if(iSprite == 0) {
		             		bb1 = criarImageIcon("../imgs/bebendo/red/red_direita_1.png","Sprite 4");
		 					bb2 = criarImageIcon("../imgs/bebendo/red/red_direita_2.png","Sprite 5");}
		             	if(iSprite == 1) {
		             		bb1 = criarImageIcon("../imgs/bebendo/green/green_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/green/green_direita_2.png","Sprite 5");}
		             	if(iSprite == 2) {
		             		bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_1.png","Sprite 4");
							bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_direita_2.png","Sprite 5");}
		            	 
		             	fila.setCadeira(1);
		            	break;
                 
	                 case 1:
	                	 sprite.setLocation(210, 260);
                   		 if(iSprite == 0){ 
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 2 ) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(2);
	                	 break;
	                
	                 case 2:
	                	 sprite.setLocation(25, 80);
                    	 if(iSprite == 0) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	        				 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
                    	 if(iSprite == 1) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
                    		 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
                    	 if(iSprite == 2) {
                    		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
    						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
                    	 fila.setCadeira(3);
                    	 break;
	                 
	                 case 3:
	                	 sprite.setLocation(90, 80);
	                  	 if(iSprite == 0) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	      					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 1) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 2) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                  	 fila.setCadeira(4);
	                	 break;
	                 
	                 case 4:
	                	 sprite.setLocation(155, 80);
	                  	 if(iSprite == 0) {	
	                  		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	      					 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 1) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	                  		 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                  	 if(iSprite == 2) {
	                  		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	  						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                  	 fila.setCadeira(5);
	                	 break;
	                 
	                 case 5:
	                	 sprite.setLocation(220, 80);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(6);
	                	 break;
	                	 
	                 case 6:
	                	 sprite.setLocation(280, 30);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(7);
	                	 break;
	                	 
	                 case 7:
	                	 sprite.setLocation(280, -35);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_esquerda_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_esquerda_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_esquerda_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(8);
	                	 break;
	                	 
	                 case 8:
	                	 sprite.setLocation(135, 190);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_frente_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_frente_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_frente_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_frente_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_frente_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_frente_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(9);
	                	 break;
	                	 
	                 case 9:
	                	 sprite.setLocation(135, 355);
	                   	 if(iSprite == 0) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/red/red_costa_1.png","Sprite 4");
	                   		 bb2 = criarImageIcon("../imgs/bebendo/red/red_costa_2.png","Sprite 5");}
	                   	 if(iSprite == 1) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/green/green_costa_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/green/green_costa_2.png","Sprite 5");}
	                   	 if(iSprite == 2) {
	                   		 bb1 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_1.png","Sprite 4");
	   						 bb2 = criarImageIcon("../imgs/bebendo/yellow/yellow_costa_2.png","Sprite 5");}
	                	 
	                   	 fila.setCadeira(0);
	                	 break;
	                	 
                }
	            break;
	    }
	}
	
	public ImageIcon criarImageIcon(String caminho, String descricao)
	{
		java.net.URL imgURL = getClass().getResource(caminho);
		if (imgURL != null) {
			return new ImageIcon(imgURL, descricao);
		} else {
			System.err.println("Nao foi possivel carregar o arquivo de imagem: " + caminho);
			return null;
		}
	}
}
