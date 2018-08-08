package com.example.marcivanlim.mvvmregistration.Registration.ViewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcivanlim.mvvmregistration.API.APIClient;
import com.example.marcivanlim.mvvmregistration.API.APIClientInterface;
import com.example.marcivanlim.mvvmregistration.API.APIInterface;
import com.example.marcivanlim.mvvmregistration.R;
import com.example.marcivanlim.mvvmregistration.Utils.UtilsInterface;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RegistrationViewModel implements RegistrationInterface {




    // EditText Validation if length is more than 5
    public Disposable observeEditTextValidation(final EditText edtText)
    {

        Disposable disposable =  RxTextView.textChangeEvents(edtText)
                .skipInitialValue()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TextViewTextChangeEvent>() {
                    @Override
                    public void onNext(TextViewTextChangeEvent textViewTextChangeEvent)
                    {
                        Boolean isLengthValid = textViewTextChangeEvent.text().length() > 5;

                        if(!isLengthValid)
                        {
                            edtText.setError("Length atleast 6 letters");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return disposable;
    }


    // Enable register button if all editText text lenght is more than 5 and password and confirm password is equal
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Disposable combineValidationForRegistration(Context context, EditText edtUserName, EditText edtFname,
                                                       EditText edtLname, EditText edtEmail, EditText edtPass,
                                                       EditText edtConfirmPW, Button btnRegistration)
    {

        InitialValueObservable<TextViewTextChangeEvent> edtUserNameObs = RxTextView.textChangeEvents(edtUserName);
        InitialValueObservable<TextViewTextChangeEvent> edtFnameObs = RxTextView.textChangeEvents(edtFname);
        InitialValueObservable<TextViewTextChangeEvent> edtLnameObs = RxTextView.textChangeEvents(edtLname);
        InitialValueObservable<TextViewTextChangeEvent> edtEmailObs = RxTextView.textChangeEvents(edtEmail);
        InitialValueObservable<TextViewTextChangeEvent> edtPWObs = RxTextView.textChangeEvents(edtPass);
        InitialValueObservable<TextViewTextChangeEvent> edtConfirmPWObs = RxTextView.textChangeEvents(edtConfirmPW);


        Disposable combineValidator = Observable
                .combineLatest(edtUserNameObs, edtFnameObs, edtLnameObs, edtEmailObs, edtPWObs, edtConfirmPWObs,
                (edtUN, edtFN, edtLN, edtEm, edtPw, edtConPw) ->
                {
                    boolean validateLength = validateLength(edtUN) && validateLength(edtFN)
                            && validateLength(edtLN) && validateLength(edtEm) && validateLength(edtPw);

                    boolean validatePass = edtPw.text().toString().equals(edtConPw.text().toString());

                    if(!validatePass)
                    {
                        edtPass.setError("Password and Confirm password must be the same");
                        edtConfirmPW.setError("Password and Confirm password must be the same");
                    }

                    return validateLength && validatePass;


                }).subscribe(aBoolean ->
        {

            btnRegistration.setEnabled(aBoolean);

            if(aBoolean)
            {
                btnRegistration.setBackground(context.getResources().getDrawable(R.drawable.border_black));
                btnRegistration.setTextColor(context.getResources().getColor(R.color.opa_black_80));
            }
            else
            {
                btnRegistration.setBackground(context.getResources().getDrawable(R.drawable.border_gray));
                btnRegistration.setTextColor(context.getResources().getColor(R.color.opa_black_25));
            }

        });

        return combineValidator;
    }



    public boolean validateLength(TextViewTextChangeEvent edtText)
    {
        boolean islengthValid = false;

        if(edtText.text().length() > 5)
        {
            islengthValid = true;
        }

        return islengthValid;
    }



    public Disposable registerUser(final Context context, UtilsInterface utilsInterface, ProgressDialog progressDialog, APIClientInterface apiClientInterface)
    {

        APIInterface apiInterface = apiClientInterface.getClient().create(APIInterface.class);


        Disposable disposable = apiInterface.registerUser("","","","","","")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {

                    @Override
                    public void onNext(String s)
                    {
                        Log.d("", "ResultData: " + s);
                        utilsInterface.toast(context, "ResultData: " + s);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.d("", "ResultData: " + e.toString());
                        utilsInterface.toast(context, "ResultData: " + e.toString());
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onComplete()
                    {
                        progressDialog.dismiss();
                    }
                });


        return disposable;
    }


}
