using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using IoTWebApi.Models;

namespace IoTWebApi.Controllers
{
    public class deployController : ApiController
    {
        // GET api/<controller>
        public List<itemDeployedModel> Get()
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            List<itemDeployedModel> items = new List<itemDeployedModel>();

            List<ItemDeployed> list = (from item in db.ItemDeployeds
                                       select item).ToList();


            foreach (ItemDeployed item in list)
            {
                projectModel newProject = new projectModel(item.Project.projectName);

                typeModel newType = new typeModel(item.Item.SubType.Type.typeID, item.Item.SubType.Type.typeName);

                subTypeModel newSubType = new subTypeModel(item.Item.SubType.subTypeID, newType, item.Item.SubType.subTypeName, item.Item.SubType.description);

                itemModel itemModel = new itemModel(item.itemID, newSubType, item.Item.itemNote);

                itemDeployedModel newDeploy = new itemDeployedModel(newProject, itemModel, item.location, item.date.ToString(), item.notes);

                items.Add(newDeploy);
            }


            return items;
        }

        // GET api/<controller>/5
        public List<itemDeployedModel> Get(int id)
        {
            IoTDatabaseDataContext db = new IoTDatabaseDataContext();

            List<itemDeployedModel> items = new List<itemDeployedModel>();

            List<ItemDeployed> list = (from item in db.ItemDeployeds
                                       where id == item.deployedID
                                       select item).ToList();


            foreach (ItemDeployed item in list)
            {
                projectModel newProject = new projectModel(item.Project.projectName);

                typeModel newType = new typeModel(item.Item.SubType.Type.typeID, item.Item.SubType.Type.typeName);

                subTypeModel newSubType = new subTypeModel(item.Item.SubType.subTypeID, newType, item.Item.SubType.subTypeName, item.Item.SubType.description);

                itemModel itemModel = new itemModel(item.itemID, newSubType, item.Item.itemNote);

                itemDeployedModel newDeploy = new itemDeployedModel(newProject, itemModel, item.location, item.date.ToString(), item.notes);

                items.Add(newDeploy);
            }


            return items;
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