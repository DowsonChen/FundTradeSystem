package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
	public abstract String getName();

	public abstract String perform(HttpServletRequest request);

	private static Map<String, Action> hash = new HashMap<String, Action>();

	public static void add(Action a) {
		synchronized (hash) {

			if (hash.get(a.getName()) != null) {
				throw new AssertionError("Two actions with the same name (" + a.getName() + "): "
						+ a.getClass().getName() + " and " + hash.get(a.getName()).getClass().getName());
			}

			hash.put(a.getName(), a);
		}
	}

	public static String perform(String name, HttpServletRequest request) {
		Action a;
		synchronized (hash) {
			a = hash.get(name);
		}

		if (a == null)
			return null;
		return a.perform(request);
	}
}
