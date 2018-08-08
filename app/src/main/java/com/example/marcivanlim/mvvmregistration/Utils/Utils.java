package com.example.marcivanlim.mvvmregistration.Utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by pc on 7/28/2018.
 */

public class Utils implements UtilsInterface {


    public void goToPage(Context context, Class cl)
    {
        Intent intent = new Intent(context, cl);
        context.startActivity(intent);

    }

    public void toast(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


}
