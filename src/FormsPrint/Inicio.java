package FormsPrint;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Inicio {

	
	public static void main(String[] args) {
		PantallaPrincipal pan=new PantallaPrincipal();
		try {
			UIManager.setLookAndFeel(
			        UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JFrame.setDefaultLookAndFeelDecorated(true);
		pan.setLocationRelativeTo(null);
		pan.setVisible(true);
		pan.setResizable(false);
		pan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
