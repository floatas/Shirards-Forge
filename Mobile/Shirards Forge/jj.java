import java.util.Hashtable;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class jj {

   public int a = 15;
   private final dd n;
   public final int b;
   public final byte[] c;
   public Image d;
   private byte[] o;
   private Hashtable p;
   private int q;
   private byte[] r;
   public int e;
   private int s;
   private long t;
   private long u;
   private boolean v;
   public boolean f;
   public long g;
   private boolean w;
   private short x;
   private short y;
   private int z;
   private int A;
   private long B;
   public int h;
   public int i;
   public int j;
   public int k;
   public int l;
   public int m;
   private int C;
   private int D;
   private String E;
   private int F;
   private long G;


   public jj(dd var1, int var2, byte[] var3) {
      this.e = this.a;
      this.s = -1;
      this.n = var1;
      this.b = var2;
      this.c = var3;
      this.h = var3[3] << 8;
      this.i = var3[4] << 8;
      if(var2 != 0 && var3[15] > 0) {
         this.j = 60;
      }

   }

   public final void a(Image var1, byte[] var2, Hashtable var3) {
      this.d = var1;
      this.o = var2;
      this.p = var3;
   }

   public final void a() {
      this.d = null;
      this.o = null;
      this.p = null;
   }

   public final void a(Graphics var1, int var2, int var3, long var4) {
      long var6 = this.g;
      Image var8 = this.d;
      if((this.e & 63) != 0 && var8 != null) {
         if(this.e != this.s) {
            this.r = (byte[])((byte[])this.p.get(new Integer(this.e & 127)));
            this.v = (this.e & 128) != 0;
            if(this.r == null) {
               this.r = (byte[])((byte[])this.p.get(new Integer(this.e & 63)));
            }

            if(this.r == null) {
               throw new RuntimeException("Unable to get anim " + (this.e & 63) + " for " + (this.b == -1?"AMMU":"monster id " + this.b));
            }

            this.q = 1;
            this.t = var4;
            this.u = (long)((this.r[this.q] & 255) << 4);
            this.s = this.e;
         }

         int var9 = this.r[this.q + 1] * 3 + this.q;
         int var10;
         if(this.v) {
            for(var10 = this.q + 2; var10 <= var9; var10 += 3) {
               RootFrame.drawSomeRegion(var1, var8, this.o, this.r[var10 + 2] & 255, var2 - this.r[var10], var3 + this.r[var10 + 1], 2, 20);
            }
         } else {
            for(var10 = this.q + 2; var10 <= var9; var10 += 3) {
               RootFrame.drawSomeRegion(var1, var8, this.o, this.r[var10 + 2] & 255, var2 + this.r[var10], var3 + this.r[var10 + 1], 0, 20);
            }
         }

         int var11;
         if(this.n.c && this.l > 0) {
            if(this.b > 0) {
               var10 = Math.max(var3 - 42, 10);
               var11 = ((this.l - this.m) * 14 + this.l - 1) / this.l;
               var1.setColor(0);
               var1.fillRect(var2 - 8, var10 - 1, 16, 3);
               if(var11 > 0) {
                  var1.setColor('\uff00');
                  var1.drawLine(var2 - 8 + 1, var10, var2 + var11 - 8, var10);
               }
            }

            if(var4 - this.G < 2500L) {
               var10 = Math.max(var3 - (int)(var4 - this.G >> 7) + 0 - 42, 10);
               var1.setColor(0);
               var1.drawString(this.E, var2, var10 + 1, 17);
               var1.drawString(this.E, var2, var10 - 1, 17);
               var1.drawString(this.E, var2 + 1, var10, 17);
               var1.drawString(this.E, var2 - 1, var10, 17);
               var1.setColor(this.F);
               var1.drawString(this.E, var2, var10, 17);
            }
         }

         if(this.w) {
            var11 = this.z * (int)(var4 - this.B) >> 6;
            boolean var12 = this.z == 0;
            this.h += var11;
            if(this.z < 0 && this.h <= this.x || this.z > 0 && this.h >= this.x || this.z == 0) {
               this.h = this.x;
               var12 = true;
            }

            var11 = this.A * (int)(var4 - this.B) >> 6;
            this.i += var11;
            if(this.A < 0 && this.i <= this.y || this.A > 0 && this.i >= this.y || this.A == 0) {
               this.i = this.y;
               if(var12) {
                  this.b();
                  var6 = var4;
                  this.w = false;
               }
            }

            this.B = var4;
         } else if(var6 > 0L && !this.f || this.r[0] <= 1) {
            if(var6 == 0L) {
               var6 = var4;
            }

            this.g = var6;
            return;
         }

         for(; var4 - this.t > this.u; this.u = (long)((this.r[this.q] & 255) << 4)) {
            this.t += this.u;
            if(2 + var9 >= this.r.length) {
               if(!this.w && !this.f) {
                  if(var6 == 0L) {
                     var6 = var4;
                  }
               } else {
                  this.q = 1;
               }
            } else {
               this.q = 2 + var9;
            }
         }

         this.g = var6;
      } else {
         if(var6 == 0L) {
            this.g = var4;
         }

      }
   }

   public final void b() {
      this.e = this.a;
      this.w = false;
      this.f = true;
      this.g = 1L;
   }

   public final void c() {
      this.e = this.a & 63 | this.s & 192;
      this.w = false;
      this.f = true;
      this.g = 1L;
   }

   public final void a(int var1, boolean var2) {
      this.e = var1;
      this.g = 0L;
      this.q = 1;
      this.t = this.n.t;
      this.f = var2;
   }

   public final void a(int var1, int var2, int var3) {
      this.e = var1;
      this.g = 0L;
      this.q = 1;
      this.t = this.B = this.n.t;
      this.f = this.w = true;
      this.x = (short)(var2 << 8);
      this.y = (short)(var3 << 8);
      int var4 = this.x - this.h;
      int var5 = this.y - this.i;
      int var6;
      if((var6 = a(var4 * var4 + var5 * var5)) != 0) {
         this.z = (var4 << 6) / var6;
         this.A = (var5 << 6) / var6;
         if(this.z == 0 && this.A == 0) {
            this.g = 1L;
         }
      }

   }

   public final jj d() {
      jj var1;
      (var1 = new jj(this.n, -1, new byte[19])).d = this.d;
      var1.o = this.o;
      var1.p = this.p;
      var1.h = this.h;
      var1.i = this.i;
      var1.a = 0;
      return var1;
   }

   private static final int a(int var0) {
      int var1 = 0;
      int var2 = var0 >= 65536?1073741824:16384;

      do {
         int var3 = var1 + var2;
         if(var0 >= var3) {
            var0 -= var3;
            var1 = var3 + var2;
         }

         var1 >>= 1;
      } while((var2 >>= 2) != 0);

      return var1;
   }

   public final int e() {
      return this.h + this.i;
   }

   public final void f() {
      this.C = this.h;
      this.D = this.i;
   }

   public final void g() {
      this.h = this.C;
      this.i = this.D;
   }

   public final void a(int var1, int var2) {
      if(this.b == 0 && var1 > 0) {
         RootFrame.vibrate(Display.getDisplay(Frame.Midlet), 300);
      }

      this.m = Math.min(this.l, this.m + var1);
      this.E = Integer.toString(var1);
      this.F = var2;
      this.G = System.currentTimeMillis();
   }

   public final boolean h() {
      return this.g > 0L && !this.w || this.w && this.i == this.y && this.h == this.x;
   }
}
