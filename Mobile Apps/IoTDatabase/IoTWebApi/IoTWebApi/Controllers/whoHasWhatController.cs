using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace IoTWebApi.Controllers
{
    public class whoHasWhatController : Controller
    {
        // GET: whoHasWhat
        public ActionResult Index()
        {

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            List<Person> persons = (from fella in db.Persons
                                    select fella).ToList();
            try
            {
                ViewBag.selectedID = persons.First().personID;
                ViewBag.persons = persons;
            }
            catch (Exception e) {
            }


            ViewBag.persons = persons;

            return View();
        }
        [HttpPost]
        public ActionResult selected(string personID)
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            int id = 0;

            int.TryParse(personID, out id);

            ViewBag.selectedID = id;

            List <ItemIssue> itemIssueList = (from itemIssue in db.ItemIssues
                                  where itemIssue.personID == id
                                  select itemIssue).ToList();



            List<Person> persons = (from fella in db.Persons
                                        select fella).ToList();

            ViewBag.itemIssueList = itemIssueList;
            ViewBag.persons = persons;

            return View("index");
        }
    }
}