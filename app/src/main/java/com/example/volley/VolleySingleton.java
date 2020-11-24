package com.example.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
//Con el singleton me asegura tener un solo cartero porque uno solo puede enviar todas las cartas
//Si tuviera muchos carteros utilizaría mal el singleton
public class VolleySingleton {
    private static VolleySingleton instance;
    private RequestQueue cartero;
    private ImageLoader imageLoader;
    private static Context ctx;

    private VolleySingleton(Context context) {
        ctx = context;
        cartero = getRequestQueue();

        //por el monento no
        imageLoader = new ImageLoader(cartero,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    //Crea una sola caja
    public static synchronized VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    //devuelve el mismo perrito
    public RequestQueue getRequestQueue() {
        if (cartero == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // cartero or BroadcastReceiver if someone passes one in.
            cartero = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return cartero;
    }

    //Solo le mando un hueso al perrito sin sacarlo
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
