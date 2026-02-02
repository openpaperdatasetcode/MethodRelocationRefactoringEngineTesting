import java.util.List;
import java.util.ArrayList;

// Parent class for target generic class extends feature
class TargetParentClass {
    protected String parentData = "ParentData";
}

// Interface for source class implements feature
interface DataProcessor<T> {
    void process(T... data) throws Exception;
}

// Source generic class (final modifier, implements DataProcessor, same package as target)
final class SourceClass<T extends CharSequence> implements DataProcessor<T> {
    // Field of target class (satisfies per_condition)
    private AbstractTargetClass<String> targetField = new AbstractTargetClass<String>() {};

    // Type parameter usage (source_class feature)
    private T genericData;

    // First member inner class (source_class feature)
    class FirstInnerClass {
        // Second member inner class (source_class feature)
        class SecondInnerClass {
            // Varargs method to be moved (default access, void return, source_inner position)
            void processTarget(T... values) {
                // Requires try-catch block
                try {
                    // With bounds (generic type with bounds)
                    class BoundedClass<U extends Number & Comparable<U>> {
                        U processValue(U value) {
                            return value;
                        }
                    }
                    BoundedClass<Integer> boundedInstance = new BoundedClass<>();

                    // Switch statement
                    int switchVar = targetField.getSwitchValue();
                    switch (switchVar) {
                        case 1:
                            // Expression statement
                            targetField.setData("Case 1");
                            break;
                        case 2:
                            targetField.setData("Case 2");
                            break;
                        default:
                            targetField.setData("Default");
                    }

                    // Variable call to target field
                    List<String> dataList = targetField.getDataList();
                    // Access instance method of target class
                    for (T val : values) {
                        // Expression statement
                        dataList.add(val.toString());
                        // Variable call (bounded class instance method)
                        Integer processed = boundedInstance.processValue(dataList.size());
                        // Access instance method
                        targetField.updateCounter(processed);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Implemented method from DataProcessor
    @Override
    public void process(T... data) throws Exception {
        new FirstInnerClass().new SecondInnerClass().processTarget(data);
    }

    // Setter for generic data
    public void setGenericData(T genericData) {
        this.genericData = genericData;
    }
}

// Target abstract generic class (abstract modifier, extends TargetParentClass, same package as source)
abstract class AbstractTargetClass<U> extends TargetParentClass {
    private String data;
    private int switchValue = 1;
    private int counter = 0;
    private List<String> dataList = new ArrayList<>();

    // Variable call / access instance method methods
    public int getSwitchValue() {
        return switchValue;
    }

    public void setSwitchValue(int switchValue) {
        this.switchValue = switchValue;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void updateCounter(int value) {
        this.counter += value;
    }

    public int getCounter() {
        return counter;
    }
}