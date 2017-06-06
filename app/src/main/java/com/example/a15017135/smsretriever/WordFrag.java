package com.example.a15017135.smsretriever;


import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class WordFrag extends Fragment {

    EditText etWord;
    Button btnRetrieveWord;
    TextView tvWord;
    String filterStr;
    String filterargArr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word, container, false);
        etWord = (EditText)view.findViewById(R.id.etWord);
        btnRetrieveWord = (Button)view.findViewById(R.id.btnRetrieveWord);
        tvWord = (TextView)view.findViewById(R.id.tvWord);

        btnRetrieveWord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int permissionCheck = PermissionChecker.checkSelfPermission
                        (WordFrag.this.getActivity(), Manifest.permission.READ_SMS);

                if (permissionCheck != PermissionChecker.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(WordFrag.this.getActivity(),
                            new String[]{Manifest.permission.READ_SMS}, 0);
                    // stops the action from proceeding further as permission not
                    //  granted yet
                    return;
                }

                // Create all messages URI
                Uri uri = Uri.parse("content://sms");
                // The columns we want
                //  date is when the message took place
                //  address is the number of the other party
                //  body is the message content
                //  type 1 is received, type 2 sent
                String[] reqCols = new String[]{"date", "address", "body", "type"};

                // Get Content Resolver object from which to
                //  query the content provider
                ContentResolver cr = getActivity().getContentResolver();
                //the filter string

                // filtering matches for ?
                String etWordResult = etWord.getText().toString();
                String[] Stringsplit = etWordResult.split("\\s+");
                filterStr = "body LIKE ?";
                filterargArr = "%" + Stringsplit[0] + "%";
            if(Stringsplit.length > 0) {
             for (int i = 1; i < Stringsplit.length; i++) {
          filterStr = filterStr + " OR body LIKE ? ";
            filterargArr = filterargArr + ",%" + Stringsplit[i] + "%";
         Log.d(filterargArr,"abc");
      }

    }
                String[] filterArg = {filterargArr.toString()};
                    Cursor cursor = cr.query(uri, reqCols, filterStr, filterArg, null);
                    String smsBody = "";

                    if (cursor.moveToFirst()) {
                        do {
                            long dateInMillis = cursor.getLong(0);
                            String date = (String) DateFormat
                                    .format("dd MMM yyyy h:mm:ss aa", dateInMillis);
                            String address = cursor.getString(1);
                            String body = cursor.getString(2);
                            String type = cursor.getString(3);
                            if (type.equalsIgnoreCase("1")) {
                                type = "Inbox:";
                            } else {
                                type = "Sent:";
                            }
                            smsBody += type + " " + address + "\n at " + date
                                    + "\n\"" + body + "\"\n\n";
                        } while (cursor.moveToNext());
                    }
                    tvWord.setText(smsBody);

                }

        });

        return view;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the read SMS
                    //  as if the btnRetrieve is clicked
                    btnRetrieveWord.performClick();

                } else {
                    // permission denied... notify user
                    Toast.makeText(WordFrag.this.getActivity(), "Permission not granted",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
