package persistence.dao;

import java.util.ArrayList;

/**
 * @author Benbiga Badr
 */
public abstract class AbstractDAO {

    public AbstractDAO(){
        super();
    }

    public abstract boolean addObject(Object o);

    public abstract boolean modifyObject(Object o_new);

    public abstract boolean removeObject(Object o);

    public abstract <T> ArrayList<T> getAllObject();
}
