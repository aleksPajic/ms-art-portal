package com.portal.art.app.services;

import com.portal.art.app.common.exceptions.ArgumentOutOfRangeException;
import com.portal.art.app.models.Art;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaginationServiceTest {

    PaginationService<Art> paginationService;
    int pageNumber;
    int pageSize;

    @Test
    public void givenPageNumberSizeAndDataList_whenDataListEmpty_thenEmptyListReturned() throws ArgumentOutOfRangeException {
        // given
        initData(1, 10);
        List<Art> pageableList = createPageableList(0);

        // when
        List<Art> pageData = paginationService.getPageData(pageableList, pageNumber, pageSize);

        // then
        assertThat(pageData).isEmpty();
    }

    @Test
    public void givenPageNumberSizeAndDataList_whenPageNumberInvalid_thenThrowException() {
        // given
        initData(-5, 10);
        List<Art> pageableList = createPageableList(0);

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // when
            paginationService.getPageData(pageableList, pageNumber, pageSize);
        });
    }

    @Test
    public void givenPageNumberSizeAndDataList_whenPageSizeInvalid_thenThrowException() {
        // given
        initData(1, -5);
        List<Art> pageableList = createPageableList(0);

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // when
            paginationService.getPageData(pageableList, pageNumber, pageSize);
        });
    }

    @Test
    public void givenPageNumber1PageSize3AndDataListWith2Entries_whenGetPageDataCalled_thenReturn2Entries() throws ArgumentOutOfRangeException {
        // given
        initData(1, 3);
        List<Art> pageableList = createPageableList(2);

        // when
        List<Art> pageData = paginationService.getPageData(pageableList, pageNumber, pageSize);

        // then
        assertThat(pageData.size()).isEqualTo(2);
    }

    @Test
    public void givenPageNumber1AndPageSizeValidAndDataListWithLessThenPageSizeEntries_whenGetPageDataCalled_thenReturnAllDataListEntries() throws ArgumentOutOfRangeException {
        // given
        initData(1, 5);
        int dataListEntries = pageSize - 2;
        List<Art> pageableList = createPageableList(dataListEntries);

        // when
        List<Art> pageData = paginationService.getPageData(pageableList, pageNumber, pageSize);

        // then
        assertThat(pageData.size()).isEqualTo(pageableList.size());
        assertThat(pageData.get(0)).isNotNull();
        assertThat(pageData.get(0)).isEqualTo(pageableList.get(0));
    }

    @Test
    public void givenPageNumberGreaterThanDataListPages_whenGetPageDataCalled_thenThrowArgumentOutOfRangeException() {
        // given
        // second page cannot be retrieved from data list because there is not enough entries for 2 pages
        initData(2, 5);
        int dataListEntries = pageSize - 2;
        List<Art> pageableList = createPageableList(dataListEntries);

        // then
        Assertions.assertThrows(ArgumentOutOfRangeException.class, () -> {
            // when
            paginationService.getPageData(pageableList, pageNumber, pageSize);
        });
    }

    @Test
    public void givenPageNumberSizeValidAndDataListNull_whenGetPageDataCalled_thenThrowArgumentOutOfRangeException() {
        // given
        initData(2, 5);

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // when
            paginationService.getPageData(null, pageNumber, pageSize);
        });
    }

    @Test
    public void givenPageNumberSizeValidAndDataListValid_whenGetPageDataCalled_returnRequiredPage() throws ArgumentOutOfRangeException {
        // given
        initData(2, 3);
        List<Art> pageableList = createPageableList(20);

        // when
        List<Art> result = paginationService.getPageData(pageableList, pageNumber, pageSize);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(pageSize);
        assertThat(result.get(0)).isEqualTo(pageableList.get(3));
        assertThat(result.get(1)).isEqualTo(pageableList.get(4));
        assertThat(result.get(2)).isEqualTo(pageableList.get(5));
    }

    @Test
    public void givenLastPageNumberAndPageSizeAndDataListWithLastPageEntriesLessThanPageSize_whenGetPageDataCalled_returnLastPageWithLessThanPageSizeEntries()
            throws ArgumentOutOfRangeException {
        // given
        int lastPage = 2;
        int pageSizeValue = 5;
        int lastPageSize = 3;
        int dataListEntries = (lastPage - 1) * pageSizeValue + lastPageSize;
        initData(lastPage, pageSizeValue);
        List<Art> pageableList = createPageableList(dataListEntries);

        // when
        List<Art> result = paginationService.getPageData(pageableList, pageNumber, pageSize);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(lastPageSize);
        assertThat(result.get(0)).isEqualTo(pageableList.get(5));
        assertThat(result.get(1)).isEqualTo(pageableList.get(6));
        assertThat(result.get(2)).isEqualTo(pageableList.get(7));
    }

    private void initData(int pageNumber, int pageSize) {
        paginationService = new PaginationService<Art>();
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    private List<Art> createPageableList(int size) {
        List<Art> pageableList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            pageableList.add(new Art());
        }

        return pageableList;
    }
}
