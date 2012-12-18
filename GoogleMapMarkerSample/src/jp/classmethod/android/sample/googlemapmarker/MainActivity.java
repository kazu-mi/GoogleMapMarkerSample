package jp.classmethod.android.sample.googlemapmarker;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * GoogleMap ��\������ Activity.
 */
public class MainActivity extends FragmentActivity {

    /** GoogleMap �C���X�^���X. */
    private GoogleMap mMap;

    /** �}�[�J�[. */
    private Marker mMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Map ��K�v�ȏꍇ�̂݃Z�b�g�A�b�v����.
     */
    private void setUpMapIfNeeded() {
        if (mMap == null) {
            FragmentManager manager = (FragmentManager) getSupportFragmentManager();
            SupportMapFragment frag = (SupportMapFragment) manager.findFragmentById(R.id.map);
            mMap = frag.getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * Map ���Z�b�g�A�b�v����.
     */
    private void setUpMap() {

        // �}�[�J�[��\��ܓx�E�o�x
        LatLng location = new LatLng(35.697261, 139.774728);
        
        // �}�[�J�[�̐ݒ�
        MarkerOptions options = new MarkerOptions();
        options.position(location);
        options.title("�N���X���\�b�h�������");
        options.snippet(location.toString());
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_logo);
        options.icon(icon);
        
        // �}�b�v�Ƀ}�[�J�[��ǉ�
        mMarker = mMap.addMarker(options);
        
//        mMap.setInfoWindowAdapter(new CustomInfoAdapter());
    }

    /**
     * InfoAdapter �̃J�X�^���N���X.
     */
    private class CustomInfoAdapter implements InfoWindowAdapter {
    
        /** Window �� View. */
        private final View mWindow;
    
        /**
         * �R���X�g���N�^.
         */
        public CustomInfoAdapter() {
            mWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);
        }
    
        @Override
        public View getInfoWindow(Marker marker) {
            render(marker, mWindow);
            return mWindow;
        }
    
        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    
        /**
         * InfoWindow ��\������.
         * @param marker {@link Marker}
         * @param view {@link View}
         */
        private void render(Marker marker, View view) {
            // �����łǂ� Marker ���^�b�v���ꂽ�����ʂ���
            if (marker.equals(mMarker)) {
                // �摜
                ImageView badge = (ImageView) view.findViewById(R.id.badge);
                badge.setImageResource(R.drawable.ic_logo);
            }
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView snippet = (TextView) view.findViewById(R.id.snippet);
            title.setText(marker.getTitle());
            snippet.setText(marker.getSnippet());
        }
    
    }

}
