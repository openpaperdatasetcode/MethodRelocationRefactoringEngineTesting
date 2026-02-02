import java.util.ArrayList;
import java.util.List;

// Parent record for super constructor invocation
protected record ParentRecord(String parentField) {}

// Source record class (protected modifier, same package as target)
protected record SourceRecord(String sourceData, TargetRecord targetField) extends ParentRecord {
    // Per_condition: source contains field of target
    
    // Super constructor invocation (record canonical constructor)
    public SourceRecord {
        super(sourceData); // Super constructor invocation
    }

    // First member inner class (source_class feature)
    class FirstInnerClass {}

    // Second member inner class (source_class feature)
    class SecondInnerClass {}

    // Method to be moved (normal, List<String> return, private access, source position)
    private List<String> processTarget() throws Exception { // requires_throws
        List<String> result = new ArrayList<>();
        // Access instance field of target inner recursive class
        TargetRecord.InnerClass.RecursiveInnerClass recInner = targetField.new InnerClass().new RecursiveInnerClass();
        
        // 3 abstract Pattern expressions (using pattern matching in instanceof)
        // 1st abstract Pattern expression
        if (recInner.getData() instanceof String s && s.length() > 0) {
            result.add(s);
        }
        // 2nd abstract Pattern expression
        if (recInner.getCount() instanceof Integer i && i > 0) {
            result.add(String.valueOf(i));
        }
        // 3rd abstract Pattern expression
        if (recInner.getFlag() instanceof Boolean b && b) {
            result.add(String.valueOf(b));
        }

        // Variable call
        recInner.setCount(recInner.getCount() + 1);
        // Expression statement
        result.add(recInner.getData());
        // Access instance field
        result.add(String.valueOf(targetField.value()));

        return result;
    }
}

// Target record class (public modifier, same package as source)
public record TargetRecord(String value) {
    // Member inner class (target_feature)
    class InnerClass {
        // Recursive inner class (target_inner_rec)
        class RecursiveInnerClass {
            // Instance fields for access
            private String data = "test";
            private Integer count = 0;
            private Boolean flag = true;

            // Variable call methods
            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public Integer getCount() {
                return count;
            }

            public void setCount(Integer count) {
                this.count = count;
            }

            public Boolean getFlag() {
                return flag;
            }

            public void setFlag(Boolean flag) {
                this.flag = flag;
            }
        }
    }
}