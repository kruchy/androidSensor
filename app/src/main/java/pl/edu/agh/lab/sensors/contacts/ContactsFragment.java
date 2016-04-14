package pl.edu.agh.lab.sensors.contacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.lab.sensors.R;

public class ContactsFragment extends Fragment {
    Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
    String _ID = ContactsContract.Contacts._ID;
    String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
    String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

    Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
    String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

    Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
    String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
    String DATA = ContactsContract.CommonDataKinds.Email.DATA;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null)
            return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment, container, false);
        List<String> list = retrieveContacts();
        TextView textView = new TextView(getContext());
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);
        for (String a : list) {
            textView.append(a + "\n");
        }
        layout.addView(textView);
        return view;
    }


    private List<String> retrieveContacts() {
        ContentResolver contentResolver = getActivity().getContentResolver();
        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, DISPLAY_NAME);
        if (cursor == null) return new ArrayList<>(0);
        ArrayList<String> ret = new ArrayList<>(cursor.getCount());
        try {
            while (cursor.moveToNext()) {
                StringBuilder output = new StringBuilder();
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    output.append("\n First Name:").append(name);
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);
                    if(phoneCursor.moveToNext()) {
                        String phoneNumber = "";
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        output.append("\n Phone number:").append(phoneNumber);
                    }
                    phoneCursor.close();
                    Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);
                    if(emailCursor.moveToNext()) {
                        String email = "";
                        email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
                        output.append("\nEmail:").append(email);
                    }
                    emailCursor.close();
                }
                output.append("\n");
                ret.add(output.toString());
            }
        } finally {
            cursor.close();
        }
        return ret;
    }


}
