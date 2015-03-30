package com.example.shaheer.listviewtest;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    EventAdapter eAdapter;
    public class VolunteerEvent {
        String name, description;
    }
    public class EventAdapter extends BaseAdapter{
        List<VolunteerEvent> vlist = getDataForListView();
        @Override
        public int getCount() {
            return vlist.size();
        }

        @Override
        public VolunteerEvent getItem(int position) {
            return vlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listitem, parent,false);
            }

            TextView name = (TextView)convertView.findViewById(R.id.textView);
            TextView desc = (TextView)convertView.findViewById(R.id.textView2);

            VolunteerEvent event = vlist.get(position);

            name.setText(event.name);
            desc.setText(event.description);

            return convertView;
        }

        public VolunteerEvent getEvent(int position){
            return vlist.get(position);
        }
    }
    public List<VolunteerEvent> getDataForListView()
    {
        List<VolunteerEvent> VolunteerEventsList = new ArrayList<VolunteerEvent>();

        for(int i=0;i<10;i++)
        {

            VolunteerEvent event = new VolunteerEvent();
            event.name = "Event "+i;
            event.description = "Fun level: over "+ (i+9000);
            VolunteerEventsList.add(event);
        }

        return VolunteerEventsList;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eAdapter = new EventAdapter();
        ListView eventListView = (ListView)findViewById(R.id.listView);
        eventListView.setAdapter(eAdapter);

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,long id) {

                VolunteerEvent event = eAdapter.getEvent(position);

                Toast.makeText(MainActivity.this, event.name, Toast.LENGTH_LONG).show();

            }
        });
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

}
