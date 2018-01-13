package model;

import com.thoughtworks.xstream.converters.SingleValueConverter;

/**
 *
 * @author garcez
 */
public class ConversorNo implements SingleValueConverter {

    @Override
    public String toString(Object object) {
        return ((No) object).getId();
    }

    @Override
    public Object fromString(String string) {
        return new No(string);
    }

    @Override
    public boolean canConvert(Class type) {
        return type.equals(No.class);
    }
}
