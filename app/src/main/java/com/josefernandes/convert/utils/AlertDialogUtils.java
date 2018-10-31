package com.josefernandes.convert.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.josefernandes.convert.R;

public class AlertDialogUtils {

    public static void mostraCaixaDeDialogo(Context context, int icon, int title, String mensagem, boolean cancelable) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(icon);
        builder.setTitle(title);
        builder.setMessage(mensagem);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setCancelable(cancelable);
        builder.create();
        builder.show();
    }

}
