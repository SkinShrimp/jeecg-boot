package org.jeecg.modules.hospital.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class Tools {
    public static HashMap parameterToMap(HttpServletRequest request) {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        Map map = request.getParameterMap();
        Set keSet = map.entrySet();
        for (Iterator itr = keSet.iterator(); itr.hasNext(); ) {
            Map.Entry me = (Map.Entry) itr.next();
            Object ok = me.getKey();
            String[] ov = (String[])me.getValue();
            if(ov != null ) {
                paramMap.put(ok.toString(), ov[0]);
            }
        }
        if(paramMap.get("pageNo")!=null){
            paramMap.put("startIndex",(Integer.parseInt(paramMap.get("pageNo").toString())-1)*Integer.parseInt(paramMap.get("pageSize").toString()));
        }
        return paramMap;
    }

    /**
     * 是否null 或者 “”
     *
     * @return
     */
    public static boolean empty(String target) {
        if (target == null || "".equals(target) || "null".equals(target)) {
            return true;
        }

        return false;

    }
}
