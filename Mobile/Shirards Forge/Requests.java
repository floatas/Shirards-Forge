import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class Requests extends MenuFrame implements Runnable {

   public static String serverName = "prod";
   public Thread thread;
   private int requestRetryCount;
   private static int y;
   private RootFrame z;
   private static byte[] stuffToSendBack;
   private ByteArrayOutputStream RequestBody;
   private DataOutputStream RequstBodyStream;
   public int c;
   public Hashtable dictionary;
   private static int squareLeft;
   private static int squareTop;
   private static int horizontalMovementGame = 5;
   private static int verticalMovementGame = 5;
   private static int I = -1;
   private static int DataUsage;
   private static int DataUsageMb = -1;
   private static String DataUsageStringMb;
   private final int M;
   private int N = 0;
   private Random random = new Random();
   public static String cPanelFirstLine = "";
   public static String cPanelSencondLine = "";
   public static String cPanelThirdLine = "";
   public static int cPanelHeightOffset;
   public boolean i;


   public Requests(int var1, RootFrame var2) {
      super(var1, "Loading...");
      this.z = var2;
      this.d(Math.min(236, 124));
      if(I == -1) {
         I = (super.screenWidth - 10) / 2;
      }

      this.d(65, 0);
      this.M = super.screenHeight - 3 - (super.screenHeight - 3 + 3 - 1) % 5;
      super.v = 150;
      this.thread = new Thread(this);
      this.RequestBody = new ByteArrayOutputStream();
      this.RequstBodyStream = new DataOutputStream(this.RequestBody);
      this.dictionary = new Hashtable();
      this.WriteNumberToRequest(255, y++);
   }

   public final void RenderFrame(Graphics var1, boolean var2) {
      this.a(var1);
      var1.setColor(0);
      var1.fillRect(super.screenX, super.screenY, super.screenWidth, super.screenHeight);
      var1.setColor(16173378);
      if(this.requestRetryCount == 0) {
         var1.drawString("Data used:", super.screenX + (super.screenWidth >> 1), 0 + super.screenY + 2, 17);
      } else {
         var1.drawString("Retry no: " + this.requestRetryCount, super.screenX + (super.screenWidth >> 1), 0 + super.screenY + 2, 17);
      }

      if(DataUsage / 10485 != DataUsageMb) {
         DataUsageMb = DataUsage / 10485;
         StringBuffer var3;
         (var3 = new StringBuffer()).append(DataUsageMb);

         while(var3.length() < 3) {
            var3.insert(0, '0');
         }

         var3.insert(var3.length() - 2, '.');
         var3.append(" MB");
         DataUsageStringMb = var3.toString();
      }

      var1.drawString(DataUsageStringMb, super.screenX + (super.screenWidth >> 1), 0 + super.screenY + 2 + RootFrame.fontHeight, 17);
      var1.setColor(16645016);
      var1.fillRect(super.screenX + squareLeft, super.screenY + squareTop, 3, 3);
      var1.setColor(16173378);
      var1.drawLine(super.screenX + I, super.screenY + this.M, super.screenX + I + 10, super.screenY + this.M);
   }

   public final void a() {
      I = Math.min(super.screenWidth - 10 - 1, Math.max(0, I + this.N));
      int var1 = squareLeft + horizontalMovementGame;
      int var2 = squareTop + verticalMovementGame;
      if(var1 <= 0) {
         horizontalMovementGame = -horizontalMovementGame;
         var1 = Math.max(1, Math.abs(var1));
      }

      int var3 = super.screenWidth - 2;
      if(var1 + 3 >= var3) {
         horizontalMovementGame = -horizontalMovementGame;
         var1 -= var1 + 3 - var3;
      }

      if(var2 + 3 == this.M && var1 + 3 - 1 >= I && var1 - 1 <= I + 10) {
         verticalMovementGame = -verticalMovementGame;
         horizontalMovementGame += this.random.nextInt() % 2;
      }

      if(var2 >= super.screenHeight - 3 && verticalMovementGame > 0) {
         var2 = 5;
      }

      if(var2 == 0) {
         verticalMovementGame = -verticalMovementGame;
      }

      squareTop = var2;
      squareLeft = var1;
      this.RepaintFrame();
   }

   public final boolean a(int var1, int var2) {
      if(var2 == 2) {
         this.N = -6;
      }

      if(var2 == 5) {
         this.N = 6;
      }

      return true;
   }

   public final void run() {
      HttpConnection request = null;
      DataOutputStream requestBody = null;
      DataInputStream responseBody = null;
      //String var4 = "http://" + vibrate + "4.eraofeidolon.com/2/" + super.webController +webController ".pl";
      String var4 = "http://localhost:58568/2/" + super.webController + ".pl";

      
      
      System.out.println("Request: " + var4);
      String var5;
      boolean var6;
      if(var6 = (var5 = Frame.Midlet.getAppProperty("Relay")) == null) {
         var5 = var4;
      }

      while(true) {
            Vector var7 = new Vector();

            try {
               int var10;
               try {
                  (request = (HttpConnection)Connector.open(var5, 3, true)).setRequestMethod("POST");
                  if(!var6) {
                     request.setRequestProperty("X-relay-url", var4);
                  }

                  requestBody = request.openDataOutputStream();
                  if(stuffToSendBack != null) {
                     requestBody.writeByte(0);
                     requestBody.writeByte(0);
                     requestBody.writeShort(stuffToSendBack.length);
                     requestBody.write(stuffToSendBack);
                     DataUsage += 4 + stuffToSendBack.length;
                  }
                    System.out.println("trying");
                    
                  requestBody.write(this.RequestBody.toByteArray());
                  DataUsage += this.RequestBody.size();
                  int responseCode;
                  if((responseCode = request.getResponseCode()) != 200) {
                     throw new IOException("rc" + responseCode);
                  }
                  System.out.println("trying2");
                  
                  responseBody = request.openDataInputStream();
                  int magic = responseBody.readUnsignedShort();
                  if( magic!= '\ubabe') {
                      System.out.println("Magic: " + magic);
                     throw new IOException("Magic number");
                  }else{
                     System.out.println("Magic number matches");
                  }

                  this.c = responseBody.readUnsignedByte();
                   int separator =   responseBody.readUnsignedByte();
                  DataUsage += responseBody.readInt();
                  int numberOfObjectsInResponse=  responseBody.readUnsignedShort();
                  
                  System.out.println("c:"+ this.c + " separator:"+separator +" dataUsage:"+DataUsage +" numOfObj:"+numberOfObjectsInResponse);
                  
                  for(var10 = 0; var10 < numberOfObjectsInResponse; ++var10) {
                     int whereToStoreObject;
                     Object recievedObject;
                     whereToStoreObject = responseBody.readUnsignedByte();
                     int objectType = responseBody.readUnsignedByte();
                     int objectSize = responseBody.readUnsignedShort();
                     label417:
                     switch(objectType) {
                     case 1:
                        switch(objectSize) {
                        case 1:
                           recievedObject = new Integer(responseBody.readByte());
                           break label417;
                        case 2:
                           recievedObject = new Integer(responseBody.readShort());
                           break label417;
                        case 3:
                        default:
                           throw new IOException("ChnkLen:" + objectSize);
                        case 4:
                           recievedObject = new Integer(responseBody.readInt());
                           break label417;
                        }
                     case 2:
                     case 3:
                        if((recievedObject = ReadNCharsFromStream(responseBody, objectSize)) == null) {
                           recievedObject = "";
                        }
                        break;
                     case 4:
                        recievedObject = new InputViewModel(responseBody);
                        break;
                     case 5:
                        int var15;
                        switch((var15 = responseBody.readUnsignedShort()) >> 8) {
                        case 1:
                        case 9:
                           byte[][] var16 = Frame.readByteMatrix(responseBody);
                           super.frame.da.put(new Integer(var15), var16);
                           continue;
                        case 2:
                        case 4:
                        case 6:
                        case 8:
                        default:
                           byte[] var19 = new byte[objectSize - 2];
                           responseBody.readFully(var19);
                           super.frame.a(var15 >> 8, var15 & 255, (Object)var19);
                           continue;
                        case 3:
                           super.frame.a(3, var15 & 255, (Object)super.frame.a(responseBody, objectSize - 2, false));
                           continue;
                        case 5:
                        case 7:
                           byte[] var17 = new byte[objectSize - 2];
                           responseBody.readFully(var17);
                           Image var18 = Image.createImage(var17, 0, var17.length);
                           super.frame.a(var15 >> 8, var15 & 255, (Object)var18);
                           Frame.gcCollect(true);
                           continue;
                        case 10:
                           super.frame.a(responseBody, objectSize - 2, true);
                           continue;
                        case 11:
                           super.frame.a(11, var15 & 255, (Object)Frame.ReadToHashtable(responseBody));
                           continue;
                        }
                     default:
                        recievedObject = new byte[objectSize];
                        responseBody.readFully((byte[])((byte[])recievedObject));
                     }

                     if(recievedObject != null) {
                        switch(whereToStoreObject) {
                        case 0:
                           stuffToSendBack = (byte[])((byte[])recievedObject);
                           break;
                        case 1:
                           var7.addElement(new hh(30, (String)null, recievedObject.toString()));
                           break;
                        case 2:
                           super.frame.ShowMessageBox(this.z, new hh(30, (String)null, recievedObject.toString()), false);
                           break;
                        case 3:
                        default:
                           Integer var43 = new Integer(whereToStoreObject);
                           Object var44;
                           if((var44 = this.dictionary.get(var43)) instanceof Vector) {
                              ((Vector)var44).addElement(recievedObject);
                           } else if(var44 != null) {//append to existing dictionary item, make list
                              Vector var45;
                              (var45 = new Vector()).addElement(var44);
                              var45.addElement(recievedObject);
                              this.dictionary.put(var43, var45);
                           } else {
                              this.dictionary.put(var43, recievedObject);
                           }
                           break;
                        case 4:
                           cPanelSencondLine = recievedObject.toString();
                           break;
                        case 5:
                           cPanelHeightOffset = ((Integer)recievedObject).intValue();
                           break;
                        case 6:
                           cPanelFirstLine = recievedObject.toString();
                           break;
                        case 7:
                           cPanelThirdLine = recievedObject.toString();
                        }
                     }
                  }

                    if(this.c != 0) {
                       super.frame.b(this.z, this, (Throwable)null);
                    } else {
                       if(var7.size() == 0) {
                          super.frame.a("Second login detected! You can only be logged in from one device at a time", this.z, (String)null, (Throwable)null, false);
                       }

                       super.frame.ShowMessageBox(this.z, new RootFrame(33, 1), false);
                       super.frame.b((RootFrame)this);
                    }

                    for(var10 = 0; var10 < var7.size(); ++var10) {
                       super.frame.ShowMessageBox(this.z, (RootFrame)var7.elementAt(var10), false);
                    }

                  return;
               } catch (SecurityException var38) {
                  super.frame.ShowMessageBox(this, new hh(31, (String)null, "Please allow GPRS-connection.\nGame uses less than 1 MB of data per hour. Data usage can be tracked in game."), false);
                  super.frame.ShowMessageBox(this, new RootFrame(33, 1), false);
                  return;
               } catch (IOException var39) {
                  if(++this.requestRetryCount <= 3 && !"rc500".equals(var39.getMessage())) {
                     synchronized(this) {
                        try {
                           Frame.gcCollect(false);
                           var10 = 1000 << this.requestRetryCount;
                           this.wait((long)var10);
                        } catch (Exception var36) {
                           ;
                        }
                     }

                     this.dictionary.clear();
                     continue;
                  }
               } catch (Throwable var40) {
                  super.frame.b(this.z, this, var40);
                  return;
               }

               //super.frame.Midlet(this.z, this, var39); some exception!!!!!!
               RootFrame var9 = (RootFrame)super.frame.stack.elementAt(super.frame.stack.size() - 1);
               super.frame.ShowMessageBox(var9, new RootFrame(33, 1), false);
            } finally {
               super.frame.d();

               try {
                  if(responseBody != null) {
                     responseBody.close();
                  }

                  if(requestBody != null) {
                     requestBody.close();
                  }

                  if(request != null) {
                     request.close();
                  }
               } catch (IOException var35) {
                  ;
               }

            }


         return;
      }
   }

   public final void Connect() {
      this.RequstBodyStream = null;
      this.thread.start();
   }

   public final void WriteNumberToRequest(int var1, int var2) {
      try {
         this.RequstBodyStream.writeByte(var1);
         this.RequstBodyStream.writeByte(1);
         if(var2 > -129 && var2 < 128) {
            this.RequstBodyStream.writeShort(1);
            this.RequstBodyStream.writeByte(var2);
         } else if(var2 > -32769 && var2 < '\u8000') {
            this.RequstBodyStream.writeShort(2);
            this.RequstBodyStream.writeShort(var2);
         } else {
            this.RequstBodyStream.writeShort(4);
            this.RequstBodyStream.writeInt(var2);
         }

      } catch (IOException var4) {
         ;
      }
   }

   public final void WriteStringToRequest(int var1, String var2) {
      try {
         this.RequstBodyStream.writeByte(var1);
         this.RequstBodyStream.writeByte(2);
         this.RequstBodyStream.writeShort(CountBytesNeededForStringEncoding(var2));
         WriteStringToStream(this.RequstBodyStream, var2);
      } catch (IOException var4) {
         ;
      }
   }

   public final void WriteByteArrayToRequest(int var1, byte[] var2) {
      try {
         this.RequstBodyStream.writeByte(var1);
         this.RequstBodyStream.writeByte(0);
         this.RequstBodyStream.writeShort(var2.length);
         this.RequstBodyStream.write(var2);
      } catch (IOException var4) {
         ;
      }
   }

   public final void WriteHashtableToRequest(Hashtable var1) {
      try {
         this.RequstBodyStream.writeByte(2);
         this.RequstBodyStream.writeByte(0);
         this.RequstBodyStream.writeShort(var1.size() << 1);
         Enumeration var2 = var1.keys();

         while(var2.hasMoreElements()) {
            short var3 = ((Integer)var2.nextElement()).shortValue();
            this.RequstBodyStream.writeShort(var3);
         }

      } catch (IOException var4) {
         ;
      }
   }

   public final Vector GetFromDHashtableValueAsVector(int var1) {
      Object var2;
      if((var2 = this.dictionary.get(new Integer(var1))) instanceof Vector) {
         return (Vector)var2;
      } else {
         Vector var3 = new Vector();
         if(var2 != null) {
            var3.addElement(var2);
         }

         return var3;
      }
   }

   public final int GetFromDHashtableAsInt(int var1) {
      Object var2;
      return (var2 = this.dictionary.get(new Integer(var1))) == null?0:((Integer)var2).intValue();
   }

   public final String GetFromDHashtable(int var1) {
      return (String)this.dictionary.get(new Integer(var1));
   }

   public static final void WriteStringToStream(OutputStream var0, String var1) throws IOException {
      for(int var2 = 0; var2 < var1.length(); ++var2) {
         char var3;
         if((var3 = var1.charAt(var2)) <= 127) {
            var0.write(var3);
         } else if(var3 <= 2047) {
            var0.write(192 | var3 >> 6);
            var0.write(128 | var3 & 63);
         } else {
            var0.write(224 | var3 >> 12);
            var0.write(128 | var3 >> 6 & 63);
            var0.write(128 | var3 & 63);
         }
      }
   }

   public static final int CountBytesNeededForStringEncoding(String var0) {
      int var1 = 0;

      for(int var2 = 0; var2 < var0.length(); ++var2) {
         char var3;
         if((var3 = var0.charAt(var2)) <= 127) {
            ++var1;
         } else if(var3 <= 2047) {
            var1 += 2;
         } else {
            var1 += 3;
         }
      }

      return var1;
   }

   public static final String ReadNCharsFromStream(InputStream var0, int var1) throws IOException {
      if(var1 == 0) {
         return null;
      } else {
         StringBuffer var2 = new StringBuffer();
         int var3 = 0;
         int var4 = -1;

         for(int var5 = 0; var5 < var1; ++var5) {
            int var6;
            if((var6 = var0.read()) == -1) {
               throw new EOFException();
            }

            if((var6 & 192) == 128) {
               var3 = var3 << 6 | var6 & 63;
               --var4;
               if(var4 == 0) {
                  var2.append((char)var3);
               }
            } else if((var6 & 128) == 0) {
               var2.append((char)var6);
            } else if((var6 & 224) == 192) {
               var3 = var6 & 31;
               var4 = 1;
            } else {
               if((var6 & 240) != 224) {
                  throw new IllegalArgumentException("utf8");
               }

               var3 = var6 & 15;
               var4 = 2;
            }
         }

         return var2.toString();
      }
   }

   public static final String BytesToString(byte[] stream, int startPos, int length) {
      try {
         return ReadNCharsFromStream(new ByteArrayInputStream(stream, startPos, length), length);
      } catch (IOException var4) {
         return null;
      }
   }

   public final void ResetNCount(int var1, int var2) {
      this.N = 0;
   }

}
