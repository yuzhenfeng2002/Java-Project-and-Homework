package com.Yuzhen.ExerciseOnline.auxiliary;

public class Auxiliary {
    public static String modifyContent(String content)
    {
        StringBuilder modifiedContent = new StringBuilder();
        boolean isInMath = false;
        for (int i = 0; i < content.length(); i++)
        {
            if (content.charAt(i) == '$' && (i + 1 >= content.length() || content.charAt(i + 1) != '$'))
            {
                if (!isInMath)
                {
                    modifiedContent.append("![](https://www.zhihu.com/equation?tex=");
                    isInMath = true;
                }
                else
                {
                    modifiedContent.append(')');
                    isInMath = false;
                }
            }
            if (content.charAt(i) == '$')
                continue;
            if (!isInMath)
            {
                modifiedContent.append(content.charAt(i));
            }
            else
            {
                char c = content.charAt(i);
                if (c == (char) 10 || c == (char) 13)
                    continue;
                if (Character.isLetterOrDigit(c))
                {
                    modifiedContent.append(content.charAt(i));
                }
                else
                {
                    modifiedContent.append('%').append(Integer.toHexString((int) c));
                }
            }
        }
        return modifiedContent.toString();
    }
}
