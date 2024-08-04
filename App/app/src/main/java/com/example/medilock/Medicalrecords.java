package com.example.medilock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class Medicalrecords extends AppCompatActivity {

    private Button btnUpload;
    private Button btnDisplay;
    private static final int PICK_PDF_REQUEST = 1;
    Uri pdfUri;
    private RecyclerView recyclerView;
    private ArrayAdapter pdfAdapter;
    private List<String> pdfList;
    private StorageReference storageRef;
    private DatabaseReference databaseReference;
    private ListView listView;
    private List<String> pdfUris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicalrecords);

        btnUpload = findViewById(R.id.btn_upload);

        storageRef = FirebaseStorage.getInstance().getReference().child("pdfs");
        databaseReference = FirebaseDatabase.getInstance().getReference("pdfs");
        // Initialize RecyclerView and PDF list
        listView = findViewById(R.id.listView);

        pdfUris = new ArrayList<String>();
        // Add PDF URIs to the list

        pdfAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, pdfUris);
        listView.setAdapter(pdfAdapter);

        // Fetch PDF files from Firebase Storage
        fetchPDFs();

        // Listen for changes in Firebase Storage directory and auto-refresh RecyclerView
        // You need to implement this method to listen for changes in the Firebase Storage directory
        // and update the PDF list accordingly

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_REQUEST);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the URI of the selected PDF file
            Uri pdfUri = data.getData();

            // Upload the PDF file to Firebase Storage
            uploadPDF(pdfUri);
            fetchPDFs();
        }
    }

    private void uploadPDF(Uri pdfUri) {
    // Create a reference to the desired location in Firebase Storage
        StorageReference pdfRef = storageRef.child("pdfs/" + System.currentTimeMillis() + ".pdf");

        // Upload the PDF file to Firebase Storage
        UploadTask uploadTask = pdfRef.putFile(pdfUri);

        // Add success and failure listeners to handle the upload result
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // Upload success
            Toast.makeText(Medicalrecords.this, "PDF uploaded successfully", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            // Upload failure
            Toast.makeText(Medicalrecords.this, "PDF upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
        fetchPDFs();
        listenForPDFUploads();

    }
    private void fetchPDFs() {
        // Get a reference to the Firebase Storage instance
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Create a reference to the PDFs directory in Firebase Storage
        StorageReference storageRef = storage.getReference().child("pdfs");

        // List all items (PDF files) in the "pdfs" directory
        storageRef.listAll()
                .addOnSuccessListener(listResult -> {
                    // Iterate through the list of items (PDF files)
                    List<String> pdfUris = new ArrayList<>();
                    for (StorageReference item : listResult.getItems()) {
                        // Get the URI of each PDF file
                        String pdfUri = item.getPath(); // or item.getDownloadUrl().toString() to get download URL
                        pdfUris.add(pdfUri);
                    }

                    // Now you have a list of PDF URIs, you can use it as needed
                    // For example, update the ListView adapter with this data
                    pdfAdapter.clear();
                    pdfAdapter.addAll(pdfUris);
                })
                .addOnFailureListener(exception -> {
                    // Handle any errors that may occur
                    Log.e("fetchPDFUrisFromStorage", "Failed to fetch PDF URIs: " + exception.getMessage());
                });
    }

    private void listenForPDFUploads() {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference("pdfs/"); // Modify the storage reference according to your storage structure
        storageRef.listAll().addOnSuccessListener(listResult -> {
            List<String> pdfUris = new ArrayList<>();
            for (StorageReference item : listResult.getItems()) {
                String pdfUri = item.getPath(); // Get the URI of the PDF file
                pdfUris.add(pdfUri);
            }
            // Now you have a list of PDF URIs, you can update the ListView adapter with this data
            pdfAdapter.clear();
            pdfAdapter.addAll(pdfUris);
        }).addOnFailureListener(exception -> {
            // Handle error
            Log.e("listenForPDFUploads", "Failed to listen for PDF uploads: " + exception.getMessage());
        });
    }
}