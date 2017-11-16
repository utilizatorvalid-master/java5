package org.utilizatorvalid.beans;

import java.io.Serializable;
import java.util.List;

interface DataBeanInterface<T extends DataBeanInterface> extends Serializable {

    List<T> getElements() throws Exception;
    List<String> getHeader();
}
