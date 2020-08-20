package SlimeBar;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Semaphore;

import javax.swing.*;

public class ControleBar
{
	private JFrame bar;
	private Mesa mesa;
	private Fila fila;
	private Semaphore qnt_clientes, mutex_fila, mutex_bar;
	private JLabel lb_bar;
	private ImageIcon im_bar;
	private JButton bt_add;
	private ImageIcon im_add;
	
	Cliente newCliente;
	
	public ControleBar(int numeroDeCadeiras)
	{
		this.mesa = new Mesa(numeroDeCadeiras);
		this.qnt_clientes = new Semaphore(numeroDeCadeiras);
		this.mutex_fila = new Semaphore(1);
		this.mutex_bar = new Semaphore(1);
		this.fila = new Fila();

		im_bar = construirBar(numeroDeCadeiras);
		im_add = criarImageIcon("../imgs/locais/add_default.png", "Adicionar Cliente");
		lb_bar = new JLabel(im_bar);
		bt_add = new JButton(im_add);
		lb_bar.setBounds(0, 0, 640, 480);
		bt_add.setBounds(50, 10, 60, 60);
		bt_add.setBackground(new Color(0, 0, 0, 0));
		bt_add.setBorder(null);
		bt_add.setFocusable(false);
    	bt_add.setContentAreaFilled(false);
    	bt_add.setBorderPainted(false);
		
		bt_add.addMouseListener(new MouseListener()
		{
            public void mouseClicked(MouseEvent e)
            {
            	String tempo_bar = "", tempo_casa = "", nome = "Sem nome";
            	JTextField barField = new JTextField(5);
                JTextField homeField = new JTextField(5);
                JPanel myPanel = new JPanel();
                
                nome = JOptionPane.showInputDialog(bar, "Nome do Cliente");
                
                myPanel.add(new JLabel("Tempo no Bar:"));
                myPanel.add(barField);
                myPanel.add(Box.createHorizontalStrut(15)); 
                myPanel.add(new JLabel("Tempo em Casa:"));
                myPanel.add(homeField);

                int result = JOptionPane.showConfirmDialog(null, myPanel, 
                         "Insira os Tempos", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION)
                {
                   tempo_bar = barField.getText();
				   tempo_casa = homeField.getText();
                }
                
                if(tempo_bar == null || tempo_bar == "" || tempo_casa == null || tempo_casa == "") {} 
                
                else
                {
            		try
            		{
            			int tb, tc;
            			
            			tb = Integer.parseInt(tempo_bar);
            			tc = Integer.parseInt(tempo_casa);
            			
            			newCliente = new Cliente(bar, lb_bar, qnt_clientes, mutex_fila, mutex_bar, fila, mesa, nome, tb, tc);
            			mutex_fila.acquire();
            			fila.insereCliente(newCliente);
            			mutex_fila.release();
            			
            			System.out.println("Cliente inserido ID: "+ newCliente.getId() + " || Nome: " + newCliente.getNome());
            			System.out.println("Tempo no Bar: " + tb + " || Tempo em Casa: " + tc);
            			
            			newCliente.start();
                    }
            		
                    catch(Exception er) {
                        JOptionPane.showMessageDialog(bar, "Valor inválido");
                    }
            	}
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {
                bt_add.setIcon(criarImageIcon("../imgs/locais/add_entered.png", "Adicionar Cliente"));
            }
            public void mouseExited(MouseEvent e) {
            	bt_add.setIcon(criarImageIcon("../imgs/locais/add_default.png", "Adicionar Cliente"));
            }
        });
		
		bar = new JFrame("Slime Bar");
		bar.setLayout(null);
		bar.setSize(640, 480);
		bar.setLocationRelativeTo(null);
		bar.add(bt_add);
		bar.add(lb_bar);
		bar.setResizable(false);
		bar.setVisible(true);
	}

	public ImageIcon construirBar(int numeroDeCadeiras)
	{
		if (numeroDeCadeiras >= 1 && numeroDeCadeiras <= 8) {
			return criarImageIcon("../imgs/locais/mesa_type_1.jpg","Mesa com 2 cadeiras");
		}
		
		else if (numeroDeCadeiras == 9) {
			return criarImageIcon("../imgs/locais/mesa_type_2.jpg","Mesa com 3 cadeiras");
		}
		
		else if (numeroDeCadeiras == 10) {
			return criarImageIcon("../imgs/locais/mesa_type_3.jpg","Mesa com 4 cadeiras");
		}
		
		return null;
	}
	
	public ImageIcon criarImageIcon(String caminho, String descricao)
	{
		java.net.URL imgURL = getClass().getResource(caminho);
		if (imgURL != null) {
			return new ImageIcon(imgURL, descricao);
		} else {
			System.err.println("Não foi possível carregar o arquivo de imagem: " + caminho);
			return null;
		}
	}
	
}
