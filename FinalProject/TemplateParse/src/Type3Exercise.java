import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Objects;

public class Type3Exercise extends BasicExercise {
    private ArrayList<String> answerList;
    public Type3Exercise(Element exercise) {
        Element question = (Element) exercise.getElementsByTagName("question").item(0);
        Element answer = (Element) exercise.getElementsByTagName("answer").item(0);
        setContent("填空题\n" + question.getElementsByTagName("main").item(0).getFirstChild().getNodeValue());
        StringBuilder answers = new StringBuilder();
        this.answerList = new ArrayList<String>();
        NodeList answerNodes = answer.getElementsByTagName("li");
        for (int i = 0; i < answerNodes.getLength(); i++) {
            Element element = (Element) answerNodes.item(i);
            this.answerList.add(element.getFirstChild().getNodeValue());
            answers.append((i + 1) + ". ").append(element.getFirstChild().getNodeValue()).append('\n');
        }
        setAnswer(answers.toString());
    }
}
