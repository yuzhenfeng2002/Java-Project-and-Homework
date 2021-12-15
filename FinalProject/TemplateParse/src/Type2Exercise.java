import org.w3c.dom.Element;

public class Type2Exercise extends BasicExercise {
    public Type2Exercise(Element exercise) {
        Element question = (Element) exercise.getElementsByTagName("question").item(0);
        Element answer = (Element) exercise.getElementsByTagName("answer").item(0);
        setContent("判断题\n" + question.getElementsByTagName("main").item(0).getFirstChild().getNodeValue());
        setAnswer(answer.getFirstChild().getNodeValue());
    }
}
