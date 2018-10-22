import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotFoundException;

public final class ii extends Frame {

   private Image g;
   private boolean h;
   private static final long i;
   private String j;
   private Player k;
   private VolumeControl l;
   private int m = 0;


   public ii(MIDlet var1) {
      super(var1, new RootFrame(0, 0, '\uc350'));
             System.out.println("ii ctor");

      String var2;
      if((var2 = System.getProperty("microedition.profiles")) != null && var2.indexOf("MIDP-2.0") >= 0) {
         this.f();
      }

      this.g = this.LoadImage("/splash.png");
      this.g();
      this.b("/avalonv5.mid");
   }

   private void f() {
      this.setFullScreenMode(true);
   }

   public final void a(Graphics var1, RootFrame var2) {
      RootFrame var3;
      if((var3 = this.c()) != var2 && var3 instanceof dd) {
         try {
            var3.RenderFrame(var1, false);
         } catch (Exception var5) {
            ;
         }
      } else {
         Image var4 = this.g;
         var1.setColor(0);
         var1.fillRect(0, 0, 240, 320);
         if(var4 != null) {
            var1.drawImage(var4, 0, 0, 20);
         }
      }

      var2.RenderFrame(var1, true);
   }

   public final void a(int var1, int var2, RootFrame var3) throws Exception {
      switch(var3.webController) {
      case 0:
         this.h = false;
         boolean var4 = false;
         this.a(var3, false);
         return;
      case 10:
         if(var2 == 4) {
            ((dd)var3).a.d = null;
            this.MenuOptions(var3, 8, false);
            return;
         }
      default:
         if(var2 == 4) {
            this.b(var3);
         } else {
            this.repaint();
         }
      }
   }

   private void a(RootFrame var1, boolean var2) {
      synchronized(this) {
         if(this.g == null) {
            return;
         }

         this.g = null;
      }

      Frame.gcCollect(false);
      byte[] var4 = null;
      RecordStore var5 = null;

      try {
         var4 = (var5 = RecordStore.openRecordStore("key", false)).getRecord(1);
      } catch (RecordStoreNotFoundException var18) {
         ;
      } catch (RecordStoreException var19) {
         ;
      } finally {
         try {
            var5.closeRecordStore();
         } catch (Exception var17) {
            ;
         }

      }

      Requests var3 = new Requests(1, var1);
      if(var4 != null && !var2) {
         var3.WriteByteArrayToRequest(1, var4);
      }

      var3.WriteStringToRequest(2, "1.1.0");
      var3.WriteNumberToRequest(3, 1);
      var3.WriteNumberToRequest(4, 4);
      var3.WriteNumberToRequest(5, 2);
      var3.WriteStringToRequest(6, "s700");
      String var6;
      if((var6 = System.getProperty("microedition.platform")) != null) {
         var3.WriteStringToRequest(7, var6);
      }

      String var7;
      if((var7 = Frame.Midlet.getAppProperty("INFO")) != null) {
         var3.WriteStringToRequest(8, var7);
      }

      this.a(var1, var3);
   }

   public final void MenuOptions(RootFrame var1, int var2, Object var3) throws IOException {
      if(var3 != "Back" && (var3 != "Cancel" || var1.webController == 23)) {
         MenuOptionHandling var4 = var1 instanceof MenuOptionHandling?(MenuOptionHandling)var1:null;
         InputViewModel var5 = var3 instanceof InputViewModel?(InputViewModel)var3:null;
         Requests var6 = null;
         boolean var7 = false;
         RootFrame var10;
         MenuOptionHandling var12;
         RootFrame var15;
         dd var16;
         switch(var1.webController) {
         case 2://LOGIN - register
            this.MenuOptions(var1, var2 == 0?4:3, false);
            break;
         case 3:
         case 4:
            (var6 = new Requests(var1.webController == 3?2:3, var1)).WriteStringToRequest(2, var4.a(0).DefaultValue.toString());
            var6.WriteStringToRequest(3, var4.a(1).DefaultValue.toString());
            break;
         case 5:
            if(var3 == "Create hero") {
               this.MenuOptions(var1, 6, false);
            } else if(var3 == "Delete hero") {
               Vector var17;
               (var17 = new Vector()).addElement("Cancel");

               for(int var18 = 0; var18 < var4.menuOptions.size(); ++var18) {
                  Object var22;
                  if((var22 = var4.menuOptions.elementAt(var18)) instanceof InputViewModel) {
                     var17.addElement(((InputViewModel)var22).InputName);
                  }
               }

               String[] var19 = new String[var17.size()];
               var17.copyInto(var19);
               this.ShowMessageBox(var1, new hh(7, "Confirmation", "Select the hero you want to delete below.\nAction can not be undone!", var19), false);
            } else {
               (var6 = new Requests(4, var1)).WriteStringToRequest(1, var5.b());
            }
            break;
         case 6:
            InputViewModel var8;
            if((var8 = var4.a(1)).j == -1) {
               this.ShowMessageBox(var1, new hh(29, "Error", "Choose gender"), false);
               return;
            }

            (var6 = new Requests(5, var1)).WriteStringToRequest(1, var4.a(0).DefaultValue.toString());
            var6.WriteNumberToRequest(2, ((Integer)var8.i[var8.j][1]).intValue());
            break;
         case 7:
            if(var3 != "Cancel") {
               (var6 = new Requests(6, var1)).WriteStringToRequest(1, (String)var3);
               ((MenuOptionHandling)this.c(var1)).menuOptions.addElement("Create hero");
               ((MenuOptionHandling)this.c(var1)).a(var3);
            }

            this.b(var1);
            break;
         case 8:
            RootFrame var9 = this.c();
            if(var3 != "Start game" && var3 != "Resume game") {
               if(var3 == "Hero info") {
                  Vector var21 = ArrayToVector((Object[])(new String[]{"Equipped items", "Spells", "Skills", "Hero sheet", "Back"}));
                  if(var9 instanceof dd) {
                     var21.insertElementAt("Quest items", 3);
                  }

                  this.ShowMessageBox(var1, new MenuOptionHandling(9, "Hero info", var21), false);
               } else if(var3 == "Quest log") {
                  if(var9 instanceof dd) {
                     this.ShowMessageBox(var1, new hh(30, "Quest log", ((dd)var9).h()), false);
                  }
               } else if(var3 == "Save & exit") {
                  this.ShowMessageBox(var1, new hh(34, "Confirmation", "Save & exit game?", new String[]{"Save & exit", "Cancel"}), false);
               } else if(var3 == "Credits") {
                  this.ShowMessageBox(var1, new hh(30, "Credits", "ÿÿÿProducer:\nHenrik Riis\nÿÿÿLead client code:\nLars Engholm Johansen\nÿÿÿLead server code:\nBrian Thorsbro\nÿÿÿStory line:\nThomas Laurfelt\nÿÿÿArtwork:\nRobert Friis\nRiana Miller\nÿÿÿQuality assurance:\nMikkel Meisler\nÿÿÿTools & portings:\nMorten Nobel-Jørgensen\nLars Engholm Johansen\nÿÿÿMusic:\nMario Barbosa\n\nÿÿÿbrought to you:\nby pascha                                               "), false);
               } else if(var3 == "Score info") {
                  (var6 = new Requests(20, var1)).WriteNumberToRequest(1, 2);
               } else if(var3 == "Rankings") {
                  (var6 = new Requests(26, var1)).WriteNumberToRequest(1, 1);
               } else if(var3 == "More games") {
                  (var6 = new Requests(7, var1)).WriteNumberToRequest(1, 6660);
               } else if(var3 == "Help") {
                  var6 = new Requests(27, var1);
               } else if(var3.toString().startsWith("Sound")) {
                  this.m = (this.m + 25) % 125;
                  this.a(this.m);
                  this.h();
                  var4.menuOptions.setElementAt("Sound: " + this.m + "%", var2);
                  var4.RenderMenus();
               }
            } else if(!this.h) {
               Frame.gcCollect(true);
               (var6 = new Requests(8, var1)).WriteNumberToRequest(1, 2);
               var6.WriteHashtableToRequest(super.da);
               var6.WriteByteArrayToRequest(3, super.c);
            } else {
               this.b(var1);
            }
            break;
         case 9:
            if(var3 == "Hero sheet") {
               (var6 = new Requests(20, var1)).WriteNumberToRequest(1, 1);
            } else if(var3 == "Equipped items") {
               var6 = new Requests(21, var1);
            } else if(var3 == "Quest items") {
               if((var10 = this.c()) instanceof dd) {
                  Vector var23;
                  (var23 = ((dd)var10).i()).addElement("Back");
                  this.ShowMessageBox(var1, new MenuOptionHandling(22, "Quest items", var23), false);
               }
            } else if(var3 == "Skills" || var3 == "Spells") {
               (var6 = new Requests(25, var1)).WriteNumberToRequest(1, var3 == "Skills"?9:13);
            }
         case 10:
         case 25:
         case 26:
         case 27:
         case 28:
         case 30:
         case 31:
         case 33:
         default:
            break;
         case 11:
            (var6 = new Requests(15, var1)).WriteNumberToRequest(1, var2);
            break;
         case 12:
            this.ShowMessageBox(var1, new hh(13, var5.InputName, var5.d(), (var5.g & 32) == 0?new String[]{"Back", "Buy"}:null), false);
            break;
         case 13:
            MenuOptionHandling var24 = (MenuOptionHandling)this.c(var1);
            (var6 = new Requests(16, var1)).WriteStringToRequest(1, var24.f().b);
            break;
         case 14:
            var10 = this.c();
            if(var2 == 6) {
               if(var10 instanceof dd) {
                  ((dd)var10).f();
               }
            } else {
               this.ShowMessageBox(var1, new hh(15, "Confirmation", (String)var5.k, new String[]{"Add", "Cancel"}), false);
            }
            break;
         case 15:
            var10 = this.c(var1);
            RootFrame var20 = this.c();
            if(var10 instanceof MenuOptionHandling && var20 instanceof dd) {
               this.b(var1);
               ((dd)var20).a(((MenuOptionHandling)var10).c);
            }
            break;
         case 16:
            (var6 = new Requests(22, var1)).WriteStringToRequest(1, ((InputViewModel)var3).b);
            break;
         case 17:
            if(var2 == 0) {
               (var6 = new Requests(23, var1)).WriteStringToRequest(1, var5.b);
               (var12 = (MenuOptionHandling)this.c(var1)).f().InputName = "";
               var12.f().c = "Nothing equipped";
            } else {
               this.ShowMessageBox(var1, new hh(18, var5.InputName, var5.d(), new String[]{"Back", "Equip", "Delete"}), false);
            }
            break;
         case 18:
         case 20:
            if(var3 == "Equip") {
               var12 = (MenuOptionHandling)this.c(var1);
               (var6 = new Requests(23, var12)).WriteStringToRequest(1, var12.f().b);
            } else if(var3 == "Delete") {
               this.ShowMessageBox(var1, new hh(21, "Confirmation", "Are you sure you want to delete this?\nAction can not be undone!", new String[]{"Cancel", "Delete"}), false);
               break;
            }

            var1 = this.b(var1);
            break;
         case 19:
            this.ShowMessageBox(var1, new hh(20, var5.InputName, var5.d(), new String[]{"Back", "Delete"}), false);
            break;
         case 21:
            InputViewModel var13 = (var12 = (MenuOptionHandling)this.c(this.c(var1))).f();
            InputViewModel var25;
            if(var12.webController == 17 && (var25 = ((MenuOptionHandling)this.c(var12)).f()).InputName.equals(var13.InputName)) {
               var25.InputName = "";
               var25.c = "Nothing equipped";
            }

            (var6 = new Requests(24, var1)).WriteStringToRequest(1, var13.b);
            var12.menuOptions.removeElementAt(var12.c);
            var12.RenderMenus();
            this.b(this.b(var1));
            var1 = null;
            break;
         case 22:
            this.ShowMessageBox(var1, new hh(30, var5.InputName, ((dd)this.c()).b(Integer.parseInt(var5.d()))), false);
            break;
         case 23:
            var6 = new Requests(18, var1);
            if(var3 == "Balance") {
               var6.WriteNumberToRequest(1, 1);
            }

            this.b(var1);
            break;
         case 24:
            if(var3 == "Accept") {
               var6 = new Requests(19, var1);
            } else {
               this.ShowMessageBox(var1, new RootFrame(33, 1), false);
            }
            break;
         case 29:
            if(var3 == "Icon overview 2") {
               (var6 = new Requests(7, var1)).WriteNumberToRequest(1, 6659);
            } else if(var3 == "Icon overview 1") {
               var6 = new Requests(28, var1);
            } else {
               String var14;
               if((var14 = var5.d()) != null) {
                  this.ShowMessageBox(var1, new hh(30, var5.InputName, var14), false);
               }
            }
            break;
         case 32:
            if((var10 = this.c()) instanceof dd) {
               this.b(var1);
               dd var11;
               (var11 = (dd)var10).a(var5.k);
               var11.b();
            }
            break;
         case 34:
            if(var3 != "Cancel") {
               if(this.c() instanceof dd) {
                  var6 = new Requests(9, var1);
                  ((dd)this.c()).d(var6);
               }

               var7 = true;
            } else {
               this.b(var1);
            }
            break;
         case 35:
            if(var3 == "Continue" && (var15 = this.c()) instanceof dd) {
               this.b(var1);
               this.d();
               (var16 = (dd)var15).g();
            }
            break;
         case 36:
            if((var15 = this.c()) instanceof dd) {
               (var16 = (dd)var15).a(var16.e, var16.f = var2 == 0);
            }

            this.b(var1);
            this.d();
         }

         if(var6 != null) {
            this.a(var1, var6);
         }

         if(var7) {
            this.ShowMessageBox(var1, new RootFrame(33, 1), false);
         }

      } else {
         this.b(var1);
      }
   }

   private void MenuOptions(RootFrame var1, int var2, boolean var3) {
      switch(var2) {
      case 3:
      case 4:
         this.ShowMessageBox(var1, 
                 new MenuOptionHandling(var2, var2 == 3?"Please login":"Please register", 
                         ArrayToVector(
                                 new Object[]{
                                     new InputViewModel("Type login", (String)null, 14), 
                                     new InputViewModel("Type password", (String)null, 14), 
                                     "Continue", 
                                     "Back"}))
                 
                 , var3);
         return;
      case 6:
         this.ShowMessageBox(var1, new MenuOptionHandling(var2, "Create hero", ArrayToVector(new Object[]{new InputViewModel("Hero name", (String)null, 14), new InputViewModel("Choose gender", new Object[][]{{"Male", new Integer(1)}, {"Female", new Integer(2)}}), "Create hero", "Cancel"})), var3);
         return;
      case 8:
         Vector var4 = ArrayToVector(new Object[]{"Hero info", "Score info", "Rankings", "Help", "More games", "Credits", "Save & exit"});
         if(var1 instanceof dd) {
            var4.insertElementAt("Resume game", 0);
            var4.insertElementAt("Quest log", 4);
         } else {
            var4.insertElementAt("Start game", 0);
         }

         var4.insertElementAt("Sound: " + this.m + "%", var4.size() - 1);
         this.ShowMessageBox(var1, new MenuOptionHandling(var2, "Main menu", var4), var3);
      case 5:
      case 7:
      default:
      }
   }

   public final void a(RootFrame var1, Requests var2, Throwable var3) throws Exception {
      if(var3 != null) {
         this.a("net(" + var2.webController + ")", var1, "Network error", var3, false);
      } else {
         Object var4 = null;
         Vector var5;
         Vector var6;
         MenuOptionHandling var7;
         MenuOptionHandling var17;
         switch(var2.webController) {
         case 1:
         case 2:
         case 19:
            byte[] var16;
            if((var2.c == 1 || var2.c == 5) && (var16 = (byte[])((byte[])var2.dictionary.get(new Integer(21)))) != null) {
               RecordStore var21 = RecordStore.openRecordStore("key", true);

               try {
                  if(var21.getNumRecords() == 0) {
                     var21.addRecord(var16, 0, var16.length);
                  } else {
                     var21.setRecord(1, var16, 0, var16.length);
                  }
               } finally {
                  try {
                     var21.closeRecordStore();
                  } catch (RecordStoreException var13) {
                     ;
                  }

               }
            }

            switch(var2.c) {
            case 1:
               if((var5 = var2.GetFromDHashtableValueAsVector(20)).size() == 0) {
                  this.MenuOptions(var1, 6, true);
                  return;
               }

               if(var5.size() < 2) {
                  var5.addElement("Create hero");
               }

               var5.addElement("Delete hero");
               this.ShowMessageBox((RootFrame)null, new MenuOptionHandling(5, "Select hero", var5), true);
               return;
            case 2:
               this.ShowMessageBox((RootFrame)null, new hh(2, (String)null, var2.GetFromDHashtable(21), new String[]{"New user", "Existing user"}), true);
               return;
            case 3:
            default:
               return;
            case 4:
               this.ShowMessageBox(var1, new RootFrame(33, 1), false);
               return;
            case 5:
               this.ShowMessageBox(var1, new hh(24, (String)null, var2.GetFromDHashtable(22), new String[]{"Accept", "Decline"}), false);
               return;
            }
         case 3:
            switch(var2.c) {
            case 1:
               var2.webController = 2;
               this.a(var1, var2, var3);
               return;
            default:
               return;
            }
         case 4:
         case 18:
            switch(var2.c) {
            case 1:
               this.MenuOptions(var1, 8, false);
               return;
            case 2:
               this.ShowMessageBox(var1, new hh(23, (String)null, var2.GetFromDHashtable(20), new String[]{"Balance", "Cancel"}), false);
               return;
            default:
               return;
            }
         case 5:
            switch(var2.c) {
            case 1:
               this.MenuOptions(var1, 8, false);
               this.b(var1);
               break;
            case 2:
               String var15;
               if((var15 = var2.GetFromDHashtable(20)) != null && var1 instanceof MenuOptionHandling) {
                  StringBuffer var20;
                  (var20 = ((MenuOptionHandling)var1).a(0).DefaultValue).setLength(0);
                  var20.append(var15);
               }
            }
         case 6:
         case 7:
         case 10:
         case 14:
         case 24:
         default:
            break;
         case 8:
            switch(var2.c) {
            case 1:
            case 2:
               byte[][] var19;
               if((var19 = (byte[][])((byte[][])super.da.get(new Integer(256)))) == null) {
                  var19 = Frame.readByteMatrix(this.ReadMapFile(1, 0, (Vector)null));
               }

               byte[][] var18;
               if((var18 = (byte[][])((byte[][])super.da.get(new Integer(2304)))) == null) {
                  var18 = Frame.readByteMatrix(this.ReadMapFile(9, 0, (Vector)null));
               }

               this.ShowMessageBox(var1, new dd(10, 240, 290, var19, var18, var2, var2.c == 2), true);
               this.h = true;
               return;
            case 3:
            default:
               return;
            }
         case 11:
            if(var1 instanceof dd) {
               ((dd)var1).c(var2);
            }
            break;
         case 12:
            if(var1 instanceof dd) {
               if(var2.i) {
                  ((dd)var1).b(var2);
               } else {
                  ((dd)var1).a(var2);
               }
            }
            break;
         case 13:
            if(var1 instanceof dd) {
               ((dd)var1).e(var2);
            }
            break;
         case 15:
         case 25:
            (var6 = var2.GetFromDHashtableValueAsVector(20)).addElement("Back");
            this.ShowMessageBox(var1, new MenuOptionHandling(var2.webController == 15?12:19, var2.GetFromDHashtable(21), var6), false);
            break;
         case 16:
            (var17 = (MenuOptionHandling)this.c(var1)).menuOptions.removeElement(var17.f());
            var17.RenderMenus();
            var17.b(var2.GetFromDHashtable(21));
         case 9:
            this.b(var1);
            break;
         case 17:
            this.ShowMessageBox(var1, new RootFrame(33, 1), false);
            break;
         case 20:
            this.ShowMessageBox(var1, new hh(30, var2.GetFromDHashtable(23), var2.GetFromDHashtable(20)), false);
            break;
         case 21:
            (var5 = var2.GetFromDHashtableValueAsVector(20)).addElement("Back");
            this.ShowMessageBox(var1, new MenuOptionHandling(16, "Equipped items", var5), false);
            break;
         case 22:
            (var6 = var2.GetFromDHashtableValueAsVector(20)).addElement("Back");
            (var7 = new MenuOptionHandling(17, var2.GetFromDHashtable(21), var6)).c = var2.GetFromDHashtableAsInt(22);
            this.ShowMessageBox(var1, var7, false);
            break;
         case 23:
            if(var2.c == 1 && (var17 = (MenuOptionHandling)var1).c != 0) {
               var7 = (MenuOptionHandling)this.c(var1);
               InputViewModel var8;
               (var8 = (InputViewModel)var17.menuOptions.elementAt(var17.c)).b = var7.f().b;
               var7.menuOptions.setElementAt(var8, var7.c);
            }

            this.b(var1);
            break;
         case 26:
         case 28:
            (var6 = var2.GetFromDHashtableValueAsVector(20)).addElement("Back");
            this.ShowMessageBox(var1, new MenuOptionHandling(29, var2.GetFromDHashtable(21), var6), false);
            break;
         case 27:
            (var6 = var2.GetFromDHashtableValueAsVector(20)).addElement("Icon overview 1");
            var6.addElement("Icon overview 2");
            var6.addElement("Back");
            this.ShowMessageBox(var1, new MenuOptionHandling(29, var2.GetFromDHashtable(21), var6), false);
         }

      }
   }

   public final int a(RootFrame var1) {
      if(this.g != null && var1.t > i && !this.h) {
         this.a(var1, false);
      }

      RootFrame.setLight(true);
      return 3000;
   }

   public final void a(String var1, RootFrame var2, String var3, Throwable var4, boolean var5) {
      String var6 = "";
      if(var4 != null) {
         String var7;
         if((var6 = (var7 = var4.toString()).substring(var7.lastIndexOf(46, var7.indexOf(58)) + 1)).startsWith("Run")) {
            var6 = var4.getMessage();
         }

         if(var4 instanceof IOException) {
            var6 = "";
            var1 = "Problem with your operator" + (var4.getMessage() == null?"":":\n" + var4.getMessage());
         } else {
            var6 = var6 + "\n\n";
         }
      }

      hh var8 = new hh(0, var3 == null?"Error":var3, var1 + "\n\n" + var6 + "s" + var2.webController + " v" + Frame.Midlet.getAppProperty("MIDlet-Version"));
      if(var5) {
         this.a(var2, (RootFrame)var8);
      } else {
         this.ShowMessageBox(var2, var8, false);
      }
   }

   public static final Vector ArrayToVector(Object[] var0) {
      Vector var1 = new Vector(var0.length);

      for(int var2 = 0; var2 < var0.length; ++var2) {
         var1.addElement(var0[var2]);
      }

      return var1;
   }

   private void g() {
      this.m = 50;
      Object var1 = null;
      RecordStore var2 = null;

      try {
         byte[] var17 = (var2 = RecordStore.openRecordStore("vol", false)).getRecord(1);
         this.m = var17[0];
         return;
      } catch (RecordStoreNotFoundException var14) {
         ;
      } catch (RecordStoreException var15) {
         return;
      } finally {
         try {
            var2.closeRecordStore();
         } catch (Exception var13) {
            ;
         }

      }

   }

   private void h() {
      RecordStore var1 = null;

      try {
         var1 = RecordStore.openRecordStore("vol", true);
         byte[] var2 = new byte[]{(byte)this.m};
         if(var1.getNumRecords() == 0) {
            var1.addRecord(var2, 0, var2.length);
         } else {
            var1.setRecord(1, var2, 0, var2.length);
         }

         return;
      } catch (Exception var11) {
         ;
      } finally {
         try {
            var1.closeRecordStore();
         } catch (Exception var10) {
            ;
         }

      }

   }

   private void b(String var1) {
      try {
         if(this.j != var1) {
            this.j = var1;
            if(this.k != null) {
               this.k.close();
            }

            if(var1 != null) {
               this.k = Manager.createPlayer(this.getClass().getResourceAsStream(var1), "audio/midi");
               this.k.setLoopCount(-1);
               if(this.m > 0) {
                  this.i();
               }
            }

         }
      } catch (Exception var3) {
         ;
      }
   }

   private void i() throws MediaException {
      if(this.k.getState() != 400) {
         this.k.start();
      }

      this.l = (VolumeControl)this.k.getControl("VolumeControl");
      if(this.l != null) {
         this.l.setLevel(this.m);
      }

   }

   private void a(int var1) {
      if(this.k != null) {
         try {
            if(var1 > 0) {
               this.i();
            } else {
               this.k.stop();
            }

            return;
         } catch (Exception var3) {
            ;
         }
      }

   }

   static {
      Font.getFont(0, 0, 8);
      i = System.currentTimeMillis() + 50000L;
   }
}
