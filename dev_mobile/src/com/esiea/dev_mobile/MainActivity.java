package com.esiea.dev_mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity {

	SectionsPagerAdapter mSectionsPagerAdapter = null;
	ViewPager mViewPager = null;
	ArrayList<String> biereList;

   
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DLTask().execute("http://binouze.fabrigli.fr/bieres.json");
        
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings1) {
        	Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show();
        	Intent i = new Intent(this, Second_Activity.class);
        	startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return PlaceholderFragment.newInstance(position + 1);
		}
		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}
	}
	
public static class PlaceholderFragment extends Fragment {
	
	protected String[] mDataset;
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	private void initDataset(){
		mDataset = new String[60];
		for(int i = 0;i<60;i++)
			mDataset[i] = "This element #" + i;
	}

    	public static PlaceholderFragment newInstance(int sectionNumber) {
    		PlaceholderFragment fragment = new PlaceholderFragment();
    		Bundle args = new Bundle();
    		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    		fragment.setArguments(args);
    		return fragment;
    	}
    	public PlaceholderFragment() {
    	}
    	
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    		View rootView = inflater.inflate(R.layout.fragment_main, container,false);
            
    		ListView listView1 = (ListView)rootView.findViewById(R.id.listView1);
            initDataset();
            CustomAdapter mAdapter = new CustomAdapter(mDataset);
            
            listView1.setAdapter(mAdapter);
    		
            return rootView;
    	}
    }
private class DLTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls){
        try {
            return downloadUrl(urls[0]);
        } catch (IOException e) {
            return "Unable to retrieve web page. URL may be invalid";
        }

    }
    @Override
    protected void onPostExecute(String result) {
        JSONArray bieres = null;
      
       Toast.makeText(getApplication().getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }
}

private String downloadUrl(String myurl) throws IOException{
    InputStream is = null;

    try {
        URL url = new URL(myurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int response = conn.getResponseCode();
        Log.v("Error login", "Can't log properly");
        is = conn.getInputStream();

        //Convert the InputStream into a string
        String contentAsString = readIt(is, 150000);
        return contentAsString;
    } finally {
        if(is != null) {
            is.close();
        }
    }
}
//Function which convert the InputStream into a string
public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"), len);
    String reponse = bufferedReader.readLine();
    return reponse;
}

}

