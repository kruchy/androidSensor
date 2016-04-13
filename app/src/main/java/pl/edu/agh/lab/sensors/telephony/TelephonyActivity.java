package pl.edu.agh.lab.sensors.telephony;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import pl.edu.agh.lab.sensors.R;

public class TelephonyActivity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null)
            return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment, container, false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.Sensors);
        TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        TextView textView = getTextView();
        textView.setText("ALL CELL INFO:");
        linearLayout.addView(textView);

        textView = getTextView();
        textView.setText(tm.getAllCellInfo().get(0).toString());
        linearLayout.addView(textView);

        for (Method method : tm.getClass().getDeclaredMethods()) {
            if (method.getParameterTypes().length == 0 && method.getName().startsWith("get") && !method.getName().startsWith("getAllCellInfo")) {
                try {
                    textView = getTextView();
                    textView.setText(method.getName() + " : " + String.valueOf(method.invoke(tm)) + " ");
                    linearLayout.addView(textView);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
//        for (Field field: tm.getClass().getFields()) {
//
//            TextView textView = getTextView();
//            field.setAccessible(true);
//            try {
//                textView.setText(field.getName() + " " +field.get(tm.getClass()));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            linearLayout.addView(textView);
//
//        }

        int phoneType = tm.getPhoneType();
        String strphoneType = "";
        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strphoneType = "CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                strphoneType = "GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                strphoneType = "NONE";
                break;
        }
        textView = getTextView();
        textView.setText("PhoneType : " + strphoneType);
        linearLayout.addView(textView);
        return view;

    }

    @NonNull
    private TextView getTextView() {
        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        textView.setLayoutParams(params);
        return textView;
    }


}
