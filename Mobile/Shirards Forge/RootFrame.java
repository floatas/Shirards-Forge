import com.nokia.mid.ui.DeviceControl;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class RootFrame {

   public static final Font font = Font.getFont(0, 0, 8);
   public static final int fontHeight = font.getHeight();
   public long t;
   public int webController;
   private int a_;
   public int v = Integer.MAX_VALUE;
   public Frame frame;


   public RootFrame(int var1) {
      this.webController = var1;
   }

   public RootFrame(int var1, int var2) {
      this.webController = var1;
      this.a_ = var2;
   }

   public RootFrame(int var1, int var2, int var3) {
      this.webController = var1;
      this.a_ = var2;
      this.v = var3;
   }

   public void RenderFrame(Graphics var1, boolean var2) {}

   public  boolean a(int var1, int var2) {
      return false;
   }

   public void ResetNCount(int var1, int var2) {}

   public void a() {}

   public void c() {
      if(this.a_ == 1) {
         Frame.Midlet.notifyDestroyed();
      }

   }

   public void d() {}

   public final void RepaintFrame() {
      this.frame.repaint();
   }

   public static final void drawRectsAndLines(Graphics var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      int var9 = var4 * (var3 - 7) / var6;
      int var10 = Math.min(var5 * (var3 - 7) / var6, var3 - 8 - var9);
      var0.setColor(var7);
      var0.fillRect(var1, var2 + 4 + var9, 5, var10);
      var0.setColor(var8);
      var0.drawLine(var1 + 2, var2, var1 + 2, var2);
      var0.drawLine(var1 + 1, var2 + 1, var1 + 3, var2 + 1);
      var0.drawLine(var1, var2 + 2, var1 + 4, var2 + 2);
      var0.drawLine(var1, var2 + var3 - 3, var1 + 4, var2 + var3 - 3);
      var0.drawLine(var1 + 1, var2 + var3 - 2, var1 + 3, var2 + var3 - 2);
      var0.drawLine(var1 + 2, var2 + var3 - 1, var1 + 2, var2 + var3 - 1);
   }

   public static final void drawSomeRegion(Graphics var0, Image var1, byte[] var2, int var3, int var4, int var5, int var6, int var7) {
      int var8 = var3 << 3;
      if(var6 == 2) {
         var4 -= var2[var8 + 6] + var2[var8 + 0];
      } else {
         var4 += var2[var8 + 6];
      }

      drawRegion(var0, var1, var2[var8 + 2] << 8 | var2[var8 + 2 + 1] & 255, var2[var8 + 4] << 8 | var2[var8 + 4 + 1] & 255, var2[var8 + 0], var2[var8 + 1], var6, var4, var5 + var2[var8 + 7], var7);
   }

   public static final void setLight(boolean var0) {
      DeviceControl.setLights(0, var0?100:0);
   }

   public static final void vibrate(Display var0, int var1) {
      var0.vibrate(var1);
   }

   public static final void drawRegion(Graphics var0, Image var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      var0.drawRegion(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

}
