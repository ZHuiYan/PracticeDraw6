package com.hencoder.hencoderpracticedraw6.practice;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hencoder.hencoderpracticedraw6.R;

import static android.os.Build.VERSION.SDK_INT;
import static com.hencoder.hencoderpracticedraw6.Utils.dpToPixel;

public class Practice01Translation extends RelativeLayout {
    Button animateBt;
    ImageView imageView;

    public Practice01Translation(Context context) {
        super(context);
    }

    public Practice01Translation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice01Translation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private State state = State.O;
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        animateBt = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);
        if (SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            // 给音乐图标加上合适的阴影
            imageView.setOutlineProvider(new MusicOutlineProvider());
        }

        animateBt.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(final View v) {
                // TODO 在这里处理点击事件，通过 View.animate().translationX/Y/Z() 来让 View 平移
              switch (state){
//                  case O:
//                      imageView.animate().translationX(300);
//                      state = State.X;
//                      break;
                  case X:
                      imageView.animate().translationX(0);
                      state = State.TY;
                      break;
                  case O:
                  case TX:
                      imageView.animate().translationX(300);
                      state = State.X;
                      break;
                  case Y:
                      imageView.animate().translationY(0);
                      state = State.TZ;
                      break;
                  case TY:
                      imageView.animate().translationY(300);
                      state = State.Y;
                      break;
                  case Z:
                      imageView.animate().translationZ(0);
                      state = State.O;
                      break;
                  case TZ:
                      imageView.animate().translationZ(300);
                      state = State.Z;
                      break;

              }

            }
        });
    }

    public enum State{
        X,
        TX,
        Y,
        TY,
        Z,
        TZ,
        O
    }
    /**
     * 为音乐图标设置三角形的 Outline。
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    class MusicOutlineProvider extends ViewOutlineProvider {
        Path path = new Path();

        {
            path.moveTo(0, dpToPixel(10));
            path.lineTo(dpToPixel(7), dpToPixel(2));
            path.lineTo(dpToPixel(116), dpToPixel(58));
            path.lineTo(dpToPixel(116), dpToPixel(70));
            path.lineTo(dpToPixel(7), dpToPixel(128));
            path.lineTo(0, dpToPixel(120));
            path.close();
        }

        @Override
        public void getOutline(View view, Outline outline) {
            outline.setConvexPath(path);
        }
    }
}