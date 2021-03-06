import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;

public class azx$a
  implements JsonDeserializer<azx>, JsonSerializer<azx>
{
  public azx a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = od.m(☃, "loot item");
    String ☃ = od.h(☃, "type");
    int ☃ = od.a(☃, "weight", 1);
    int ☃ = od.a(☃, "quality", 0);
    bar[] ☃;
    bar[] ☃;
    if (☃.has("conditions")) {
      ☃ = (bar[])od.a(☃, "conditions", ☃, bar[].class);
    } else {
      ☃ = new bar[0];
    }
    if (☃.equals("item")) {
      return azv.a(☃, ☃, ☃, ☃, ☃);
    }
    if (☃.equals("loot_table")) {
      return baa.a(☃, ☃, ☃, ☃, ☃);
    }
    if (☃.equals("empty")) {
      return azu.a(☃, ☃, ☃, ☃, ☃);
    }
    throw new JsonSyntaxException("Unknown loot entry type '" + ☃ + "'");
  }
  
  public JsonElement a(azx ☃, Type ☃, JsonSerializationContext ☃)
  {
    JsonObject ☃ = new JsonObject();
    
    ☃.addProperty("weight", Integer.valueOf(☃.c));
    ☃.addProperty("quality", Integer.valueOf(☃.d));
    if (☃.e.length > 0) {
      ☃.add("conditions", ☃.serialize(☃.e));
    }
    if ((☃ instanceof azv)) {
      ☃.addProperty("type", "item");
    } else if ((☃ instanceof baa)) {
      ☃.addProperty("type", "item");
    } else if ((☃ instanceof azu)) {
      ☃.addProperty("type", "empty");
    } else {
      throw new IllegalArgumentException("Don't know how to serialize " + ☃);
    }
    ☃.a(☃, ☃);
    
    return ☃;
  }
}
