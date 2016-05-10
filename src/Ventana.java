
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static java.lang.Thread.sleep;
import javax.swing.JOptionPane;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;


public class Ventana extends JFrame
{
  int contadorProcesos=0;
  Random tamanoAleatorio=new Random();
  Random tiempoAleatorio=new Random();
  
  DefaultTableCellRenderer centrado=new DefaultTableCellRenderer();
  static DefaultTableModel modelProcesos=new DefaultTableModel();
  static JTable tablaProcesos=new JTable(modelProcesos);
  
  Thread auto;
  static JButton[]botonesMemoria=new JButton[20];
  static JLabel NumDisponibles;
  static JLabel numUtilizados;
  
  /////////////////////////////////////////////////////
  public Ventana()
  {
    setTitle("Paginacion Real de Memoria");
    setVisible(true);
    initialize();
    this.getContentPane().setBackground(Color.LIGHT_GRAY);
    
    auto.start();
    
  }
  
  private void initialize()
  {
    setBounds(10,10,540,520);
    setDefaultCloseOperation(3);
    getContentPane().setLayout(null);
   
    centrado = new DefaultTableCellRenderer();
    centrado.setHorizontalAlignment(0);
    
    JScrollPane laminaProcesos = new JScrollPane();
    
    laminaProcesos.setBounds(10, 20, 500, 316);
    this.getContentPane().add(laminaProcesos);
    
    tablaProcesos = new JTable();
    tablaProcesos.setModel(new DefaultTableModel(
      new Object[0][], 
      new String[] {
      "N Proceso", "Tamaño", "Status"}));
    
    laminaProcesos.setViewportView(tablaProcesos);
    
    tablaProcesos.getColumnModel().getColumn(0).setCellRenderer(this.centrado);
    tablaProcesos.getColumnModel().getColumn(1).setCellRenderer(this.centrado);
    tablaProcesos.getColumnModel().getColumn(2).setCellRenderer(this.centrado);
   
    JLabel lblMemoria = new JLabel("Memoria");
    lblMemoria.setFont(new Font("Calibri", 50, 18));
    lblMemoria.setForeground(Color.red);
    lblMemoria.setBounds(110, 381, 80, 14);
    this.add(lblMemoria);
    
    JLabel lblDisponible = new JLabel("Disponible");
    lblDisponible.setBounds(188,381,73,14);
    this.add(lblDisponible);
    
    JLabel lblOcupado = new JLabel("Utilizada");
    lblOcupado.setBounds(290,381,63,14);
    this.add(lblOcupado);
    
    NumDisponibles = new JLabel("22");
    NumDisponibles.setForeground(Color.blue);
    NumDisponibles.setFont(new Font("Times New Roman",0,20));
    NumDisponibles.setBounds(255, 379, 21, 14);
    this.add(NumDisponibles);
    
    numUtilizados = new JLabel("0");
    numUtilizados.setFont(new Font("Times New Roman",0,20));
    numUtilizados.setForeground(Color.blue);
    numUtilizados.setBounds(350,379,21,14);
    this.add(numUtilizados);
    
    final BufferMemoria memoria = new BufferMemoria();
    
    JButton info = new JButton("INFO");
    info.setBackground(new java.awt.Color(102, 102, 102));
    info.setForeground(new java.awt.Color(255, 255, 255));
    info.setBounds(460,450,60,25);
    this.add(info);
    
    info.addActionListener(new ActionListener() {
       
        public void actionPerformed(ActionEvent e) 
        {
          JOptionPane.showMessageDialog(null, "Desarrollado por:\nSamuel Ramirez Torres\nCodigo:304454235\nBibliogafia:\nSistemas.Operativos\nWilliam Stallings\n2000 2da Edicion Prentice Hall ");
        }
    });
    
   
    
    int x=10,y=400;
    for(int i=0;i<botonesMemoria.length;i++)
    {   
        if(i>=10)
            y=420;
        
        if(i==10)
            x=10;
        
        botonesMemoria[i] = new JButton();
        botonesMemoria[i].setBounds(x, y, 50, 20);
        botonesMemoria[i].setBackground(Color.WHITE);
        botonesMemoria[i].setForeground(Color.WHITE);
        botonesMemoria[i].setFont(new Font("Times New Roman", 0, 14));

        this.getContentPane().add(botonesMemoria[i]);

        x+=50;
    }

    auto=new Thread(){
        public void run()
        {
            while(true)
            {

                new Proceso(memoria, "Proceso " + (contadorProcesos+1), contadorProcesos, tamanoAleatorio.nextInt(6) + 2, tiempoAleatorio.nextInt(12) + 5).start();
                contadorProcesos += 1;

                try 
                {
                    sleep(4000);
                } 
                catch (InterruptedException ex) 
                {

                }           
            }
        }  
    };
    
    
    
 
  }
  
  
  
  
  //////////////////////////////////////////////////////////////////////////////////
  
  public static void main(String[] args)
  {
    Ventana ventana = new Ventana();       
  }
   
}
