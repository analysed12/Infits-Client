package com.example.infits;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Section2Q4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section2Q4 extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, reporttv, textView79;
    CardView ivUpload;

    ImageView ivUploadimg;

    private Bitmap bitmap;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgpath = null;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Section2Q4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section2Q4.
     */
    // TODO: Rename and change types and number of parameters
    public static Section2Q4 newInstance(String param1, String param2) {
        Section2Q4 fragment = new Section2Q4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_section2_q4, container, false);

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);

        reporttv = view.findViewById(R.id.textView80);

        textView79 = view.findViewById(R.id.textView79);

        ivUpload = view.findViewById(R.id.ivUpload);

        ivUploadimg = view.findViewById(R.id.ivUploadimg);

        ivUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();

            }
        });


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataSectionTwo.s2q4 = reporttv.getText().toString();
                try {
                    if (imgpath.equals("") || imgpath.equals(" "))
                        Toast.makeText(getContext(), "Upload an image", Toast.LENGTH_SHORT).show();
                    else {
                        ConsultationFragment.psection2 += 1;
                        Navigation.findNavController(v).navigate(R.id.action_section2Q4_to_section2Q5);
                    }
                }catch(NullPointerException ex){

                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConsultationFragment.psection2>0)
                    ConsultationFragment.psection2-=1;
                requireActivity().onBackPressed();
            }
        });

        imgBack.setOnClickListener(v -> requireActivity().onBackPressed());

        return view;
    }



    private void selectImage() {
        try {

            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Camera Permission error", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), "Change app permission in your device settings", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inputStreamImg = null;
        if (requestCode == PICK_IMAGE_GALLERY) {
            try {
                if (data.getData() != null) {
                    Uri selectedImage = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    //ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    // bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                    ivUploadimg.setImageBitmap(bitmap);
                   imgpath = getRealPathFromURI(selectedImage);
                    destination = new File(imgpath.toString());
                    //Toast.makeText(getActivity(), "Path: "+imgPath, Toast.LENGTH_SHORT).show();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);
                    byte[] b = baos.toByteArray();
                    DataSectionTwo.imgPath = Base64.encodeToString(b, Base64.DEFAULT);

                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), "No picture selected", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = getActivity().managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}