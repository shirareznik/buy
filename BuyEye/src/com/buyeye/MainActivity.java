package com.buyeye;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

public class MainActivity extends TabActivity {
    
	@SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Parse.initialize(this, this.getString(R.string.parseApplication),
				this.getString(R.string.clientKey));
        PushService.setDefaultPushCallback(this, ViewTaskActivity.class);
        if (ParseUser.getCurrentUser() == null) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivityForResult(intent, 1338);
			//Intent intent1 = new Intent(this, ProfileActivity.class);
			//startActivityForResult(intent1, 1339);
		} else{
			afterLoggedIn();
		}
	}
 
	
	
	private void afterLoggedIn(){
	       Resources res = getResources();
	        Intent post = new Intent(this,Post.class);
	        Intent profile = new Intent(this,ProfileActivity.class);
	        Intent browse = new Intent(this,Browse.class);
	        
	        
	        
	        @SuppressWarnings("deprecation")
			TabHost mTabHst = getTabHost();
	     mTabHst.addTab(mTabHst.newTabSpec("tab_test1")
	            .setIndicator("Browse",res.getDrawable(R.drawable.one_g))
	            .setContent(browse));
	     mTabHst.addTab(mTabHst.newTabSpec("tab_test2")
	            .setIndicator("Profile",res.getDrawable(R.drawable.two_g))
	            .setContent(profile));
	     mTabHst.addTab(mTabHst.newTabSpec("tab_test3")
	            .setIndicator("Post",res.getDrawable(R.drawable.three_g))
	            .setContent(post));
	     mTabHst.setCurrentTab(2);
	    
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1338 && resultCode == RESULT_OK) {
		this.afterLoggedIn();
		}
		
	}
}


//public class MainActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
   // SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
   /*
	ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    */
    

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
	
	/*
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a DummySectionFragment (defined as a static inner class
            // below) with the page number as its lone argument.
            Fragment fragment = new DummySectionFragment();
            Bundle args = new Bundle();
            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }
*/
    /**
     * A dummy fragment representing a section of the app, but that simply
     * displays dummy text.
     */
  
	//public static class DummySectionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
	/*
        public static final String ARG_SECTION_NUMBER = "section_number";

        public DummySectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
            TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
*/

