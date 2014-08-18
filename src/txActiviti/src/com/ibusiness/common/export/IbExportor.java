package com.ibusiness.common.export;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface IbExportor {
    void export(HttpServletResponse response, IbTableModel tableModel)
            throws IOException;
}
