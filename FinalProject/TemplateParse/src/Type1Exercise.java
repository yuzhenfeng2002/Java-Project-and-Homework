import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.Objects;

public class Type1Exercise extends BasicExercise{
    private ArrayList<String> optionList;
    private Integer trueIndex = 0;
    public Type1Exercise(Element exercise) {
        StringBuilder content = new StringBuilder();
        this.optionList = new ArrayList<String>();
        Element question = (Element) exercise.getElementsByTagName("question").item(0);
        content.append(question.getElementsByTagName("main").item(0).getFirstChild().getNodeValue()).append('\n');
        Element answer = (Element) exercise.getElementsByTagName("answer").item(0);
        NodeList optionNodes = question.getElementsByTagName("option");
        for (int i = 0; i < optionNodes.getLength(); i++) {
            Element option = (Element) optionNodes.item(i);
            this.optionList.add(option.getFirstChild().getNodeValue());
            content.append((char) ((byte) 'A' + i) + ". ").append(option.getFirstChild().getNodeValue()).append('\n');
            if (Objects.equals(option.getAttribute("isTrue"), "Y")) {
                trueIndex = i;
            }
        }
        setContent("选择题\n" + content.toString());
        setAnswer(String.valueOf(((char) ((byte)'A' + trueIndex))));
    }
}
