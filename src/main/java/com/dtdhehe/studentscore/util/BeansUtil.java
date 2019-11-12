package com.dtdhehe.studentscore.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author 陈姗姗
 * @version 1.0
 * @date 2019/11/15 15:34
 * @description 拷贝对象非空属性
 **/
public class BeansUtil {
    /**
     * @Description <p>获取到对象中属性为null的属性名  </P>
     * @param source 要拷贝的对象
     * @return
     */
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (StringUtils.isEmpty(srcValue)){
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * @Description <p> 拷贝非空对象属性值 </P>
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * 比较两个实体属性值，返回一个map以有差异的属性名为key，value为一个list分别存obj1,obj2此属性名的值
     * @param obj1 进行属性比较的对象1
     * @param obj2 进行属性比较的对象2
     * @param compareArr 选择比较的属性数组
     * @return 属性差异比较结果map
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, List<Object>> compareFields(Object obj1, Object obj2, String[] compareArr) {
        try{
            Map<String, List<Object>> map = new HashMap<>();
            List<String> compareList = null;
            if(compareArr != null && compareArr.length > 0){
                // array转化为list
                compareList = Arrays.asList(compareArr);
            }
            // 只有两个对象都是同一类型的才有可比性
            if (obj1.getClass() == obj2.getClass()) {
                Class clazz = obj1.getClass();
                // 获取object的属性描述
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz,Object.class).getPropertyDescriptors();
                // 遍历所有的属性了
                for (PropertyDescriptor pd : pds) {
                    // 属性名
                    String name = pd.getName();
                    // 如果当前属性选择比较
                    if(compareList != null && compareList.contains(name)){
                        // get方法
                        Method readMethod = pd.getReadMethod();
                        // 在obj1上调用get方法等同于获得obj1的属性值
                        Object o1 = readMethod.invoke(obj1);
                        // 在obj2上调用get方法等同于获得obj2的属性值
                        Object o2 = readMethod.invoke(obj2);
                        //判断是否属于时间戳
                        if(o1 instanceof Timestamp){
                            o1 = new Date(((Timestamp) o1).getTime());
                        }
                        if(o2 instanceof Timestamp){
                            o2 = new Date(((Timestamp) o2).getTime());
                        }
                        if(o1 == null && o2 == null){
                            continue;
                        }else if(o1 == null){
                            List<Object> list = new ArrayList<>();
                            list.add(null);
                            list.add(o2);
                            map.put(name, list);
                            continue;
                        }
                        // 比较这两个值是否相等,不等就可以放入map了
                        if (!o1.equals(o2)) {
                            List<Object> list = new ArrayList<>();
                            list.add(o1);
                            list.add(o2);
                            map.put(name, list);
                        }
                    }
                }
            }
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

    }
}
