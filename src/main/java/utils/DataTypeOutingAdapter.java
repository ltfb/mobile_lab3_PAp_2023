package utils;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import logic.DataTypeOuting;

public class DataTypeOutingAdapter implements JsonSerializer<DataTypeOuting>, JsonDeserializer<DataTypeOuting> {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	@Override
	public JsonElement serialize(DataTypeOuting outing, Type type, JsonSerializationContext jsonSerializationContext) {
		JsonObject outingObject = new JsonObject();
		outingObject.addProperty("name", outing.getName());
		outingObject.addProperty("date", outing.getDate());
		outingObject.addProperty("hour", outing.getHour());
		outingObject.addProperty("maxTourists", outing.getMaxTourist());
		outingObject.addProperty("place", outing.getPlace());
		outingObject.addProperty("freePlaces", outing.getFreePlaces());

		// Serializar el LocalDateTime en el formato deseado
		outingObject.addProperty("entryDate", formatter.format(outing.getEntryDate()));

		String imageBase64 = null;
		if (outing.getImage() != null) {
			// Serializar imagen en el formato deseado
			imageBase64 = Base64.getEncoder().encodeToString(outing.getImage());
		}
		outingObject.addProperty("image", imageBase64);

		return outingObject;
	}

	@Override
	public DataTypeOuting deserialize(JsonElement jsonElement, Type type,
			JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject outingObject = jsonElement.getAsJsonObject();
		String name = outingObject.get("name").getAsString();
		String date = outingObject.get("date").getAsString();
		String hour = outingObject.get("hour").getAsString();
		int maxTourists = outingObject.get("maxTourists").getAsInt();
		String place = outingObject.get("place").getAsString();
		int freePlaces = outingObject.get("freePlaces").getAsInt();

		// Deserializar el LocalDateTime desde la cadena
		LocalDateTime entryDate = LocalDateTime.parse(outingObject.get("entryDate").getAsString(), formatter);

		// Deserializar el atributo 'image' desde una cadena Base64
		byte[] image = null;
		if (outingObject.get("image") != null) {
			image = Base64.getDecoder().decode(outingObject.get("image").getAsString());
		}
		return new DataTypeOuting(name, date, hour, maxTourists, place, entryDate, image);
	}
}
