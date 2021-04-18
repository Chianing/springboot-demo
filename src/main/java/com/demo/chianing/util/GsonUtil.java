package com.demo.chianing.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.util.*;

public class GsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(GsonUtil.class);

    private static final Gson gson = initGson();

    /* List<JsonElement>类型 */
    public static final Type TYPE_JSON_ELEMENT_LIST = new TypeToken<ArrayList<JsonElement>>() {
    }.getType();
    /* List<JsonObject>类型 */
    public static final Type TYPE_JSON_OBJECT_LIST = new TypeToken<ArrayList<JsonObject>>() {
    }.getType();
    /* Map<String, Object>类型 */
    public static final Type TYPE_STRING_OBJECT_MAP = new TypeToken<Map<String, Object>>() {
    }.getType();

    /* json对象 class列表 */
    private static final List<Class<?>> CLASS_LIST_JSON_ELEMENT = Arrays.asList(JsonObject.class, JsonArray.class);
    /* 基本数据类型 class列表 */
    private static final List<Class<?>> CLASS_LIST_PRIMITIVE = Arrays.asList(String.class, Integer.class, Long.class, Byte.class, Boolean.class);

    private static Gson initGson() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .serializeNulls()
                .setDateFormat(DateUtil.YYYY_MM_DD_HH_MM_SS)
                // 枚举类序列化规则
                .registerTypeHierarchyAdapter(Enum.class, (JsonSerializer<Enum<?>>) (src, clazz, context) -> new JsonPrimitive(src.toString()))
                // 枚举类反序列化规则
                .registerTypeHierarchyAdapter(Enum.class, (JsonDeserializer<Enum<?>>) (json, clazz, context) -> getAnEnum(json, clazz));

        return gsonBuilder.create();

    }

    private static Enum<? extends Enum<?>> getAnEnum(JsonElement json, Type clazz) {
        try {
            // 必须是基本数据类型
            if (json.isJsonPrimitive()) {
                JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();

                // 反射读取所有得枚举实例
                Enum<?>[] enumConstants = (Enum<?>[]) Class.forName(clazz.getTypeName()).getEnumConstants();

                if (jsonPrimitive.isNumber()) { // 数字
                    return enumConstants[jsonPrimitive.getAsInt()];
                } else if (jsonPrimitive.isString()) { // 字符串
                    String val = jsonPrimitive.getAsString();
                    for (Enum<?> constant : enumConstants) {
                        if (constant.name().equalsIgnoreCase(val)) {
                            return constant;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | ArrayIndexOutOfBoundsException e) {
            logger.error("gson format error", e);
        }
        throw new IllegalArgumentException("bad param:" + json.getAsString());
    }

    /**
     * 任意对象转换为json字符串
     */
    public static String toJsonString(Object javaObject) {
        return gson.toJson(javaObject);
    }

    /**
     * 获取json对象
     */
    public static JsonObject toJsonObject(String json) {
        return toObject(json, JsonObject.class);
    }

    /**
     * java对象转换为json对象
     */
    public static JsonObject toJsonObject(Object javaObject) {
        String jsonStr = toJsonString(javaObject);
        return toJsonObject(jsonStr);
    }

    /**
     * 获取json对象列表
     */
    public static List<JsonObject> toJsonObjectList(String json) {
        return toObject(json, TYPE_JSON_OBJECT_LIST);
    }

    /**
     * 获取json对象列表
     */
    public static List<JsonObject> toJsonObjectList(JsonObject jsonObject) {
        return jsonObject == null ? null : toJsonObjectList(jsonObject.toString());
    }

    /**
     * 获取json对象列表
     */
    public static List<JsonElement> toJsonElementList(String json) {
        return toObject(json, TYPE_JSON_ELEMENT_LIST);
    }

    /**
     * 获取json对象列表
     */
    public static List<JsonElement> toJsonElementList(JsonObject jsonObject) {
        return jsonObject == null ? null : toJsonElementList(jsonObject.toString());
    }

    /**
     * json字符串转为对象
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        return (StringUtil.isBlank(json) || clazz == null) ?
                null : gson.fromJson(json, clazz);
    }

    /**
     * json字符串转为对象
     */
    public static <T> T toObject(String json, Type type) {
        return (StringUtil.isBlank(json) || type == null) ?
                null : gson.fromJson(json, type);
    }

    /**
     * object转为java对象
     */
    public static <T> T toObject(Object javaObject, Class<T> clazz) {
        return toObject(toJsonString(javaObject), clazz);
    }

    /**
     * json字符串转为map对象
     */
    public static Map<String, Object> toMap(String json) {
        return toObject(json, TYPE_STRING_OBJECT_MAP);
    }

    /**
     * json对象转为java对象
     */
    public static <T> T toObject(JsonElement jsonElement, Class<T> clazz) {
        return (jsonElement == null || clazz == null) ?
                null : gson.fromJson(jsonElement, clazz);
    }

    /**
     * json字符串转为对象列表
     */
    public static <T> List<T> toObjectList(String json, Class<T> clazz) {
        if (StringUtil.isBlank(json) || clazz == null) {
            return Collections.emptyList();
        }

        List<JsonElement> jsonElements = toJsonElementList(json);
        if (CollectionUtils.isEmpty(jsonElements)) {
            return Collections.emptyList();
        }

        List<T> resultList = new ArrayList<>();
        jsonElements.forEach(jsonElement -> resultList.add(toObject(jsonElement, clazz)));
        return resultList;

    }

    public static <T> List<T> toObjectList(JsonObject jsonObject, Class<T> clazz) {
        return (jsonObject == null || clazz == null) ?
                Collections.emptyList() : toObjectList(jsonObject.toString(), clazz);
    }

    public static String getString(JsonObject jsonObject, String fieldName) {
        Object result = getFromJsonObject(jsonObject, fieldName, String.class);
        return result == null ?
                null : (String) result;
    }

    public static String getString(String json, String fieldName) {
        return getString(toJsonObject(json), fieldName);
    }

    public static String getString(Object javaObject, String fieldName) {
        return (javaObject == null || StringUtil.isBlank(fieldName)) ?
                null : getString(toJsonString(javaObject), fieldName);
    }

    public static Integer getInteger(JsonObject jsonObject, String fieldName) {
        Object result = getFromJsonObject(jsonObject, fieldName, Integer.class);
        return result == null ? null : (Integer) result;
    }

    public static Integer getInteger(String json, String fieldName) {
        return getInteger(toJsonObject(json), fieldName);
    }

    public static Integer getInteger(Object javaObject, String fieldName) {
        return (javaObject == null || StringUtil.isBlank(fieldName)) ?
                null : getInteger(toJsonString(javaObject), fieldName);
    }

    public static Long getLong(JsonObject jsonObject, String fieldName) {
        Object result = getFromJsonObject(jsonObject, fieldName, Long.class);
        return result == null ? null : (Long) result;
    }

    public static Long getLong(String json, String fieldName) {
        return getLong(toJsonObject(json), fieldName);
    }

    public static Long getLong(Object javaObject, String fieldName) {
        return (javaObject == null || StringUtil.isBlank(fieldName)) ?
                null : getLong(toJsonString(javaObject), fieldName);
    }

    public static Byte getByte(JsonObject jsonObject, String fieldName) {
        Object result = getFromJsonObject(jsonObject, fieldName, Byte.class);
        return result == null ? null : (Byte) result;
    }

    public static Byte getByte(String json, String fieldName) {
        return getByte(toJsonObject(json), fieldName);
    }

    public static Byte getByte(Object javaObject, String fieldName) {
        return (javaObject == null || StringUtil.isBlank(fieldName)) ?
                null : getByte(toJsonString(javaObject), fieldName);
    }

    public static Boolean getBoolean(JsonObject jsonObject, String fieldName) {
        Object result = getFromJsonObject(jsonObject, fieldName, Boolean.class);
        return result == null ? null : (Boolean) result;
    }

    public static Boolean getBoolean(String json, String fieldName) {
        return getBoolean(toJsonObject(json), fieldName);
    }

    public static Boolean getBoolean(Object javaObject, String fieldName) {
        return (javaObject == null || StringUtil.isBlank(fieldName)) ?
                null : getBoolean(toJsonString(javaObject), fieldName);
    }

    public static JsonObject getJsonObject(JsonObject jsonObject, String fieldName) {
        Object result = getFromJsonObject(jsonObject, fieldName, JsonObject.class);
        return result == null ? null : (JsonObject) result;
    }

    public static JsonObject getJsonObject(String json, String fieldName) {
        return getJsonObject(toJsonObject(json), fieldName);
    }

    public static JsonObject getJsonObject(Object object, String fieldName) {
        return (object == null || StringUtil.isBlank(fieldName)) ?
                null : getJsonObject(toJsonString(object), fieldName);
    }


    public static JsonArray getJsonArray(JsonObject jsonObject, String fieldName) {
        Object result = getFromJsonObject(jsonObject, fieldName, JsonArray.class);
        return result == null ? null : (JsonArray) result;
    }

    public static JsonArray getJsonArray(String json, String fieldName) {
        return getJsonArray(toJsonObject(json), fieldName);
    }

    public static JsonArray getJsonArray(Object object, String fieldName) {
        return (object == null || StringUtil.isBlank(fieldName)) ?
                null : getJsonArray(toJsonString(object), fieldName);
    }

    /**
     * 从jsonObject中获取对象
     */
    private static Object getFromJsonObject(JsonObject jsonObject, String fieldName, Class<?> clazz) {
        if (jsonObject == null || StringUtil.isBlank(fieldName) || clazz == null) {
            return null;
        }

        try {
            if (CLASS_LIST_JSON_ELEMENT.contains(clazz)) {
                return getJsonElement(jsonObject, fieldName, clazz);
            }
            if (CLASS_LIST_PRIMITIVE.contains(clazz)) {
                return getPrimitiveObject(jsonObject, fieldName, clazz);
            }
            return null;
        } catch (Exception e) {
            logger.error("获取json字段异常, jsonObject: {}, fieldName: {}, clazz: {}",
                    jsonObject.toString(), fieldName, clazz.getName(), e);
            return null;
        }

    }

    /**
     * 从jsonObject中获取json类型对象
     */
    private static Object getJsonElement(JsonObject jsonObject, String fieldName, Class<?> clazz) {
        JsonElement jsonElement = jsonObject.get(fieldName);
        if (jsonElement == null) {
            return null;
        }
        if (clazz == JsonObject.class) {
            return jsonElement.getAsJsonObject();
        } else if (clazz == JsonArray.class) {
            return jsonElement.getAsJsonArray();
        }
        return null;
    }

    /**
     * 从jsonObject中获取基本数据类型对象
     */
    private static Object getPrimitiveObject(JsonObject jsonObject, String fieldName, Class<?> clazz) {
        JsonElement jsonElement = jsonObject.get(fieldName);
        if (jsonElement == null || !jsonElement.isJsonPrimitive()) {
            return null;
        }
        JsonPrimitive field = jsonElement.getAsJsonPrimitive();
        if (field == null) {
            return null;
        }

        if (clazz == String.class) {
            return field.getAsString();
        } else if (clazz.equals(Integer.class)) {
            return field.getAsInt();
        } else if (clazz.equals(Long.class)) {
            return field.getAsLong();
        } else if (clazz.equals(Byte.class)) {
            return field.getAsByte();
        } else if (clazz.equals(Boolean.class)) {
            return field.getAsBoolean();
        } else {
            return null;
        }
    }


}
