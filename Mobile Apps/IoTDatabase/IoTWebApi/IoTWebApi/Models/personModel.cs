using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace IoTWebApi.Models
{
    public class personModel
    {
        public int personID { get; set; }

        public string name { get; set; }

        public string phoneNumber { get; set; }

        public string email { get; set; }

        public personModel(int personID , string name, string phoneNumber, string email)
        {
            this.personID = personID;

            this.name = name;

            this.phoneNumber = phoneNumber;

            this.email = email;
        }
    }
}