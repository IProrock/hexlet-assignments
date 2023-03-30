package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {
    private String name;
    private Map<String, String> atributes;

    public SingleTag(String name, Map<String, String> atributes) {
        this.name = name;
        this.atributes = atributes;
    }

    @Override
    public String toString() {

        String secPart = "";

        if (!this.atributes.isEmpty()) {
            for(Map.Entry<String, String> entry : atributes.entrySet()) {
                secPart = secPart + " " + entry.getKey() + "=\"" + entry.getValue() + "\"";
            }
        }

        String output = "<" + this.name + secPart + ">";

        return  output;
    }
}
// END
