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
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.border.BevelBorder;

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
		this.setTitle("Pcl Forms a Printer UTP");
		

		
	
		PrintService[] ps = PrintServiceLookup.lookupPrintServices( DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);
		        for( int i=0 ; i< ps.length; i++)
		        {
		           jComboBoxImpresora.addItem(ps[i].getName());
		          
		           
		        }
		        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
		        jComboBoxImpresora.setSelectedItem(service.getName());//Default printer!!!
		       

		
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
			jContentPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jContentPane.setBackground(new Color(153, 153, 255));
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
			jComboBoxImpresora.setBounds(new Rectangle(30, 16, 218, 25));
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
			jButtonCargar.setBounds(new Rectangle(30, 87, 218, 31));
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
					if(ip.contains("_")){//si tiene guión volálo
					 ip=ip.substring(0, ip.indexOf("_"));
					}
		            
					 System.out.println(ip);
					 String ruta=".\\src\\FormsPrint\\lpr.exe -S "+ip+" -Praw -o l .\\src\\FormsPrint\\FORMULARIOS.ram";
		             try {
		            	
						rt.exec(ruta);
						
						JOptionPane.showMessageDialog(null,"Datos cargados en "+printer+" Puerto "+ip);
						
						
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
