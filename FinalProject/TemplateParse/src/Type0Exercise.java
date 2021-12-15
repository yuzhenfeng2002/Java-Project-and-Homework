import org.w3c.dom.*;

public class Type0Exercise extends BasicExercise {
    public Type0Exercise(Element exercise) {
        Element question = (Element) exercise.getElementsByTagName("question").item(0);
        Element answer = (Element) exercise.getElementsByTagName("answer").item(0);
        setContent("问答题\n" + question.getElementsByTagName("main").item(0).getFirstChild().getNodeValue());
        setAnswer(answer.getFirstChild().getNodeValue());
    }

    public String toXML()
    {

    }
}
