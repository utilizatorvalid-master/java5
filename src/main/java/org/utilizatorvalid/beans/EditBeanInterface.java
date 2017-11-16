package org.utilizatorvalid.beans;

import java.io.Serializable;
import java.util.List;

public interface EditBeanInterface<T extends EditBeanInterface> extends Serializable {
    List<String> getAttributes();
}
