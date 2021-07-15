package com.example.emailtestcomponent;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
////import java.mail functions
////import javax.mail.*;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import java.util.Properties;
//
//public class MainActivity extends AppCompatActivity {
//
//    //create variabels
//    EditText email,message;
//    Button btn_send;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        email = findViewById(R.id.email);
//        message = findViewById(R.id.message);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                btn_send.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        final String userName = "pdncpathiraja@gmail.com";
//                        final String password = "pdnc45325";
//
//                        String messageToSend = message.getText().toString();
//
//                        Properties prop = new Properties();
//                        prop.put("mail.smtp.auth","true");
//                        prop.put("mail.smtp.starttls.enable","true");
//                        prop.put("mail.smtp.host","smtp.gmail.com");
//                        prop.put("mail.smtp.port","587");
//                        Session session = Session.getInstance(prop,
//                                new javax.mail.Authenticator() {
//                                    @Override
//                                    protected PasswordAuthentication getPasswordAuthentication() {
//                                        return new PasswordAuthentication(userName, password);
//                                    }
//                                });
//                        try {
//                            Message message = new MimeMessage(session);
//                            message.setFrom( new InternetAddress(userName));
//                            message.setRecipients(Message.RecipientType.TO , InternetAddress.parse(email.getText().toString()));
//                            message.setSubject("Sending Email Without Open Gmail app");
//                            message.setText(messageToSend);
//                            Transport.send(message);
//                            Toast.makeText(getApplicationContext(), "Email Send Successful", Toast.LENGTH_LONG).show();
//                        }
//                        catch (MessagingException e){
//                            throw new RuntimeException(e);
//                        }
//
//                    }
//                });
//            }
//        });
//
//
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//    }
//}


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends Activity {
    EditText editTextTo,editTextSubject,editTextMessage;
    Button send;

    @Override
    protected void onResume() {
        super.onResume();
        editTextTo.setText("");
        editTextSubject.setText("");
        editTextMessage.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTo=(EditText)findViewById(R.id.editText1);
        editTextSubject=(EditText)findViewById(R.id.editText2);
        editTextMessage=(EditText)findViewById(R.id.editText3);

        send=(Button)findViewById(R.id.button1);

        send.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String to=editTextTo.getText().toString();
                String subject=editTextSubject.getText().toString();
                String message=editTextMessage.getText().toString();


                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }

        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }

}