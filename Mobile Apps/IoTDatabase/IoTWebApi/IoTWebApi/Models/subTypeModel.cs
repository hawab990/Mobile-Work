using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace IoTWebApi.Models
{
    public class subTypeModel
    {
        public int subTypeID { get; set; }

        public typeModel type { get; set; }

        public string subTypeName { get; set; }

        public string description { get; set; }

        public subTypeModel(int subTypeID , typeModel type,string subTypeName)
        {
            this.subTypeID = subTypeID;

            this.type = type;

            this.subTypeName = subTypeName;
        }

        public subTypeModel(int subTypeID , typeModel type, string subTypeName,string description)
        {
            this.type = type;

            this.subTypeID = subTypeID;

            this.subTypeName = subTypeName;

            this.description = description;
        }
    }
}