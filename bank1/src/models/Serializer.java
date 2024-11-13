package models;

import java.io.*;
import java.util.Base64;

public class Serializer {
    public  static  String serialize(Serializable obj) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);

        objectStream.writeObject(obj);

        return Base64.getEncoder().encodeToString(byteStream.toByteArray());
    }

    public static Object deserialize(String base64String) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(base64String);

        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));

        return objectInputStream.readObject();
    }
}
