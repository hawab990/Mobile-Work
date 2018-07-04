package bit.hawwag2.languagetranslator;


    //class for the custom spinner layout which will house a text view and an image
public class CustomOutPutDropDown {
    //Data members
    int outPutLangImage;
    String outPutLangName;

    //getters that allows the custom view to take the image and name from this class.
    //getter that helps
    public int getOutPutLangImage() {
        return outPutLangImage;
    }


    //getter for the flag that represents the selected language
    public String getOutPutLangName() {
        return outPutLangName;
    }

    //Constructor that allows this class to be used as an object in the spinner.
    public CustomOutPutDropDown(int outPutLangImage, String outPutLangName)
    {
        this.outPutLangImage = outPutLangImage;
        this.outPutLangName = outPutLangName;
    }


    //To string layout(and contents of spinner
    @Override
    public String toString()
    {
        return outPutLangImage + outPutLangName;

    }


}
