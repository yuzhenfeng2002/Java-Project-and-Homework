package com.Yuzhen.ExerciseOnline.auxiliary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Auxiliary {
    public static String modifyContent(String content) {
        StringBuilder modifiedContent = new StringBuilder();
        boolean isInMath = false;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '$' && (i + 1 >= content.length() || content.charAt(i + 1) != '$')) {
                if (!isInMath) {
                    modifiedContent.append("![](https://www.zhihu.com/equation?tex=");
                    isInMath = true;
                } else {
                    modifiedContent.append(')');
                    isInMath = false;
                }
            }
            if (content.charAt(i) == '$')
                continue;
            if (!isInMath) {
                modifiedContent.append(content.charAt(i));
            } else {
                char c = content.charAt(i);
                if (c == (char) 10 || c == (char) 13)
                    continue;
                if (Character.isLetterOrDigit(c)) {
                    modifiedContent.append(content.charAt(i));
                } else {
                    modifiedContent.append('%').append(Integer.toHexString((int) c));
                }
            }
        }
        return modifiedContent.toString();
    }

    public static Map<String, Object> modifyRatioExercise(String content)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        String findStr = "<opt>";
        int lastIndex = 0;
        Integer count = 0;
        while (lastIndex != -1) {
            lastIndex = content.indexOf(findStr, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }
        result.put("opt_num", count);
        result.put("modified_str", content.replace("<opt>", "- "));
        return result;
    }

    public static String getNewFileName(String oldFileName) {
        int lastIndex = oldFileName.lastIndexOf(".");
        String fileType = oldFileName.substring(lastIndex);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmssSSS");
        String time = sdf.format(now);
        String newFileName = time + fileType;
        return newFileName;
    }
}
