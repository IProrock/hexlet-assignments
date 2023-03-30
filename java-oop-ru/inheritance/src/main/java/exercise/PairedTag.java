package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String name;
    private Map<String, String> atributes;
    private String body;
    private List<Tag> listOfSingleTags;

    public PairedTag(String name, Map<String, String> atributes, String body, List<Tag> listOfSingleTags) {
        this.name = name;
        this.atributes = atributes;
        this.body = body;
        this.listOfSingleTags = listOfSingleTags;
    }

    @Override
    public String toString() {
        String secPart = "";

        if (!this.atributes.isEmpty()) {
            for (Map.Entry<String, String> entry : atributes.entrySet()) {
                secPart = secPart + " " + entry.getKey() + "=\"" + entry.getValue() + "\"";
            }
        }

        String thirdPart = "";
        for (Tag tag : listOfSingleTags) {
            thirdPart = thirdPart + tag.toString();
        }

        return "<" + this.name + secPart + ">" + thirdPart + body + "</" + this.name + ">";
    }
}
// END
