
package publish;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the publish. package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publish.
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DataTypeOutingArray }
     * 
     * @return
     *     the new instance of {@link DataTypeOutingArray }
     */
    public DataTypeOutingArray createDataTypeOutingArray() {
        return new DataTypeOutingArray();
    }

    /**
     * Create an instance of {@link DataTypeOuting }
     * 
     * @return
     *     the new instance of {@link DataTypeOuting }
     */
    public DataTypeOuting createDataTypeOuting() {
        return new DataTypeOuting();
    }

    /**
     * Create an instance of {@link DataTypeInscription }
     * 
     * @return
     *     the new instance of {@link DataTypeInscription }
     */
    public DataTypeInscription createDataTypeInscription() {
        return new DataTypeInscription();
    }

    /**
     * Create an instance of {@link DataTypeActivityArray }
     * 
     * @return
     *     the new instance of {@link DataTypeActivityArray }
     */
    public DataTypeActivityArray createDataTypeActivityArray() {
        return new DataTypeActivityArray();
    }

    /**
     * Create an instance of {@link DataTypeActivity }
     * 
     * @return
     *     the new instance of {@link DataTypeActivity }
     */
    public DataTypeActivity createDataTypeActivity() {
        return new DataTypeActivity();
    }

}
