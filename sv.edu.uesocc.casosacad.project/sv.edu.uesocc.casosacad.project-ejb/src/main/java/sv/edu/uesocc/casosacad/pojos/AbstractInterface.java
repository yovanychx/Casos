package sv.edu.uesocc.casosacad.pojos;

import java.util.List;


/**
 *
 * @author wxlter97
 * @param <T>
 */

public interface AbstractInterface<T> {

  public void create(T t);

    public void edit(T t);
    
    public void remove(T t);

    public T find(Object o);

    public List<T> findAll();
    
    public List<T> findRange(int first, int pageSize);

    public int count();
    
    public List<T> findBy(String parameter, String value);
    
    public List<T> findByJoined(String parameter, Object value);
  
    public List<T> findByMultiple(List<Object> parameters);
    
}
