import org.w3c.dom.Element;

public class Type4Exercise extends BasicExercise {
    public Type4Exercise(Element exercise) {
        Element question = (Element) exercise.getElementsByTagName("question").item(0);
        Element answer = (Element) exercise.getElementsByTagName("answer").item(0);
        setContent("程序分析题\n" + question.getElementsByTagName("main").item(0).getFirstChild().getNodeValue());
        setAnswer(answer.getFirstChild().getNodeValue());
    }
}
