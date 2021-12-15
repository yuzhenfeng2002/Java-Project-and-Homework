import java.io.*;
import java.util.*;

import org.w3c.dom.*;

import javax.xml.parsers.*;

public class Main {

    public static void main(String[] args) {
        try {
            File f = new File("test.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            NodeList nl = doc.getElementsByTagName("exercise");
            for (int i = 0; i < nl.getLength(); i++) {
                Element exercise = (Element) nl.item(i);
                String type = exercise.getElementsByTagName("type").item(0).getFirstChild().getNodeValue();
                switch (Integer.parseInt(type) + 1) {
                    case 1: {
                        Type0Exercise x = new Type0Exercise(exercise);
                        System.out.println(x.getContent());
                        System.out.println(x.getAnswer());
                        break;
                    }
                    case 2: {
                        Type1Exercise x = new Type1Exercise(exercise);
                        System.out.println(x.getContent());
                        System.out.println(x.getAnswer());
                        break;
                    }
                    case 3: {
                        Type2Exercise x = new Type2Exercise(exercise);
                        System.out.println(x.getContent());
                        System.out.println(x.getAnswer());
                        break;
                    }
                    case 4: {
                        Type3Exercise x = new Type3Exercise(exercise);
                        System.out.println(x.getContent());
                        System.out.println(x.getAnswer());
                        break;
                    }
                    case 5: {
                        Type4Exercise x = new Type4Exercise(exercise);
                        System.out.println(x.getContent());
                        System.out.println(x.getAnswer());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
