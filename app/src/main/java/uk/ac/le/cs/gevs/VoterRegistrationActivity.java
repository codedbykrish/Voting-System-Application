package uk.ac.le.cs.gevs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uk.ac.le.cs.gevs.databinding.ActivityVoterRegistrationBinding;

public class VoterRegistrationActivity extends AppCompatActivity {

    ActivityVoterRegistrationBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoterRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.editTextEmail.getText().toString();
                String password = binding.editTextPassword.getText().toString();
                String confirmPassword = binding.editTextConfirmPassword.getText().toString();
                if(email.equals("")||password.equals("")||confirmPassword.equals(""))
                    Toast.makeText(VoterRegistrationActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else{
                    if(password.equals(confirmPassword)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);
                        if(checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData(email, password);
                            if(insert == true){
                                Toast.makeText(VoterRegistrationActivity.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),VoterLoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(VoterRegistrationActivity.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(VoterRegistrationActivity.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(VoterRegistrationActivity.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.LoginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoterRegistrationActivity.this, VoterLoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
//    private EditText editTextEmail, editTextFullName, editTextDateOfBirth, editTextPassword, editTextConstituency;
//    private Button buttonScanQR, buttonRegister;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_voter_registration);
//
//        // Initialize UI components
//        editTextEmail = findViewById(R.id.editTextEmail);
////        editTextFullName = findViewById(R.id.editTextFullName);
////        editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);
////        editTextPassword = findViewById(R.id.editTextPassword);
////        editTextConstituency = findViewById(R.id.editTextConstituency);
////        buttonScanQR = findViewById(R.id.buttonScanQR);
//        buttonRegister = findViewById(R.id.buttonRegister);
//    }
//    // Registration logic
//    private void registerVoter() {
//        // Get user input
//        String email = editTextEmail.getText().toString().trim();
//        String fullName = editTextFullName.getText().toString().trim();
//        String dateOfBirth = editTextDateOfBirth.getText().toString().trim();
//        String password = editTextPassword.getText().toString().trim();
//        String constituency = editTextConstituency.getText().toString().trim();
//
//        // Validate user input
//        if (email.isEmpty() || fullName.isEmpty() || dateOfBirth.isEmpty() || password.isEmpty() || constituency.isEmpty()) {
//            // Display an error message if any field is empty
//            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Perform database operations (You need to implement this part)
//        // For example, you can create a method in a DatabaseHelper class to insert the voter information into the database.
//
//        // Display a success message
//        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
//
//        // Optionally, you can navigate to another activity or perform other actions after successful registration.
//    }
//    // Add registration logic here
//}
