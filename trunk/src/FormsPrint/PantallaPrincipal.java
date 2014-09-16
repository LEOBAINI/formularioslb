package FormsPrint;
import java.awt.Rectangle;
import java.io.IOException;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Font;

public class PantallaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JComboBox<String> jComboBoxImpresora = null;
	private JButton jButtonCargar = null;

	/**
	 * This is the default constructor
	 */
	public PantallaPrincipal() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/logoTF.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("Carga de Formularios");
		

		
	
		PrintService[] ps = PrintServiceLookup.lookupPrintServices( DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);
		        for( int i=0 ; i< ps.length; i++)
		        {
		           jComboBoxImpresora.addItem(ps[i].getName());
		          
		           
		        }
		
		}

	

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJComboBoxImpresora(), null);
			jContentPane.add(getJButtonCargar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jComboBoxImpresora	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox<String> getJComboBoxImpresora() {
		if (jComboBoxImpresora == null) {
			jComboBoxImpresora = new JComboBox<String>();
			jComboBoxImpresora.setBounds(new Rectangle(16, 16, 240, 36));
		}
		return jComboBoxImpresora;
	}

	/**
	 * This method initializes jButtonCargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonCargar() {
		if (jButtonCargar == null) {
			jButtonCargar = new JButton();
			jButtonCargar.setBounds(new Rectangle(25, 85, 218, 45));
			jButtonCargar.setText("Cargar Formularios");
			jButtonCargar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String printer=jComboBoxImpresora.getSelectedItem().toString();
					String ip=null;
					ip=LeeRegistro.leer(printer);
					Runtime rt = Runtime.getRuntime();
					
					
					if(printer.contains("\\")){//si tiene barra es printer de red compartida
					
					 
						String com1="net use lpt2: /del";
						String com2="net use lpt2 "+printer+" /yes";
						String com3="cmd copy /b .\\src\\FormsPrint\\FORMULARIOS.ram lpt2";
						
						
					
						try {
							rt.exec(com1);
							rt.exec(com2);
							rt.exec(com3);
							JOptionPane.showMessageDialog(null,"Enviando datos en "+printer+" Puerto LPT2, Impresora por USB");
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,"Error al cargar! "+e1.getMessage());
							
						}
						
					}//fin if
					else{
					
					 ip=ip.replaceAll("_","");
		             String ruta=".\\src\\FormsPrint\\lpr.exe -S "+ip+" -Praw -o l .\\src\\FormsPrint\\FORMULARIOS.ram";
		             try {
		            	
						rt.exec(ruta);
						
						JOptionPane.showMessageDialog(null," datos en "+printer+" Puerto "+ip);
						
						
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,"Error al cargar "+e1.getMessage());
					}
					
					}//fin else
					
				}
			});
		}
		return jButtonCargar;
	}

}
