package com.portal.art.app.services;

import com.portal.art.app.common.exceptions.ArgumentOutOfRangeException;
import com.portal.art.app.models.PortalPageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaginationService {

    public List<PortalPageable> getPageData(List<PortalPageable> pageableList, int pageNumber, int pageSize) throws ArgumentOutOfRangeException {
        if (pageNumber < 0 || pageSize < 0) {
            throw new IllegalArgumentException();
        }

        if (pageNumber == 1 && pageableList.size() < pageSize) {
            return new ArrayList<>(pageableList);
        }
        int numberOfAvailablePages = pageableList.size() / pageSize;
        if(pageNumber > numberOfAvailablePages) {
            throw new ArgumentOutOfRangeException();
        }

        return Collections.emptyList();
    }
}
