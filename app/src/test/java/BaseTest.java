import android.os.Build;

import com.techja.myapplication.App;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import okhttp3.mockwebserver.MockWebServer;

@Config(manifest = Config.NONE, sdk = Build.VERSION_CODES.O_MR1, application = App.class)
@RunWith(RobolectricTestRunner.class)
public abstract class BaseTest {
    MockWebServer mockWebServer;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockWebServer=new MockWebServer();
        mockWebServer.start();
    }
}
