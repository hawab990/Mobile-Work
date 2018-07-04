using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace IoTWebApi.Controllers
{
    public class projectController : Controller
    {
        // GET: project
        public ActionResult Index()
        {

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            List<Project> projects = (from project in db.Projects
                                      select project).ToList();

            ViewBag.projects = projects;

            return View();
        }

        public ActionResult addItem()
        {
            return View();
        }

        public ActionResult newItem(string projectName)
        {
            Project newProject = new Project();

            newProject.projectName = projectName;

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            db.Projects.InsertOnSubmit(newProject);

            db.SubmitChanges();

            return RedirectToAction("index");
        }


        public ActionResult delItem(string itemID)
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();
            int id = 0; ;
            string result = "";

            if (!int.TryParse(itemID, out id))
            {
                result = "dude plz";
            }
            else
            {
                Project toDelete = (from project in db.Projects
                                    where id == project.projectID
                                    select project).Single();

                db.Projects.DeleteOnSubmit(toDelete);

                try
                {
                    db.SubmitChanges();
                    result = "project was deleted";
                }
                catch
                {
                    result = "Project is being used";
                }
            }

            
            return RedirectToAction("result", "Home", new { area = "", result = result });
        }
    }
}