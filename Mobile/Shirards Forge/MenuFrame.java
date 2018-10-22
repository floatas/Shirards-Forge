import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class MenuFrame extends RootFrame {

   public static Image Symbols;
   private static Image menuImage;
   public int screenX;
   public int screenY;
   public int screenWidth;
   public int screenHeight;
   public String MessageToDisplay;
   public int p;
   public final int MessageToDisplayHeight;
   private int y;
   private int z;
   private int A;


   public MenuFrame(int var1, String var2) {
      super(var1);
      this.MessageToDisplay = var2;
      this.MessageToDisplayHeight = var2 == null?0:RootFrame.fontHeight + 1;
   }

   public MenuFrame(int var1, String var2, int var3, int var4) {
      this(var1, var2);
      this.y = var3;
      this.z = var4;
   }

   public final void d(int var1) {
      this.A = var1;
      this.b(this.MessageToDisplay);
      this.screenX = Math.max(0, 240 - this.screenWidth >> 1);
   }

   public final void b(String var1) {
      this.MessageToDisplay = var1;
      if(var1 != null) {
         this.screenWidth = Math.max(this.A, RootFrame.font.stringWidth(var1) + 1);
      } else {
         this.screenWidth = this.A;
      }
   }

   public final void d(int var1, int var2) {
      this.p = var2 == 0?0:var2 + 1;
      this.screenHeight = Math.min(320 - this.MessageToDisplayHeight - var2 - 2, var1);
      if(this.y != 0) {
         this.screenHeight = Math.min(this.screenHeight, this.z - this.y - this.MessageToDisplayHeight - 2);
         this.screenY = (this.z - this.y - this.screenHeight - this.MessageToDisplayHeight >> 1) + this.MessageToDisplayHeight + this.y;
      } else {
         this.screenY = (320 - this.screenHeight - this.MessageToDisplayHeight - var2 >> 1) + this.MessageToDisplayHeight;
      }
   }

   public final void a(Graphics var1) {
      var1.setFont(RootFrame.font);
      if(menuImage == null) {
         menuImage = super.frame.LoadImage("/menu.png");
      }

      int var2 = this.screenX;

      do {
         int var3 = Math.min(menuImage.getWidth(), this.screenWidth + this.screenX - var2);
         RootFrame.drawRegion(var1, menuImage, 0, 0, var3, menuImage.getHeight(), 0, var2, this.screenY - this.MessageToDisplayHeight - 9, 20);
         RootFrame.drawRegion(var1, menuImage, 0, 0, var3, menuImage.getHeight(), 0, var2, this.screenY + this.screenHeight + this.p, 20);
      } while((var2 += menuImage.getWidth()) < this.screenWidth + this.screenX);

      var1.setClip(0, 0, 240, 320);
      var1.setColor(5263424);
      var1.drawRect(this.screenX - 2, this.screenY - this.MessageToDisplayHeight - 1 - 9, this.screenWidth + 3, this.screenHeight + 1 + 18 + this.MessageToDisplayHeight + this.p);
      var1.setColor(11369747);
      var1.drawLine(this.screenX - 1, this.screenY - this.MessageToDisplayHeight - 9, this.screenX - 1, this.screenY + this.screenHeight + 9 + this.p - 1);
      var1.drawLine(this.screenX + this.screenWidth, this.screenY - this.MessageToDisplayHeight - 9, this.screenX + this.screenWidth, this.screenY + this.screenHeight + 9 + this.p - 1);
      if(this.MessageToDisplayHeight > 0) {
         var1.setColor(8610337);
         var1.fillRect(this.screenX, this.screenY - this.MessageToDisplayHeight, this.screenWidth, this.MessageToDisplayHeight);
         var1.setColor(16173378);
         var1.drawString(this.MessageToDisplay, this.screenX + (this.screenWidth - 1 >> 1) + 1, 0 + this.screenY - this.MessageToDisplayHeight, 17);
         var1.setColor(11369747);
         var1.drawLine(this.screenX, this.screenY - 1, this.screenX + this.screenWidth, this.screenY - 1);
      }

      if(this.p > 0) {
         var1.setColor(11369747);
         var1.drawLine(this.screenX, this.screenY + this.screenHeight, this.screenX + this.screenWidth, this.screenY + this.screenHeight);
      }

   }
}
