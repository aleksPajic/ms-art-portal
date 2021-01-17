package com.portal.art.app.services;

import com.portal.art.app.models.PortalPageable;

import java.util.Collections;
import java.util.List;

public class PaginationService {

    public List<PortalPageable> getPageData(List<PortalPageable> pageableList, int pageNumber, int pageSize) {
        if (pageNumber < 0 || pageSize < 0) {
            throw new IllegalArgumentException();
        }
        return Collections.emptyList();
    }
}
