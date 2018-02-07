package com.apnozhan.villa.requester;

import com.apnozhan.villa.models.Model;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Lenovo on 12/16/2017.
 * request wrapper class
 */

public class Request<E extends Model> {
    public static final int METHOD_POST = 1;
    public static final int METHOD_GET = 2;
    public static final int RESPONSE_NO_INTERNET = -1;
    public static final int STATUS_OK = 200;
    public static final int STATUS_CREATED = 201;
    public static final int STATUS_NOT_ACCEPTABLE = 403;
    public static int RESPONSE_NOT_AUTHENTICATED = 401;

    private int id;
    private int method;
    private String url;
    private JSONObject jsonParams;
    private RequestParams requestParams;
    private String fileKey;
    private String fileType;
    private File file;
    private boolean authenticated;
    private static int nextID = 1;

    private Class<E> objectsClass;
    private ResponseWaiter responseWaiter;
    private ModelWaiter<E> modelWaiter;
    private ListWaiter<E> listWaiter;
    private PaginatedListWaiter<E> paginatedListWaiter;


    private Request(int method, String url) {
        this.id = nextID++;
        this.method = method;
        this.url = url;
        authenticated = true;
    }

    public Request(int method, String url, ResponseWaiter waiter) {
        this(method, url);
        this.responseWaiter = waiter;
    }

    public Request(int method, String url, ResponseWaiter waiter, JSONObject jsonParams) {
        this(method, url, waiter);
        this.jsonParams = jsonParams;
    }

    public Request(int method, String url, ResponseWaiter waiter, RequestParams requestParams) {
        this(method, url, waiter);
        this.requestParams = requestParams;
    }


    public Request(int method, String url, Class<E> objectsClass, ModelWaiter<E> modelWaiter) {
        this(method, url);
        this.modelWaiter = modelWaiter;
        this.objectsClass = objectsClass;
    }

    public Request(int method, String url, Class<E> objectsClass, ModelWaiter<E> modelWaiter, RequestParams params) {
        this(method, url, objectsClass, modelWaiter);
        this.requestParams = params;
    }

    public Request(int method, String url, Class<E> objectsClass, ModelWaiter<E> modelWaiter, JSONObject jsonParams) {
        this(method, url, objectsClass, modelWaiter);
        this.jsonParams = jsonParams;
    }


    public Request(int method, String url, Class<E> objectsClass, ListWaiter<E> listWaiter) {
        this(method, url);
        this.listWaiter = listWaiter;
        this.objectsClass = objectsClass;
    }

    public Request(int method, String url, Class<E> objectsClass, ListWaiter<E> listWaiter, RequestParams params) {
        this(method, url, objectsClass, listWaiter);
        this.requestParams = params;
    }

    public Request(int method, String url, Class<E> objectsClass, ListWaiter<E> listWaiter, JSONObject jsonParams) {
        this(method, url, objectsClass, listWaiter);
        this.jsonParams = jsonParams;
    }


    public Request(int method, String url, Class<E> objectsClass, PaginatedListWaiter<E> paginatedListWaiter) {
        this(method, url);
        this.paginatedListWaiter = paginatedListWaiter;
        this.objectsClass = objectsClass;
    }

    public Request(int method, String url, Class<E> objectsClass, PaginatedListWaiter<E> paginatedListWaiter, RequestParams params) {
        this(method, url, objectsClass, paginatedListWaiter);
        this.requestParams = params;
    }

    public Request(int method, String url, Class<E> objectsClass, PaginatedListWaiter<E> paginatedListWaiter, JSONObject jsonParams) {
        this(method, url, objectsClass, paginatedListWaiter);
        this.jsonParams = jsonParams;
    }


    String getUrl() {
        return url;
    }

    public int getID() {
        return id;
    }

    int getMethod() {
        return method;
    }

    Class<E> getObjectsClass() {
        return objectsClass;
    }

    boolean isAuthenticated() {
        return authenticated;
    }

    public void setNonAuthenticated() {
        this.authenticated = false;
    }

    public void setRequestParams(RequestParams requestParams) {
        this.requestParams = requestParams;
    }

    Map<String, List<String>> getParamsHashMap() {

        HashMap<String, List<String>> toReturn = new HashMap<>();
        if (requestParams != null) {
            for (String key : requestParams.getParams().keySet()) {
                List<String> toPut = new ArrayList<>();
                toPut.add(requestParams.getParams().get(key));
                toReturn.put(key, toPut);
            }
        }
        return toReturn;
    }

    JSONObject getJsonParams() {
        return jsonParams;
    }

    public RequestParams getRequestParams() {
        return requestParams;
    }

    public void putFile(String key, File file, String fileType) {
        this.fileKey = key;
        this.file = file;
        this.fileType = fileType;
    }

    public File getFile() {
        return this.file;
    }

    String getFileKey() {
        return this.fileKey;
    }

    String getFileType() {
        return this.fileType;
    }

    Waiter getWaiter() {
        if (responseWaiter != null) {
            return responseWaiter;
        } else if (modelWaiter != null) {
            return modelWaiter;
        } else if (listWaiter != null) {
            return listWaiter;
        } else if (paginatedListWaiter != null) {
            return paginatedListWaiter;
        } else {
            return null;
        }
    }

    ListWaiter<E> getListWaiter() {
        return listWaiter;
    }

    ResponseWaiter getResponseWaiter() {
        return responseWaiter;
    }

    ModelWaiter<E> getModelWaiter() {
        return modelWaiter;
    }

    PaginatedListWaiter<E> getPaginatedListWaiter() {
        return paginatedListWaiter;
    }
    public void setResponseWaiter(ResponseWaiter responseWaiter){
        this.responseWaiter = responseWaiter;
    }

}
