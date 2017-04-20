package cn.edu.gdmec.s07150808.mynotepad.otheresactivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.gdmec.s07150808.mynotepad.R;


public class UserRegist extends Activity {

	private EditText regist_ID, re_password, rer_password;
	private Button regist_btn;
	// 注册用ip
	private static final String URLS ="http://192.168.1.252:8080/mymoney/index.do?m=user&a=reg";

	String result="";
	int code;
	String mess;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.useregist);
		init();
		regist_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Handler handler=new Handler(){
					@Override
					public void handleMessage(Message msg) {
						// TODO Auto-generated method stub
						super.handleMessage(msg);
						switch (msg.what) {
							case 1:
								parser(result);
								if(code==1){

									Toast.makeText(UserRegist.this, mess, Toast.LENGTH_LONG).show();
								}
								else {
									Toast.makeText(UserRegist.this, mess, Toast.LENGTH_LONG).show();

								}
								break;

							default:
								break;
						}
					}
				};

				new Thread(){
					public void run() {
						getContent();
						Message mes=new Message();
						mes.what=1;
						handler.sendMessage(mes);
					};
				}.start();
			}
		});
	}



	private void init() {
		regist_btn = (Button) findViewById(R.id.regist_btn);
		regist_ID = (EditText) findViewById(R.id.regist_ID);
		re_password = (EditText) findViewById(R.id.re_password);
	}

	private void getContent() {
		OutputStream out=null;
		InputStreamReader in = null;
		HttpURLConnection conn=null;
		try {
			URL url=new URL(URLS);
			//由url对象获得HttpURLConnection（连接对象）
			conn=(HttpURLConnection) url.openConnection();
			//设置请求方式
			conn.setRequestMethod("POST");
			//系统默认是允许输入信息的
			conn.setDoInput(true);
			//设置允许输出信息；
			conn.setDoOutput(true);
			//设置请求的性质，信息内容的类型
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//设置内容的编码格式
			conn.setRequestProperty("Charset", "utf-8");
			String data = "username=" + URLEncoder.encode(regist_ID.getText().toString().trim(), "UTF-8")
					+ "&password=" + URLEncoder.encode(re_password.getText().toString().trim(), "UTF-8");//传递的数据
			//设置请求信息的数组长度
			conn.setRequestProperty("Content-Length",String.valueOf(data.getBytes().length));
			//5、获取输出流
			out=conn.getOutputStream();
			out.write(data.getBytes());
			out.flush();


			/**
			 * 获取响应的的信息
			 */
			//获取响应信息的输入流转换成字符流；
			in=new InputStreamReader(conn.getInputStream());
			BufferedReader reader=new BufferedReader(in);
			String st="";
			while ((st=reader.readLine())!=null) {
				result=result+st;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				conn.disconnect();
			}
		}
	}

	private void parser(String result) {

		try {
			JSONObject obj= new JSONObject(result);
			code=Integer.valueOf(obj.getString("code").toString());
			mess=obj.getString("msg").toString();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
