package com.gao.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author gs
 * @Date created time 2019/4/29 18:40
 * @Description
 */
public class BaseController {

    protected static void setPageSort(Page page , BaseVO vo){
        if(!CollectionUtils.isEmpty(vo.getSortList())){
            List<String> list = new ArrayList<>();
            //驼峰转下划线
            for(int i = 0; i<vo.getSortList().size();i++){
                list.add(humpToLine(vo.getSortList().get(i)));
            }
            if(null != vo.getSortType() && "desc".equals(vo.getSortType())){
                page.setDescs(list);
            }else {
                page.setAscs(list);
            }
        }
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /** 驼峰转下划线 */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
