import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class Main extends MIDlet {

   private ii a;


   public final void startApp() throws MIDletStateChangeException {
      try {
         this.a = new ii(this);
         Display.getDisplay(this).setCurrent(this.a);
      } catch (Throwable var3) {
         Form var2;
         (var2 = new Form("Error")).append(var3.getClass().getName());
         var3.printStackTrace();
         System.out.println();
         if(var3.getMessage() != null) {
            var2.append(var3.getMessage());
         }

         Display.getDisplay(this).setCurrent(var2);
      }
   }

   public final void pauseApp() {}

   public final void destroyApp(boolean var1) throws MIDletStateChangeException {
      if(this.a != null) {
         this.a.a();
      }

   }
}
