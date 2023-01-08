package ba.unsa.etf.rpr.domain;

/**
 * Interface that forces all POJO beans to have an ID field.
 *
 * @author Benjamin Kadic
 */
public interface Idable {

    void setId(int id);

    int getId();
}