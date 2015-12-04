package tw.edu.hk.db_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    UserItem userItem;
    UserDAO ud;
    EditText editTextName;
    EditText editTextPhone;
    EditText editTextCode;
    Button btn1;

    ListView lv1;

    private SimpleAdapter simpleAdapter;
    private ArrayList<HashMap<String, String>> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<HashMap<String, String>>();
        ud = new UserDAO(getApplicationContext());

        editTextName = (EditText)findViewById(R.id.editText);
        editTextPhone = (EditText)findViewById(R.id.editText2);
        editTextCode = (EditText)findViewById(R.id.editText3);
        btn1 = (Button)findViewById(R.id.button);

        lv1 = (ListView)findViewById(R.id.listView1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userItem = new UserItem(editTextName.getText().toString());

                userItem.setPhone(editTextPhone.getText().toString());
                userItem.setCode(Integer.parseInt(editTextCode.getText().toString()));

                ud.insert(userItem);
                updateUserList();
            }
        });

        updateUserList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateUserList(){

        int userCount = 0;
        if(ud.getCount() == 0)
            return;

        arrayList.clear();
        userCount = ud.getCount();

        for (int i = 0; i < userCount ; i++) {
            HashMap<String, String> item = new HashMap<String, String>();

            userItem = ud.get((long)i+1);
            item.put("Name",userItem.getName());
            item.put("Code", Integer.toString(userItem.getCode()));// keyItem.keyType

            arrayList.add(item);
        }

        simpleAdapter = new SimpleAdapter(this,
                arrayList, R.layout.simple_adapter, new String[]{ "Name", "Code"},
                new int[]{R.id.nameL, R.id.codeL});

        lv1.setAdapter(simpleAdapter);
    }
}
