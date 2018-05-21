import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.view.ListViewer;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ListViewerTest {

    private ListViewer<Client> mock;

    @Before
    public void setUp() {
        mock = mock(ListViewer.class);
    }

    @Test
    public void simple_test() {
        mock.listView(anyList());
        verify(mock).listView(anyList());
    }
    @Test
    public void illegal_verify_test() {
        verifyZeroInteractions(mock);
    }

}
