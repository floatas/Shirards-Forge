import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class dd extends RootFrame {

   private final byte[][] h;
   private final byte[] i;
   private final byte[][] j;
   private boolean k;
   private byte[] l;
   private final byte[] m;
   private final jj[] n;
   public jj a;
   private int o;
   private Requests p;
   private Hashtable q;
   private static final Random random;
   private static final String[] actionsInCombat;
   private static final String[] charStats;
   private static final String[] charStatsExplanation;
   public static final String[] itemTypesWithBackBtn;
   private Image B;
   private Image C;
   private byte[] D;
   private final int E;
   private final int F;
   private int G;
   private int H;
   private int I;
   private int J;
   private int K;
   private int L;
   private long M;
   private Image cPanelImage;
   private Image O;
   private long P;
   private int Q;
   private int R;
   private final Vector S;
   private boolean T;
   private boolean U;
   private long V;
   private int W;
   private int X;
   public boolean c;
   private int Y;
   private int Z;
   private int aa;
   private int ab;
   private jj ac;
   private final MenuOptionHandling[] ad;
   private final Stack ae;
   private static final Object[] af;
   private int ag;
   private int ah;
   private final Stack ai;
   private Stack aj;
   private int ak;
   private byte[] al;
   private Vector am;
   private Vector an;
   private int ao;
   private int ap;
   private String aq;
   private int ar;
   public jj d;
   private byte[] as;
   private int at;
   private Requests au;
   private Stack av;
   private boolean aw;
   private long ax;
   public byte[] e;
   public boolean f;
   private int ay;
   private int az;
   private byte[] aA;
   private int aB;
   private int aC;
   private int aD;
   private final Vector aE;
   private final Vector aF;
   public long g;
   private int aG;
   private byte[] aH;
   private byte[] aI;
   private MenuOptionHandling aJ;


   public dd(int var1, int var2, int var3, byte[][] var4, byte[][] var5, Requests var6, boolean var7) {
      super(var1);
      System.currentTimeMillis();
      this.S = new Vector();
      this.ad = new MenuOptionHandling[8];
      this.ae = new Stack();
      this.ai = new Stack();
      this.aj = new Stack();
      this.al = new byte[2];
      this.av = new Stack();
      this.f = true;
      this.aE = new Vector();
      this.aF = new Vector();
      this.E = var2;
      this.F = var3;
      this.h = var4;
      this.j = var5;
      this.k = var7;
      this.i = (byte[])((byte[])var6.dictionary.get(new Integer(21)));
      this.l = (byte[])((byte[])var6.dictionary.get(new Integer(25)));
      this.n = new jj[this.l.length];
      this.m = new byte[this.l.length];

      for(int var8 = 0; var8 < var5.length; ++var8) {
         byte[] var9;
         int var10 = (var9 = var5[var8])[0] & 255;
         if(var9[11] != 0 && this.l[var10] == 3) {
            this.l[var10] = 2;
         }
      }

      super.v = 50;
      this.G = var2 >> 1;
      this.H = (var3 >> 1) + 6;
      this.W = var6.GetFromDHashtableAsInt(26);
      this.X = var6.GetFromDHashtableAsInt(27);
      this.I = var6.GetFromDHashtableAsInt(22);
      this.J = var6.GetFromDHashtableAsInt(23) << 8;
      this.K = var6.GetFromDHashtableAsInt(24) << 8;
      this.ad[6] = new MenuOptionHandling(6, (String)null, ii.ArrayToVector(new Object[]{"End turn", "Try to flee"}), 9, 279);
      this.ad[7] = new MenuOptionHandling(7, "Target", ii.ArrayToVector(new Object[]{"Change target"}), 9, 279);
   }

   private void k() {
      this.T = false;
      Vector var1 = new Vector();
      if(this.p == null) {
         super.frame.ea = new Hashtable();
      }

      if(this.L != this.I) {
         this.Q = this.J + 128 >> 8;
         this.R = this.K + 128 >> 8;
      }

      if(!this.U) {
         Image var2 = super.frame.LoadImage("/70");
         byte[] var3 = super.frame.b(8, 0, var1);
         Hashtable var4 = super.frame.a(0, var1);
         byte[] var5;
         (var5 = new byte[19])[15] = 1;
         this.a = new jj(this, 0, var5);
         this.a.a(var2, var3, var4);
         this.n[0] = this.a;
         super.frame.e();
      } else {
         this.W = this.Q;
         this.X = this.R;
      }

      this.a.h = this.J;
      this.a.i = this.K;
      DataInputStream var18;
      int var19;
      byte[] var20;
      int var21;
      if((var18 = super.frame.ReadMapFile(2, this.I, var1)) != null) {
         try {
            var18.readByte();
            var18.readShort();
            this.aB = var18.readUnsignedShort();
            this.aC = var18.readUnsignedShort();
            this.aD = var18.readUnsignedShort();
            this.ay = var18.readShort();
            this.az = var18.readShort();
            this.aA = new byte[this.ay * this.az];
            var18.readFully(this.aA);
         } catch (Exception var15) {
            throw new RuntimeException("Unable to read map \'" + this.I + "\'");
         } finally {
            try {
               var18.close();
            } catch (Exception var14) {
               ;
            }

         }

         for(var19 = 0; var19 < this.h.length; ++var19) {
            var21 = (var20 = this.h[var19])[0] & 255;
            if(this.i[var21] == 3 && var20[12] == 6 && this.I == var20[14]) {
               this.b(var20);
            }
         }
      }

      this.q = super.frame.b(this.I, var1);
      this.B = super.frame.a(4, 0, var1);
      this.C = super.frame.a(5, 2, var1);
      this.D = super.frame.b(6, 2, var1);
      this.aF.removeAllElements();

      for(var19 = 0; var19 < this.j.length; ++var19) {
         var21 = (var20 = this.j[var19])[0] & 255;
         if(var20[1] == this.I && (this.l[var21] == 1 || this.l[var21] == 2 || var20[11] != 0)) {
            Image var6 = super.frame.a(7, var20[12], var1);
            byte[] var7 = super.frame.b(8, var20[12], var1);
            Hashtable var8 = super.frame.a(var20[12], var1);
            if(var6 != null && var7 != null && var8 != null) {
               if(this.n[var21] == null) {
                  this.n[var21] = new jj(this, var21, var20);
               }

               this.n[var21].a(var6, var7, var8);
               this.n[var21].e = this.n[var21].a = var20[13] & 255;
               if(var20[15] > 0) {
                  this.n[var21].a(14, true);
               } else {
                  this.n[var21].f = true;
               }

               if(this.l[var21] == 2) {
                  this.aF.addElement(this.n[var21]);
               }
            }
         } else if(this.n[var21] != null) {
            this.n[var21].a();
         }
      }

      if(var1.size() > 0) {
         if(this.p != null) {
            StringBuffer var24 = new StringBuffer("Unable to get resources from server:");

            for(int var25 = 0; var25 < var1.size(); ++var25) {
               var24.append("\n").append(Integer.toHexString(((Integer)var1.elementAt(var25)).shortValue()));
            }

            throw new RuntimeException(var24.toString());
         } else {
            ByteArrayOutputStream var22 = new ByteArrayOutputStream();
            DataOutputStream var23 = new DataOutputStream(var22);

            try {
               for(var21 = 0; var21 < var1.size(); ++var21) {
                  var23.writeShort(((Integer)var1.elementAt(var21)).shortValue());
               }
            } catch (IOException var17) {
               ;
            }

            this.p = new Requests(10, this);
            this.p.WriteByteArrayToRequest(1, var22.toByteArray());
            this.p.WriteHashtableToRequest(super.frame.da);
            this.p.WriteByteArrayToRequest(3, super.frame.c);
            if(this.U) {
               this.d(this.p);
            }

            super.frame.a((RootFrame)this, this.p);
         }
      } else {
         this.aE.removeAllElements();

         for(var19 = 0; var19 < this.h.length; ++var19) {
            if((var20 = this.h[var19])[1] == this.I || var20[1] == 0) {
               this.aE.addElement(var20);
            }
         }

         this.aF.addElement(this.a);
         if(this.a != null && this.a.d == null) {
            this.a.d = super.frame.LoadImage("/70");
         }

         this.M = super.t;
         this.p = null;
         this.U = true;
         this.T = true;
         this.L = this.I;
         this.b();
      }
   }

   public final void RenderFrame(Graphics var1, boolean var2) {
      var1.setFont(RootFrame.font);
      var1.setColor(0);
      var1.fillRect(0, 0, 240, 320);
      if(this.T && this.a.d != null) {
         this.a(var1, 0, 0, this.E, this.c?Math.min(279, this.F):this.F);
         var1.setClip(0, 0, 240, 320);
         int var4;
         if(!this.c && super.t - this.M < 4000L) {
            String var3 = this.b(this.aD);
            var4 = RootFrame.font.stringWidth(var3);
            var1.setColor(8610337);
            var1.fillRect(240 - var4 - 6 >> 1, 0, var4 + 6, RootFrame.fontHeight);
            var1.setColor(16173378);
            var1.drawString(var3, 120, 0, 17);
         }

         this.ap = (this.ap + 1) % 3;
         if(this.c) {
            if(this.O == null) {
               this.O = super.frame.LoadImage("/fpanel.png");
            }

            RootFrame.drawRegion(var1, this.O, 0, 0, 240, 9, 0, 0, 0, 20);
            var1.setColor(0);
            jj var10 = this.a;
            var4 = this.a.l > 0?var10.m * 109 / var10.l:109;
            var1.fillRect(119 - var4, 2, var4, 5);
            int var5 = this.ah * 109 / 720;
            var4 = this.ag * 109 / 720 + (this.ag + this.ah <= 720?var5:0);
            var1.fillRect(238 - var4, 2, var4, 5);
            if(this.ap != 0 && this.ah > 0 && this.ag + this.ah <= 720) {
               RootFrame.drawRegion(var1, this.O, 109 - var4, 50, var5, 5, 0, 238 - var4, 2, 20);
            }

            if(this.ab == 5) {
               var1.fillRect(0, 279, 240, 41);
               if(this.aq != null) {
                  var1.setColor(this.ar);
                  var1.drawString(this.aq, 1, 320 - (41 - RootFrame.fontHeight >> 1), 36);
               }
            } else {
               RootFrame.drawRegion(var1, this.O, 0, 9, 240, 41, 0, 0, 279, 20);
               if(this.aF.size() < 3) {
                  var1.fillRect(188, 298, 19, 19);
               }

               int var6 = Math.min(11, this.ae.size());

               for(int var7 = 0; var7 < var6; ++var7) {
                  Object[] var8 = (Object[])((Object[])this.ae.elementAt(var7));
                  RootFrame.drawRegion(var1, this.O, ((Integer)var8[0]).intValue() * 22 + 36, 30, 14, 15, 0, var7 * 16 + 34, 283, 20);
               }

               if(var2 && (this.ab == 4 || this.ab == 3)) {
                  var1.setColor(Math.abs(255 - ((int)super.t & 510)) << 8 | 16711680);
                  var1.drawRect(this.Y * 22 + 33, 298, 19, 19);
               }
            }

            if(this.ab == 3) {
               this.ad[this.Y].RenderFrame(var1, true);
            }

         } else {
            var1.setColor(0);
            if(this.cPanelImage == null) {
               this.cPanelImage = super.frame.LoadImage("/cpanel.png");
            }

            var1.drawImage(this.cPanelImage, 0, 320, 36);
            if(this.S.size() == 0 || this.ap == 0 || !var2) {
               var1.fillRect(37, 307, 17, 13);
            }

            int var9 = 122 * Requests.cPanelHeightOffset >> 8;
            var1.fillRect(59 + var9, 311, 122 - var9, 4);
            var4 = 300 - (RootFrame.fontHeight >> 1);
            var1.setColor(16777215);
            var1.drawString(Requests.cPanelFirstLine, 50, 0 + var4, 20);
            var1.drawString(Requests.cPanelSencondLine, 119, 0 + var4, 20);
            var1.drawString(Requests.cPanelThirdLine, 167, 0 + var4, 20);
         }
      } else {
         var1.setColor(16777215);
         var1.drawString("Please wait...", 120, 160, 65);
      }
   }

   public final void a(Graphics var1, int var2, int var3, int var4, int var5) {
      var1.setClip(var2, var3, var4, var5);
      int var6;
      int var7;
      if(this.c) {
         var6 = (this.Z << 8) + 128;
         var7 = (this.aa << 8) + 128;
      } else {
         var6 = this.a.h;
         var7 = this.a.i;
      }

      int var8 = this.G;
      int var9 = this.H;
      var1.setColor(0);
      var1.fillRect(var2, var3, var4, var5);
      int var10 = var8 - ((256 + (var6 & 255) - (var7 & 255)) * 16 >> 8);
      int var11 = 1 + var9 - ((256 + (var6 & 255) + (var7 & 255)) * 8 >> 8);
      int var12 = var6 >> 8;
      int var13 = var7 >> 8;
      int var14 = (var2 - var10 + 3152) / 32 - 99;
      int var15 = (var3 - var11 + 2) / 8 - 2;
      var10 += var14 * 32;
      int var16 = var11 += var15 * 8;

      int var17;
      int var18;
      int var19;
      int var20;
      int var21;
      for(var17 = var15; var16 < var3 + var5; ++var17) {
         var18 = var10 + (var17 % 2 != 0?16:0);
         var19 = var12 + var14 + (var17 + 1 >> 1);

         for(var20 = var13 - var14 + (var17 >> 1); var18 < var2 + var4; var18 += 32) {
            if((var19 | var20) >= 0 && var19 < this.ay && var20 < this.az && (var21 = this.aA[var20 * this.ay + var19] & 15) != 0) {
               var1.drawRegion(this.B, 0, (var21 - 1) * 15, 32, 15, 0, var18, var16, 20);
            }

            ++var19;
            --var20;
         }

         var16 += 8;
      }

      var17 = ((var6 - var7) * 16 >> 8) - var8;
      var18 = ((var6 + var7) * 8 >> 8) - var9;
      int var22;
      int var24;
      int var25;
      int var27;
      int var28;
      int var29;
      int var30;
      if(this.c) {
         var1.setClip(var2, var3, var4, var5);
         var1.setColor(0);
         var19 = (this.Z - this.aa) * 16 - var17 - 64;
         var20 = (this.Z + this.aa) * 8 - var18 + 40;

         for(var21 = 0; var21 < 9; ++var21) {
            if((var22 = 1 + var21) > 4) {
               var22 = Math.min(4, 10 - var22);
            }

            var1.drawLine(var19 + 16 * (-var22 + var21) - 1, var20 + 8 * (-var22 - var21), var19 + 16 * (var22 + var21), var20 + 8 * (var22 - var21));
            var1.drawLine(var19 + 16 * (-var22 + var21) - 1, var20 + 8 * (var22 + var21 - 8), var19 + 16 * (var22 + var21), var20 + 8 * (-var22 + var21 - 8));
         }

         if(this.ab == 0) {
            var1.setColor(Math.abs(255 - ((int)super.t & 510)) << 8);
         } else {
            var1.setColor('\uff00');
         }

         var21 = this.ac.h >>> 8;
         var22 = this.ac.i >>> 8;
         int var23 = (var21 - var22) * 16 - var17;
         var24 = (var21 + var22) * 8 - var18 - 8;
         var1.drawLine(var23 - 1, var24, var23 + 16, var24 + 8);
         var1.drawLine(var23 + 16, var24 + 8, var23 - 1, var24 + 16);
         var1.drawLine(var23 - 1, var24 + 16, var23 - 16 - 1, var24 + 8);
         var1.drawLine(var23 - 16 - 1, var24 + 8, var23, var24);
         if(this.ab == 2) {
            var1.setColor(Math.abs(255 - ((int)super.t & 510)) << 8 | 255);
            var1.fillRect((this.al[0] - this.al[1]) * 16 - var17 - 8, (this.al[0] + this.al[1]) * 8 - var18 - 4, 16, 8);
         } else if(this.ab == 1) {
            var1.setColor(Math.abs(255 - ((int)super.t & 510)) << 8 | 255);

            for(var25 = this.aj.size() - 1; var25 >= 0; --var25) {
               byte[] var26;
               var27 = (var26 = (byte[])((byte[])this.aj.elementAt(var25)))[0] & 255;
               var28 = var26[1] & 255;
               var1.fillRect((var27 - var28) * 16 - var17 - 8, (var27 + var28) * 8 - var18 - 4, 16, 8);
               var1.setColor(6316287);
            }
         } else {
            var1.setColor(255);

            for(var25 = 0; var25 < this.ai.size(); ++var25) {
               Vector var36 = (Vector)this.ai.elementAt(var25);

               for(var27 = 0; var27 < var36.size(); ++var27) {
                  byte[] var38;
                  var29 = (var38 = (byte[])((byte[])var36.elementAt(var27)))[0] & 255;
                  var30 = var38[1] & 255;
                  var1.fillRect((var29 - var30) * 16 - var17 - 8, (var29 + var30) * 8 - var18 - 4, 16, 8);
               }
            }
         }
      }

      jj var35;
      int var37;
      if((var19 = this.aF.size()) > 0) {
         var20 = 0;
         var21 = Integer.MIN_VALUE;

         label134:
         for(boolean var32 = false; !var32; var32 = true) {
            jj var33;
            for(var24 = (var33 = (jj)this.aF.elementAt(var20)).e(); var20 < var19; var21 = var37) {
               var35 = var33;
               var37 = var24;
               ++var20;
               if(var20 < var19 && (var24 = (var33 = (jj)this.aF.elementAt(var20)).e()) < var37) {
                  this.aF.setElementAt(var33, var20 - 1);
                  this.aF.setElementAt(var35, var20);
                  var33 = var35;
                  var27 = var24;
                  var24 = var37;
                  var37 = var27;
               }

               if(var37 < var21) {
                  break label134;
               }
            }
         }
      }

      var10 += 16;
      var16 = var11 + 8;
      var20 = 0;
      jj var31;
      if(var19 == 0) {
         var31 = null;
         var22 = Integer.MAX_VALUE;
      } else {
         var22 = ((var31 = (jj)this.aF.elementAt(0)).e() * 8 >> 8) - var18;
      }

      long var34 = System.currentTimeMillis();

      for(var25 = var15; var16 < var3 + var5 + 32; ++var25) {
         while(var22 < var16) {
            var37 = var31.b;
            var1.setClip(var2, var3, var4, var5);
            var27 = ((this.n[var37].h - this.n[var37].i) * 16 >> 8) - var17;
            this.n[var37].a(var1, var27, var22, var34);
            ++var20;
            if(var20 < var19) {
               var22 = ((var31 = (jj)this.aF.elementAt(var20)).e() * 8 >> 8) - var18;
            } else {
               var22 = Integer.MAX_VALUE;
            }
         }

         var37 = var10 + (var25 % 2 != 0?16:0);
         var27 = var12 + var14 + (var25 + 1 >> 1);

         for(var28 = var13 - var14 + (var25 >> 1); var37 < var2 + var4 + 32; var37 += 32) {
            if((var27 | var28) >= 0 && var27 < this.ay && var28 < this.az && (var29 = (this.aA[var28 * this.ay + var27] & 240) >> 4) >= 2) {
               var30 = var29 - 2 << 3;
               var1.drawRegion(this.C, this.D[var30 + 2] << 8 | this.D[var30 + 2 + 1] & 255, this.D[var30 + 4] << 8 | this.D[var30 + 4 + 1] & 255, this.D[var30 + 0], this.D[var30 + 1], 0, var37 + this.D[var30 + 6], var16 + this.D[var30 + 7], 20);
            }

            ++var27;
            --var28;
         }

         var16 += 8;
      }

      var35 = this.d;
      if(this.d != null && var35.b == -1) {
         var1.setClip(var2, var3, var4, var5);
         var35.a(var1, ((var35.h - var35.i) * 16 >> 8) - var17, ((var35.h + var35.i) * 8 >> 8) - var18, var34);
      }

   }

   public final void b() {
      this.S.removeAllElements();
      if(!this.c && this.I == this.L && !this.k) {
         int var1;
         int var3;
         int var4;
         label101:
         for(var1 = 0; var1 < this.aE.size(); ++var1) {
            byte[] var2 = (byte[])((byte[])this.aE.elementAt(var1));
            if(this.i[var2[0] & 255] == 2 && (var2[1] == this.L || var2[1] == 0) && (var2[4] == 0 || (var2[2] & 255) <= this.Q && (var2[3] & 255) <= this.R && (var2[4] & 255) > this.Q && (var2[5] & 255) > this.R)) {
               var3 = 14 + (var2[13] & 255);

               for(var4 = 1; var4 < var2[var3]; ++var4) {
                  if(var2[var3 + 1] == 0 && this.i[var2[var3 + 1 + var4] & 255] != 3 || var2[var3 + 1] == 1 && this.l[var2[var3 + 1 + var4] & 255] != 3) {
                     continue label101;
                  }
               }

               switch(var2[6]) {
               case 0:
                  if(this.a((Object)var2)) {
                     var1 = -1;
                  }
                  break;
               case 1:
                  this.S.addElement(var2);
               }
            }
         }

         while(!this.av.empty()) {
            Requests var7 = (Requests)this.av.pop();
            this.d(var7);
            super.frame.a((RootFrame)this, var7);
         }

         for(var1 = 0; var1 < this.aF.size(); ++var1) {
            jj var8;
            if((var8 = (jj)this.aF.elementAt(var1)).b != 0) {
               var3 = Math.abs(this.a.h - var8.h) >> 8;
               var4 = Math.abs(this.a.i - var8.i) >> 8;
               byte var5 = var8.c[9];
               byte var6 = var8.c[10];
               if(var5 > 0 && var3 < var5 && var4 < var5 && !this.aw && this.a(this.a, var8)) {
                  this.a(this.a(var8.c[5], var8.c[6]), new byte[]{(byte)var8.b});
                  return;
               }

               if(var6 > 0 && var3 < var6 && var4 < var6 && this.a(this.a, var8)) {
                  this.S.addElement(var8);
               }
            }
         }

      }
   }

   private boolean a(jj var1, jj var2) {
      int var3 = var1.h + 128 >> 8;
      int var4 = var1.i + 128 >> 8;
      int var5 = var2.h + 128 >> 8;
      int var6 = var2.i + 128 >> 8;

      do {
         if(var3 == var5 && var4 == var6) {
            return true;
         }

         if(Math.abs(var3 - var5) > Math.abs(var4 - var6)) {
            if(var3 > var5) {
               --var3;
            } else {
               ++var3;
            }
         } else if(var4 > var6) {
            --var4;
         } else {
            ++var4;
         }
      } while(this.b(var3, var4));

      return false;
   }

   public final void a(String var1, byte[] var2) {
      if(!this.aw) {
         this.aw = true;
         this.e = var2;
         hh var3;
         (var3 = new hh(36, (String)null, var1, new String[]{"Auto fight", "Manual"})).c = true;
         if(!this.f) {
            var3.b = 1;
         }

         super.frame.ShowMessageBox(this, var3, false);
      }
   }

   public final void a(byte[] var1, boolean var2) {
      Requests var3 = new Requests(12, this);
      if(var1.length > 0) {
         this.d(var3);
      }

      var3.WriteByteArrayToRequest(1, var1);
      byte[] var4 = new byte[this.aF.size() * 3];

      for(int var5 = 0; var5 < this.aF.size(); ++var5) {
         jj var6 = (jj)this.aF.elementAt(var5);
         if(this.l[var6.b] == 1) {
            this.l[var6.b] = 2;
         }

         int var7 = var5 * 3;
         var4[var7] = (byte)var6.b;
         var4[var7 + 1] = (byte)(var6.h + 128 >> 8);
         var4[var7 + 2] = (byte)(var6.i + 128 >> 8);
      }

      var3.WriteByteArrayToRequest(2, var4);
      if(var2) {
         var3.WriteNumberToRequest(3, 1);
         var3.i = true;
      }

      super.frame.a((RootFrame)this, var3);
   }

   public final void a(Requests var1) {
      for(int var2 = 0; var2 < 6; ++var2) {
         Vector var3;
         if((var3 = var1.GetFromDHashtableValueAsVector(30 + var2)).isEmpty()) {
            var3.addElement("Empty");
         }

         this.ad[var2] = new MenuOptionHandling(var2, actionsInCombat[var2], var3, 9, 279);
      }

      this.Z = var1.GetFromDHashtableAsInt(20);
      this.aa = var1.GetFromDHashtableAsInt(21);
      this.an = new Vector();
      this.aF.removeAllElements();
      Vector var6 = var1.GetFromDHashtableValueAsVector(22);

      for(int var7 = 0; var7 < var6.size(); ++var7) {
         byte[] var4 = (byte[])((byte[])var6.elementAt(var7));
         jj var5;
         if((var5 = this.n[var4[0] & 255]) == null) {
            throw new RuntimeException("Mob " + (var4[0] & 255) + " dead");
         }

         this.aF.addElement(var5);
         this.an.addElement(var5);
         var5.f();
         var5.h = var4[1] << 8;
         var5.i = var4[2] << 8;
         var5.l = var4[3] << 8 | var4[4] & 255;
         var5.m = var4[5] << 8 | var4[6] & 255;
         var5.a = var4[7] & 255;
      }

      this.ac = (jj)this.aF.elementAt(1);
      this.c = true;
      this.l();
      this.k = false;
   }

   private void l() {
      this.ab = 4;
      this.ag = 0;
      this.ad[6].c = this.ad[7].c = 0;
      this.as = null;
      this.ae.removeAllElements();
      this.Y = 0;
      this.ax = System.currentTimeMillis();
      this.aq = null;
      this.o();
   }

   public final void b(Requests var1) {
      switch(var1.c) {
      case 1:
         RootFrame.vibrate(Display.getDisplay(Frame.Midlet), 600);
         super.frame.ShowMessageBox(this, new hh(30, (String)null, "New turn starts", (String[])null, 3000), false);
         this.l();
         return;
      case 2:
         super.frame.ShowMessageBox(this, new hh(30, (String)null, "Congratulations, you won the fight!", (String[])null, 3000), false);
         String var2;
         if((var2 = (String)var1.dictionary.get(new Integer(24))) != null) {
            super.frame.ShowMessageBox(this, new hh(30, "Battle report", var2), false);
         }

         this.c(var1);
         this.l = (byte[])((byte[])var1.dictionary.get(new Integer(23)));
         this.a(var1, (byte[])((byte[])var1.dictionary.get(new Integer(28))));
         return;
      case 3:
         super.frame.ShowMessageBox(this, new hh(30, (String)null, "You lost the fight!\n\nLuckily someone found you and brought you back to the start of this adventure so that you can continue your quest."), false);
         this.I = var1.GetFromDHashtableAsInt(25);
         this.J = (this.Q = var1.GetFromDHashtableAsInt(26)) << 8;
         this.K = (this.R = var1.GetFromDHashtableAsInt(27)) << 8;
         this.T = false;
         this.a(var1, (byte[])null);
         return;
      case 4:
         super.frame.ShowMessageBox(this, new hh(30, (String)null, "You flee"), false);
         this.a(var1, (byte[])null);
         this.a.h = (short)((this.Q = this.W) << 8);
         this.a.i = (short)((this.R = this.X) << 8);
      default:
      }
   }

   public final void a(Requests var1, byte[] var2) {
      int var3;
      if(var2 != null) {
         for(var3 = 0; var3 < var2.length; ++var3) {
            int var4 = var2[var3] & 255;
            this.c(var4);
            this.aF.removeElement(this.n[var4]);
         }
      }

      byte[] var9;
      if(!var1.i) {
         this.ae.removeAllElements();

         for(var3 = 0; var3 < this.an.size(); ++var3) {
            ((jj)this.an.elementAt(var3)).g();
         }

         this.aF.removeAllElements();

         for(var3 = 0; var3 < this.j.length; ++var3) {
            int var5 = (var9 = this.j[var3])[0] & 255;
            if(var9[1] == this.L && this.l[var5] == 2) {
               this.aF.addElement(this.n[var5]);
            }
         }

         this.aF.addElement(this.a);
         this.a.a = 15;
         this.c = false;
         this.ac = null;
         this.am = null;

         for(var3 = 0; var3 < 6; ++var3) {
            this.ad[var3] = null;
         }
      }

      if(var2 != null) {
         for(var3 = 0; var3 < this.h.length; ++var3) {
            byte var10;
            if(((var10 = (var9 = this.h[var3])[6]) == 2 || var10 == 3) && this.i[var9[0] & 255] == 2) {
               int var6 = var9[8] & 255;

               for(int var7 = 0; var7 < var2.length; ++var7) {
                  int var8 = var2[var7] & 255;
                  if(var10 == 2 && var8 == var6 || var10 == 3 && this.n[var8] != null && this.n[var8].c[2] == var6) {
                     this.a((Object)var9);
                  }
               }
            }
         }
      }

      this.an = null;
      this.aw = false;
      this.c(var1);
      this.au = null;
      Frame.gcCollect(true);
   }

   public final boolean a(Object var1) {
      if(var1 instanceof jj) {
         if(!this.aw) {
            jj var11 = (jj)var1;
            this.a(this.a(var11.c[7], var11.c[8]), new byte[]{(byte)var11.b});
         }

         return false;
      } else {
         byte[] var2;
         boolean var3;
         int var4;
         int var7;
         int var8;
         int var9;
         int var13;
         var2 = (byte[])((byte[])var1);
         var3 = false;
         var4 = var2[13] & 255;
         label146:
         switch(var2[12]) {
         case 1:
            super.frame.ShowMessageBox(this, new hh(30, (String)null, this.a(var2[14], var2[15])), false);
            this.n();
            break;
         case 2:
            if(this.aw) {
               return false;
            }

            byte[] var5 = new byte[var4];
            System.arraycopy(var2, 14, var5, 0, var5.length);
            this.a("Entering fight", var5);
            var13 = 0;

            while(true) {
               if(var13 >= var4) {
                  break label146;
               }

               byte var14 = var2[14 + var13];

               for(var8 = 0; var8 < this.aF.size(); ++var8) {
                  jj var16;
                  if((var16 = (jj)this.aF.elementAt(var8)).b == var14) {
                     super.frame.ShowMessageBox(this, new hh(30, (String)null, this.a(var16.c[5], var16.c[6])), false);
                     break label146;
                  }
               }

               ++var13;
            }
         case 3:
            Requests var6;
            (var6 = new Requests(11, this)).WriteNumberToRequest(1, var2[0] & 255);
            this.av.push(var6);
            break;
         case 4:
            this.J = (short)((this.Q = var2[15] & 255) << 8);
            this.K = (short)((this.R = var2[16] & 255) << 8);
            if(var2[14] != this.L) {
               this.I = var2[14];
               this.T = false;
            } else {
               this.a.h = this.J;
               this.a.i = this.K;
            }
            break;
         case 5:
            var7 = 0;

            while(true) {
               if(var7 >= var4) {
                  break label146;
               }

               var8 = var2[14 + var7] & 255;

               for(var9 = 0; var9 < this.j.length; ++var9) {
                  byte[] var15;
                  if((var15 = this.j[var9])[0] == var8) {
                     this.a(var15);
                     break;
                  }
               }

               ++var7;
            }
         case 6:
            if(this.L == (var2[14] & 255)) {
               this.b(var2);
            }
            break;
         case 7:
            for(var7 = 0; var7 < var4; ++var7) {
               var8 = var2[14 + var7] & 255;
               this.c(var8);

               for(var9 = 0; var9 < this.aF.size(); ++var9) {
                  jj var10;
                  if((var10 = (jj)this.aF.elementAt(var9)) != null && var10.b == var8) {
                     this.aF.removeElementAt(var9);
                     break;
                  }
               }
            }
         case 8:
         case 9:
         default:
            break;
         case 10:
            this.a.d = null;
            super.frame.ShowMessageBox(this, new MenuOptionHandling(11, "Pick interest", ii.ArrayToVector((Object[])itemTypesWithBackBtn)), false);
            break;
         case 11:
            this.av.push(new Requests(17, this));
         }

         int var12 = 14 + var4 + 1 + var2[14 + var4];

         for(var13 = 1; var13 <= var2[var12]; ++var13) {
            var7 = var2[var12 + var13] & 255;
            if(this.i[var7] == 1) {
               this.i[var7] = 2;
               var3 = true;
            }
         }

         var13 = var12 + var2[var12] + 1;

         for(var7 = 1; var7 <= var2[var13]; ++var7) {
            var8 = var2[var13 + var7] & 255;
            if(this.i[var8] == 2) {
               this.i[var8] = 3;
               if(var8 != var2[0]) {
                  for(var9 = 0; var9 < this.h.length; ++var9) {
                     if(this.h[var9][0] == var8 && this.h[var9][12] == 6) {
                        this.a((Object)this.h[var9]);
                     }
                  }
               }

               var3 = true;
            }
         }

         var7 = var13 + var2[var13] + 1;

         for(var8 = 1; var8 <= var2[var7]; ++var8) {
            var9 = var2[var7 + var8] & 255;
            if(this.i[var9] != 3) {
               this.i[var9] = 4;
            }
         }

         return var3;
      }
   }

   private void a(byte[] var1) {
      int var2 = var1[0] & 255;
      this.l[var2] = 2;
      jj var3 = this.n[var2];
      if(var1[1] == this.L && var3 != null && !this.aF.contains(var3)) {
         this.aF.addElement(var3);
      }

   }

   private void c(int var1) {
      for(int var2 = 0; var2 < this.j.length; ++var2) {
         byte[] var3;
         if((var3 = this.j[var2])[0] == var1) {
            this.l[var1] = 3;
            this.m[var1] = var3[11];
            return;
         }
      }

   }

   private void b(byte[] var1) {
      for(int var2 = 0; var2 < var1[18]; ++var2) {
         System.arraycopy(var1, var2 * var1[17] + 14 + 5, this.aA, (var1[16] + var2) * this.ay + var1[15], var1[17]);
      }

   }

   public final void a() {
      if(!this.T) {
         this.n();
         if(this.p == null || !this.p.thread.isAlive()) {
            this.k();
         }

      } else {
         super.frame.d();
         super.frame.serviceRepaints();
         if(this.k) {
            this.a(new byte[0], false);
         } else {
            int var4;
            jj var5;
            if(this.c) {
               int var1;
               if(this.ab != 5 && this.ab != 6 && (var1 = (int)((super.t - this.ax) / 1000L)) != 0) {
                  long var2 = (super.t - this.ax) % 1000L;
                  this.ax = super.t - var2;
                  this.ag += var1 * 5;
                  if(this.ag > 720) {
                     this.j();
                     this.ag = 720;
                  }
               }

               int var17;
               if(this.ab == 3 && this.Y < 6) {
                  Vector var15 = this.ad[this.Y].menuOptions;

                  for(var17 = 0; var17 < var15.size(); ++var17) {
                     InputViewModel var18;
                     byte[] var20 = (byte[])((byte[])(var18 = (InputViewModel)var15.elementAt(var17)).k);
                     if(this.ag + ((var20[0] & 255) << 8 | var20[1] & 255) > 720) {
                        var18.g |= 24;
                     } else {
                        var18.g &= -25;
                     }
                  }
               } else if(this.ab == 5 && (this.d == null || this.d.h() && this.d.g + (long)this.at < System.currentTimeMillis())) {
                  if(this.ao < this.am.size()) {
                     if(this.as != null && this.as[2] == 1) {
                        this.o();
                     }

                     this.as = (byte[])((byte[])this.am.elementAt(this.ao));
                     this.at = (this.as[1] & 255) << 4;
                     this.ar = this.as[3] == 0?'\uff00':16719904;
                     this.aq = Requests.BytesToString(this.as, 5, this.as[4]);
                     var1 = 5 + this.as[4];
                     var17 = this.as[var1] & 255;
                     int var3 = this.as[var1 + 1] & 255;
                     label211:
                     switch(this.as[0]) {
                     case 0:
                        var4 = 0;

                        while(true) {
                           if(var4 >= this.as[var1] << 1) {
                              break label211;
                           }

                           this.n[this.as[var1 + 1 + var4] & 255].a = this.as[var1 + 2 + var4] & 255;
                           var4 += 2;
                        }
                     case 1:
                        this.d = this.n[var17];
                        this.d.a(var3, false);
                        var4 = 0;

                        while(true) {
                           if(var4 >= this.as[var1 + 2]) {
                              break label211;
                           }

                           var5 = this.n[this.as[var1 + 3 + var4 * 3] & 255];

                           try {
                              var5.a(this.as[var1 + 4 + var4 * 3] << 8 | this.as[var1 + 5 + var4 * 3] & 255, this.ar);
                           } catch (Exception var14) {
                              ;
                           }

                           ++var4;
                        }
                     case 2:
                        if(this.n[var17] != null) {
                           this.d = this.n[var17];
                           this.d.a(this.as[var1 + 3] & 255, var3, this.as[var1 + 2] & 255);
                        }
                        break;
                     case 3:
                        if(this.n[var17] != null) {
                           this.d = this.n[var17].d();
                           this.d.a(this.as[var1 + 3] & 255, var3, this.as[var1 + 2] & 255);
                        }
                        break;
                     case 4:
                        this.n[var17].h = (short)(this.as[var1 + 1] << 8);
                        this.n[var17].i = (short)(this.as[var1 + 2] << 8);
                     case 5:
                     default:
                        break;
                     case 6:
                        for(var4 = 0; var4 < this.as[var1]; ++var4) {
                           if((var5 = this.n[this.as[var1 + 1 + var4] & 255]).b != 0) {
                              this.aF.removeElement(var5);
                           }

                           if(var5.b == this.ac.b && this.aF.size() > 1) {
                              this.m();
                           }
                        }
                     }

                     ++this.ao;
                  } else {
                     this.d = null;
                     this.b(this.au);
                  }
               }
            } else {
               long var16 = super.t - this.P;
               boolean var19 = super.t - this.V > 1000L;

               for(var4 = 0; var4 < this.aF.size(); ++var4) {
                  byte[] var6;
                  if((var6 = (var5 = (jj)this.aF.elementAt(var4)).c)[15] != 0) {
                     int var7 = var5.b;
                     if((var5.j | var5.k) != 0) {
                        short var8 = (short)(var5.h + a((int)((long)var5.j * var16 >> 7), -256, 256));
                        short var9 = (short)(var5.i + a((int)((long)var5.k * var16 >> 7), -256, 256));
                        int var10 = var8 + 128 >> 8;
                        int var11 = var9 + 128 >> 8;
                        int var12;
                        if((var7 == 0 || (random.nextInt() & 15) != 0) && this.b(var10, var11) && (var6[18] == 0 || (var6[16] & 255) <= var10 && (var6[17] & 255) <= var11 && (var6[18] & 255) > var10 && (var6[19] & 255) > var11)) {
                           var5.h = var8;
                           var5.i = var9;
                           if(var7 == 0) {
                              this.Q = var10;
                              this.R = var11;
                              var12 = this.R * this.ay + this.Q;
                              if(this.o != var12) {
                                 var19 = true;
                                 this.o = var12;
                              }
                           }
                        } else if(var7 == 0) {
                           if(var5.j != 0) {
                              var5.h = (short)((var5.h - 128 & -256) + (var5.j > 0?383:128));
                           } else if(var5.k != 0) {
                              var5.i = (short)((var5.i - 128 & -256) + (var5.k > 0?383:128));
                           }
                        } else {
                           var12 = random.nextInt() > 0?60:-60;
                           short var13 = 14;
                           if(random.nextInt() > 0) {
                              var5.j = var12;
                              var5.k = 0;
                              if(var12 > 0) {
                                 var13 = 206;
                              }
                           } else {
                              var5.j = 0;
                              var5.k = var12;
                              if(var12 > 0) {
                                 var13 = 78;
                              } else {
                                 var13 = 142;
                              }
                           }

                           var5.a(var13, true);
                        }
                     }
                  }
               }

               if(var19) {
                  this.b();
                  this.V = super.t;
               }

               if(super.t - this.g > 10000L) {
                  this.g = super.t;

                  for(var4 = 0; var4 < this.j.length; ++var4) {
                     byte[] var21 = this.j[var4];
                     if(this.m[var21[0]] != 0 && --this.m[var21[0]] == 0) {
                        this.a(var21);
                     }
                  }
               }
            }

            this.P = super.t;
            this.RepaintFrame();
         }
      }
   }

   private void m() {
      this.ac = (jj)this.aF.elementAt((this.aF.indexOf(this.ac) + 1) % this.aF.size());
      if(this.ac.b == 0) {
         this.m();
      }

   }

   private void n() {
      if(this.a != null) {
         this.a.j = this.a.k = 0;
         this.a.c();
      }

   }

   private void o() {
      for(int var1 = 0; var1 < this.n.length; ++var1) {
         if(this.n[var1] != null) {
            this.n[var1].b();
         }
      }

   }

   public final void c(Requests var1) {
      this.aG = var1.GetFromDHashtableAsInt(21);
      if(this.aG != 0) {
         this.aH = (byte[])((byte[])var1.dictionary.get(new Integer(22)));
         this.aI = new byte[6];
         this.a(-1);
      }
   }

   public final void a(int var1) {
      Vector var2 = new Vector();

      for(int var3 = 0; var3 < 6; ++var3) {
         int var4 = this.aH[var3] < 48?3:(this.aH[var3] < 78?2:1);
         if(var1 == var3) {
            --this.aG;
            ++this.aI[var3];
            this.aH[var3] = (byte)(this.aH[var3] + var4);
         }

         InputViewModel var5;
         (var5 = new InputViewModel(String.valueOf('\u000b') + (char)var3 + this.aH[var3] + charStats[var3])).c = "Click to add: " + var4 + "" + '\u000b' + (char)var3;
         var5.k = charStatsExplanation[var3];
         var2.addElement(var5);
      }

      if(this.aG <= 0) {
         this.f();
      } else {
         var2.addElement("Save points for later");
         MenuOptionHandling var6 = new MenuOptionHandling(14, "Raises left: " + this.aG, var2);
         if(this.aJ != null) {
            var6.c = this.aJ.c;
            super.frame.a((RootFrame)this.aJ, (RootFrame)var6);
         } else {
            super.frame.ShowMessageBox(this, var6, false);
         }

         this.aJ = var6;
      }
   }

   public final void f() {
      super.frame.b((RootFrame)this.aJ);
      this.aJ = null;
      Requests var1;
      (var1 = new Requests(14, this)).WriteByteArrayToRequest(1, this.aI);
      super.frame.a((RootFrame)this, var1);
   }

   private boolean b(int var1, int var2) {
      byte var3 = (var1 | var2) >= 0 && var1 < this.ay && var2 < this.az?this.aA[var2 * this.ay + var1]:0;
      return (this.aB >> (var3 & 15) & this.aC >> ((var3 & 240) >> 4) & 1) == 1 ^ (var3 & 240) == 16;
   }

   public final boolean a(int var1, int var2) {
      if(!this.T) {
         return false;
      } else {
         switch(var1) {
         case 49:
            var2 = 2;
         case 50:
         case 52:
         case 53:
         case 54:
         case 56:
         default:
            break;
         case 51:
            var2 = 1;
            break;
         case 55:
            var2 = 6;
            break;
         case 57:
            var2 = 5;
         }

         byte[] var5;
         Object var15;
         if(this.c) {
            label202:
            switch(this.ab) {
            case 0:
               switch(var2) {
               case 1:
               case 2:
               case 5:
               case 6:
                  this.m();
                  break label202;
               case 3:
               case 4:
               case 8:
                  this.ab = 4;
               case 7:
               default:
                  break label202;
               }
            case 1:
            case 2:
               var5 = new byte[2];
               System.arraycopy(this.al, 0, var5, 0, 2);
               switch(var2) {
               case 1:
                  --var5[1];
                  break;
               case 2:
                  --var5[0];
                  break;
               case 3:
               case 8:
                  var15 = this.ad[this.Y].menuOptions.elementAt(this.ad[this.Y].c);
                  this.ag += this.ah;
                  if(this.ab == 2) {
                     if((this.d(var5[0] & 255, var5[1] & 255) & 15) == 0) {
                        break label202;
                     }

                     this.ae.push(new Object[]{new Integer(this.Y), var15, new Integer(this.ag), var5});
                  } else {
                     this.ai.addElement(this.aj);
                     byte[] var7;
                     (var7 = new byte[(this.aj.size() << 1) - 1])[0] = (byte)(this.aj.size() - 1);

                     for(int var8 = 1; var8 < this.aj.size(); ++var8) {
                        System.arraycopy(this.aj.elementAt(var8), 0, var7, (var8 << 1) - 1, 2);
                     }

                     this.ae.push(new Object[]{new Integer(this.Y), var15, new Integer(this.ag), var7});
                  }

                  this.ah = 0;
               case 4:
                  this.ab = 4;
                  this.ah = 0;
                  break label202;
               case 5:
                  ++var5[0];
                  break;
               case 6:
                  ++var5[1];
                  break;
               case 7:
               default:
                  break label202;
               }

               int var16 = (var5[0] & 255) - this.Z;
               int var17 = (var5[1] & 255) - this.aa;
               if(Math.abs(var16) + Math.abs(var17) + (var16 <= 0?1:0) + (var17 <= 0?1:0) < 6 && (this.ab == 2 || this.b(var5[0] & 255, var5[1] & 255))) {
                  if(this.ab == 1) {
                     label214: {
                        if(this.aj.size() > 1) {
                           byte[] var18 = (byte[])((byte[])this.aj.elementAt(this.aj.size() - 2));
                           if(var5[0] == var18[0] && var5[1] == var18[1]) {
                              this.ah -= this.ak;
                              this.aj.pop();
                              this.al = var5;
                              break label214;
                           }
                        }

                        if(this.ag + this.ah + this.ak >= 720) {
                           break;
                        }

                        this.aj.addElement(var5);
                        this.ah += this.ak;
                     }
                  }

                  this.al = var5;
               }
               break;
            case 3:
            case 4:
               label215: {
                  Object var3;
                  switch(var2) {
                  case -10:
                     this.j();
                     break;
                  case -9:
                  case -7:
                  case -6:
                  case -5:
                  case -4:
                  case -3:
                  case -2:
                  case -1:
                  case 0:
                  case 7:
                  default:
                     return false;
                  case -8:
                  case 4:
                     if(this.ab == 4 && !this.ae.isEmpty() && (var3 = this.ae.pop()) != af) {
                        byte[] var11;
                        int var14 = (var11 = (byte[])((byte[])((InputViewModel)((Object[])((Object[])var3))[1]).k))[0] << 8 | var11[1] & 255;
                        if(var11[2] == 1 && !this.ai.isEmpty()) {
                           var14 *= ((Stack)this.ai.pop()).size() - 1;
                        }

                        this.ag -= var14;
                     }

                     this.ab = 4;
                     break;
                  case 1:
                  case 6:
                     if(this.ab == 3) {
                        this.ad[this.Y].a(var1, var2);
                     }

                     this.ab = 3;
                     break;
                  case 2:
                     --this.Y;
                     break;
                  case 3:
                  case 8:
                     if(this.ab == 3) {
                        if((var3 = this.ad[this.Y].menuOptions.elementAt(this.ad[this.Y].c)) instanceof InputViewModel) {
                           InputViewModel var4;
                           if(!(var4 = (InputViewModel)var3).c()) {
                              var5 = (byte[])((byte[])var4.k);
                              this.ak = (var5[0] & 255) << 8 | var5[1] & 255;
                              if(var5[2] == 2 || var5[2] == 1) {
                                 this.al = new byte[2];
                                 this.al[0] = (byte)(this.a.h >> 8);
                                 this.al[1] = (byte)(this.a.i >> 8);
                                 if(var5[2] == 1) {
                                    Stack var6;
                                    if(!this.ai.isEmpty() && !(var6 = (Stack)this.ai.peek()).isEmpty()) {
                                       System.arraycopy(var6.peek(), 0, this.al, 0, 2);
                                    }

                                    this.ah = 0;
                                 }

                                 this.aj = new Stack();
                                 this.aj.push(this.al);
                                 this.ab = var5[2];
                                 break label215;
                              }

                              if(this.ag + this.ak <= 720) {
                                 this.ag += this.ak;
                                 this.ae.push(new Object[]{new Integer(this.Y), var3, new Integer(this.ag), new byte[]{(byte)this.ac.b}});
                              }
                           } else {
                              super.frame.ShowMessageBox(this, new hh(30, (String)null, "Not enough time.\nYou can free up time by removing last selection with right screen key"), false);
                           }
                        } else if(var3 == "Try to flee") {
                           super.frame.ShowMessageBox(this, new hh(35, "Confirmation", "Try to flee", new String[]{"Cancel", "Continue"}), false);
                        } else if(var3 == "End turn") {
                           this.j();
                        } else if(var3 == "Change target") {
                           this.ab = 0;
                           break;
                        }

                        this.ab = 4;
                     } else {
                        this.ab = 3;
                     }
                     break;
                  case 5:
                     ++this.Y;
                  }

                  int var9 = this.aF.size() < 3?7:8;
                  this.Y = (this.Y + var9) % var9;
                  Object var12 = this.ad[this.Y].menuOptions.elementAt(this.ad[this.Y].c);
                  if(this.ab == 3 && var12 instanceof InputViewModel) {
                     var5 = (byte[])((byte[])((InputViewModel)var12).k);
                     this.ah = var5[0] << 8 | var5[1] & 255;
                  } else {
                     this.ah = 0;
                  }
               }
            }
         } else {
            switch(var2) {
            case 1:
               this.a.k = -45;
               this.a.a(142, true);
               break;
            case 2:
               this.a.j = -45;
               this.a.a(14, true);
               break;
            case 3:
            case 8:
               if(this.S.size() != 0) {
                  Vector var10 = new Vector();

                  for(int var13 = 0; var13 < this.S.size(); ++var13) {
                     if((var15 = this.S.elementAt(var13)) instanceof jj) {
                        var5 = ((jj)var15).c;
                     } else {
                        var5 = (byte[])((byte[])var15);
                     }

                     var10.addElement(new InputViewModel(this.a(var5[7], var5[8]), var15));
                  }

                  var10.addElement("Cancel");
                  super.frame.ShowMessageBox(this, new MenuOptionHandling(32, "Choose action", var10), false);
                  this.n();
               }
               break;
            case 4:
            case 7:
            default:
               return false;
            case 5:
               this.a.j = 45;
               this.a.a(206, true);
               break;
            case 6:
               this.a.k = 45;
               this.a.a(78, true);
            }
         }

         super.frame.b();
         return true;
      }
   }

   public final void g() {
      this.ae.removeAllElements();
      this.ae.push(af);
      this.ag = 0;
      this.j();
      this.ab = 4;
   }

   public final void ResetNCount(int var1, int var2) {
      if(this.T) {
         switch(var1) {
         case 49:
            var2 = 2;
         case 50:
         case 52:
         case 53:
         case 54:
         case 56:
         default:
            break;
         case 51:
            var2 = 1;
            break;
         case 55:
            var2 = 6;
            break;
         case 57:
            var2 = 5;
         }

         switch(var2) {
         case 1:
         case 6:
            this.a.k = 0;
            break;
         case 2:
         case 5:
            this.a.j = 0;
         case 3:
         case 4:
         }

         if((this.a.j | this.a.k) == 0) {
            this.a.c();
         }

      }
   }

   private byte d(int var1, int var2) {
      return (var1 | var2) >= 0 && var1 < this.ay && var2 < this.az?this.aA[var2 * this.ay + var1]:0;
   }

   public final void d() {
      this.n();
   }

   public final void c() {
      this.M = System.currentTimeMillis();
      if(this.T) {
         this.b();
      }

      if(this.T && this.a != null && this.a.d == null) {
         this.a.d = super.frame.LoadImage("/70");
      }

   }

   public final String h() {
      Vector var1 = new Vector();
      Vector var2 = new Vector();
      StringBuffer var3 = new StringBuffer("\t\tMain quests:\t\b");
      StringBuffer var4 = new StringBuffer("\f\t\tOptional quests:\t\b");

      for(int var5 = 0; var5 < this.h.length; ++var5) {
         byte[] var6 = this.h[var5];
         if(this.i[var6[0] & 255] == 2) {
            Integer var7 = new Integer((var6[9] & 255) << 8 | var6[10] & 255);
            if(var6[11] == 1) {
               if(!var1.contains(var7)) {
                  var1.addElement(var7);
                  var3.append("\n\n").append(this.a(var6[9], var6[10]));
               }
            } else if(var6[11] == 2 && !var2.contains(var7)) {
               var2.addElement(var7);
               var4.append("\n\n").append(this.a(var6[9], var6[10]));
            }
         }
      }

      if(var1.isEmpty()) {
         var3.append("\n\nNone");
      }

      if(var2.isEmpty()) {
         var4.append("\n\nNone");
      }

      return var3.append(var4).toString();
   }

   public final Vector i() {
      Vector var1 = new Vector();

      label38:
      for(int var2 = 0; var2 < this.h.length; ++var2) {
         byte[] var3;
         if((var3 = this.h[var2])[12] == 8 && this.i[var3[0] & 255] == 3) {
            try {
               InputViewModel var4 = new InputViewModel(new DataInputStream(new ByteArrayInputStream(var3, 14, var3[13])));

               for(int var5 = 0; var5 < this.h.length; ++var5) {
                  if(this.h[var5][12] == 9 && this.i[this.h[var5][0] & 255] == 3 && Requests.BytesToString(this.h[var5], 14, this.h[var5][13]).equals(var4.b)) {
                     continue label38;
                  }
               }

               var4.InputName = this.b(Integer.parseInt(var4.InputName));
               var1.addElement(var4);
            } catch (IOException var7) {
               ;
            }
         }
      }

      return var1;
   }

   public final String a(byte var1, byte var2) {
      return this.b((var1 & 255) << 8 | var2 & 255);
   }

   public final String b(int var1) {
      Integer var2 = new Integer(var1);
      String var3;
      return (var3 = (String)super.frame.f.get(var2)) != null?var3:(this.q == null?"map texts null":((var3 = (String)this.q.get(var2)) != null?var3:"WTT #" + var1));
   }

   public final void d(Requests var1) {
      var1.WriteByteArrayToRequest(10, this.i);
      var1.WriteNumberToRequest(11, this.I);
      var1.WriteNumberToRequest(12, this.Q);
      var1.WriteNumberToRequest(13, this.R);
      var1.WriteByteArrayToRequest(14, this.l);
      var1.WriteNumberToRequest(15, this.W);
      var1.WriteNumberToRequest(16, this.X);
   }

   private static final int a(int var0, int var1, int var2) {
      return var0 < var1?var1:(var0 > var2?var2:var0);
   }

   public final void j() {
      synchronized(this) {
         if(this.ab == 6 || this.ab == 5) {
            return;
         }
      }

      this.ab = 6;
      Requests var1 = new Requests(13, this);
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      DataOutputStream var3 = new DataOutputStream(var2);

      for(int var4 = 0; var4 < this.ae.size(); ++var4) {
         Object[] var5 = (Object[])((Object[])this.ae.elementAt(var4));

         try {
            if(var5 == af) {
               var2.write((byte[])((byte[])var5[1]));
            } else {
               InputViewModel var6;
               byte[] var7 = (var6 = (InputViewModel)var5[1]).b.getBytes();
               var3.write(var7.length);
               var3.write(var7);
               var3.writeShort(((Integer)var5[2]).intValue());
               var3.write(((byte[])((byte[])var6.k))[2]);
               var3.write((byte[])((byte[])var5[3]));
            }

            var1.WriteByteArrayToRequest(1, var2.toByteArray());
         } catch (IOException var8) {
            ;
         }

         var2.reset();
      }

      this.ai.removeAllElements();
      super.frame.a((RootFrame)this, var1);
   }

   public final void e(Requests var1) {
      this.am = var1.GetFromDHashtableValueAsVector(20);
      this.ao = 0;

      for(int var2 = 0; var2 < this.am.size(); ++var2) {
         this.am.elementAt(var2);
         Object var3 = null;
      }

      this.ab = 5;
      this.au = var1;
   }

   static {
      char[] var10000 = new char[]{'?', '-', '+', 'C', 'D'};
      var10000 = new char[]{'?', '-', '+', 'D'};
      random = new Random();
      actionsInCombat = new String[]{"Movement", "Melee attack", "Ranged attack", "Defence", "Offence spells", "Defensive spells"};
      charStats = new String[]{" Strength", " Intelligence", " Dexterity", " Toughness", " Willpower", " Speed"};
      charStatsExplanation = new String[]{"Strength gives:\n+weapon damage\n+carry more weight\n+heavier weapons", "Intelligence gives:\n+effect to all spells cast on foe", "Dexterity gives:\n+hit chance\n+defense\n+complex weapon", "Toughness gives:\n+life points", "Willpower gives:\n+spell resistance\n+protect spells cheaper prices", "Speed makes U:\n+Faster when using skills and spells in fight"};
      itemTypesWithBackBtn = new String[]{"Melee weapons", "Missile weapons", "Armours", "Skills", "Spells", "Back"};
      af = new Object[]{new Integer(6), new byte[]{(byte)1, (byte)102, (byte)0, (byte)0}};
   }
}
