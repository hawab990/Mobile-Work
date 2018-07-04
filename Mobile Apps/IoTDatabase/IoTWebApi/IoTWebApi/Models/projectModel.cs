using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace IoTWebApi.Models
{
    public class projectModel
    {
        public string name { get; set; }

        public projectModel(string name)
        {
            this.name = name;
        }
    }
}