package model;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class ConversorNo implements SingleValueConverter {

    @Override
    public String toString(Object object) {
        return ((No) object).getId();
    }

    @Override
    public Object fromString(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean canConvert(Class type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
