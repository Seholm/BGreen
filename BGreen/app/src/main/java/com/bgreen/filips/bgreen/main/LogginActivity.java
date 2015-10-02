package com.bgreen.filips.bgreen.main;

import android.content.Intent;
import android.content.IntentSender;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.profile.IUserHandler;
import com.bgreen.filips.bgreen.profile.ProfileService;
import com.bgreen.filips.bgreen.profile.User;
import com.bgreen.filips.bgreen.profile.UserHandler;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

public class LogginActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;

    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;

    private ProfileService pService= new ProfileService();
    private IUserHandler handler = new UserHandler(LogginActivity.this);
    private User user = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        pService.fetchAllProfiles();

        // Build GoogleApiClient to request access to the basic user profile and email
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .addScope(new Scope(Scopes.EMAIL))
                .build();

        //OnClicklistner for the signInButton
        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loggin, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart(){
        // Connect GoogleApiClient
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        // Disconnect GoogleApiClient
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        // onConnected indicates that an account was selected on the device, that the selected
        // account has granted any requested permissions to our app and that we were able to
        // establish a service connection to Google Play services.
        mShouldResolve = false;

        // Show the signed-in UI
        getProfileInformation();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            // If the error resolution was not successful we should not resolve further.
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            mGoogleApiClient.connect();

        }
    }

    private void onSignInClicked() {
        // User clicked the sign-in button, so begin the sign-in process and automatically
        // attempt to resolve any errors that occur.
        mShouldResolve = true;
        mGoogleApiClient.connect();

        // Show a message to the user that we are signing in.
        //TODO;Toast to show user that we are signing in
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_button) {
            onSignInClicked();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Could not connect to Google Play Services.  The user needs to select an account,
        // grant permissions or resolve an error in order to sign in. Refer to the javadoc for
        // ConnectionResult to see possible error codes.

        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    mIsResolving = false;
                    mGoogleApiClient.connect();

                }
            } else {
                // Could not resolve the connection result, show the user an
                // error dialog.
                //TODO;showErrorDialog(connectionResult);
            }
        } else {
            //TODO;Show the signed-out UI
        }
    }

    //TODO;SPARA PROFILEN I EN EGEN KLASS??

    //Gets the profileIno and put it on the TabActivity
    private void getProfileInformation(){
        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {

            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);

            if(handler.getUserID() != null){
                System.out.println(handler.getUserID());
                pService.startUpFetchOfUser(handler.getUserID());
            }else {
                user.setUser(currentPerson.getName().getGivenName(),
                        currentPerson.getName().getFamilyName(),
                        Plus.AccountApi.getAccountName(mGoogleApiClient),
                        0, 0, currentPerson.getImage().getUrl());
                pService.saveNewProfile(user, handler);
            }

            Intent tabActivityIntent = new Intent(this, TabActivity.class);
            startActivity(tabActivityIntent);
            finish();

        }else{

            System.out.println("****CURRENT PERSON IS NULL*****");
        }
    }
}
