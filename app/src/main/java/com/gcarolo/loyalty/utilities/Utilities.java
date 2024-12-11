package com.gcarolo.loyalty.utilities;

import static com.gcarolo.loyalty.utilities.PropertiesManager.ALIAS;

import android.content.Context;
import android.content.DialogInterface;
import android.security.KeyPairGeneratorSpec;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Base64;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.gcarolo.loyalty.R;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.security.auth.x500.X500Principal;

public class Utilities {

    private KeyStore keyStore;
    private static Utilities instance;
    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    private Utilities() {

    }

    public static Utilities getInstance() {
        if (null == instance) {
            synchronized (AppContext.class) {
                if (null == instance) {
                    instance = new Utilities();
                }
            }
        }
        return instance;
    }

    public static boolean validateEmailFormat(String email) {
        if (TextUtils.isEmpty(email))
            return false;

        return email.matches(EMAIL_PATTERN);
    }

    public String encrypt(@NonNull String plainText) {
        if (null == this.keyStore) {
            return null;
        } else if (TextUtils.isEmpty(plainText)) {
            return null;
        }

        try {
            if (!this.keyStore.containsAlias(ALIAS))
                createNewKeys();

            // Init cipher.
//            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(ALIAS, null);
            PublicKey publicKey = keyStore.getCertificate(ALIAS).getPublicKey();

            Cipher input = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            input.init(Cipher.ENCRYPT_MODE, publicKey);

            // Encrypt
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, input);
            cipherOutputStream.write(plainText.getBytes("UTF-8"));
            cipherOutputStream.close();

            return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
        } catch (Exception ex) {
            return null;
        }
    }

    public String decrypt(@NonNull String encryptedValue) {
        if (null == this.keyStore) {
            return null;
        } else if (TextUtils.isEmpty(encryptedValue)) {
            return null;
        }

        try {
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(ALIAS, null);
//            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(ALIAS, null);
            Cipher output = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//            output.init(Cipher.DECRYPT_MODE, privateKeyEntry.getPrivateKey());
            output.init(Cipher.DECRYPT_MODE, privateKey);

            CipherInputStream cipherInputStream =
                    new CipherInputStream(
                            new ByteArrayInputStream(Base64.decode(encryptedValue, Base64.DEFAULT)), output);

            ArrayList<Byte> values = new ArrayList<>();
            int nextByte;
            while ((nextByte = cipherInputStream.read()) != -1) {
                values.add((byte) nextByte);
            }

            byte[] bytes = new byte[values.size()];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = values.get(i);
            }

            return new String(bytes, 0, bytes.length, "UTF-8");

        } catch (Exception ex) {
            return null;
        }
    }

    private void createNewKeys() {
        if (null == this.keyStore) {
            return;
        }

        try {
            if (this.keyStore.containsAlias(ALIAS)) {
                return;
            }

            // Init certificate params.
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            end.add(Calendar.YEAR, 1);
            KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(AppContext.getInstance().getContext())
                    .setAlias(ALIAS)
                    .setSubject(new X500Principal("CN=ShappKeystore, O=Liverpool, C=MX"))
                    .setSerialNumber(BigInteger.ONE)
                    .setStartDate(start.getTime())
                    .setEndDate(end.getTime())
                    .build();

            // Generate keys.
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            generator.initialize(spec);
            generator.generateKeyPair();
        } catch (Exception ex) {
        }
    }

    public void setPickerInput(TextInputLayout textInputLayout, final String[] options, OptionsListener listener) {
        if (null != textInputLayout && null != textInputLayout.getEditText()) {
            EditText editText = textInputLayout.getEditText();
            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPickerDialog(AppContext.getInstance().getContext(), editText.getHint().toString(), options, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editText.setText(options[which]);
                            if (listener != null) {
                                listener.onOptionSelected(options[which]);
                            }
                        }
                    });
                }
            });
            editText.setLongClickable(false);
            editText.setClickable(true);
            editText.setFocusable(false);
            editText.setFocusableInTouchMode(false);
            editText.setInputType(InputType.TYPE_NULL);
            editText.setCursorVisible(false);
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down_2, 0);
        }
    }

    public static void showPickerDialog(Context context, String title, String[] items, DialogInterface.OnClickListener callback) {
        if (items == null || context == null) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.Base_Theme_GrupoCaroloLoyalty));
        builder.setTitle(title)
                .setCancelable(true)
                .setItems(items, callback)
                .create()
                .show();
    }

    public interface OptionsListener {
        void onOptionSelected(String option);
    }

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(String.format("%02X", messageDigest[i]));

            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String moneyFormat(Double s) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(s);
    }
}
