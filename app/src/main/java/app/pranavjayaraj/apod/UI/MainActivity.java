package app.pranavjayaraj.apod.UI;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.transition.Fade;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import app.pranavjayaraj.apod.R;
import app.pranavjayaraj.apod.UI.ViewPager.APODViewPager;
import app.pranavjayaraj.apod.ViewModel.VModel;
import app.pranavjayaraj.apod.ViewModel.ViewModelCallbacks;


public class MainActivity extends AppCompatActivity implements FragmentChangeListener{
    private static final String TAG_PICTURE_LIST_FRAGMENT = "tag_picture_list_fragment";
    private static final String TAG_PICTURE_DETAIL_FRAGMENT = "tag_picture_detail_fragment";
    private static final String TAG_VIEW_PAGER_FRAGMENT = "tag_view_pager_fragment";
    private static final String TAG_SPLASH_SCREEN_FRAGMENT = "tag_splash_screen_fragment";

    private VModel mVModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVModel = ViewModelProviders.of(this).get(VModel.class);
        loadInitialData();
        Progress();
    }

    private void Progress()
    {
        ProgressFragment progressFragment = new ProgressFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, progressFragment, TAG_SPLASH_SCREEN_FRAGMENT)
                .commit();
    }

    private void loadInitialData(){
        mVModel.loadInitialData(new ViewModelCallbacks() {
            @Override
            public void onResponse(String message)
            {
                attachPictureListFragment();
                Log.d("FLOW TEST", "ON RESPONSE RECEIVED IN MAINACTIVITY: " + message);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
                Looper.prepare();
                attachPictureListFragment();
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG)
                        .show();
                Log.d("FLOW TEST", "ON FAILURE RECEIVED IN MAINACTIVITY: " + throwable.getMessage());
            }
        });
    }



    private void attachPictureListFragment() {
        APODListFragment apodListFragment = new APODListFragment();
        apodListFragment.setEnterTransition(new Fade());

        Bundle args = new Bundle();
        apodListFragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,
                        apodListFragment,
                        APODListFragment.class.getSimpleName())
                .addToBackStack(TAG_PICTURE_LIST_FRAGMENT)
                .commit();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public void onBackPressed() {
        Log.d("BACKSTACKTEST", ""+ getSupportFragmentManager().getBackStackEntryCount());
        if(getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void attachDetailAPOD(int clickedPosition, ImageView sharedImageView)
    {
        APODDetailFragment apodDetailFragment = new APODDetailFragment();
        Bundle args = new Bundle();
        args.putInt(APODDetailFragment.EXTRA_KEY_PICTURE_POSITION,clickedPosition);
        apodDetailFragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,
                        apodDetailFragment,
                        APODDetailFragment.class.getSimpleName())
                .addToBackStack(TAG_PICTURE_DETAIL_FRAGMENT)
                .commit();
    }

    @Override
    public void attachImageView(int position, ImageView sharedImageView) {
        mVModel.setCurrentPosition(position);
        mVModel.setHasAccessedViewPager(true);

       APODViewPager viewPager = new APODViewPager();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container,
                        viewPager,
                        APODViewPager.class.getSimpleName())
                .addToBackStack(TAG_VIEW_PAGER_FRAGMENT)
                .commit();
    }

}
