package andy.ayaseruri.trackpageindicator;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import andy.ayaseruri.lib.TabView;
import andy.ayaseruri.lib.TrackPageIndicator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        TrackPageIndicator trackPageIndicator = (TrackPageIndicator)findViewById(R.id.indicator);

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.pager_item_view, null);
                container.addView(view);
                ((TextView) view.findViewById(R.id.text_view)).setText(position + "");
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }
        });

        ArrayList<TabViewHolder> tabViewHolderArrayList = new ArrayList<>();
        tabViewHolderArrayList.add(new TabViewHolder(R.drawable.index, R.drawable.index1, "首页", R.color.tab_text_normal, R.color.tab_text_selected));
        tabViewHolderArrayList.add(new TabViewHolder(R.drawable.index, R.drawable.index1, "首页", R.color.tab_text_normal, R.color.tab_text_selected));
        tabViewHolderArrayList.add(new TabViewHolder(R.drawable.index, R.drawable.index1, "首页", R.color.tab_text_normal, R.color.tab_text_selected));

//        tabViewHolderArrayList.add(new TabViewHolder("首页", R.color.tab_text_normal, R.color.tab_text_selected));
//        tabViewHolderArrayList.add(new TabViewHolder("首页", R.color.tab_text_normal, R.color.tab_text_selected));
//        tabViewHolderArrayList.add(new TabViewHolder("首页", R.color.tab_text_normal, R.color.tab_text_selected));

        ArrayList<TabView.IGetSnapshot> iGetSnapshotArrayList = new ArrayList<>();
        for(final TabViewHolder tabViewHolder : tabViewHolderArrayList){
            iGetSnapshotArrayList.add(new TabView.IGetSnapshot() {
                @Override
                public View getOrgSnapshot() {
                    View view = LayoutInflater.from(MainActivity.this).inflate(getResources().getIdentifier("ayaseruri_tab_view", "layout", MainActivity.this.getPackageName()), null);
                    ImageView imageView = (ImageView)view.findViewById(R.id.tab_icon);
                    TextView textView = (TextView)view.findViewById(R.id.tab_text);

                    if(null != imageView && -1 != tabViewHolder.getIconOrgRes()){
                        imageView.setImageResource(tabViewHolder.getIconOrgRes());
                    }

                    if(null != textView && !"".equals(tabViewHolder.getText())){
                        textView.setText(tabViewHolder.getText());
                    }

                    if(null != textView && -1 != tabViewHolder.getTextColorOrg()){
                        textView.setTextColor(getResources().getColor(tabViewHolder.getTextColorOrg()));
                    }

                    return view;
                }

                @Override
                public void getAftSnapshot(View orgView) {
                    ImageView imageView = (ImageView)orgView.findViewById(R.id.tab_icon);
                    TextView textView = (TextView)orgView.findViewById(R.id.tab_text);

                    if(null != imageView && -1 != tabViewHolder.getIconAftRes()){
                        imageView.setImageResource(tabViewHolder.getIconAftRes());
                    }

                    if(null != textView && -1 != tabViewHolder.getTextColorAft()){
                        textView.setTextColor(getResources().getColor(tabViewHolder.getTextColorAft()));
                    }
                }
            });
        }

        trackPageIndicator.setViewPager(viewPager, iGetSnapshotArrayList);
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
