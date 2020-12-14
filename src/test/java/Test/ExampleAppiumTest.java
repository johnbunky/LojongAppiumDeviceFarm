package Test;

import Base.BaseTest;
import Page.SomePage;
import org.junit.Test;

public class ExampleAppiumTest extends BaseTest {
    SomePage somePage = new SomePage();

    @Test
    public void testWiki() {

        clickElement(somePage.search);
        setText(somePage.searchInput, "selenium");
    }
}
