package rjr.studio.passgate.utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectUtility {

	@SuppressWarnings("unchecked")
    public static <T> T mergeOldNew(T entityOld, T entityNew) throws InstantiationException, IllegalAccessException, Exception {
        T returnValue = null;
        if (entityOld.getClass().equals(entityNew.getClass())) {
            Class<?> clazz = entityNew.getClass();
            
            //TODO: RJR da verificare e sostituire il metodo deprecato
            returnValue = (T) clazz.newInstance();
            while (clazz != Object.class) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {

                    if (!java.lang.reflect.Modifier.isFinal(field.getModifiers())) {

                        field.setAccessible(true);
                        Object value1 = field.get(entityOld);
                        Object value2 = field.get(entityNew);
                        Object value = (value2 != null) ? value2 : value1;
                        field.set(returnValue, value);
                    }
                }
                clazz = clazz.getSuperclass();
            }
		} else {
			throw new Exception("I due oggetti devono essere dello stesso tipo: " + entityOld.getClass().getName()
					+ "!= " + entityNew.getClass().getName());
        }
		return returnValue;
	}

	public static List<String> stringToListString(String s, String divider) {
		List<String> rtn = new ArrayList<String>();

		if (null != s && !s.trim().isEmpty()) {
			rtn = Arrays.asList(s.split(divider, -1));
        }

		return rtn;
    }
	
}
