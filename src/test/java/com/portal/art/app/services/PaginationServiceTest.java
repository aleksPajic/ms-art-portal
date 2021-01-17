package com.portal.art.app.services;

import com.portal.art.app.models.PortalPageable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class PaginationServiceTest {

    @Test
    public void givenPageNumberSizeAndDataList_whenDataListEmpty_thenEmptyListReturned() {
        // given
        PaginationService paginationService = new PaginationService();
        int pageNumber = 1;
        int pageSize = 10;
        List<PortalPageable> pageableList = new ArrayList<>();

        // when
        List<PortalPageable> pageData = paginationService.getPageData(pageableList, pageNumber, pageSize);

        // then
        assertThat(pageData).isEmpty();
    }

    @Test
    public void givenPageNumberSizeAndDataList_whenPageNumberInvalid_thenThrowException() {
        // given
        PaginationService paginationService = new PaginationService();
        int pageNumber = -5;
        int pageSize = 10;
        List<PortalPageable> pageableList = new ArrayList<>();

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // when
            paginationService.getPageData(pageableList, pageNumber, pageSize);
        });
    }

}
