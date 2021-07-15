package com.example.emailtestcomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import java.mail functions
//import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    //create variabels
    EditText email,message;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        message = findViewById(R.id.message);
        new Thread(new Runnable() {
            @Override
            public void run() {
                btn_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String userName = "pdncpathiraja@gmail.com";
                        final String password = "pdnc45325";

                        String messageToSend = message.getText().toString();

                        Properties prop = new Properties();
                        prop.put("mail.smtp.auth","true");
                        prop.put("mail.smtp.starttls.enable","true");
                        prop.put("mail.smtp.host","smtp.gmail.com");
                        prop.put("mail.smtp.port","587");
                        Session session = Session.getInstance(prop,
                                new javax.mail.Authenticator() {
                                    @Override
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(userName, password);
                                    }
                                });
                        try {
                            Message message = new MimeMessage(session);
                            message.setFrom( new InternetAddress(userName));
                            message.setRecipients(Message.RecipientType.TO , InternetAddress.parse(email.getText().toString()));
                            message.setSubject("Sending Email Without Open Gmail app");
                            message.setText(messageToSend);
                            Transport.send(message);
                            Toast.makeText(getApplicationContext(), "Email Send Successfull", Toast.LENGTH_LONG).show();
                        }
                        catch (MessagingException e){
                            throw new RuntimeException(e);
                        }

                    }
                });
            }
        });



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}