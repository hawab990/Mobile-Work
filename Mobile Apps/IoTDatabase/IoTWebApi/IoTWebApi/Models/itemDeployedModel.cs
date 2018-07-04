using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace IoTWebApi.Models
{
    public class itemDeployedModel
    {
        public projectModel project { get; set; }

        public itemModel item { get; set; }

        public string location { get; set; }

        public string date { get; set; }

        public string notes { get; set; }

        public itemDeployedModel(projectModel project,itemModel item,String location,string date,string notes)
        {

            this.project = project;

            this.item = item;

            this.location = location;

            this.date = date;

            this.notes = notes;

        }

    }
}