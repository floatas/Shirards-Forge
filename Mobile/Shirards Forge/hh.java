import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class hh extends MenuFrame {

   private final Vector d;
   public final String a;
   private final String[] e;
   private int f;
   public int b;
   private final int g;
   private int h;
   private boolean i;
   private final long B;
   private final int C;
   private int D;
   private char E;
   private Font font;
   private int G;
   private boolean H;
   private int[] I;
   private int J;
   public boolean c;


   public hh(int webController, String var2, String var3) {
      this(webController, var2, var3, (String[])null);
   }

   public hh(int webController, String var2, String var3, String[] var4) {
      this(webController, var2, var3, var4, Integer.MAX_VALUE);
   }

   public hh(int webController, String var2, String var3, String[] var4, int var5) {
      this(webController, 220, 300, var2, var3, var4, var5);
   }

   private hh(int var1, int var2, int var3, String var4, String var5, String[] var6, int var7) {
      super(var1, var4);
      this.d = new Vector();
      this.D = 16173378;
      this.E = 1;
      this.font = RootFrame.font;
      this.G = -1;
      this.I = new int[]{0, this.E, 0};
      this.d(var2);
      this.B = System.currentTimeMillis() + (long)var7;
      this.a = var5;
      this.e = var6;
      this.C = var2 - 6 - 2;
      this.d(var2);
      var3 = Math.min(302 - super.MessageToDisplayHeight - super.p - 2, var3);
      int var8 = 0;
      int var9 = 0;

      while(var8 < var5.length()) {
         char var10;
         switch(var10 = var5.charAt(var8++)) {
         case 1:
         case 2:
         case 3:
            this.b(var9, var8 - 1);
            var9 = var8;
            this.E = var10;
            break;
         case 4:
            this.b(var9, var8 - 1);
            var9 = var8;
            this.a(0, 0, 0, 0);
            this.H = true;
         case 5:
         case 8:
         default:
            break;
         case 6:
            this.b(var9, var8 - 1);
            this.D = (var5.charAt(var8) & 255) << 16 | (var5.charAt(var8 + 1) & 255) << 8 | var5.charAt(var8 + 2) & 255;
            var9 = var8 += 3;
            break;
         case 7:
            this.b(var9, var8 - 1);
            var9 = var8;
            this.D = 16173378;
            break;
         case 9:
            this.b(var9, var8 - 1);
            this.G = var5.charAt(var8);
            this.font = Font.getFont(this.G & 96, this.G & 7, this.G & 24);
            ++var8;
            var9 = var8;
            break;
         case 10:
            this.b(var9, var8 - 1);
            var9 = var8;
            this.b();
            break;
         case 11:
            this.b(var9, var8 - 1);
            if(10 + this.I[0] > this.C) {
               this.b();
            }

            this.a(10, 9, -11, var5.charAt(var8));
            ++var8;
            var9 = var8;
            break;
         case 12:
            this.b(var9, var8 - 1);
            var9 = var8;
            if(this.I.length > 3) {
               this.b();
            }

            this.d.addElement(new int[]{0, 12, 5});
         }
      }

      this.b(var9, var8);
      this.b();
      int var14 = 0;
      int var11 = 0;

      int var12;
      for(var12 = 0; var12 < this.d.size(); ++var12) {
         int[] var13 = (int[])((int[])this.d.elementAt(var12));
         this.J += var13[2];
         var14 = Math.max(var14, var13[0]);
         if(this.J <= var3) {
            var11 = this.J;
         }
      }

      this.d(Math.min(this.J, var11) + 4, var6 != null?RootFrame.fontHeight + 4:0);
      if(var6 != null) {
         var12 = var6.length << 2;

         for(int var15 = 0; var15 < var6.length; ++var15) {
            var12 += RootFrame.font.stringWidth(var6[var15]);
         }

         this.g = var12;
         super.v = 100;
      } else {
         this.g = 0;
         super.v = var7;
      }

      this.d(Math.max(var14 + (super.screenHeight - 2 < this.J?8:2), Math.min(this.g, super.screenWidth)));
   }

   private void b(int var1, int var2) {
      if(var1 != var2) {
         int var3 = 0;
         int var4 = var1;

         while(var3 <= this.C - this.I[0] && var4 < var2) {
            int var5 = var4;

            char var6;
            while(var4 < var2 && (var6 = this.a.charAt(var4++)) != 32 && var6 != 45) {
               ;
            }

            int var8 = this.font.substringWidth(this.a, var5, var4 - var5);
            if(var3 + var8 <= this.C - this.I[0]) {
               var3 += var8;
            } else {
               char var7;
               if(var1 == var5 && this.I.length == 3) {
                  for(var3 += var8; var3 > this.C - this.I[0]; var3 -= this.font.charWidth(var7)) {
                     --var4;
                     var7 = this.a.charAt(var4);
                  }
               } else {
                  var4 = var5;
               }

               if(var3 > 0) {
                  this.a(var3, RootFrame.fontHeight, var1, var4 - var1);
               }

               this.b();
               var3 = 0;
               var1 = var4;
            }
         }

         this.a(var3, RootFrame.fontHeight, var1, var4 - var1);
      }
   }

   private void a(int var1, int var2, int var3, int var4) {
      int[] var5 = new int[this.I.length + 5];
      System.arraycopy(this.I, 0, var5, 0, this.I.length);
      var5[this.I.length + 0] = var1;
      var5[this.I.length + 1] = this.G;
      var5[this.I.length + 2] = this.D;
      var5[this.I.length + 3] = var3;
      var5[this.I.length + 4] = var4;
      var5[0] += var1;
      var5[1] = this.E;
      var5[2] = Math.max(var5[2], var2);
      this.I = var5;
   }

   private void b() {
      if(this.H) {
         this.H = false;

         for(int var1 = 3; var1 < this.I.length; var1 += 5) {
            if(this.I[var1 + 0] == 0) {
               this.I[var1 + 0] = this.C - this.I[0];
               this.I[0] = this.C;
               this.I[var1 + 2] = 6316128;
               break;
            }
         }
      }

      this.d.addElement(this.I);
      this.I = new int[]{0, this.E, RootFrame.fontHeight};
   }

   public final void RenderFrame(Graphics var1, boolean var2) {
      this.a(var1);
      var1.setColor(0);
      var1.fillRect(super.screenX, super.screenY, super.screenWidth, super.screenHeight);
      if(super.screenHeight - 2 < this.J) {
         RootFrame.drawRectsAndLines(var1, super.screenX + super.screenWidth - 6, super.screenY + 1, super.screenHeight - 2, this.f * 10, super.screenHeight - 2, this.J, 11369747, 16173378);
      }

      int var3 = -1;
      int var4 = Math.max(-this.f * 10 + 1, -this.J + super.screenHeight - 1);
      int var5 = super.screenX + 1;
      var1.setClip(super.screenX + 1, super.screenY + 1, super.screenWidth - 2, super.screenHeight - 2);

      int var6;
      int var8;
      int var9;
      int var11;
      int var15;
      for(var6 = 0; var6 < this.d.size() && var4 <= super.screenHeight; ++var6) {
         int[] var7 = (int[])((int[])this.d.elementAt(var6));
         if(var4 + var7[2] > 0) {
            var8 = var5;
            switch(var7[1]) {
            case 2:
               var8 = super.screenX + super.screenWidth - 7 - var7[0];
               break;
            case 3:
               var8 = var5 + (super.screenWidth - 7 - var7[0] >> 1);
               break;
            case 12:
               var1.drawLine(super.screenX + 5, super.screenY + 2 + var4, super.screenX + super.screenWidth - (super.screenHeight - 2 < this.J?12:6), super.screenY + 2 + var4);
            }

            for(var9 = 3; var9 < var7.length; var9 += 5) {
               if(var7[var9 + 3] == -11) {
                  Image var10 = MenuFrame.Symbols;
                  if(MenuFrame.Symbols == null) {
                     MenuFrame.Symbols = var10 = super.frame.LoadImage("/symb.png");
                  }

                  var11 = super.screenY + var4 + (var7[2] - 9 >> 1);
                  RootFrame.drawRegion(var1, var10, var7[var9 + 4] * 9, 0, 9, 9, 0, var8, var11, 20);
               } else {
                  var1.setColor(var7[var9 + 2]);
                  if(var7[var9 + 4] == 0) {
                     var15 = this.font.charWidth('.');

                     for(var11 = 0; var11 < var7[var9 + 0]; var11 += var15) {
                        var1.drawChar('.', var8 + var11, 0 + super.screenY + var4, 20);
                     }

                     var8 += var7[var9 + 0];
                     continue;
                  }

                  if(var3 != var7[var9 + 1]) {
                     Font var14 = Font.getFont((var3 = var7[var9 + 1]) & 96, var3 & 7, var3 & 24);
                     var1.setFont(var14);
                  }

                  var1.drawSubstring(this.a, var7[var9 + 3], var7[var9 + 4], var8, 0 + super.screenY + var4, 20);
               }

               var8 += var7[var9 + 0];
            }
         }

         var4 += var7[2];
      }

      if(this.e != null) {
         var1.setClip(super.screenX, super.screenY + super.screenHeight + 1, super.screenWidth, super.p - 1);
         var1.setColor(4210752);
         var1.fillRect(super.screenX, super.screenY + super.screenHeight, super.screenWidth, super.p);
         var1.setFont(RootFrame.font);
         var1.setClip(super.screenX + 1, super.screenY + super.screenHeight, super.screenWidth - 2, super.p);
         int var13;
         if(this.g < super.screenWidth) {
            var6 = super.screenX + (super.screenWidth - this.g >> 1);
         } else if(this.b != 0) {
            var13 = 0;

            for(var8 = 0; var8 < this.b; ++var8) {
               var13 += RootFrame.font.stringWidth(this.e[var8]) + 4;
            }

            if(this.h + var13 < super.screenX + 10) {
               this.h = 12 - var13;
            } else if(this.h + var13 + RootFrame.font.stringWidth(this.e[this.b]) > super.screenX + super.screenWidth - 10) {
               this.h = super.screenWidth - 12 - var13 - RootFrame.font.stringWidth(this.e[this.b]);
            }

            var6 = super.screenX + 3 + this.h;
         } else {
            var6 = super.screenX + 3;
         }

         for(var13 = 0; var13 < this.e.length; ++var13) {
            var8 = RootFrame.font.stringWidth(this.e[var13]) + 1;
            int var12;
            if(this.b == var13) {
               var9 = 12686873;
               int var10000 = (this.i = !this.i)?16777215:0;
               var11 = var10000;
               var15 = var10000;
               var12 = 16645016;
            } else {
               var9 = 9137427;
               var15 = 11898650;
               var11 = 6311968;
               var12 = 16173378;
            }

            var1.setColor(var9);
            var1.fillRect(var6 - 1, super.screenY + super.screenHeight + 3, var8, RootFrame.fontHeight);
            var1.setColor(var15);
            var1.drawLine(var6 - 2, super.screenY + super.screenHeight + 2, var6 + var8 - 1, super.screenY + super.screenHeight + 2);
            var1.drawLine(var6 - 2, super.screenY + super.screenHeight + 2, var6 - 2, super.screenY + super.screenHeight + super.p - 2);
            var1.setColor(var11);
            var1.drawLine(var6 - 2, super.screenY + super.screenHeight + super.p - 2, var6 + var8 - 1, super.screenY + super.screenHeight + super.p - 2);
            var1.drawLine(var6 + var8 - 1, super.screenY + super.screenHeight + 2, var6 + var8 - 1, super.screenY + super.screenHeight + super.p - 2);
            var1.setColor(var12);
            var1.drawString(this.e[var13], var6, 0 + super.screenY + super.screenHeight + 3, 20);
            var6 += var8 + 3;
         }
      }

   }

   public final void a() {
      if(this.B < super.t) {
         super.frame.b((RootFrame)this);
         super.frame.d();
      }

      this.RepaintFrame();
   }

   public final boolean a(int var1, int var2) {
      if(var2 != 1 && var2 != 6) {
         if(this.e != null && (var2 == 2 || var2 == 5)) {
            this.b = (this.b + this.e.length + (var2 == 5?1:-1)) % this.e.length;
         } else {
            if(var2 != 8 && var2 != 3 && var2 != 4) {
               return false;
            }

            if(this.e != null && var2 != 4) {
               super.frame.b(this, this.b, this.e[this.b]);
            } else if(!this.c) {
               super.frame.b((RootFrame)this);
            }
         }
      } else {
         this.f = Math.max(0, Math.min(this.f + (var2 == 1?-1:1), (this.J - super.screenHeight + 1 + 10) / 10));
      }

      return true;
   }
}
