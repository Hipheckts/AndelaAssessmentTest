package com.challenge.andela.jdl;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

/**
 * Created by mokeam on 3/4/17.
 */
//This class shows the detail of each user

public class DetailActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbar;
    Toolbar toolbar;
    TextView profileTextView;
    ImageView profileImageView;
    FloatingActionButton fab;
    String name;
    String profileUrl;
    String imageName;
    Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton)findViewById(R.id.fab);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //get the shared resources from the DeveloperAdapter Class
        bundle = getIntent().getExtras();
        name = bundle.getString("name");
        imageName = bundle.getString("image");
        profileUrl = bundle.getString("profileUrl");


        //sets the title of the toolbar with the user's username
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(name);


        //sets the imageview resource
        profileImageView = (ImageView) findViewById(R.id.image);
        Picasso.with(getBaseContext()).load(imageName).into(profileImageView);

        //sets the github url
        profileTextView = (TextView)findViewById(R.id.profileTextView);
        profileTextView.setText(profileUrl);

        //event handler for github url onclick for viewing the url in the browser.
        profileTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = profileTextView.getText().toString();
                Intent openLink = new Intent(Intent.ACTION_VIEW);
                openLink.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                openLink.setData(Uri.parse(url));
                startActivity(openLink);
            }
        });

        //event handler for fab onclick for sharing with other apps that has the ability to perform Intent.ACTION_SEND
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                String text = "Check out this awesome developer " + "@"+name +","+ profileUrl;
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(shareIntent, "Share With"));
            }
        });

    }

}