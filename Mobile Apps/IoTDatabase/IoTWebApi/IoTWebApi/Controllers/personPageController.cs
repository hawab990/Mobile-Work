using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using IoTWebApi.Models;

namespace IoTWebApi.Controllers
{
    public class personPageController : Controller
    {
        // GET: personPage
        public ActionResult personAdd()
        {
            return View();
        }

        [HttpPost]
        public ActionResult post(string name, string phone, string email)
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();
            Person p = new Person();

            if (name != "" && email != "")
            {
                p.name = name;
                p.phoneNumber = phone;
                p.email = email;

                db.Persons.InsertOnSubmit(p);
                db.SubmitChanges();

                ViewBag.result = name + " added!";
                ViewBag.colour = "green";
            }
            else
            {
                ViewBag.result = "Please insert a person";
                ViewBag.colour = "red";
            }
            
            return View("personAdd");
        }

        public ActionResult index()
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            return View(db.Persons.ToList());
        }

        [HttpPost]
        public ActionResult del(string id)
        {
            int idCast = -1;
            int.TryParse(id, out idCast);

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();
            string name = "";

            try
            {
                name = (from person in db.Persons
                               where person.personID == idCast
                               select person).Single().name;
            }
            catch (Exception e) { }

            ViewBag.delete = "Nothing selected";

            if (idCast !=  -1)
            {
                var sel = from person in db.Persons
                          where person.personID == idCast
                          select person;

                foreach (var item in sel)
                {
                    db.Persons.DeleteOnSubmit(item);
                    ViewBag.delete = name + " deleted!";
                }

                db.SubmitChanges();

                
            }


            

            return RedirectToAction("result", "Home", new { area = "", result = ViewBag.delete });
        }

    }
}