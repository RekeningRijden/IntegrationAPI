package pagination;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by martijn on 25-6-2016.
 */
public class PaginationTest {

    private Pagination pagination;

    public PaginationTest() {
    }

    @Before
    public void setUp() {
        this.pagination = new Pagination();
    }

    @Test
    public void testConstructor() {
        for (int pageSize = 0; pageSize < 100; pageSize++) {
            for (int pageIndex = 0; pageIndex < 100; pageIndex++) {
                Pagination pagination = new Pagination(pageSize, pageIndex);
                Assert.assertEquals(pageSize, pagination.getPageSize());
                Assert.assertEquals(pageIndex, pagination.getPageIndex());
            }
        }
    }

    @Test
    public void testPageSize() {
        for (int i = 0; i < 100; i++) {
            this.pagination.setPageSize(i);
            Assert.assertEquals(i, this.pagination.getPageSize());
        }
    }

    @Test
    public void testPageIndex() {
        for (int i = 0; i < 100; i++) {
            this.pagination.setPageIndex(i);
            Assert.assertEquals(i, this.pagination.getPageIndex());
        }
    }

    @Test
    public void testTotalCount() {
        for (int i = 0; i < 100; i++) {
            this.pagination.setTotalCount(i);
            Assert.assertEquals(i, this.pagination.getTotalCount());
        }
    }
}
