import java.io.DataInputStream;
import java.io.IOException;

public final class InputViewModel {

   public String InputName;
   public String b;
   public String c;
   public StringBuffer DefaultValue;
   public int InputMaxLength;
   public int f = -1;
   public int g;
   public int h;
   public Object[][] i;
   public int j = -1;
   public Object k;


   public InputViewModel(String var1, Object var2) {
      this.InputName = var1;
      this.k = var2;
   }

   public InputViewModel(String var1, String var2, int var3) {
      this.InputName = var1;
      this.DefaultValue = var2 == null?new StringBuffer():new StringBuffer(var2);
      this.InputMaxLength = var3;
   }

   public InputViewModel(String var1) {
      this.InputName = var1;
   }

   public InputViewModel(DataInputStream var1) throws IOException {
      this.InputName = Requests.ReadNCharsFromStream(var1, var1.readUnsignedByte());
      if(this.InputName == null) {
         this.InputName = "";
      }

      this.b = Requests.ReadNCharsFromStream(var1, var1.readUnsignedByte());
      this.f = var1.readByte();
      this.g = var1.readUnsignedByte();
      this.c = Requests.ReadNCharsFromStream(var1, var1.readUnsignedByte());
      int var2;
      if((var2 = var1.readUnsignedShort()) > 0) {
         this.k = new byte[var2];
         var1.readFully((byte[])((byte[])this.k));
      }

   }

   public InputViewModel(String var1, Object[][] var2) {
      this.InputName = var1;
      this.i = var2;
   }

   public final int a() {
      if(this.f >= 0) {
         this.h = 15 + a(this.InputName, true);
      } else if(this.InputMaxLength != 0) {
         this.h = 5 + Math.max(a(this.InputName, true), a(this.DefaultValue.toString(), true));
      } else if(this.i != null) {
         this.h = 5 + Math.max(this.j >= 0?a(this.i[this.j][0].toString(), true):0, a(this.InputName, true));
      } else {
         this.h = a(this.InputName, true);
      }

      if(this.c != null) {
         this.h = Math.max(this.h, a(this.c, true));
      }

      return this.h;
   }

   public static final int a(String var0, boolean var1) {
      int var2 = 0;
      int var3 = 0;
      int var4 = 0;
      boolean var5 = false;

      while(var4 < var0.length()) {
         char var6='a';//RANDOM
         while(var4 < var0.length() && (var6 = var0.charAt(var4)) > 11) {
            ++var4;
         }

         var2 += RootFrame.font.substringWidth(var0, var3, var4 - var3);
         var3 = var4;
         switch(var6) {
         case 4:
            if(var1) {
               var2 += 6;
            }
         case 1:
         case 2:
         case 3:
         case 7:
            ++var4;
            var3 = var4;
         case 5:
         case 8:
         case 9:
         case 10:
         default:
            break;
         case 6:
            var3 = var4 += 4;
            break;
         case 11:
            var2 += 10;
            var3 = var4 += 2;
            var5 = false;
         }
      }

      return var2;
   }

   public final String b() {
      return this.b == null?this.InputName:this.b;
   }

   public final boolean c() {
      return (this.g & 8) != 0;
   }

   public final String d() {
      return this.k == null?null:Requests.BytesToString((byte[])((byte[])this.k), 0, ((byte[])((byte[])this.k)).length);
   }
}
