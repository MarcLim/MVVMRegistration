package com.example.marcivanlim.mvvmregistration.Registration.View;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;
import android.widget.EditText;

import com.example.marcivanlim.mvvmregistration.API.APIClientInterface;
import com.example.marcivanlim.mvvmregistration.MainApplication;
import com.example.marcivanlim.mvvmregistration.R;
import com.example.marcivanlim.mvvmregistration.Registration.ViewModel.RegistrationInterface;
import com.example.marcivanlim.mvvmregistration.Utils.UtilsInterface;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RegistrationView extends Activity {


    @BindView(R.id.edtUserName) EditText edtUserName;
    @BindView(R.id.edtFName) EditText edtFName;
    @BindView(R.id.edtLName) EditText edtLName;
    @BindView(R.id.edtEmail) EditText edtEmail;
    @BindView(R.id.edtPW) TextInputEditText edtPW;
    @BindView(R.id.edtConfirmPW) TextInputEditText edtConfirmPW;
    @BindView(R.id.btnRegister) Button btnRegister;

    ProgressDialog progressDialog;

    @Inject RegistrationInterface registrationInterface;
    @Inject UtilsInterface utilsInterface;
    @Inject APIClientInterface apiClientInterface;

    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_view); ButterKnife.bind(this);
        ((MainApplication) getApplication()).getAppComponent().inject(RegistrationView.this);

        initEditTextValidations();
    }


    // Initialize all EditText Validation ( Input must be more than 5 )
    public void initEditTextValidations()
    {
        Disposable obsUserName = registrationInterface.observeEditTextValidation(edtUserName);
        Disposable obsFName = registrationInterface.observeEditTextValidation(edtFName);
        Disposable obsLName = registrationInterface.observeEditTextValidation(edtLName);
        Disposable obsEmail = registrationInterface.observeEditTextValidation(edtEmail);
        Disposable obsPW = registrationInterface.observeEditTextValidation(edtPW);
        Disposable obsConPw = registrationInterface.observeEditTextValidation(edtConfirmPW);


        Disposable obsMainRegistrationValidator = registrationInterface
                .combineValidationForRegistration(getApplicationContext(), edtUserName, edtFName, edtLName, edtEmail,
                        edtPW, edtConfirmPW, btnRegister);


        compositeDisposable.add(obsUserName);
        compositeDisposable.add(obsFName);
        compositeDisposable.add(obsLName);
        compositeDisposable.add(obsEmail);
        compositeDisposable.add(obsPW);
        compositeDisposable.add(obsConPw);
        compositeDisposable.add(obsMainRegistrationValidator);
    }



    @OnClick({R.id.btnRegister})
    public void registerUser()
    {
        showLoading();
        Disposable obsRegisterUser =  registrationInterface.registerUser(getApplicationContext(), utilsInterface, progressDialog, apiClientInterface);
        compositeDisposable.add(obsRegisterUser);
    }



    @Override
    protected void onDestroy()
    {
        compositeDisposable.clear();
        super.onDestroy();
    }


    

    //-------------------------------------------  LOADING --------------------------------------------



    public void showLoading()
    {
        progressDialog = new ProgressDialog(RegistrationView.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait... registering user");
        progressDialog.show();
    }



}
