package com.example.xbree;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xbree.Entities.Evenement;
import com.example.xbree.Retrofit.INodeJS;
import com.example.xbree.Retrofit.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AddEvent extends AppCompatActivity implements View.OnClickListener {
    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    EditText nom, type, distance, lieu, prix, contact, datedebut, datefin, camp_desc, nbPlace;
    Button add;
    SharedPreferences sharedPreferences;
    private final static int id_user = 12;
    DatePickerDialog.OnDateSetListener dateSetListener1, dateSetListener2;
    public static INodeJS iNodeJS;
    //upload imgae
    Uri picUri;
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private final static int ALL_PERMISSIONS_RESULT = 107;
    private final static int IMAGE_RESULT = 200;
    public Button fabCamera, fabUpload;
    Bitmap mBitmap;
    TextView textView;
    ImageView imageView;
    private static final String[] cats = {"For Rental", "For Corental"};
    //map
    public static final String MY_PREFS_NAME2 = "CurrentUser";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        sharedPreferences = getApplicationContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        int idus = sharedPreferences.getInt("idUser", 0);
        nom = findViewById(R.id.nom);
        type = findViewById(R.id.type);
        distance = findViewById(R.id.distance);
        lieu = findViewById(R.id.lieu);
        prix = findViewById(R.id.prix);
        contact = findViewById(R.id.infoline);
        add = findViewById(R.id.addE);
        datedebut = findViewById(R.id.dateDebut);
        datefin = findViewById(R.id.dateFin);
        nbPlace = findViewById(R.id.nbPlace);
        camp_desc = findViewById(R.id.description);

        fabCamera = findViewById(R.id.fab);
        fabUpload = findViewById(R.id.fabUpload);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        fabCamera.setOnClickListener(this);
        fabUpload.setOnClickListener(this);

        //Init API
        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("okkkk");
                //String ss = String.valueOf(imageView);
                //System.out.println(ss);
                //System.out.println(idus);
                LayoutInflater inflater = LayoutInflater.from(AddEvent.this);
                View view = inflater.inflate(R.layout.calender_dialog, null);
                final AlertDialog alertDialog = new AlertDialog.Builder(AddEvent.this)
                        .setView(view)
                        .create();

                alertDialog.show();

                Button acceptButton = view.findViewById(R.id.acceptButton);
                Button cancelButton = view.findViewById(R.id.cancelButton);

                acceptButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addEvenement(nom.getText().toString(), type.getText().toString(), datedebut.getText().toString(), datefin.getText().toString(), Integer.parseInt(distance.getText().toString()),
                                lieu.getText().toString(), Integer.parseInt(contact.getText().toString()), Integer.parseInt(prix.getText().toString()), camp_desc.getText().toString(), Integer.parseInt(nbPlace.getText().toString()), idus);
                        Intent intent = new Intent(Intent.ACTION_INSERT);
                        intent.setData(CalendarContract.Events.CONTENT_URI);
                        intent.putExtra(CalendarContract.Events.TITLE, nom.getText().toString());
                        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, lieu.getText().toString());
                        intent.putExtra(CalendarContract.Events.DESCRIPTION, camp_desc.getText().toString());
                        intent.putExtra(CalendarContract.Events.ALL_DAY, true);
                        intent.putExtra(Intent.EXTRA_EMAIL, "bassem.sweki@esprit.tn");
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        } else {
                            System.out.println("LLEEEEEE");
                        }

                    }
                });
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addEvenement(nom.getText().toString(), type.getText().toString(), datedebut.getText().toString(), datefin.getText().toString(), Integer.parseInt(distance.getText().toString()),
                                lieu.getText().toString(), Integer.parseInt(contact.getText().toString()), Integer.parseInt(prix.getText().toString()), camp_desc.getText().toString(), Integer.parseInt(nbPlace.getText().toString()), idus);
                        Intent r = new Intent(AddEvent.this, Accueil.class);
                        startActivity(r);
                    }
                });
                //Intent i = new Intent(AddEvent.this, Accueil.class);
                //startActivity(i);
            }
        });
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        datedebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEvent.this,
                        dateSetListener1, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.getWindow();
                datePickerDialog.show();
            }
        });
        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month += 1;
                String d = day + "/" + month + "/" + year;
                datedebut.setText(d);
            }
        };

        datefin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEvent.this,
                        dateSetListener2
                        , year, month, day);
                Calendar mindate = Calendar.getInstance();
                mindate.set(year, month, day);
                datePickerDialog.getDatePicker().setMinDate(mindate.getTimeInMillis() - 1000);
                datePickerDialog.getWindow();
                datePickerDialog.show();

            }
        });
        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month += 1;
                String date = day + "/" + month + "/" + year;
                datefin.setText(date);
            }
        };

    }

    private void addEvenement(final String nom_evenement, final String type_evenement, final String date_debut_evenement, final String date_fin_evenement, final int camp_distanse,
                              final String camp_lieu, final int camp_contact, final int camp_prix, final String description_evenement, final int nbplace_evenement
            , int id_user) {

        System.out.println(id_user);

        compositeDisposable.add(iNodeJS.addEvenement(nom_evenement, type_evenement, date_debut_evenement, date_fin_evenement, camp_distanse, camp_lieu, camp_contact, description_evenement, nbplace_evenement, camp_prix, id_user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(AddEvent.this, "evenement ajouté", Toast.LENGTH_SHORT).show();
                        System.out.println("evenement ajouté");
                    }
                })
        );

    }


    //upload image
    private void askPermissions() {
        permissions.add(CAMERA);
        permissions.add(WRITE_EXTERNAL_STORAGE);
        permissions.add(READ_EXTERNAL_STORAGE);
        permissionsToRequest = findUnAskedPermissions(permissions);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (permissionsToRequest.size() > 0)
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }
    }

    private void initRetrofitClient() {
        OkHttpClient client = new OkHttpClient.Builder().build();

        myAPI = new Retrofit.Builder().baseUrl("http://192.168.1.19:3000").client(client).build().create(INodeJS.class);
    }


    public Intent getPickImageChooserIntent() {

        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = this.getPackageManager();

        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));
        Log.d("testtttttttttttt", chooserIntent.toString());

        return chooserIntent;
    }


    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = this.getExternalFilesDir("");
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {


            if (requestCode == IMAGE_RESULT) {


                String filePath = getImageFilePath(data);
                if (filePath != null) {
                    mBitmap = BitmapFactory.decodeFile(filePath);
                    imageView.setImageBitmap(mBitmap);
                    Log.d("image", filePath);
                }
            }

        }

    }


    private String getImageFromFilePath(Intent data) {
        boolean isCamera = data == null || data.getData() == null;

        if (isCamera) return getCaptureImageOutputUri().getPath();

        else
            Log.d("haythem", getPathFromURI(data.getData()));
        return getPathFromURI(data.getData());

    }

    public String getImageFilePath(Intent data) {

        return getImageFromFilePath(data);
    }

    private String getPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = this.getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        Log.d("teeeeest", cursor.getString(column_index));
        return cursor.getString(column_index);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("pic_uri", picUri);
    }

/*    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        picUri = savedInstanceState.getParcelable("pic_uri");
    }*/

    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(AddEvent.this);

    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                        }
                                    });
                            return;
                        }
                    }

                }

                break;
        }

    }

    private void multipartImageUpload() {
        try {
            File filesDir = this.getFilesDir();
            File file = new File(filesDir, "image" + ".png");

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
            byte[] bitmapdata = bos.toByteArray();


            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();


            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part image = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload");
            Call<ResponseBody> req = iNodeJS.postImage(image, name);
            req.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.code() == 200) {
                        textView.setText("Uploaded Successfully!");
                        textView.setTextColor(Color.BLUE);
                    }

                    Toast.makeText(AddEvent.this, response.code() + " qaaza", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    textView.setText("Uploaded Failed!");
                    textView.setTextColor(Color.RED);
                    Toast.makeText(AddEvent.this, "Request failed", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                startActivityForResult(getPickImageChooserIntent(), IMAGE_RESULT);
                break;

            case R.id.fabUpload:
                if (mBitmap != null)
                    multipartImageUpload();
                else {
                    Toast.makeText(view.getContext(), "Bitmap is null. Try again", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

}