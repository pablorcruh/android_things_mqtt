package com.example.mqtt;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MainActivity extends Activity {
    private static final String TAG ="Things";
    private static final String topic = "pabcruhe/test";
    private static final String hello = "Hello World!";
    private static final int qos = 1;
    private static final String broker = "tcp://iot.eclipse.org:1883";
    private static final String clientId = "lens_wmB92qWcZrmoxuSeVCXCwCXCmxG";

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        try{
            MqttClient client = new MqttClient(broker, clientId, new MemoryPersistence());
            Log.i(TAG,"Conectando al broker: "+broker);
            client.connect();
            Log.i(TAG,"Conectado");
            Log.i(TAG,"Publicando mensaje: "+hello);
            MqttMessage message = new MqttMessage(hello.getBytes());
            message.setQos(qos);
            client.publish(topic,message);
            Log.i(TAG,"Mensaje publicado");
            client.disconnect();
            Log.i(TAG,"Desconectado");
        }catch(MqttException e){
            Log.e(TAG, "Error en MQTT", e);
            e.printStackTrace();
        }
    }


}
