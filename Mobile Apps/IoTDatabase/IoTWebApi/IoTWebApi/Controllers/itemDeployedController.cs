using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace IoTWebApi.Controllers
{
    public class itemDeployedController : Controller
    {
        // GET: itemDeployed
        public ActionResult Index()
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            List<ItemDeployed> itemList = (from item in db.ItemDeployeds
                                           select item).ToList();

            ViewBag.itemList = itemList;


            return View();
        }
        public ActionResult addItem()
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            List<Project> projects = (from project in db.Projects
                                      select project).ToList();

            ViewBag.projects = projects;

            List<Item> items = (from item in db.Items
                                select item).ToList();


            ViewBag.items = items;

            return View();
        }
        [HttpPost]
        public ActionResult delItem(string itemID)
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            int id = 0;
            string result;
            int.TryParse(itemID, out id);
            ItemDeployed toDelete = null; ;

            try
            {
                toDelete = (from item in db.ItemDeployeds
                            where id == item.deployedID
                            select item).First();
            }
            catch (Exception e) { }

            if (toDelete == null)
            {
                result = "What item were you trying to delete again, I forgot?";
            }
            else
            {
                result = "Item undeployed successfully";

                db.ItemDeployeds.DeleteOnSubmit(toDelete);
            }

            

            db.SubmitChanges();

            

            return RedirectToAction("result", "Home", new { area = "", result = result });
        }

        [HttpPost]
        public ActionResult newItem(string projectName, string projectList, string itemListID, string location, string notes)
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            if (projectList != "")
            {
                Project newProject = new Project();

                newProject.projectName = projectName;

                db.Projects.InsertOnSubmit(newProject);

                db.SubmitChanges();

                projectList = projectName;
            }
            int projectID = (from project in db.Projects
                             where projectList == project.projectName
                             select project).First().projectID;
            int itemID = -1;

            int.TryParse(itemListID, out itemID);

            try
            {
                ItemDeployed newItemDeployed = new ItemDeployed();

                newItemDeployed.itemID = itemID;
                newItemDeployed.projectID = projectID;
                newItemDeployed.location = location;
                newItemDeployed.date = DateTime.Now;
                newItemDeployed.notes = notes;

                db.ItemDeployeds.InsertOnSubmit(newItemDeployed);

                db.SubmitChanges();
            }
            catch (Exception e) { }

            


            return RedirectToAction("index");
        }

    }
}