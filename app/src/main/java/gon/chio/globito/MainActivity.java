package gon.chio.globito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ImageView> globos =new ArrayList<ImageView>();;
    int count=0;
    ConstraintLayout contenedor;

    ImageView globo;
    boolean arriba=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contenedor = findViewById(R.id.contenedor);
       globo = findViewById(R.id.globo);
        AnimationDrawable animD = (AnimationDrawable) globo.getBackground();
        animD.setEnterFadeDuration(1000);
        animD.setExitFadeDuration(1000);
        animD.start();

       /* Toast toast = Toast.makeText(this, "primero: "+globos.get(0).getX()+" " +
                "\nsegundo: "+globos.get(1).getX()+" " +"\ntercero: "+globos.get(2).getX(), Toast.LENGTH_LONG);
        toast.show();*/

        /*for(int i = 0; i < 3;i++){
            (new Handler()).postDelayed(this::mover, 6000);
        }*/

    }



    public void mover(){

        ObjectAnimator animation = ObjectAnimator.ofFloat(globo, "translationY", -1000f);
        animation.setDuration(5000);
        animation.start();
        count++;
    }

    public void crear(){
        for(int i=0;i<3;i++){
            ImageView globo = new ImageView(this);
            globo.setId(i);
            globos.add(globo);
            globos.get(i).setImageResource(R.drawable.globoi);
            LinearLayout.LayoutParams tamanio = new LinearLayout.LayoutParams(175, 200);
            globos.get(i).setLayoutParams(tamanio);
            globos.get(i).setX(((i-1)*200)+300);
            globos.get(i).setY(2000);
            globos.get(i).setBackground(ContextCompat.getDrawable(this, R.drawable.background_list));

            contenedor.addView(globos.get(i));
            AnimationDrawable animD = (AnimationDrawable) globos.get(i).getBackground();
            animD.setEnterFadeDuration(1000);
            animD.setExitFadeDuration(1000);
            animD.start();
            (new Handler()).postDelayed(this::mover, 6000);
        }

    }

}