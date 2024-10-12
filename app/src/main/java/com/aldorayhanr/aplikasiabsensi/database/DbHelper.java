package com.aldorayhanr.aplikasiabsensi.database;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DbHelper {
    private static final String BASE_URL = "http://192.168.18.12/LoginRegister/login-registration-android/";
    private static final String LOGIN_URL = BASE_URL + "login.php";
    private static final String REGISTER_URL = BASE_URL + "register.php";
    private static final String INSERT_URL = BASE_URL + "insert.php";  // Sesuaikan URL insert di server
    private static final String DELETE_URL = BASE_URL + "delete.php";  // Sesuaikan URL delete di server
    private static final String SELECT_URL = BASE_URL + "select.php";  // Sesuaikan URL select di server
    private static final String UPDATE_URL = BASE_URL + "update.php";  // Sesuaikan URL update di server

    private RequestQueue requestQueue;

    public DbHelper(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }
    // Metode untuk insert user data ke server
    public void insertUserData(String nipNim, String nama, String password, String role, final VolleyCallback callback) {
        StringRequest insertRequest = new StringRequest(Request.Method.POST, INSERT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            callback.onSuccess(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError("Error parsing response");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError("Error: " + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nip_nim", nipNim);
                params.put("nama", nama);
                params.put("password", password);
                params.put("role", role);
                return params;
            }
        };

        requestQueue.add(insertRequest);
    }

    // Metode untuk update user data di server
    public void updateUserData(String nipNim, String nama, String password, String role, final VolleyCallback callback) {
        StringRequest updateRequest = new StringRequest(Request.Method.POST, UPDATE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            callback.onSuccess(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError("Error parsing response");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError("Error: " + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nip_nim", nipNim);  // Menggunakan nip/nim sebagai identifikasi user
                params.put("nama", nama);        // Data yang diperbarui
                params.put("password", password);
                params.put("role", role);
                return params;
            }
        };

        requestQueue.add(updateRequest);
    }

    // Metode untuk delete user data dari server
    public void deleteUserData(String nipNim, final VolleyCallback callback) {
        StringRequest deleteRequest = new StringRequest(Request.Method.POST, DELETE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            callback.onSuccess(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError("Error parsing response");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError("Error: " + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nip_nim", nipNim);  // Menggunakan nip/nim sebagai identifikasi user
                return params;
            }
        };

        requestQueue.add(deleteRequest);
    }

    // Metode untuk select user data dari server
    public void selectUserData(String nipNim, final VolleyCallback callback) {
        StringRequest selectRequest = new StringRequest(Request.Method.POST, SELECT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            callback.onSuccess(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError("Error parsing response");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError("Error: " + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nip_nim", nipNim);  // Menggunakan nip/nim sebagai identifikasi user
                return params;
            }
        };

        requestQueue.add(selectRequest);
    }

    // Interface untuk callback Volley
    public interface VolleyCallback {
        void onSuccess(JSONObject result);
        void onError(String error);
    }


}
