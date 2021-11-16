package se.lexicon.io;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JSONManager {

    private static final JSONManager INSTANCE = new JSONManager();

    public static JSONManager getInstance(){
        return INSTANCE;
    }

    private final ObjectMapper objectMapper;

    private JSONManager() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());
    }

    public <T> void  serializeToJSON(Collection<T> data, File file) {
        try{
            objectMapper.writeValue(file, data);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public <T> List<T> deserializeFromJSON(File file, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
        try{
            result = objectMapper.readValue(file, type);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return result;
    }



}
