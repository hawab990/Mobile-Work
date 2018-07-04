package op_bit.ayaapp;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlParser {

    String xmlFileString = "MedicalData";

    //These Are the strings for the tags in the xml file this removes typing errors
    final String firstName = "firstName";

    final String lastName = "lastName";

    final String gender = "gender";

    final String NHI = "NHI";

    final String DOB = "DOB";

    final String BSA = "BSA";

    final String Name = "Name";

    final String Address = "Address";

    final String Number = "Number";

    final String existingAllergies = "existingAllergies";

    final String other = "other";

    final String phone = "phone";

    final String oncologist = "oncologist";

    final String AYAKeyWorker = "AYAKeyWorker";

    final String contactsName = "contactsName";

    final String diagnosis = "diagnosis";

    final String dateDiagnosed = "dateDiagnosed";

    final String treatment = "treatment";

    final String remission = "remission";

    File xmlFile;
    //constructor creating the file
    public XmlParser(Context context)
    {
        selectFile(context);
    }
    //make/select the file
    public void selectFile(Context context)
    {
        File cacheDir = context.getCacheDir();
        xmlFile = new File(cacheDir, xmlFileString);
    }

    public void saveTempDiagnosisInfo(ViewDiagnosisInformationData data,Context context)
    {
        xmlFileString = "tempMedicalData";
        selectFile(context);
        saveDiagnosisInfoXml(data);

        xmlFileString = "medicalData";
        selectFile(context);

    }


    public ViewDiagnosisInformationData readTempDiagnosisInfo(Context context)
    {
        ViewDiagnosisInformationData data;

        xmlFileString = "tempMedicalData";
        selectFile(context);
        data = readDiagnosisInfo();

        xmlFileString = "medicalData";
        selectFile(context);

        return data;
    }




    public void saveTempHealthInfo(ViewHealthInformationData savedInformation,Context context)
    {
        xmlFileString = "tempMedicalHealthData";
        selectFile(context);
        saveHealthInfoXml(savedInformation);

        xmlFileString = "MedicalHealthData";
        selectFile(context);

    }

    public ViewHealthInformationData readTempHealthInfomation(Context context)
    {
        ViewHealthInformationData tempInfodata;

        xmlFileString = "tempMedicalData";
        selectFile(context);
        tempInfodata = readHealthInfo();

        xmlFileString = "MedicalData";
        selectFile(context);

        return tempInfodata;
    }



    public void createXmlFile()           //Change to private when finished app
    {
        try {
            FileOutputStream fileos = new FileOutputStream(xmlFile);

            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);

            xmlSerializer.startDocument("UTF-8", true);

            xmlSerializer.startTag(null, "medical");

            xmlSerializer.startTag(null, "healthInfo");

            xmlSerializer.startTag(null, firstName);
            xmlSerializer.endTag(null, firstName);

            xmlSerializer.startTag(null, lastName);
            xmlSerializer.endTag(null, lastName);

            xmlSerializer.startTag(null, gender);
            xmlSerializer.endTag(null, gender);

            xmlSerializer.startTag(null, NHI);
            xmlSerializer.endTag(null, NHI);

            xmlSerializer.startTag(null, DOB);
            xmlSerializer.endTag(null, DOB);

            xmlSerializer.startTag(null, BSA);
            xmlSerializer.endTag(null, BSA);

            xmlSerializer.startTag(null, "GPDetails");

            xmlSerializer.startTag(null, Name);
            xmlSerializer.endTag(null, Name);

            xmlSerializer.startTag(null, Address);
            xmlSerializer.endTag(null, Address);

            xmlSerializer.startTag(null, Number);
            xmlSerializer.endTag(null, Number);

            xmlSerializer.endTag(null, "GPDetails");

            xmlSerializer.startTag(null, existingAllergies);
            xmlSerializer.endTag(null, existingAllergies);

            xmlSerializer.startTag(null, other);
            xmlSerializer.endTag(null, other);

            xmlSerializer.startTag(null, "OnocologyTeam");

            xmlSerializer.startTag(null, phone);
            xmlSerializer.endTag(null, phone);

            xmlSerializer.startTag(null, oncologist);
            xmlSerializer.endTag(null, oncologist);

            xmlSerializer.startTag(null, AYAKeyWorker);
            xmlSerializer.endTag(null, AYAKeyWorker);

            xmlSerializer.startTag(null, contactsName);
            xmlSerializer.endTag(null, contactsName);

            xmlSerializer.endTag(null, "OnocologyTeam");

            xmlSerializer.endTag(null, "healthInfo");

            xmlSerializer.startTag(null, "diagnosisInfo");

            xmlSerializer.startTag(null, diagnosis);
            xmlSerializer.endTag(null, diagnosis);

            xmlSerializer.startTag(null, dateDiagnosed);
            xmlSerializer.endTag(null, dateDiagnosed);

            xmlSerializer.startTag(null, treatment);
            xmlSerializer.endTag(null, treatment);

            xmlSerializer.startTag(null, remission);
            xmlSerializer.endTag(null, remission);

            xmlSerializer.endTag(null, "diagnosisInfo");

            xmlSerializer.startTag(null, "medications");
            xmlSerializer.endTag(null, "medications");

            xmlSerializer.startTag(null, "appointments");
            xmlSerializer.endTag(null, "appointments");

            xmlSerializer.startTag(null, "PotentialLongTermEffects");
            xmlSerializer.endTag(null, "PotentialLongTermEffects");


            xmlSerializer.endTag(null, "medical");

            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            fileos.write(dataWrite.getBytes());
            fileos.close();

        }catch (Exception e){}
    }

    public void saveDiagnosisInfoXml(ViewDiagnosisInformationData diagnosisInfo)
    {
        {
            //checks to see if there is an xml file before trying to edit/add to it
            if(!xmlFile.exists())
            {
                createXmlFile();
            }
            try {
                //Get a doc file for editing the xml
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(xmlFile);

                //Write all the data to the doc by tag name REQUIRES there to be only one tag in the xmlFile for example this will ONLY work if there is one <firstName></firstName>

                doc.getElementsByTagName(diagnosis).item(0).setTextContent(diagnosisInfo.Diagnosis);

                doc.getElementsByTagName(dateDiagnosed).item(0).setTextContent(diagnosisInfo.DateDiagnosed);

                doc.getElementsByTagName(treatment).item(0).setTextContent(diagnosisInfo.Treatment);

                doc.getElementsByTagName(remission).item(0).setTextContent(diagnosisInfo.Remission);

                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(xmlFile);
                transformer.transform(source, result);

            }catch (Exception e){}
        }
    }
    //Returns a class instance of the data in the xml file for the health info
    public ViewDiagnosisInformationData readDiagnosisInfo() {

         ViewDiagnosisInformationData DiagnosisData = new ViewDiagnosisInformationData();
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            DiagnosisData.Diagnosis = doc.getElementsByTagName(diagnosis).item(0).getTextContent();

            DiagnosisData.DateDiagnosed = doc.getElementsByTagName(dateDiagnosed).item(0).getTextContent();

            DiagnosisData.Treatment = doc.getElementsByTagName(treatment).item(0).getTextContent();

            DiagnosisData.Remission = doc.getElementsByTagName(remission).item(0).getTextContent();

        }catch(Exception e){}

        return DiagnosisData;
    }


    public ArrayList<MedicationEntry> readMdications()
    {
        ArrayList<MedicationEntry> medList = new ArrayList<MedicationEntry>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            NodeList medicationList = doc.getElementsByTagName("medication");

            MedicationEntry currentMed = new MedicationEntry();

            for (int i = 0; i < medicationList.getLength(); i++) {

                Node med=medicationList.item(i);

                currentMed.medName = med.getChildNodes().item(0).getTextContent();
                currentMed.cullmativeDossage = med.getChildNodes().item(1).getTextContent();
                currentMed.dossage = med.getChildNodes().item(2).getTextContent();
                medList.add(currentMed);
            }

        }catch (Exception e)
        {
            Log.i("Error", "msg" + e.getMessage() + " " + e.getLocalizedMessage());
        }

        return medList;
    }


    public void newMedicationEntry(MedicationEntry medEntry)
    {
        try {
            //get The existing XML file to edit it
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(xmlFile);

            //create a new medication NODE(tag) and apend all the other tags to it
            Element newMed = doc.createElement("medication");

            Element medName = doc.createElement("medicationName");
            medName.appendChild(doc.createTextNode(medEntry.medName));
            newMed.appendChild(medName);

            Element medCumulativeDossage = doc.createElement("cumulativeDosage");
            medCumulativeDossage.appendChild(doc.createTextNode(medEntry.cullmativeDossage));
            newMed.appendChild(medCumulativeDossage);

            Element medDossage = doc.createElement("dosage");
            medDossage.appendChild(doc.createTextNode(medEntry.dossage));
            newMed.appendChild(medDossage);

            //now append the new med node to the doc at the tag of medications
            doc.getElementsByTagName("medications").item(0).appendChild(newMed);


            //Save the file back to the xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

        }catch (Exception e){}

    }
    public void saveLongTermEffects(String longTermEffect)
    {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(xmlFile);

            Element newLongTermEffect = doc.createElement("longTermEffect");
            newLongTermEffect.appendChild(doc.createTextNode(longTermEffect));

            doc.getElementsByTagName("PotentialLongTermEffects").item(0).appendChild(newLongTermEffect);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

        }catch (Exception e){}

    }

    public void saveLongTermEffects(String[] longTermEffects)
    {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(xmlFile);

            for (String item:longTermEffects) {

                Element newLongTermEffect = doc.createElement("longTermEffect");
                newLongTermEffect.appendChild(doc.createTextNode(item));

                doc.getElementsByTagName("PotentialLongTermEffects").item(0).appendChild(newLongTermEffect);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

        }catch (Exception e){}

    }

    public void saveHealthInfoXml(ViewHealthInformationData healthInfo)
    {

        //checks to see if there is an xml file before trying to edit/add to it
        if(!xmlFile.exists())
        {
            createXmlFile();
        }
        try {
            //Get a doc file for editing the xml
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            //Write all the data to the doc by tag name REQUIRES there to be only one tag in the xmlFile for example this will ONLY work if there is one <firstName></firstName>

            doc.getElementsByTagName(firstName).item(0).setTextContent(healthInfo.FirstName);

            doc.getElementsByTagName(lastName).item(0).setTextContent(healthInfo.LastName);

            doc.getElementsByTagName(gender).item(0).setTextContent(healthInfo.Gender);

            doc.getElementsByTagName(NHI).item(0).setTextContent(healthInfo.NHINumber);

            doc.getElementsByTagName(DOB).item(0).setTextContent(healthInfo.DOB);

            doc.getElementsByTagName(BSA).item(0).setTextContent(healthInfo.BSA);

            doc.getElementsByTagName(Name).item(0).setTextContent(healthInfo.GPName);

            doc.getElementsByTagName(Address).item(0).setTextContent(healthInfo.GPAddress);

            doc.getElementsByTagName(Number).item(0).setTextContent(healthInfo.GPNumber);

            doc.getElementsByTagName(existingAllergies).item(0).setTextContent(healthInfo.Allergies);

            doc.getElementsByTagName(other).item(0).setTextContent(healthInfo.Other);

            doc.getElementsByTagName(phone).item(0).setTextContent(healthInfo.Phone);

            doc.getElementsByTagName(oncologist).item(0).setTextContent(healthInfo.Oncologist);

            doc.getElementsByTagName(AYAKeyWorker).item(0).setTextContent(healthInfo.AYAKeyWorker);



            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

        }catch (Exception e){}
    }
    /*
    Returns a class instance of the data in the xml file for the health info
     */
    public ViewHealthInformationData readHealthInfo() {

        ViewHealthInformationData data = new ViewHealthInformationData();
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            data.FirstName = doc.getElementsByTagName("firstName").item(0).getTextContent();

            data.LastName = doc.getElementsByTagName("lastName").item(0).getTextContent();

            data.Gender = doc.getElementsByTagName("gender").item(0).getTextContent();

            data.NHINumber = doc.getElementsByTagName("NHI").item(0).getTextContent();

            data.DOB = doc.getElementsByTagName("DOB").item(0).getTextContent();

            data.BSA = doc.getElementsByTagName("BSA").item(0).getTextContent();

            data.GPName = doc.getElementsByTagName("Name").item(0).getTextContent();

            data.GPAddress = doc.getElementsByTagName("Address").item(0).getTextContent();

            data.GPNumber = doc.getElementsByTagName("Number").item(0).getTextContent();

            data.Allergies = doc.getElementsByTagName("exestingAllergies").item(0).getTextContent();

            data.Other = doc.getElementsByTagName("other").item(0).getTextContent();

            data.Phone = doc.getElementsByTagName("phone").item(0).getTextContent();

            data.Oncologist = doc.getElementsByTagName("Oncologist").item(0).getTextContent();

            data.AYAKeyWorker = doc.getElementsByTagName("AYAKeyWorker").item(0).getTextContent();



        }catch(Exception e){}

        return data;
    }

    public void readAllInfoToLog() {
        try {

            FileInputStream fis = new FileInputStream(xmlFile);

            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            isr.close();
            fis.close();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(data));

            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    Log.i("xmlParser", "Start document");
                } else if (eventType == XmlPullParser.END_DOCUMENT) {
                    Log.i("xmlParser","End document");
                } else if (eventType == XmlPullParser.START_TAG) {
                    Log.i("xmlParser","Start tag " + xpp.getName());
                } else if (eventType == XmlPullParser.END_TAG) {
                    Log.i("xmlParser","End tag " + xpp.getName());
                } else if (eventType == XmlPullParser.TEXT) {
                    Log.i("xmlParser","Text " + xpp.getText());
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {}
    }
}
