package gon.chio.globito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ImageView> globos =new ArrayList<>(5);
    int contador = 0;

    ConstraintLayout contenedor;


    //manejarán el loop para los globos
    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //primero posicionar globos
            posicionar();
            // repetir
            handler.postDelayed(runnable, 9500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contenedor = findViewById(R.id.contenedor);

        crearGlobos();

        //inicar el loop
        handler.post(runnable);

    }



    public void mover(){
        //Aplicar la animacion de movimiento vertical- cada globo tiene un tamaño distinto, por lo que les doy un limite personalizado
        //solo lo justo para desaparecer de pantalla
        float limiteSup = globos.get(contador).getLayoutParams().height;
        ObjectAnimator animation = ObjectAnimator.ofFloat(globos.get(contador), "translationY", -limiteSup);
        animation.setDuration(10000);
        animation.start();
        globos.get(contador).setVisibility(View.VISIBLE);
        contador++;
        if(contador == 5){
            contador = 0;
        }
       /* Toast toast = Toast.makeText(MainActivity.this,"contador: "+contador, Toast.LENGTH_LONG);
        toast.show();*/

    }

    public void posicionar(){
        //preparo una posicion previa al movimiento, la cual es al azar

        int max = contenedor.getWidth();
        int min = 0,posX;
        int range = max - min + 1;

        //intentando evitar que se posicionen por fuera del layout para cualquier dispositivo
        posX = ((int)(Math.random() * range) + min)-(globos.get(contador).getWidth()/2);

          /*Toast toast = Toast.makeText(this,"posX: "+ posX + "\n contador: "+contador, Toast.LENGTH_LONG);
        toast.show();*/
        globos.get(contador).setY(1800);
        globos.get(contador).setX(posX);

         mover();


    }

    public void crearGlobos(){
        //creacion inicial del array con los ImageView para cada globo

        for(int i=0; i < 5;i++){
            globos.add(new ImageView(MainActivity.this));
            globos.get(i).setImageResource(R.drawable.globoi);
            globos.get(i).setBackgroundResource(R.drawable.background_list);

            contenedor.addView(globos.get(i));

            animColores(globos.get(i));

            globos.get(i).setVisibility(View.INVISIBLE);
            if(i == 0 || i==4){
                globos.get(i).getLayoutParams().height = 200;
                globos.get(i).getLayoutParams().width = 175;
            }else if(i==1 || i==3){
                globos.get(i).getLayoutParams().height = 280;
                globos.get(i).getLayoutParams().width = 245;
            }else {
                globos.get(i).getLayoutParams().height = 360;
                globos.get(i).getLayoutParams().width = 315;
            }


        }

    }
    public void animColores(ImageView imagen) {
        //Animacion simple del fondo para cada globo (es igual para todos)

        AnimationDrawable animD = (AnimationDrawable) imagen.getBackground();
        animD.setEnterFadeDuration(1000);
        animD.setExitFadeDuration(1000);
        animD.start();
    }

}