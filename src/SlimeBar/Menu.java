package SlimeBar;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Menu
{
	@SuppressWarnings("unused")
	private ControleBar bar;
	private JFrame jf_menu;
	private JLabel lb_menu;
	private JButton bt_iniciar;
	private JButton bt_sair;
	private ImageIcon im_menu;
	private ImageIcon im_iniciar;
	private ImageIcon im_sair;
	private ImageIcon im_iniciar_entered;
	private ImageIcon im_sair_entered;
	
	public Menu()
	{
		jf_menu = new JFrame("Slime Bar");
		im_menu = criarImageIcon("../imgs/menu/menu.jpg", "Menu");
		im_iniciar = criarImageIcon("../imgs/menu/bt_iniciar_default.png", "Iniciar");
		im_sair = criarImageIcon("../imgs/menu/bt_sair_default.png", "Sair");
		im_iniciar_entered = criarImageIcon("../imgs/menu/bt_iniciar_sob.png", "Iniciar Entered");
		im_sair_entered = criarImageIcon("../imgs/menu/bt_sair_sob.png", "Sair Entered");
		
		lb_menu = new JLabel(im_menu);
		bt_iniciar = new JButton();
		bt_sair = new JButton();
		bt_iniciar.setIcon(im_iniciar);
		bt_sair.setIcon(im_sair);
		
		bt_iniciar.addMouseListener(new MouseListener()
		{
            public void mouseClicked(MouseEvent e)
            {
            	int cadeiras;
            	String qntCadeiras = (JOptionPane.showInputDialog(jf_menu, "Numero de Cadeiras"));
            	
            	try {
	            	if(qntCadeiras == null || qntCadeiras == "") {
	            		JOptionPane.showMessageDialog(jf_menu, "Valor inválido");
	            		
	            	}else if(Integer.parseInt(qntCadeiras) < 1){
	            		JOptionPane.showMessageDialog(jf_menu, "Numero minimo de cadeiras excedido(min:1)");
	            		
	            	}else if(Integer.parseInt(qntCadeiras) > 10){
	            		JOptionPane.showMessageDialog(jf_menu, "Numero maximo de cadeiras excedido(max:10)");
	            		
	            	} else {
                        cadeiras = Integer.parseInt(qntCadeiras);
                        iniciar(cadeiras);
                        jf_menu.dispose();
                    }
            	}catch(Exception er) {
                    JOptionPane.showMessageDialog(jf_menu, "Valor Invalido");
            	}
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {
                bt_iniciar.setIcon(im_iniciar_entered);
            }
            public void mouseExited(MouseEvent e) {
            	bt_iniciar.setIcon(im_iniciar);
            }
        });
		
		bt_sair.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            	System.exit(0);
            }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {
                bt_sair.setIcon(im_sair_entered);
            }
            public void mouseExited(MouseEvent e) {
            	bt_sair.setIcon(im_sair);
            }
        });
	}
	
	public void apresenta()
	{
		lb_menu.setBounds(0 ,0 , 640, 480);
		bt_iniciar.setBounds(420, 300, 171, 55);
		bt_sair.setBounds(420, 360, 171, 55);
		bt_iniciar.setBackground(new Color(0, 0, 0, 0));
		bt_sair.setBackground(new Color(0, 0, 0, 0));
		bt_iniciar.setFocusable(false);
		bt_iniciar.setBorder(null);
		bt_sair.setFocusable(false);
		bt_iniciar.setContentAreaFilled(false);
    	bt_iniciar.setBorderPainted(false);
		bt_sair.setBorder(null);
		jf_menu.add(bt_iniciar);
		jf_menu.add(bt_sair);
		jf_menu.add(lb_menu);
		jf_menu.setSize(640, 480);
		jf_menu.setLocationRelativeTo(null);
		jf_menu.setResizable(false);
		jf_menu.setVisible(true);
	}
	
	public void iniciar(int numeroDeCadeiras) {
		bar = new ControleBar(numeroDeCadeiras);
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
