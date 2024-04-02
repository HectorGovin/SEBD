
package MenuPrincipal;

import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

public class ImpresionTicket {
    
    public void Ticket(){
        PrinterMatrix printer = new PrinterMatrix();
        
        String Folio = "001";
        String nombreVendedor = "JAVIER LOPEZ";
        String nombreCliente = "DANIEL MARTINEZ LARA";
        
        Extenso e = new Extenso();
        
        e.setNumber(101.85);
        
        printer.setOutSize(11, 32);
        
        printer.printCharAtCol(1, 1, 32, "=");
        printer.printTextWrap(1, 2, 8, 32, "TICKET DE COMPRA");
        printer.printTextWrap(2, 3, 1, 32, "FOLIO: "+Folio);
        printer.printTextWrap(3, 3, 1, 32, "FECHA: 02/04/2024");
        printer.printTextWrap(4, 3, 1, 32, "HORA: 02:12 p.m.");
        printer.printTextWrap(5, 3, 1, 32, "VENDEDOR: "+nombreVendedor);
        printer.printTextWrap(6, 3, 1, 32, "CLIENTE: "+nombreCliente);
        
        printer.toFile("ticket.txt");
        
        FileInputStream inputStream = null;
        
        try{
            inputStream = new FileInputStream("ticket.txt");
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        if(inputStream == null){
            return;
        }
        
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream,docFormat,null);
        
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        
        if(defaultPrintService !=null){
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try{
                printJob.print(document, attributeSet);
            }catch(Exception ex){
                System.out.println("Error: "+ex.toString());
            }
        }else{
            System.err.println("No hay una impresora instalada");
        }
        
    }
    
}
