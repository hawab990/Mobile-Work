using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using IoTWebApi.Models;

namespace IoTWebApi.Controllers
{
    public class personController : ApiController
    {
        // GET: api/person
        public IEnumerable<personModel> Get()
        {
            List<personModel> per = new List<personModel>();

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            var sel = from person in db.Persons
                      select person;

            foreach (var item in sel)
            {
                personModel newPerson = new personModel(item.personID, item.name, item.phoneNumber, item.email);
                per.Add(newPerson);
            }

            return per;
        }

        // GET: api/person/5
        public List<personModel> Get(int id)
        {
            List<personModel> per = new List<personModel>();

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            var sel = from person in db.Persons
                      where person.personID == id
                      select person;

            foreach(var item in sel)
            {
                personModel newPerson = new personModel(item.personID, item.name, item.phoneNumber, item.email);
                per.Add(newPerson);
            }

            return per;
        }

        // POST: api/person
        public void Post([FromBody]Person value)
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            Person newPerson = new Person();

            //newPerson.personID = value.personID;
            newPerson.name = value.name;
            newPerson.phoneNumber = value.phoneNumber;
            newPerson.email = value.email;

            db.Persons.InsertOnSubmit(newPerson);
            db.SubmitChanges();
            

        }

        // PUT: api/person/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/person/5
        public void Delete(int id)
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            var sel = from person in db.Persons
                      where person.personID == id
                      select person;

            foreach(var item in sel)
            {
                db.Persons.DeleteOnSubmit(item);
            }
            
            db.SubmitChanges();
        }
    }
}
