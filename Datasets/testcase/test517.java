import java.util.Objects;

// Parent class for source record's extends feature
class RecordParent {
    protected String protectedField = "ProtectedValue";
    
    protected int getProtectedValue() {
        return 10;
    }
}

// Source record class (default modifier, same package as target, extends RecordParent)
record SourceRecord(String sourceData, ProtectedTargetRecord targetField) extends RecordParent {
    // Per_condition: source contains field of target
    
    // Final method to be moved (normal type, base type return, default package access, source position)
    final int processTarget() {
        // Method types parameter is:none
        int result = 0;
        int count = 0;
        
        // Access outer protected (from RecordParent)
        this.protectedField = "UpdatedProtected";
        result += this.getProtectedValue();
        
        // Uses outer this (SourceRecord's this)
        if (Objects.isNull(this.targetField)) {
            // NullPointerException (no_new_exception: no explicit new Exception())
            throw new NullPointerException("Target field cannot be null");
        }
        
        // Do statement
        do {
            // Variable call (target field)
            int switchVar = this.targetField.id();
            // Switch case
            switch (switchVar) {
                case 1:
                    // Expression statement
                    result += 5;
                    break;
                case 2:
                    result += 10;
                    break;
                default:
                    result += 1;
            }
            count++;
            // While statement
        } while (count < this.targetField.count());
        
        // Additional expression statement
        result = result * 2;
        return result;
    }

    // First local inner class (source_class feature)
    public void methodWithFirstLocalInner() {
        class FirstLocalInner {
            void printSourceData() {
                System.out.println(SourceRecord.this.sourceData);
            }
        }
        new FirstLocalInner().printSourceData();
    }

    // Second local inner class (source_class feature)
    public void methodWithSecondLocalInner() {
        class SecondLocalInner {
            int calculateSum() {
                return SourceRecord.this.targetField.id() + SourceRecord.this.targetField.count();
            }
        }
        new SecondLocalInner().calculateSum();
    }
}

// Target record class (protected modifier, same package as source, empty target_feature)
protected record ProtectedTargetRecord(int id, int count) {}

// Subclass for call_method (public modifier, sub_class type)
public class SourceRecordSubclass extends SourceRecord {
    public SourceRecordSubclass(String sourceData, ProtectedTargetRecord targetField) {
        super(sourceData, targetField);
    }

    // Instance method (call_method target_feature), return type int, pos in if/else
    public int callProcessTarget() {
        int status = this.targetField().count();
        if (status > 0) {
            // Lambda expression: (parameters) -> methodBody
            Runnable lambda = () -> System.out.println("Processing target: " + this.targetField().id());
            lambda.run();
            return this.processTarget();
        } else {
            return 0;
        }
    }
}