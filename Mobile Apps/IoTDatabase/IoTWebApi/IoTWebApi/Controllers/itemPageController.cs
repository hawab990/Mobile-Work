using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;


namespace IoTWebApi.Controllers
{
    public class itemPageController : Controller
    {
        // GET: itemPage
        public ActionResult addItem()
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            List<Type> currentTypes = new List<Type>();

            List<SubType> currentSubstypes = new List<SubType>();


            currentTypes = (from type in db.Types
                                  select type).ToList();


            currentSubstypes = (from type in db.SubTypes
                           select type).ToList();

            ViewBag.currentTypes = currentTypes;

            ViewBag.currentSubtypes = currentSubstypes;



            return View();
        }
        public ActionResult delItem(string itemId)
        {
            int id = 0;

            int.TryParse(itemId, out id);

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();
            Item toDelete = null;
            try
            {
                toDelete = (from item in db.Items
                            where item.itemID == id
                            select item).Single();
            } catch (Exception e) { }
            string result;
            if (toDelete != null)
            {
                db.Items.DeleteOnSubmit(toDelete);

                
                try
                {
                    db.SubmitChanges();
                    result = "success the item has been deleted";
                }
                catch (Exception e)
                {
                    result = "you can not delete this item because it is issued to someone or deployed in the field";
                }
            }
            else
            {
                result = "the item has already been deleted you cheeky bugger";
            }

            return RedirectToAction("result", "Home", new { area = "" , result = result });
        }
        public ActionResult index()
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            List<Item> itemList = (from item in db.Items
                                   select item).ToList();

            ViewBag.itemList = itemList;


            return View();
        }
        [HttpPost]
        public ActionResult newItem(string type,string subType, string itemNote,string subTypeDescription,string typeList, string subTypeList)
        { 
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            int subTypeID;

            string typeName = typeList;

            if (subTypeList == "")
            {
                if (typeList == "")
                {
                    IoTWebApi.Type newType = new IoTWebApi.Type();

                    newType.typeName = type;

                    

                    int typeId = -1;
                    try { 
                    typeId = (from theType in db.Types
                                         where type == theType.typeName
                                         select theType).First().typeID;
                    }catch(Exception e) { }

                    if (typeId == -1)
                    {
                        db.Types.InsertOnSubmit(newType);

                        db.SubmitChanges();
                    }
                    typeName = type;


                }

                SubType newSubType = new SubType();

                newSubType.subTypeName = subType;

                newSubType.description = subTypeDescription;

                newSubType.typeID = (from theType in db.Types
                                        where typeName == theType.typeName
                                        select theType).First().typeID;
                //To check if the sub type allready exists

                subTypeID = -1;

                try
                {
                    subTypeID = (from theSubType in db.SubTypes
                                 where subType == theSubType.subTypeName
                                 select theSubType).First().subTypeID;
                }catch(Exception e) { };

                if (subTypeID == -1)
                {
                    db.SubTypes.InsertOnSubmit(newSubType);

                    db.SubmitChanges();
                }
                subTypeID = (from theSubType in db.SubTypes
                             where subType == theSubType.subTypeName
                             select theSubType).First().subTypeID;
            }
            else
            {
                //newSubType.subTypeName = subTypeList;

                subTypeID = (from theSubType in db.SubTypes
                                      where subTypeList == theSubType.subTypeName
                                      select theSubType).First().subTypeID;
            }
            Item newItem = new Item();

            newItem.subTypeID = subTypeID;
            newItem.itemNote = itemNote;

            db.Items.InsertOnSubmit(newItem);

            db.SubmitChanges();

            return RedirectToAction("index");

            //return View("index");
        }

    }
}