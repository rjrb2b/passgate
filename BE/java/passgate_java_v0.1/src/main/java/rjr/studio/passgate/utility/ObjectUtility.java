package rjr.studio.passgate.utility;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ObjectUtility {

	@Deprecated
	@SuppressWarnings("unchecked")
	public static <T> T mergeOldNewDep(T entityOld, T entityNew) throws Exception {
		T returnValue = null;
		if (entityOld.getClass().equals(entityNew.getClass())) {
			Class<?> clazz = entityNew.getClass();

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

	@SuppressWarnings("unchecked")
	public static <T> T mergeOldNew(T entityOld, T entityNew) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		if (!Objects.equals(entityOld.getClass(), entityNew.getClass())) {
			throw new IllegalArgumentException("The two objects must be instances of the same class: "
					+ entityOld.getClass().getName() + " != " + entityNew.getClass().getName());
		}

		T returnValue = (T) entityOld.getClass().getDeclaredConstructor().newInstance();

		Class<?> clazz = entityNew.getClass();
		while (clazz != Object.class) {
			for (Field field : clazz.getDeclaredFields()) {
				if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())
						&& !java.lang.reflect.Modifier.isTransient(field.getModifiers())) {
					field.setAccessible(true);
					Object value1 = field.get(entityOld);
					Object value2 = field.get(entityNew);
					Object value = (value2 != null) ? value2 : value1;
					field.set(returnValue, value);
				}
			}
			clazz = clazz.getSuperclass();
		}

		return returnValue;
	}

	public static List<String> stringToListString(String s, String divider) {
		List<String> rtn = new ArrayList<>();

		if (null != s && !s.trim().isEmpty()) {
			rtn = Arrays.asList(s.split(divider, -1));
		}

		return rtn;
	}

}
