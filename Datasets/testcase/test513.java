import java.util.ArrayList;
import java.util.List;

// Source generic class (default modifier, same package as target)
class SourceClass<S> {
    // Field of target class (satisfies per_condition)
    private TargetClass<T> targetField;

    // First member inner class
    class FirstInnerClass {
        // Second member inner class (recursive inner - source_inner_rec)
        class SecondInnerClass {
            // Instance method to be moved (public, void return, source_inner_rec position)
            public void methodToMove() {
                // Constructor invocation
                targetField = new TargetClass<>();
                // Empty statement
                ;
                // Variable call
                int switchVar = targetField.getSwitchValue();
                // Switch statement
                switch (switchVar) {
                    case 1:
                        // Expression statement
                        targetField.addElement("Case 1");
                        break;
                    case 2:
                        targetField.addElement("Case 2");
                        break;
                    default:
                        targetField.addElement("Default");
                }
                // Additional expression statement
                targetField.clearElements();
            }
        }
    }
}

// Target generic class (default modifier, same package as source)
class TargetClass<T> {
    // Member inner class (target_feature)
    class InnerDataHolder {
        private List<T> elements = new ArrayList<>();
    }

    private final InnerDataHolder dataHolder = new InnerDataHolder();
    private int switchValue;

    public int getSwitchValue() {
        return switchValue;
    }

    public void setSwitchValue(int switchValue) {
        this.switchValue = switchValue;
    }

    public void addElement(T element) {
        dataHolder.elements.add(element);
    }

    public void clearElements() {
        dataHolder.elements.clear();
    }
}