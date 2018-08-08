package com.example.marcivanlim.mvvmregistration.Registration.ViewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import com.example.marcivanlim.mvvmregistration.API.APIClientInterface;
import com.example.marcivanlim.mvvmregistration.Utils.UtilsInterface;

import io.reactivex.disposables.Disposable;

public interface RegistrationInterface {

    Disposable observeEditTextValidation(EditText edtText);

    Disposable registerUser(Context context, UtilsInterface utilsInterface, ProgressDialog progressDialog, APIClientInterface apiClientInterface);

    Disposable combineValidationForRegistration(Context context, EditText edtUserName, EditText edtFname,
                                                EditText edtLname, EditText edtEmail, EditText edtPass,
                                                EditText edtConfirmPass, Button btnRegistration);

}
