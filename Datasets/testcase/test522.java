import java.sql.SQLException;
import java.util.List;

// Parent class for source class extends feature
class ParentClass {
    protected String parentField;

    public ParentClass(String parentField) {
        this.parentField = parentField;
    }
}

// Interface for source class implements feature
interface DataProcessor {
    Object processData() throws SQLException;
}

// Source normal class (protected modifier, same package as target, extends ParentClass, implements DataProcessor)
protected class SourceClass extends ParentClass implements DataProcessor {
    // Field of target class (satisfies per_condition)
    private SealedTargetClass targetField;

    // Member inner class (source_class feature)
    class FirstInnerClass {
        // Recursive inner class (source_inner_rec position)
        class SecondInnerClass {
            // Instance method to be moved (final, Object return, source_inner_rec position)
            final Object processTarget() throws SQLException {
                // Super constructor invocation (in inner class context)
                super();
                
                // With bounds (generic type with bounds)
                class BoundedClass<T extends Number & Comparable<T>> {
                    T processValue(T value) {
                        return value;
                    }
                }
                BoundedClass<Integer> boundedInstance = new BoundedClass<>();
                
                // Variable call to target field
                if (targetField == null) {
                    // SQLException (no_new_exception: no explicit new Exception())
                    throw new SQLException("Target field cannot be null");
                }
                
                // Variable call to target inner class
                SealedTargetClass.InnerClass inner = targetField.new InnerClass();
                inner.setData("Processed data");
                Integer processedValue = boundedInstance.processValue(inner.getCount());
                
                // Return combined object (satisfies Object return type)
                return List.of(inner.getData(), processedValue);
            }
        }
    }

    // Local inner class (source_class feature)
    public void methodWithLocalInner() {
        class LocalInnerClass {
            void printSourceInfo() {
                System.out.println(SourceClass.this.toString());
            }
        }
        new LocalInnerClass().printSourceInfo();
    }

    // Implemented method from DataProcessor
    @Override
    public Object processData() throws SQLException {
        return new FirstInnerClass().new SecondInnerClass().processTarget();
    }

    // Super constructor invocation for SourceClass
    public SourceClass(String parentField) {
        super(parentField);
        this.targetField = new SealedTargetClass();
    }
}

// Target normal class (sealed modifier, same package as source)
sealed class SealedTargetClass permits SealedTargetClass.InnerClass {
    // Member inner class (target_feature)
    final class InnerClass extends SealedTargetClass {
        private String data;
        private int count = 0;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}