import org.w3c.dom.Element;

public class Type5Exercise extends BasicExercise {
    public Type5Exercise(Element exercise) {
        Element question = (Element) exercise.getElementsByTagName("question").item(0);
        Element answer = (Element) exercise.getElementsByTagName("answer").item(0);
        setContent("编程题\n" + question.getElementsByTagName("main").item(0).getFirstChild().getNodeValue());
        setAnswer(answer.getFirstChild().getNodeValue());
    }
}
