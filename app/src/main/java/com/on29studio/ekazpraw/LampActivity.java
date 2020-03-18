package com.on29studio.ekazpraw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class LampActivity extends Activity implements View.OnClickListener {

    public final static String PREF_IP = "PREF_IP_ADDRESS";
    public final static String PREF_PORT = "PREF_PORT_NUMBER";
    public final static String PREF_KEY = "";

    // declare buttons and text inputs
    private Switch ruang1, ruang2, ruang3, ruang4, ruang5, ruang6, ruang7, ruang8;
    private EditText editTextIPAddress, editTextPortNumber;

    // shared preferences objects used to save the IP address and port so that the user doesn't have to
    // type them next time he/she opens the app.
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lamp_layout);

        sharedPreferences = getSharedPreferences("HTTP_HELPER_PREFS", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ruang1 = (Switch) findViewById(R.id.sw_switch1);
        ruang2 = (Switch) findViewById(R.id.sw_switch2);
        ruang3 = (Switch) findViewById(R.id.sw_switch3);
        ruang4 = (Switch) findViewById(R.id.sw_switch4);
        ruang5 = (Switch) findViewById(R.id.sw_switch5);
        ruang6 = (Switch) findViewById(R.id.sw_switch6);
        ruang7 = (Switch) findViewById(R.id.sw_switch7);
        ruang8 = (Switch) findViewById(R.id.sw_switch8);

        // assign text inputs
        editTextIPAddress = (EditText) findViewById(R.id.editTextIPAddress);
        editTextPortNumber = (EditText) findViewById(R.id.editTextPortNumber);

        // set button listener (this class)
        ruang1.setOnClickListener(this);
        ruang2.setOnClickListener(this);
        ruang3.setOnClickListener(this);
        ruang4.setOnClickListener(this);
        ruang5.setOnClickListener(this);
        ruang6.setOnClickListener(this);
        ruang7.setOnClickListener(this);
        ruang8.setOnClickListener(this);

        // get the IP address and port number from the last time the user used the app,
        // put an empty string "" is this is the first time.
        editTextIPAddress.setText(sharedPreferences.getString(PREF_IP, ""));
        editTextPortNumber.setText(sharedPreferences.getString(PREF_PORT, ""));
        ruang1.setChecked(sharedPreferences.getBoolean("ruang1", false));
        ruang2.setChecked(sharedPreferences.getBoolean("ruang2", false));
        ruang3.setChecked(sharedPreferences.getBoolean("ruang3", false));
        ruang4.setChecked(sharedPreferences.getBoolean("ruang4", false));
        ruang5.setChecked(sharedPreferences.getBoolean("ruang5", false));
        ruang6.setChecked(sharedPreferences.getBoolean("ruang6", false));
        ruang7.setChecked(sharedPreferences.getBoolean("ruang7", false));
        ruang8.setChecked(sharedPreferences.getBoolean("ruang8", false));
    }

    @Override
    public void onClick(View view) {

        // get the pin number
        String parameterValue = "";
        // get the ip address
        String ipAddress = editTextIPAddress.getText().toString().trim();
        // get the port number
        String portNumber = editTextPortNumber.getText().toString().trim();


        // save the IP address and port for the next time the app is used
        editor.putString(PREF_IP, ipAddress); // set the ip address value to save
        editor.putString(PREF_PORT, portNumber); // set the port number to save
        editor.commit(); // save the IP and PORT

        // get the pin number from the button that was clicked
        if (view.getId() == ruang1.getId()) {
            parameterValue = "2";
            if (ruang1.isChecked()) {
                editor.putBoolean("ruang1", true);
                editor.commit();
            } else {
                editor.putBoolean("ruang1", false);
                editor.commit();
            }
        } else if (view.getId() == ruang2.getId()) {
            parameterValue = "3";
            if (ruang2.isChecked()) {
                editor.putBoolean("ruang2", true);
                editor.commit();
            } else {
                editor.putBoolean("ruang2", false);
                editor.commit();
            }
        } else if (view.getId() == ruang3.getId()) {
            parameterValue = "4";
            if (ruang3.isChecked()) {
                editor.putBoolean("ruang3", true);
                editor.commit();
            } else {
                editor.putBoolean("ruang3", false);
                editor.commit();
            }
        } else if (view.getId() == ruang4.getId()) {
            parameterValue = "5";
            if (ruang4.isChecked()) {
                editor.putBoolean("ruang4", true);
                editor.commit();
            } else {
                editor.putBoolean("ruang4", false);
                editor.commit();
            }
        } else if (view.getId() == ruang5.getId()) {
            parameterValue = "6";
            if (ruang5.isChecked()) {
                editor.putBoolean("ruang5", true);
                editor.commit();
            } else {
                editor.putBoolean("ruang5", false);
                editor.commit();
            }
        } else if (view.getId() == ruang6.getId()) {
            parameterValue = "7";
            if (ruang6.isChecked()) {
                editor.putBoolean("ruang6", true);
                editor.commit();
            } else {
                editor.putBoolean("ruang6", false);
                editor.commit();
            }
        } else if (view.getId() == ruang7.getId()) {
            parameterValue = "8";
            if (ruang7.isChecked()) {
                editor.putBoolean("ruang7", true);
                editor.commit();
            } else {
                editor.putBoolean("ruang7", false);
                editor.commit();
            }
        } else if (view.getId() == ruang8.getId()) {
            parameterValue = "9";
            if (ruang8.isChecked()) {
                editor.putBoolean("ruang8", true);
                editor.commit();
            } else {
                editor.putBoolean("ruang8", false);
                editor.commit();
            }
        }
        else {
            parameterValue = "10";
        }

        // execute HTTP request
        if (ipAddress.length() > 0 && portNumber.length() > 0) {
            new HttpRequestAsyncTask(
                    view.getContext(), parameterValue, ipAddress, portNumber, "pin"
            ).execute();
        }
    }

    /**
     * Description: Send an HTTP Get request to a specified ip address and port.
     * Also send a parameter "parameterName" with the value of "parameterValue".
     *
     * @param parameterValue the pin number to toggle
     * @param ipAddress      the ip address to send the request to
     * @param portNumber     the port number of the ip address
     * @param parameterName
     * @return The ip address' reply text, or an ERROR message is it fails to receive one
     */
    public String sendRequest(String parameterValue, String ipAddress, String portNumber, String parameterName) {
        String serverResponse = "ERROR";

        try {

            HttpClient httpclient = new DefaultHttpClient(); // create an HTTP client
            // define the URL e.g. http://myIpaddress:myport/?pin=13 (to toggle pin 13 for example)
            URI website = new URI("http://" + ipAddress + ":" + portNumber + "/?" + parameterName + "=" + parameterValue);
            HttpGet getRequest = new HttpGet(); // create an HTTP GET object
            getRequest.setURI(website); // set the URL of the GET request
            HttpResponse response = httpclient.execute(getRequest); // execute the request
            // get the ip address server's reply
            InputStream content = null;
            content = response.getEntity().getContent();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    content
            ));
            serverResponse = in.readLine();
            // Close the connection
            content.close();
        } catch (ClientProtocolException e) {
            // HTTP error
            serverResponse = e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            // IO error
            serverResponse = e.getMessage();
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // URL syntax error
            serverResponse = e.getMessage();
            e.printStackTrace();
        }
        // return the server's reply/response text
        return serverResponse;
    }

    /**
     * An AsyncTask is needed to execute HTTP requests in the background so that they do not
     * block the user interface.
     */
    private class HttpRequestAsyncTask extends AsyncTask<Void, Void, Void> {

        // declare variables needed
        private String requestReply, ipAddress, portNumber;
        private Context context;
        private AlertDialog alertDialog;
        private String parameter;
        private String parameterValue;

        /**
         * Description: The asyncTask class constructor. Assigns the values used in its other methods.
         *
         * @param context        the application context, needed to create the dialog
         * @param parameterValue the pin number to toggle
         * @param ipAddress      the ip address to send the request to
         * @param portNumber     the port number of the ip address
         */
        public HttpRequestAsyncTask(Context context, String parameterValue, String ipAddress, String portNumber, String parameter) {
            this.context = context;

            alertDialog = new AlertDialog.Builder(this.context)
                    .setTitle("HTTP Response From IP Address:")
                    .setCancelable(true)
                    .create();

            this.ipAddress = ipAddress;
            this.parameterValue = parameterValue;
            this.portNumber = portNumber;
            this.parameter = parameter;
        }

        /**
         * Name: doInBackground
         * Description: Sends the request to the ip address
         *
         * @param voids
         * @return
         */
        @Override
        protected Void doInBackground(Void... voids) {
            alertDialog.setMessage("Data sent, waiting for reply from server...");
            if (!alertDialog.isShowing()) {
                alertDialog.show();
            }
            requestReply = sendRequest(parameterValue, ipAddress, portNumber, parameter);
            return null;
        }

        /**
         * Name: onPostExecute
         * Description: This function is executed after the HTTP request returns from the ip address.
         * The function sets the dialog's message with the reply text from the server and display the dialog
         * if it's not displayed already (in case it was closed by accident);
         *
         * @param aVoid void parameter
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            alertDialog.setMessage(requestReply);
            if (!alertDialog.isShowing()) {
                alertDialog.show(); // show dialog
            }
        }

        /**
         * Name: onPreExecute
         * Description: This function is executed before the HTTP request is sent to ip address.
         * The function will set the dialog's message and display the dialog.
         */
        @Override
        protected void onPreExecute() {
            alertDialog.setMessage("Sending data to server, please wait...");
            if (!alertDialog.isShowing()) {
                alertDialog.show();
            }
        }

    }
}