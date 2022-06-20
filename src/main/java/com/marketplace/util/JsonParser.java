package com.marketplace.util;

import java.io.StringReader;
import java.time.LocalDateTime;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser {

	/**
	 * Instancia de la clase
	 */
	private static Gson instance;

	/**
	 * Crea una instancia de tipo Gson con el adapter de fecha
	 * @return instancia de tipo Gson
	 */
	private static Gson getGson() {
		if(instance == null) {
			instance = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDateTime.class, new DateAdapter())
					.create();
		}
		return instance;
	}

	/**
	 * Convierte un string en formato json a una clase especifica
	 * @param json string a convertir
	 * @param destinationClass clase a la cual va a ser parseao el json
	 * @return objeto de clase
	 */
	public static <T> T parseJson(String json, Class<T> destinationClass) {
		return getGson().fromJson(json, destinationClass);
	}

	/**
	 * crea un JSON string con la informacion del objeto
	 * @param obj a convertir
	 * @return JSON string con info del objeto
	 */
	public static String toJson(Object obj) {
		return getGson().toJson(obj);
	}

	/**
	 * Crea un JsonObject con el contenido del JSON string
	 * @param jsonString string en formato JSON
	 * @return JsonObject con la info del string
	 */
	public static JsonObject toJsonObject(String jsonString) {
		JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
		JsonObject object = jsonReader.readObject();
		jsonReader.close();
		return object;
	}

	/**
	 * convierte un objeto a un JsonObject
	 * @param obj java
	 * @return JsonObject 
	 */
	public static JsonObject toJsonObject(Object obj) {
		return toJsonObject(toJson(obj));
	}
}