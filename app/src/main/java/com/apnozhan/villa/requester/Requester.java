package com.apnozhan.villa.requester;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.apnozhan.villa.MainActivity;
import com.apnozhan.villa.models.Model;
import com.apnozhan.villa.models.Token;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.koushikdutta.ion.builder.Builders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Lenovo on 12/16/2017.
 * requester class
 */

public class Requester implements ModelWaiter<Token> {
    public static final String URL_CLIENT="client";
    public static final String URL_PICTURE = "picture";
    private String REFRESH_TOKEN = "refresh token";
    private String ACCESS_TOKEN = "access token";
    private String TOKEN_TYPE = "token type";
    private static final String BASE_URL = "https://appetizer.pro/v2/client-api/";
    public static final String URL_REGISTER = "register/";
    public static final String URL_ACTIVATE = "activate/";
    public static final String URL_DEVICE_ADD = "add-device/";
    public static final String URL_LOGIN = "login/";
    private static final String URL_REFRESH_TOKEN = "refresh-token/";
    private static final String URL_REVOKE_TOKEN = "logout/";
    public static final String URL_BASE_CONFIG = "configs/";

    private MainActivity mainActivity;
    private ArrayList<Request> pendingRequests;
    private Token token;
    private boolean handlingFailure;
    private int requestIdRefreshToken;

    public Requester(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.pendingRequests = new ArrayList<>();
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivity);
//        String accessToken = sharedPreferences.getString(ACCESS_TOKEN, null);
//        String refreshToken = sharedPreferences.getString(ACCESS_TOKEN, null);
//        String tokenType = sharedPreferences.getString(ACCESS_TOKEN, null);
        this.token = new Token();
        this.token.access_token = "Hanieh123";
        this.token.token_type = "Bearer";
        this.token.refresh_token = "SALAM SALAM HAMEGI SALAM";
//        if (accessToken != null) {
//            this.token = new Token();
//            this.token.access_token = accessToken;
//            this.token.refresh_token = refreshToken;
//            this.token.token_type = tokenType;
//        }
    }


    public void request(Request<? extends Model> request) {
        pendingRequests.add(request);
        if (request.isAuthenticated() && token == null) {
            authenticationFailure();
        } else {
            if (isInternetAvailable()) {
                Builders.Any.B b;
                final ResponseHandler handler = new ResponseHandler<>(request, this);
                switch (request.getMethod()) {
                    case Request.METHOD_GET:
                        b = Ion.with(mainActivity)
                                .load("GET", getAbsoluteUrl(request.getUrl()));
                        if (request.isAuthenticated()) {
                            b = b.setHeader("Authorization", token.toString());
                        }
                        b.addQueries(request.getParamsHashMap());
                        b.asByteArray().withResponse()
                                .setCallback(new FutureCallback<Response<byte[]>>() {
                                    @Override
                                    public void onCompleted(Exception e, Response<byte[]> result) {
                                        handler.handleResponse(e, result);
                                    }
                                });
                        break;
                    case Request.METHOD_POST:
                        b = Ion.with(mainActivity)
                                .load("POST", getAbsoluteUrl(request.getUrl()));
                        if (request.isAuthenticated()) {
                            b = b.setHeader("Authorization", token.toString());
                        }
                        if (request.getJsonParams() != null) {
                            JSONObject jsonObject = request.getJsonParams();
                            JsonParser parser = new JsonParser();
                            JsonObject o = parser.parse(jsonObject.toString()).getAsJsonObject();
                            b.setJsonObjectBody(o);

                        } else if (request.getRequestParams() != null) {
                            b.setMultipartParameters(request.getParamsHashMap());
                        }
                        if (request.getFile() != null) {
                            b.setMultipartFile(request.getFileKey(), request.getFileType(), request.getFile());
                        }

                        b.asByteArray().withResponse()
                                .setCallback(new FutureCallback<Response<byte[]>>() {
                                    @Override
                                    public void onCompleted(Exception e, Response<byte[]> result) {
                                        handler.handleResponse(e, result);
                                    }
                                });
                        break;
                }
            } else {
                requestDone(request);
                request.getWaiter().onFailure(request.getID(), Request.RESPONSE_NO_INTERNET);
            }
        }

    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null;

    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL.concat(relativeUrl);
    }

    void requestDone(Request request) {
        pendingRequests.remove(request);
    }

    private void authenticationFailure() {
        Log.e("REQUESTER","AUTH FAILURE");
        if (!handlingFailure) {
            Log.e("REQUESTER","AUTH FAILURE in if");
            this.handlingFailure = true;
            if (this.token == null) {
                Log.e("REQUESTER","NO TOKEN");
                openLoginPage();
            } else {
                RequestParams params = new RequestParams();
                params.put("refresh_token", this.token.refresh_token);
                params.put("grant_type", "refresh_token");
                Request<Token> refreshTokenRequest = new Request<>(Request.METHOD_POST, Requester.URL_REFRESH_TOKEN, Token.class, this, params);
                refreshTokenRequest.setNonAuthenticated();
                requestIdRefreshToken = refreshTokenRequest.getID();
                request(refreshTokenRequest);
            }
        }
    }

    private void openLoginPage() {
//        mainActivity.getMainViewHolder().setLoading(LoadingViewHolder.DONE);
//        RegisterAndLoginFragment fragment = new RegisterAndLoginFragment();
//        mainActivity.pushFragment(fragment, MainViewHolder.ANIMATION_PUSH);
//        Log.e("REQUESTER","PUSHED");
        //TODO
    }

    public void loginDone(Token token) {
//        this.token = token;
//        handlingFailure = false;
//        for (Request pendingRequest : pendingRequests) {
//            if (pendingRequest.getUrl().equals(Requester.URL_REVOKE_TOKEN)) {
//                String newToken = token.access_token;
//                RequestParams params = pendingRequest.getRequestParams();
//                params.put("token", newToken);
//                pendingRequest.setRequestParams(params);
//            }
//        }
//        ArrayList<Request> failedRequests = new ArrayList<>();
//        failedRequests.addAll(pendingRequests);
//        pendingRequests.clear();
//        for (Request request : failedRequests) {
//            request(request);
//        }
//        mainActivity.getMainViewHolder().setLoading(LoadingViewHolder.DONE);
        //TODO
    }

    boolean isHandlingFailure() {
        return handlingFailure;
    }

    boolean checkAuthentication(Request request, int statusCode) {
        if (!request.isAuthenticated()) {
            return true;
        } else if (statusCode == Request.RESPONSE_NOT_AUTHENTICATED) {
            authenticationFailure();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public void onFailure(int requestId, int statusCode) {
        if (requestId == requestIdRefreshToken) {
            if (statusCode == Request.RESPONSE_NOT_AUTHENTICATED) {
                openLoginPage();
            } else {
                //TODO
            }
        }
    }

    @Override
    public void onSuccess(int requestId, int statusCode, Token response) {
        loginDone(token);
    }
}

class ResponseHandler<E extends Model> {
    private Request<E> request;
    private Requester requester;

    ResponseHandler(Request<E> request, Requester requester) {
        this.request = request;
        this.requester = requester;
    }

    void handleResponse(Exception e, Response<byte[]> result) {
        if (request.getWaiter().isAlive()) {
            if (e != null) {
                requester.requestDone(request);
                request.getWaiter().onFailure(request.getID(), Request.RESPONSE_NO_INTERNET);
            } else {
                int statusCode = result.getHeaders().code();
                if (!(request.isAuthenticated() && requester.isHandlingFailure())) {
                    if (isSuccess(statusCode)) {
                        requester.requestDone(request);
                        String responseBody = new String(result.getResult());
                        Log.e("RESPONSE",responseBody);
                        if (request.getResponseWaiter() != null) {
                            request.getResponseWaiter().onSuccess(request.getID(), statusCode);
                        } else if (request.getListWaiter() != null) {
                            Gson gson = new Gson();
                            ArrayList<E> toReturn = new ArrayList<>();
                            try {
                                JSONArray response = new JSONArray(responseBody);
                                for (int i = 0; i < response.length(); i++) {
                                    E responseObject = gson.fromJson(response.getJSONObject(i).toString(), request.getObjectsClass());
                                    toReturn.add(responseObject);
                                }
                                request.getListWaiter().onSuccess(request.getID(), statusCode, toReturn);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            if (request.getPaginatedListWaiter() != null) {
                                try {
                                    JSONObject response = new JSONObject(responseBody);
                                    Gson gson = new Gson();
                                    String next = response.getString("next");
                                    String previous = response.getString("previous");
                                    ArrayList<E> arrayResult = new ArrayList<>();
                                    JSONArray resultArray = response.getJSONArray("results");
                                    for (int i = 0; i < resultArray.length(); i++) {
                                        try {
                                            JSONObject object = resultArray.getJSONObject(i);
                                            E resultObject = gson.fromJson(object.toString(), request.getObjectsClass());
                                            arrayResult.add(resultObject);
                                            i++;
                                        } catch (JSONException e1) {
                                            break;
                                        }
                                    }
                                    boolean hasNext = !(next == null || next.isEmpty() || next.equals("null"));
                                    boolean hasPrevious = !(previous == null || previous.isEmpty() || previous.equals("null"));
                                    request.getPaginatedListWaiter().onSuccess(request.getID(), statusCode, arrayResult, hasNext, hasPrevious);
                                } catch (JSONException e2) {
                                    request.getWaiter().onFailure(statusCode, request.getID());

                                }
                            } else if (request.getModelWaiter() != null) {
                                Gson gson = new Gson();
                                E responseObject = gson.fromJson(responseBody, request.getObjectsClass());
                                request.getModelWaiter().onSuccess(request.getID(), statusCode, responseObject);
                            }
                        }
                    } else if (requester.checkAuthentication(request, statusCode)) {
                        requester.requestDone(request);
                        request.getWaiter().onFailure(statusCode, request.getID());
                    }
                }


            }
        }
    }

    private boolean isSuccess(int statusCode) {
        return statusCode / 100 == 2;
    }

}
