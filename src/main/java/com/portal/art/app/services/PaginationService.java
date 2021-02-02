package com.portal.art.app.services;

import com.portal.art.app.common.exceptions.ArgumentOutOfRangeException;
import com.portal.art.app.models.PortalPageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaginationService {

    public List<PortalPageable> getPageData(List<PortalPageable> pageableList, int pageNumber, int pageSize) throws ArgumentOutOfRangeException {
        if (pageNumber < 0 || pageSize < 0 || pageableList == null) {
            throw new IllegalArgumentException();
        }

        if (pageableList.isEmpty()) {
            return Collections.emptyList();
        }

        int numberOfAvailablePages = (int) Math.ceil((double)pageableList.size() / pageSize);
        if(pageNumber > numberOfAvailablePages) {
            throw new ArgumentOutOfRangeException();
        }

        int pageStartingIndex = (pageNumber - 1) * pageSize;
        List<PortalPageable> result = new ArrayList<>();
        int lastPageIndex = Math.min(pageStartingIndex + pageSize, pageableList.size());
        for(int i = pageStartingIndex; i < lastPageIndex; i++) {
            result.add(pageableList.get(i));
        }

        return result;
    }
}
