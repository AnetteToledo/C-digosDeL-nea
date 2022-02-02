
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class GraficaCodigosDeLinea extends Frame implements WindowListener, TextListener, AdjustmentListener{

  private Label mensaje;
  private TextField caja;
  private AreaGrafica graf;
  private Scrollbar barra;
  private Panel panel;

  public static void main(String[] args){
    GraficaCodigosDeLinea obj = new GraficaCodigosDeLinea();
  }

  public GraficaCodigosDeLinea(){
    setVisible(true);
    setTitle("Códigos de línea");
    setSize(750,500);
    setLocation(20,20);
    setLocationRelativeTo(null);
    setResizable(false);
    setBackground(Color.black);  
    addWindowListener(this);
    Panel panel = new Panel();
    panel.setLayout(new FlowLayout());
    Font font = new Font("Agency FB", Font.BOLD, 14);
    add(panel,BorderLayout.NORTH);
    mensaje = new Label(" Bits de entrada: ");
    mensaje.setFont(font);
    panel.add(mensaje);
    mensaje.setBackground(Color.black);
    mensaje.setForeground(Color.white);    
    caja = new TextField(20);
    caja.addTextListener(this);
    panel.add(caja);
    caja.setBackground(Color.white);
    graf = new AreaGrafica();
    add(graf,BorderLayout.CENTER);
    barra = new Scrollbar(0, 1, 1, 1, 1);
    barra.addAdjustmentListener(this);
    add(barra,BorderLayout.SOUTH);
    validate();
  }

  public void windowClosing(WindowEvent ev){
    System.exit(0);
  }
  public void windowClosed(WindowEvent ev){}
  public void windowOpened(WindowEvent ev){}
  public void windowActivated(WindowEvent ev){}
  public void windowDeactivated(WindowEvent ev){}
  public void windowIconified(WindowEvent ev){}
  public void windowDeiconified(WindowEvent ev){}

  public void textValueChanged(TextEvent e){
    if(vale(caja.getText())){
      if((caja.getText().length()-29)>0)
        barra.setMaximum(caja.getText().length()-29);
      else
        barra.setMaximum(1);
      barra.setValue(-1);
      validate();
      graf.graficar(caja.getText());
      caja.setBackground(Color.white);
    }
    else{
      graf.graficar("");
      caja.setBackground(Color.red);
      JOptionPane.showMessageDialog(null, "¡Solo se admiten dígitos binarios!");
    }
  }

  private boolean vale(String a){
    for(int i=0;i<a.length();i++)
      if(a.charAt(i)!='0' && a.charAt(i)!='1')
        return false;
    return true;
  }

  public void adjustmentValueChanged(AdjustmentEvent e){
    String subCad = caja.getText().substring(barra.getValue(),caja.getText().length());
    graf.graficar(subCad);
  }
}