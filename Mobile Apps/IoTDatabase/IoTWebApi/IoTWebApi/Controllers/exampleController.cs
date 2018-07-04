using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using IoTWebApi.Models;


namespace IoTWebApi.Controllers
{
    public class exampleController : ApiController
    {
        // GET api/<controller>
        public IEnumerable<personModel> Get()
        {
            List<personModel> list = new List<personModel>();

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();
             
            var people = from person in db.Persons
                         select person;

            foreach (var item in people)
            {
                personModel newOne = new personModel(item.personID,item.name,item.phoneNumber,item.email);

                list.Add(newOne);
            }

            return list;
        }

        // GET api/<controller>/5
        public string Get(int id)
        {
        

            return "";
        }

        // POST api/<controller>
        public void Post([FromBody]string value)
        {

        }

        // PUT api/<controller>/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/<controller>/5
        public void Delete(int id)
        {
        }
    }
}