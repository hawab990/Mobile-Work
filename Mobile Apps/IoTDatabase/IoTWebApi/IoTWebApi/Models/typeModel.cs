using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace IoTWebApi.Models
{
    public class typeModel
    {
        public int typeID { get; set; }
        public string typeName { get; set; }

        public typeModel(int typeID , string typeName)
        {
            this.typeName = typeName;

            this.typeID = typeID;
        }
    }
}