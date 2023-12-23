package com.example.tictactoe;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tictactoe.databinding.ActivityNewCodeBinding;
public class NewCode extends AppCompatActivity implements View.OnClickListener {
    private ActivityNewCodeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage.launch("image/*");
            }
        });

        binding.addTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = binding.editText.getText().toString();
                if (!text.isEmpty()) {
                    generateTv(text);
                    binding.editText.setText("");
                } else {
                    Toast.makeText(NewCode.this, "Enter Text", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private final ActivityResultLauncher<String> selectImage = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    binding.imageView.setImageURI(uri);
                }
            }
    );

    private void generateTv(String texts) {
        int positionX = 150;
        int positionY = 500;
        TextView tv = new TextView(this);
        tv.setText(texts);
        tv.setX(positionX);
        tv.setY(positionY);
        tv.setTextColor(Color.RED);
        tv.setTextSize(33f);
        tv.setOnTouchListener(mOnTouchListenerTv2);
        tv.setOnClickListener(this);
        binding.linear.addView(tv);
        positionX += 100;
    }

    private final View.OnTouchListener mOnTouchListenerTv2 = new View.OnTouchListener() {
        int prevX = 0;
        int prevY = 0;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) v.getLayoutParams();
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    par.topMargin += (int) (event.getRawY() - prevY);
                    prevY = (int) event.getRawY();
                    par.leftMargin += (int) (event.getRawX() - prevX);
                    prevX = (int) event.getRawX();
                    v.setLayoutParams(par);
                    return true;

                case MotionEvent.ACTION_UP:
                    par.topMargin += (int) (event.getRawY() - prevY);
                    par.leftMargin += (int) (event.getRawX() - prevX);
                    v.setLayoutParams(par);
                    return true;

                case MotionEvent.ACTION_DOWN:
                    prevX = (int) event.getRawX();
                    prevY = (int) event.getRawY();
                    par.bottomMargin = -2 * v.getHeight();
                    par.rightMargin = -2 * v.getWidth();
                    v.setLayoutParams(par);
                    return true;
            }
            return false;
        }
    };
    @Override
    public void onClick(View view) {}
}