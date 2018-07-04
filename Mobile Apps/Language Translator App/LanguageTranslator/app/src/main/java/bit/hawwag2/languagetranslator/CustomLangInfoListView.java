package bit.hawwag2.languagetranslator;

    //Custom Class for the dialogue fragment
public class CustomLangInfoListView {
    int textToSpeech;
    int goBackImageButton;
    String langDefinition;

    public int getGoBackImageButton() {
        return goBackImageButton;
    }

    //Getters and setters for the custom lang object
    public int getTextToSpeech() {
        return textToSpeech;
    }

    public String getLangDefinition() {
        return langDefinition;
    }

    //Constructor for how the custom dialogue fragment will look.
    public CustomLangInfoListView(int goBackImageButton, String langDefinition,int textToSpeech)
    {
        this.goBackImageButton=goBackImageButton;
        this.textToSpeech = textToSpeech;
        this.langDefinition = langDefinition;
    }

    //custom toString for the  object list layout.
    @Override
    public String toString()
    {
        //further formatting the text
        langDefinition.split("\\r\\n|\\n|\\r");
        return langDefinition + textToSpeech;

    }

}
