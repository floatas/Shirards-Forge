import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class MenuOptionHandling extends MenuFrame {

   private static final String[] e = new String[]{" 0", "1.,:;-_\'?!\"", "abc2", "def3", "ghi4", "jkl5", "mno6", "pqrs7", "tuv8", "wxyz9"};
   public static final int fontHeight = RootFrame.fontHeight;
   private int f = 0;
   public final Vector menuOptions;
   public int c;
   private int g;
   private int rootFontHeight;
   private boolean i;
   private boolean B;
   private int C;
   private int D;
   private int E;
   private long F;
   private int G;
   private int H;
   public static Image ItemsImage;


   public MenuOptionHandling(int menuId, String title, Vector options) {
      super(menuId, title);
      this.menuOptions = options;
      this.RenderMenus();
   }

   public MenuOptionHandling(int var1, String var2, Vector var3, int var4, int var5) {
      super(var1, var2, var4, var5);
      this.menuOptions = var3;
      this.RenderMenus();
   }

   public final void RenderMenus() {
      this.g = 0;
      this.rootFontHeight = fontHeight;
      boolean var1 = false;

      int var2;
      for(var2 = 0; var2 < this.menuOptions.size(); ++var2) {
         if(this.menuOptions.elementAt(var2) instanceof InputViewModel) {
            InputViewModel var3 = (InputViewModel)this.menuOptions.elementAt(var2);
            this.g = Math.max(this.g, var3.a());
            if(var3.c != null) {
               var1 = true;
            }

            if(var3.f >= 0) {
               this.rootFontHeight = 14;
            }
         } else {
            this.g = Math.max(this.g, InputViewModel.a(this.menuOptions.elementAt(var2).toString(), true));
         }
      }

      var2 = var1?Math.max(fontHeight, 9) + 2:0;
      this.d(this.menuOptions.size() * this.rootFontHeight + 2, var2);
      this.G = Math.min(this.menuOptions.size(), (super.screenHeight - 2) / this.rootFontHeight);
      this.H = this.menuOptions.size() > this.G?6:0;
      this.d(this.g + 2 + this.H);
      this.d(this.G * this.rootFontHeight + 2, var2);
      this.c = Math.min(this.c, this.menuOptions.size() - 1);
   }

   public final void RenderFrame(Graphics var1, boolean var2) {
      var1.setColor(0);
      var1.fillRect(super.screenX, super.screenY, super.screenWidth, super.screenHeight);
      var1.setColor(4210752);
      var1.fillRect(super.screenX, super.screenY + super.screenHeight, super.screenWidth, super.p);
      this.a(var1);
      if(this.H != 0) {
         this.f = Math.min(this.f, this.c - 1);
         this.f = Math.max(this.f, Math.max(this.c - this.G + 2, 0));
         this.f = Math.min(this.f, this.menuOptions.size() - this.G);
         RootFrame.drawRectsAndLines(var1, super.screenX + super.screenWidth - 6, super.screenY + 1, super.screenHeight - 2, this.f, this.G, this.menuOptions.size(), 11369747, 16173378);
      }

      int var3 = super.screenX + 1;
      int var4 = super.screenY + 1 + (this.rootFontHeight - fontHeight >> 1);
      int var5 = super.screenY + 1 + (this.rootFontHeight - 14 >> 1);
      int var6 = 0;

      for(int var7 = this.f; var7 < this.menuOptions.size() && var6 <= super.screenHeight - this.rootFontHeight; ++var7) {
         InputViewModel var8 = this.menuOptions.elementAt(var7) instanceof InputViewModel?(InputViewModel)this.menuOptions.elementAt(var7):null;
         if(var7 == this.c) {
            if(var8 != null && (var8.g & 48) != 0) {
               switch(var8.g & 48) {
               case 16:
                  var1.setColor(7171437);
                  break;
               case 32:
                  var1.setColor(13434880);
               }
            } else {
               var1.setColor(12686873);
            }

            if(var8 != null && var8.f >= 0) {
               var1.fillRect(var3 + 14, super.screenY + var6 + 1, super.screenWidth - this.H - 2 - 14, this.rootFontHeight);
            } else {
               var1.fillRect(var3, super.screenY + var6 + 1, super.screenWidth - this.H - 2, this.rootFontHeight);
            }

            var1.setColor(16645016);
         } else {
            var1.setColor(16173378);
         }

         if(var8 != null) {
            switch(var8.g & 48) {
            case 16:
               var1.setColor(13290186);
               break;
            case 32:
               var1.setColor(var7 == this.c?16759739:16740464);
            }

            int var10;
            int var11;
            if(var8.InputMaxLength == 0 && var8.i == null) {
               if(var8.f >= 0) {
                  Image var14 = ItemsImage;
                  if(ItemsImage == null) {
                     try {
                        ItemsImage = var14 = Image.createImage("/items.png");
                     } catch (IOException var12) {
                        ;
                     }
                  }

                  var10 = var8.f * 14 % var14.getWidth();
                  var11 = var8.f * 14 / var14.getWidth() * 14;
                  RootFrame.drawRegion(var1, var14, var10, var11, 14, 14, 0, var3, var5 + var6, 20);
                  this.a(var1, var8.InputName, var3 + 14 + 1, var4 + var6, this.g - 14 - 1, this.rootFontHeight);
               } else {
                  this.a(var1, var8.InputName, var3, var4 + var6, this.g, this.rootFontHeight);
               }
            } else {
               String var9;
               if(var8.InputMaxLength != 0) {
                  if((var7 != this.c || !this.B) && var8.DefaultValue.length() <= 0) {
                     var9 = var8.InputName;
                     var1.setColor(16645016);
                  } else {
                     var9 = var8.DefaultValue.toString();
                  }
               } else if(var8.j >= 0) {
                  var9 = var8.i[var8.j][0].toString();
               } else {
                  var9 = var8.InputName;
                  var1.setColor(16645016);
               }

               var10 = super.screenY + var6 + (this.rootFontHeight >> 1);
               var1.drawLine(var3, var10 - 3, var3, var10 + 3);
               var1.drawLine(var3 + 1, var10 - 2, var3 + 1, var10 + 2);
               var1.drawLine(var3 + 2, var10 - 1, var3 + 2, var10 + 1);
               var1.drawLine(var3 + 3, var10, var3 + 3, var10);
               var1.drawString(var9, var3 + 5, 0 + var4 + var6, 20);
               if(var7 == this.c && this.B && (System.currentTimeMillis() - this.F < 1000L || (this.i = !this.i))) {
                  var1.setColor(16777215);
                  var11 = var3 + 5 + RootFrame.font.substringWidth(var9, 0, this.E);
                  var1.fillRect(var11, var4 + var6, 2, fontHeight);
               }
            }
         } else {
            this.a(var1, this.menuOptions.elementAt(var7).toString(), var3, var4 + var6, this.g, this.rootFontHeight);
         }

         var6 += this.rootFontHeight;
      }

      String var13;
      if(super.p != 0 && this.menuOptions.elementAt(this.c) instanceof InputViewModel && (var13 = ((InputViewModel)this.menuOptions.elementAt(this.c)).c) != null) {
         var1.setColor(16645016);
         this.a(var1, var13, var3, super.screenY + super.screenHeight + 2, super.screenWidth - 2, this.rootFontHeight);
      }

   }

   public final void a(Graphics var1, String var2, int var3, int var4, int var5, int var6) {
      int var7 = var1.getColor();
      int var8 = 0;
      int var9 = 0;
      boolean var10 = false;

      while(var9 < var2.length()) {
         char var12 ='a';//RANDOM
         while(var9 < var2.length() && (var12 = var2.charAt(var9)) > 11) {
            ++var9;
         }

         var1.drawSubstring(var2, var8, var9 - var8, var3, 0 + var4, 20);
         var3 += RootFrame.font.substringWidth(var2, var8, var9 - var8);
         var8 = var9;
         switch(var12) {
         case 2:
         case 4:
            var3 += var5 - InputViewModel.a(var2, false);
         case 1:
            ++var9;
            var8 = var9;
            break;
         case 3:
            var3 += var5 - InputViewModel.a(var2, false) >> 1;
            ++var9;
            var8 = var9;
         case 5:
         case 8:
         case 9:
         case 10:
         default:
            break;
         case 6:
            var1.setColor((var2.charAt(var9 + 1) & 255) << 16 | (var2.charAt(var9 + 2) & 255) << 8 | var2.charAt(var9 + 3) & 255);
            var8 = var9 += 4;
            break;
         case 7:
            var1.setColor(var7);
            ++var9;
            var8 = var9;
            break;
         case 11:
            Image var11 = MenuFrame.Symbols;
            if(MenuFrame.Symbols == null) {
               MenuFrame.Symbols = var11 = super.frame.LoadImage("/symb.png");
            }

            ++var9;
            RootFrame.drawRegion(var1, var11, var2.charAt(var9) * 9, 0, 9, 9, 0, var3, var4 + (var6 - 9 >> 1), 20);
            var3 += 10;
            ++var9;
            var8 = var9;
            var10 = false;
         }
      }

   }

   public final void a() {
      this.RepaintFrame();
   }

   public final boolean a(int var1, int var2) {
      if(this.menuOptions.size() <= 0) {
         return false;
      } else {
         long var3 = System.currentTimeMillis();
         InputViewModel var5;
         if(this.B && (var1 >= 48 && var1 <= 57 || var1 == 42 || var1 == -7 || var1 == -8)) {
            var5 = (InputViewModel)this.menuOptions.elementAt(this.c);
            if(var1 != -7 && var1 != -8) {
               String var6 = var1 == 42?"@%&/()=+*":e[var1 - 48];
               if(this.C == var1 && var3 - this.F < 1000L) {
                  var5.DefaultValue.setCharAt(this.E - 1, var6.charAt(++this.D % var6.length()));
                  this.F = var3;
               } else {
                  this.D = 0;
                  if(var5.DefaultValue.length() < var5.InputMaxLength) {
                     var5.DefaultValue.insert(this.E++, var6.charAt(this.D % var6.length()));
                     this.C = var1;
                     this.F = var3;
                  }
               }

               var5.DefaultValue.setCharAt(0, Character.toUpperCase(var5.DefaultValue.charAt(0)));

               for(int var7 = 1; var7 < var5.DefaultValue.length(); ++var7) {
                  var5.DefaultValue.setCharAt(var7, Character.toLowerCase(var5.DefaultValue.charAt(var7)));
               }

               this.g = Math.max(this.g, var5.a());
               this.d(this.g + 2 + this.H);
               this.i = true;
            } else {
               if(this.E > 0) {
                  var5.DefaultValue.deleteCharAt(--this.E);
               }

               if(this.E == 0 && var5.DefaultValue.length() > 0) {
                  var5.DefaultValue.setCharAt(0, Character.toUpperCase(var5.DefaultValue.charAt(0)));
               }
            }
         } else if(this.B && var2 == 2 && this.E > 0) {
            this.F = 0L;
            --this.E;
         } else if(this.B && var2 == 5) {
            this.F = 0L;
            if(this.E < ((InputViewModel)this.menuOptions.elementAt(this.c)).DefaultValue.length()) {
               ++this.E;
            }
         } else if(var2 != 6 && var2 != 1) {
            if(var2 != 8 && var2 != 3) {
               return false;
            }

            if(this.menuOptions.elementAt(this.c) instanceof InputViewModel) {
               if(!(var5 = (InputViewModel)this.menuOptions.elementAt(this.c)).c()) {
                  if(var5.InputMaxLength != 0) {
                     if(this.B = !this.B) {
                        this.E = var5.DefaultValue.length();
                        super.v = 400;
                        super.frame.b();
                     } else {
                        super.v = Integer.MAX_VALUE;
                     }
                  } else if(var5.i != null) {
                     var5.j = (var5.j + 1) % var5.i.length;
                     this.g = Math.max(this.g, var5.a());
                     this.d(this.g + 2 + this.H);
                  } else {
                     super.frame.b(this, this.c, this.menuOptions.elementAt(this.c));
                  }
               }
            } else {
               super.frame.b(this, this.c, this.menuOptions.elementAt(this.c));
            }
         } else {
            this.c = (this.c + this.menuOptions.size() + (var2 == 6?1:-1)) % this.menuOptions.size();
            this.B = false;
            super.v = Integer.MAX_VALUE;
         }

         return true;
      }
   }

   public final InputViewModel a(int var1) {
      return (InputViewModel)this.menuOptions.elementAt(var1);
   }

   public final InputViewModel f() {
      return (InputViewModel)this.menuOptions.elementAt(this.c);
   }

   public final void a(Object var1) {
      if(var1 instanceof InputViewModel) {
         var1 = ((InputViewModel)var1).InputName;
      }

      for(int var2 = 0; var2 < this.menuOptions.size(); ++var2) {
         Object var3;
         if((var3 = this.menuOptions.elementAt(var2)) instanceof InputViewModel) {
            if(((InputViewModel)var3).InputName.equals(var1)) {
               this.menuOptions.removeElementAt(var2);
               this.RenderMenus();
               return;
            }
         } else if(var3.equals(var1)) {
            this.menuOptions.removeElementAt(var2);
            this.RenderMenus();
            return;
         }
      }

   }

}
