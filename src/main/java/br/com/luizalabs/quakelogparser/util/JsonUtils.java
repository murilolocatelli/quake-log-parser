package br.com.luizalabs.quakelogparser.util;

import com.google.gson.Gson;

public class JsonUtils {

	public static String toJson(final Object src) {

		return new Gson().toJson(src);
	}
}
