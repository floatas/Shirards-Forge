using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using Microsoft.AspNetCore.Mvc;

namespace Shirard.Controllers
{
    [Route("2")]
    public class GameController : Controller
    {
        [Route("1.pl")]
        [HttpPost]
        public IActionResult Index()
        {
            //Where to store recieved object
            //0 - send it back?
            //1 - login related
            //2 -
            //3 -
            //4 -
            //5 -
            //6 -
            //7 -
            //default
            //gameResponse.WriteByte(1);//1,2,3,4,5,6,7, default

            //Object type:
            //1 - number
            //2,3 - string
            //4 - ask for input ?
            //5 - sub switchas
            //default - everything as byte[]
            //gameResponse.WriteByte(0);//1,2,3,4,5, default

            //When not logged in will show message in nice border and then nothing ? blank screen, game looks like still running
            byte[] response = GetResponse(new RequestObject(WhereToStore.ObjAsString1, ObjectTypes.String1, "Hello world!"),
                new RequestObject(WhereToStore.ObjAsString1, ObjectTypes.String1, "Second line!"),
                new RequestObject(WhereToStore.ObjAsString1, ObjectTypes.String1, "Third line!"));

            Response.Body.Write(response, 0, response.Length);

            return Ok();
        }

        public byte[] GetResponse(params BaseRequestObject[] requestObjects)
        {
            var revBytes = new List<byte[]>
            {
                ReversedBytes.WriteUshort(47806), //magic number
                ReversedBytes.WriteByte(123), //????
                ReversedBytes.WriteByte(213), //random separator

                ReversedBytes.WriteInt(876), //size of response, doesn't really matter

                ReversedBytes.WriteUshort((ushort) requestObjects.Length),
                requestObjects.SelectMany(x => x.GetBytes()).ToArray()
            };

            return revBytes.SelectMany(x => x).ToArray();
        }
    }

    public abstract class BaseRequestObject
    {
        public abstract byte[] GetBytes();
    }

    public enum WhereToStore
    {
        SendItBack = 0,
        ObjAsString1 = 1,
        ObjAsString2 = 2,
        addToDictionary = 3,
        cPanelSecondLine = 4,
        cPanelHeightOffset = 5,
        cPanelFirstLine = 6,
        cPanelThirdLine = 7,
    }


    public enum ObjectTypes
    {
        Numeric = 1,
        String1 = 2,
        String2 = 3,
        AskForInput = 4,
        SubSwitchComplex = 5
    }

    public class RequestObject : BaseRequestObject
    {
        public byte WhereToStore { get; set; }
        public byte ObjectType { get; set; }
        public object[] Content { get; set; }

        public RequestObject()
        {
        }

        public RequestObject(WhereToStore whereToStore, ObjectTypes objectType, params object[] content)
        {
            WhereToStore = (byte)whereToStore;
            ObjectType = (byte)objectType;
            Content = content;
        }

        public override byte[] GetBytes()
        {
            var revBytes = new List<byte[]>
            {
                ReversedBytes.WriteByte(WhereToStore),
                ReversedBytes.WriteByte(ObjectType),
            };

            List<byte> content = new List<byte>();

            foreach (var item in Content)
            {
                if (item is int)
                {
                    content.AddRange(ReversedBytes.WriteInt((int)item).ToList());
                }
                else if (item is string)
                {
                    content.AddRange(ReversedBytes.WriteString((string)item).ToList());
                }
                else if (item is byte)
                {
                    content.AddRange(ReversedBytes.WriteByte((byte)item).ToList());
                }
                else if (item is ushort)
                {
                    content.AddRange(ReversedBytes.WriteUshort((ushort)item).ToList());
                }
                else
                {
                    throw new NotImplementedException();
                }
            }

            revBytes.Add(ReversedBytes.WriteUshort((ushort)content.Count));
            revBytes.Add(content.ToArray());

            return revBytes.SelectMany(x => x).ToArray();
        }
    }

    public static class ReversedBytes
    {
        public static byte[] WriteUshort(ushort value)
        {
            return BitConverter.GetBytes(value).Reverse().ToArray();
        }

        public static byte[] WriteInt(int value)
        {
            return BitConverter.GetBytes(value).Reverse().ToArray();
        }

        public static byte[] WriteByte(byte value)
        {
            //return BitConverter.GetBytes(value).Reverse().ToArray();
            return new byte[] { value };
        }

        public static byte[] WriteString(String var1)
        {
            using (var memoryStream = new MemoryStream())
            {
                var binaryWriter = new BinaryWriter(memoryStream);

                for (int var2 = 0; var2 < var1.Length; ++var2)
                {
                    char var3;
                    if ((var3 = var1[var2]) <= 127)
                    {
                        binaryWriter.Write(var3);
                    }
                    else if (var3 <= 2047)
                    {
                        binaryWriter.Write(192 | var3 >> 6);
                        binaryWriter.Write(128 | var3 & 63);
                    }
                    else
                    {
                        binaryWriter.Write(224 | var3 >> 12);
                        binaryWriter.Write(128 | var3 >> 6 & 63);
                        binaryWriter.Write(128 | var3 & 63);
                    }
                }

                return memoryStream.ToArray().ToArray();
            }
        }
    }

    public class GameResponse
    {
        private readonly MemoryStream _memoryStream;
        private readonly BinaryWriter _binaryWriter;
        public GameResponse()
        {
            _memoryStream = new MemoryStream();
            _binaryWriter = new BinaryWriter(_memoryStream);
        }

        public void WriteMagicNumber()
        {
            WriteUshort(47806);
        }

        public void WriteSeparator()
        {
            WriteUshort(1);//random...
        }

        public void WriteUshort(ushort value)
        {
            _binaryWriter.Write(BitConverter.GetBytes(value).Reverse().ToArray());
        }

        public void WriteInt(int value)
        {
            _binaryWriter.Write(BitConverter.GetBytes(value).Reverse().ToArray());
        }

        public void WriteByte(byte value)
        {
            _binaryWriter.Write(BitConverter.GetBytes(value).Reverse().ToArray());
        }

        public byte[] GetArray()
        {
            return _memoryStream.ToArray().Reverse().ToArray();
        }

        public void WriteString(String var1)
        {
            for (int var2 = 0; var2 < var1.Length; ++var2)
            {
                char var3;
                if ((var3 = var1[var2]) <= 127)
                {
                    _binaryWriter.Write(var3);
                }
                else if (var3 <= 2047)
                {
                    _binaryWriter.Write(192 | var3 >> 6);
                    _binaryWriter.Write(128 | var3 & 63);
                }
                else
                {
                    _binaryWriter.Write(224 | var3 >> 12);
                    _binaryWriter.Write(128 | var3 >> 6 & 63);
                    _binaryWriter.Write(128 | var3 & 63);
                }
            }
        }
    }

}