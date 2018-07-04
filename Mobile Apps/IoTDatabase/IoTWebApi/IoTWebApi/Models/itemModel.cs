using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace IoTWebApi.Models
{
    public class itemModel
    {
        public int itemID { get; set; }

        public subTypeModel subType { get; set; }

        public string itemNote { get; set; }

        public itemModel(int itemID ,subTypeModel subType, string itemNote)
        {
            this.subType = subType;

            this.itemID = itemID;

            this.itemNote = itemNote;
        }
    }
}