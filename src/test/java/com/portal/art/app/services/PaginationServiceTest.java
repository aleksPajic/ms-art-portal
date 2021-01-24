package com.portal.art.app.services;

import com.portal.art.app.models.Art;
import com.portal.art.app.models.PortalPageable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class PaginationServiceTest {

    PaginationService paginationService;
    int pageNumber;
    int pageSize;

    @Test
    public void givenPageNumberSizeAndDataList_whenDataListEmpty_thenEmptyListReturned() {
        // given
        initData(1, 10);
        List<PortalPageable> pageableList = new ArrayList<>();

        // when
        List<PortalPageable> pageData = paginationService.getPageData(pageableList, pageNumber, pageSize);

        // then
        assertThat(pageData).isEmpty();
    }

    @Test
    public void givenPageNumberSizeAndDataList_whenPageNumberInvalid_thenThrowException() {
        // given
        initData(-5, 10);
        List<PortalPageable> pageableList = new ArrayList<>();

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
        List<PortalPageable> pageableList = new ArrayList<>();

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // when
            paginationService.getPageData(pageableList, pageNumber, pageSize);
        });
    }

    @Test
    public void givenPageNumber1PageSize3AndDataListWith2Entries_whenGetPageDataCalled_thenReturn2Entries() {
        // given
        initData(1, 3);
        List<PortalPageable> pageableList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            pageableList.add(new Art());
        }

        // when
        List<PortalPageable> pageData = paginationService.getPageData(pageableList, pageNumber, pageSize);

        // then
        assertThat(pageData.size()).isEqualTo(2);
    }

    @Test
    public void givenPageNumberSizeValidAndDataListWithLessThenPageSizeEntries_whenGetPageDataCalled_thenReturnAllDataListEntries() {
        
    }

    private void initData(int pageNumber, int pageSize) {
        paginationService = new PaginationService();
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
