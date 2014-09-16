package FormsPrint;
import java.lang.reflect.InvocationTargetException;
 
 
public class LeeRegistro {
 
   
	
	
	public static String leer(String impresora){
        String productName = null;
 
        try {
 
            // Leer valor
            // HKEY, Key, valueName
            productName = WinRegistry.readString(
                    WinRegistry.HKEY_LOCAL_MACHINE,
                    "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Print\\Printers\\"+impresora,
                    "port");
 
          /*  //Crear llave
            WinRegistry.createKey(WinRegistry.HKEY_CURRENT_USER,
                    "OS");
 
            //Escribir valor
            WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER,
                    "OS","Name",productName);*/
 
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
 
        System.out.println("Puerto = " + productName);  // Windows X
		
        return productName;
 
	}
}