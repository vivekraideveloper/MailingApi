package com.vijayjaidewan01vivekrai.mailingapi_github;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity {
    EditText to, subject, message;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        to = findViewById(R.id.to);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread() {
                    public void run() {

                        String to_person = to.getText().toString();
                        String subject_person = subject.getText().toString();
                        String message_person = message.getText().toString();

                        try{

                            String from = "vijayjaidewan01@gmail.com";
                            String pass = "VJ05111988";
                            String host = "smtp.gmail.com";

                            Properties p = new Properties();

                            Session session = Session.getInstance(p);
                            MimeMessage message = new MimeMessage(session);

                            InternetAddress fromAdr = new InternetAddress(from);
                            InternetAddress toAdr = new InternetAddress(to_person);
                            message.setRecipient(javax.mail.Message.RecipientType.TO, toAdr);
                            message.setFrom(fromAdr);
                            message.setSubject(subject_person);
                            message.setText(message_person);
                            Transport tpt = session.getTransport("smtps");
                            tpt.connect(host, from, pass);
                            tpt.sendMessage(message, message.getAllRecipients());

                        }catch (Exception e){

                        }
                    }
                };

                thread.start();

            }
        });
    }
}
