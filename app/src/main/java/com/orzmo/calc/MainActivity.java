package com.orzmo.calc;

/**
 * @author Pani <panilsy@icloud.com>
 * @blog panicard.com
 */

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.orzmo.calc.controller.Calc;
import com.orzmo.calc.utils.CallBack;

public class MainActivity extends AppCompatActivity {

    private Calc calc;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initView();
    }

    /**
     * @description 初始化布局文件
     */
    private void initView() {
        // 设置基础布局文件
        setContentView(R.layout.activity_main);

        // 获取结果显示框实例
        this.resultView = (TextView) findViewById(R.id.text_result);

        // 绑定点击事件
        bindTapEvent();

        // 初始化计算器实例
        this.calc = new Calc(new CallBack() {
            @Override
            public void run (String s) {
                Log.d("MainActivity", "callback");
                showToast(s);
            }
        });


    }

    /**
     * @description 绑定点击事件函数
     */
    private void bindTapEvent() {
        // 引入所有按钮
        Button button_ac = (Button) findViewById(R.id.button_ac);
        Button button_revers = (Button) findViewById(R.id.button_revers);
        Button button_mod = (Button) findViewById(R.id.button_mod);
        Button button_div = (Button) findViewById(R.id.button_div);
        Button button_7 = (Button) findViewById(R.id.button_7);
        Button button_8 = (Button) findViewById(R.id.button_8);
        Button button_9 = (Button) findViewById(R.id.button_9);
        Button button_multi = (Button) findViewById(R.id.button_multi);
        Button button_4 = (Button) findViewById(R.id.button_4);
        Button button_5 = (Button) findViewById(R.id.button_5);
        Button button_6 = (Button) findViewById(R.id.button_6);
        Button button_sub = (Button) findViewById(R.id.button_sub);
        Button button_1 = (Button) findViewById(R.id.button_1);
        Button button_2 = (Button) findViewById(R.id.button_2);
        Button button_3 = (Button) findViewById(R.id.button_3);
        Button button_plus = (Button) findViewById(R.id.button_plus);
        Button button_0 = (Button) findViewById(R.id.button_0);
        Button button_dot = (Button) findViewById(R.id.button_dot);
        Button button_equal = (Button) findViewById(R.id.button_equal);

        // 特殊事件 开平方
        Button button_squr = (Button) findViewById(R.id.button_squr);
        Button button_sin = (Button) findViewById(R.id.button_sin);

        // 绑定点击事件
        this.eventGenerator(button_ac, 'a');
        this.eventGenerator(button_revers, 'r');
        this.eventGenerator(button_mod, 'm');
        this.eventGenerator(button_div, 'd');
        this.eventGenerator(button_7, '7');
        this.eventGenerator(button_8, '8');
        this.eventGenerator(button_9, '9');
        this.eventGenerator(button_multi, 't');
        this.eventGenerator(button_4, '4');
        this.eventGenerator(button_5, '5');
        this.eventGenerator(button_6, '6');
        this.eventGenerator(button_sub, 's');
        this.eventGenerator(button_1, '1');
        this.eventGenerator(button_2, '2');
        this.eventGenerator(button_3, '3');
        this.eventGenerator(button_plus, 'p');
        this.eventGenerator(button_0, '0');
        this.eventGenerator(button_dot, 'o');
        this.eventGenerator(button_equal, 'e');
        this.eventGenerator(button_squr, 'u');
        this.eventGenerator(button_sin, 'i');
        this.textViewEventGenerator(this.resultView, 'b');

    }

    /**
     * @description 点击事件生成器
     * @param b1
     * @param c
     */
    private void eventGenerator(Button b1, final char c) {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTapEvent(c);
            }
        });
    }

    private void textViewEventGenerator(TextView t1, final char c) {
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTapEvent(c);
            }
        });
    }

    /**
     * @description 处理点击事件
     * @param c
     */
    private void handleTapEvent(char c) {
        switch (c) {
            case 'a':
                this.calc.resetCalc();
                break;
            case 'b':
                this.calc.backInput();
                break;
            case 'r':
                this.calc.revers(new CallBack() {
                    @Override
                    public void run (String s) {
                        Log.d("MainActivity", "callback");
                        showToast(s);
                    }
                });
                break;
            case 'm':
                this.calc.setOp(c, new CallBack() {
                    @Override
                    public void run (String s) {
                        Log.d("MainActivity", "callback");
                        showToast(s);
                    }
                });
                break;
            case 'd':
                this.calc.setOp(c, new CallBack() {
                    @Override
                    public void run (String s) {
                        Log.d("MainActivity", "callback");
                        showToast(s);
                    }
                });
                break;
            case 't':
                this.calc.setOp(c, new CallBack() {
                    @Override
                    public void run (String s) {
                        Log.d("MainActivity", "callback");
                        showToast(s);
                    }
                });
                break;
            case 's':
                this.calc.setOp(c, new CallBack() {
                    @Override
                    public void run (String s) {
                        Log.d("MainActivity", "callback");
                        showToast(s);
                    }
                });
                break;
            case 'p':
                this.calc.setOp(c, new CallBack() {
                    @Override
                    public void run (String s) {
                        Log.d("MainActivity", "callback");
                        showToast(s);
                    }
                });
                break;
            case 'o':
                this.calc.setDot(new CallBack() {
                    @Override
                    public void run (String s) {
                        Log.d("MainActivity", "callback");
                        showToast(s);
                    }
                });
                break;
            case 'e':
                this.renderResult(this.calc.equals());
                return;

            case 'u':
                this.calc.setOp('u',new CallBack() {
                    @Override
                    public void run (String s) {
                        Log.d("MainActivity", "callback");
                        showToast(s);
                    }
                });
                break;

            case 'i':
                this.calc.setOp('i',new CallBack() {
                    @Override
                    public void run (String s) {
                        Log.d("MainActivity", "callback");
                        showToast(s);
                    }
                });
                break;

                default:
                    this.calc.setNum(c);
                    break;
        }

        // 渲染结果
        this.renderResult(this.calc.getNowNum());
    }

    private void renderResult(String r) {
        this.resultView.setText(r);
    }


    public void showToast(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
    }
}
