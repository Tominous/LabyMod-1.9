import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class jz$c
{
  private final String a;
  private final int b;
  
  public jz$c(String ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public String a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.b;
  }
  
  public static class a
    implements JsonDeserializer<jz.c>, JsonSerializer<jz.c>
  {
    public jz.c a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      JsonObject ☃ = od.m(☃, "version");
      return new jz.c(od.h(☃, "name"), od.n(☃, "protocol"));
    }
    
    public JsonElement a(jz.c ☃, Type ☃, JsonSerializationContext ☃)
    {
      JsonObject ☃ = new JsonObject();
      ☃.addProperty("name", ☃.a());
      ☃.addProperty("protocol", Integer.valueOf(☃.b()));
      return ☃;
    }
  }
}
