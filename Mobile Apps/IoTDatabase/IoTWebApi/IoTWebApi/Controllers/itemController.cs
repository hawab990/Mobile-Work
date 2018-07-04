using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using IoTWebApi.Models;

namespace IoTWebApi.Controllers
{
    public class itemController : ApiController
    {
        // GET api/<controller>
        public IEnumerable<itemModel> Get()
        {
            List<itemModel> itemList = new List<itemModel>();

            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            var allItems= from theItem in db.Items
                                 select theItem;

            foreach (var item in allItems)
            {
                typeModel newType = new typeModel(item.SubType.typeID , item.SubType.Type.typeName);

                subTypeModel newSubType = new subTypeModel(item.SubType.subTypeID , newType, item.SubType.subTypeName, item.SubType.description);

                itemModel itemModel = new itemModel(item.itemID , newSubType, item.itemNote);

                itemList.Add(itemModel);
            }

            return itemList;
        }

        // GET api/<controller>/5
        public string Get(int id)
        {
            return "value";
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
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            ItemIssue item = db.ItemIssues.Where(x => x.issueID == id).Single();

            db.ItemIssues.DeleteOnSubmit(item);

            db.SubmitChanges();
        }
    }
}