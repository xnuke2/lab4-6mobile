// Generated by view binder compiler. Do not edit!
package com.example.lab4_6mobile.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.lab4_6mobile.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonLogin;

  @NonNull
  public final Button buttonLoginToRegistration;

  @NonNull
  public final EditText editTextTextEmailAddressLogin;

  @NonNull
  public final EditText editTextTextPasswordLogin;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView textViewLogin;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonLogin,
      @NonNull Button buttonLoginToRegistration, @NonNull EditText editTextTextEmailAddressLogin,
      @NonNull EditText editTextTextPasswordLogin, @NonNull ConstraintLayout main,
      @NonNull TextView textViewLogin) {
    this.rootView = rootView;
    this.buttonLogin = buttonLogin;
    this.buttonLoginToRegistration = buttonLoginToRegistration;
    this.editTextTextEmailAddressLogin = editTextTextEmailAddressLogin;
    this.editTextTextPasswordLogin = editTextTextPasswordLogin;
    this.main = main;
    this.textViewLogin = textViewLogin;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonLogin;
      Button buttonLogin = ViewBindings.findChildViewById(rootView, id);
      if (buttonLogin == null) {
        break missingId;
      }

      id = R.id.buttonLoginToRegistration;
      Button buttonLoginToRegistration = ViewBindings.findChildViewById(rootView, id);
      if (buttonLoginToRegistration == null) {
        break missingId;
      }

      id = R.id.editTextTextEmailAddressLogin;
      EditText editTextTextEmailAddressLogin = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextEmailAddressLogin == null) {
        break missingId;
      }

      id = R.id.editTextTextPasswordLogin;
      EditText editTextTextPasswordLogin = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPasswordLogin == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.textViewLogin;
      TextView textViewLogin = ViewBindings.findChildViewById(rootView, id);
      if (textViewLogin == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, buttonLogin,
          buttonLoginToRegistration, editTextTextEmailAddressLogin, editTextTextPasswordLogin, main,
          textViewLogin);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
