
import javax.swing.table.DefaultTableModel;

public class Proceso extends Thread
{
  BufferMemoria memoria;
  String nombre;
  int tiempo=0;
  float progreso;
  int tamaño;
  int numero;
  
  int tiempoVida=50;
 
  
  public Proceso(BufferMemoria memoria, String nom, int numero, int tamaño, int tiempoProceso)
  {
    this.memoria=memoria;
    this.nombre=nom;
    this.numero=numero;
    this.tamaño=tamaño;
   
    this.progreso=tiempoVida/tiempoProceso;
    
    Ventana.modelProcesos = (DefaultTableModel)Ventana.tablaProcesos.getModel();
    String[] nuevaFila = { "", "", "", ""};
    Ventana.modelProcesos.addRow(nuevaFila);
    
    Ventana.tablaProcesos.setValueAt(nombre,numero,0);
    Ventana.tablaProcesos.setValueAt(tamaño,numero,1);
    Ventana.tablaProcesos.setValueAt("Esperando",numero,2);
    
  }
  
  public void ejecutar()
  {
   String status="";   
      
    while (tiempo < tiempoVida)
    {
        try
        {

          if(getState().toString().equals("RUNNABLE"))
              status="Ejecutando";

          Ventana.tablaProcesos.setValueAt(status,numero,2);

          sleep(1000);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
        
        
        tiempo+=progreso;
        
        if(tiempo>=tiempoVida)
        {
          Ventana.tablaProcesos.setValueAt("Finalizado",numero, 2);
        }
    }
    memoria.salir(numero,tamaño);
    
  }
  
  public void run()
  {
    while(true)
    {
      if (tamaño<BufferMemoria.espaciosDisponibles)
      {
        memoria.entrar(numero,tamaño);
        ejecutar();
        break;
      }
      
      try
      {
        sleep(1000);
      }
      catch (InterruptedException e1)
      {
        e1.printStackTrace();
      }
    }
    
  }
  
  
}

