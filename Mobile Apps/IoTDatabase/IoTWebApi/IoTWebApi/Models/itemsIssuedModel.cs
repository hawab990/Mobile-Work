using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace IoTWebApi.Models
{
    public class itemsIssuedModel
    {
        public int itemIssuedID { get; set; }
        public personModel person { get; set; }

        public itemModel item { get; set; }

        public bool? itemReturned { get; set; }

        public itemsIssuedModel(int itemIssuedID , personModel person, itemModel item, bool? itemReturned)
        {
            this.itemIssuedID = itemIssuedID;

            this.person = person;
            
            this.item = item;

            this.itemReturned = itemReturned;
        }
    }
}