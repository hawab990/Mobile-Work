using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using IoTWebApi.Models;

namespace IoTWebApi.Controllers
{
    public class itemIssuedPageController : Controller
    {
        // GET: itemIssuedPage
        public ActionResult Index()
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            List<ItemIssue> select = (from item in db.ItemIssues
                         select item).ToList();

            List<ItemIssue> returnList = new List<ItemIssue>();

            foreach (ItemIssue item in select)
            {
                if (item.itemReturned == null || item.itemReturned == false)
                {
                    returnList.Add(item);
                }
            }


            return View(returnList);

        }

        [HttpPost]
        public ActionResult checkOutItem(string personString, string itemString)
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();
            ItemIssue ii = new ItemIssue();

            int pID = -1;
            int.TryParse(personString, out pID);

            int iID = -1;
            int.TryParse(itemString, out iID);

            try
            {
                ii.personID = pID;
                ii.itemID = iID;
                ii.dateTaken = DateTime.Now;

                db.ItemIssues.InsertOnSubmit(ii);

                db.SubmitChanges();
            }
            catch (Exception e) { };

            

            return RedirectToAction("index");
        }

        [HttpGet]
        public ActionResult checkOutItem()
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            List<Person> per = (from person in db.Persons
                                select person).ToList();

            List<Item> it = (from item in db.Items
                     select item).ToList();

            ViewBag.person = per;
            ViewBag.item = it;

            return View();
        }

        [HttpPost]
        public ActionResult returnItem(string id)
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            int iID = -1;
            int.TryParse(id, out iID);
            string result;

            if (iID == -1)
            {
                ItemIssue select = (from item in db.ItemIssues
                                    where item.issueID == iID
                                    select item).First();

                select.itemReturned = true;
                select.dateReturned = DateTime.Now;

                db.SubmitChanges();

                result = "item returned";
            }
            else
            {
                result = "No item";
            }

            return RedirectToAction("result", "Home", new { area = "", result = result });
        }

    }
}