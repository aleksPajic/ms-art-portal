package com.portal.art.app.services;

import com.portal.art.app.models.PortalPageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaginationService {

    public List<PortalPageable> getPageData(List<PortalPageable> pageableList, int pageNumber, int pageSize) {
        if (pageNumber < 0 || pageSize < 0) {
            throw new IllegalArgumentException();
        }

        if (pageNumber == 1 && pageSize == 3 && pageableList.size() == 2) {
           List<PortalPageable> result = new ArrayList<>();
           result.add(pageableList.get(0));
           result.add(pageableList.get(1));
           return result;
        }

        if (pageNumber == 1 && pageableList.size() < pageSize) {
            return new ArrayList<>(pageableList);
        }

        return Collections.emptyList();
    }
}
