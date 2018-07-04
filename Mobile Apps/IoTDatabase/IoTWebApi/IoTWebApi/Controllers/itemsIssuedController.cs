using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using IoTWebApi.Models;
using Newtonsoft.Json;

namespace IoTWebApi.Controllers
{
    public class itemsIssuedController : ApiController
    {
        // GET api/<controller>             returns all the itemsissued in the data base
        public IEnumerable<itemsIssuedModel> Get()
        {
            //example: api/itemsIssued
            List<ItemIssue> itemsIssued = new List<ItemIssue>();

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            itemsIssued = (from itemIssued in db.ItemIssues
                       select itemIssued).ToList();

            return populateList(itemsIssued);
        }
 

        // GET api/<controller>/5
        public List<itemsIssuedModel> Get(int id)
        {
            List<ItemIssue> itemsIssued = new List<ItemIssue>();

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            itemsIssued = (from itemIssued in db.ItemIssues
                           where itemIssued.issueID == id
                           select itemIssued).ToList();

            return populateList(itemsIssued);
        }
        // GET api/<controller>/5

        //search the items issued by the persons name           //example: api/itemsIssued?person=bob
        [HttpGet]
        public List<itemsIssuedModel> person(string person)
        {

            List<ItemIssue> allIssued = new List<ItemIssue>();

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            allIssued = (from itemIssued in db.ItemIssues
                         where person == itemIssued.Person.name                  
                         select itemIssued).ToList();


            return populateList(allIssued);
        }

        //search all the items issued by what type they are     //example: api/itemsIssued?type=sensor
        // GET api/<controller>/5
        [HttpGet]
        public IEnumerable<itemsIssuedModel> type(string type)
        {
            
            List<ItemIssue> allIssued = new List<ItemIssue>();

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            allIssued = (from itemIssued in db.ItemIssues
                         where type == itemIssued.Item.SubType.Type.typeName
                         select itemIssued).ToList(); ;

 
            return populateList(allIssued);
        }

        //method to return the list of itemsIssued makes the code more modular
        private List<itemsIssuedModel> populateList(List<ItemIssue> allItemsLinq)
        {
            List<itemsIssuedModel> itemsIssued = new List<itemsIssuedModel>();

            foreach (var item in allItemsLinq)
            {
                typeModel newType = new typeModel(item.Item.SubType.Type.typeID,item.Item.SubType.Type.typeName);

                subTypeModel newSubType = new subTypeModel(item.Item.SubType.subTypeID,newType, item.Item.SubType.subTypeName, item.Item.SubType.description);

                itemModel itemModel = new itemModel(item.itemID,newSubType, item.Item.itemNote);

                personModel newPerson = new personModel(item.Person.personID , item.Person.name, item.Person.phoneNumber, item.Person.email);

                itemsIssuedModel newItem = new itemsIssuedModel(item.issueID,newPerson, itemModel, item.itemReturned);

                itemsIssued.Add(newItem);

            }

            return itemsIssued;

        }

        //           // POST api/<controller>
        [HttpPost]
        public void Post([FromBody]newItemIssued json)
        {

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            ItemIssue newItemIssue = new ItemIssue();

            newItemIssue.personID = json.personID;

            newItemIssue.itemID = json.itemID;

            newItemIssue.dateTaken = DateTime.Now;

            db.ItemIssues.InsertOnSubmit(newItemIssue);

            
            try
            {
                db.SubmitChanges();
            }
            catch (Exception e) {
                //bad foreign keys
            }
            
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