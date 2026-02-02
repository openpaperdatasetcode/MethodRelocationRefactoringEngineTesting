// Source enum class (private modifier, same package as target)
private enum SourceEnum {
    INSTANCE;

    // Field of target enum class (satisfies per_condition)
    private TargetEnum targetField = TargetEnum.PRIMARY;

    // First anonymous inner class (source_class feature)
    private Runnable firstAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("First anonymous inner class in source enum");
        }
    };

    // Second anonymous inner class (source_class feature)
    private java.util.function.Consumer<String> secondAnonymous = new java.util.function.Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println("Second anonymous inner class: " + s);
        }
    };

    // Instance method to be moved (public, returns TargetEnum type, source position)
    public TargetEnum.InnerClass.RecursiveInnerClass processTarget() {
        // Super constructor invocation (enum constant constructor context)
        super();

        // VariableDeclarationStatement (protected modifier, pos: diff_package_others, target_feature: this.field, 1)
        protected int fieldVal = 1; // target_feature: 1
        this.fieldVal = this.ordinal(); // target_feature: this.field

        // Variable call to target field
        TargetEnum.InnerClass.RecursiveInnerClass recInner = 
            targetField.new InnerClass().new RecursiveInnerClass();
        
        // Additional variable call
        recInner.setData("Processed data: " + fieldVal);
        recInner.setOrdinal(this.ordinal());

        return recInner; // Return TargetEnum inner recursive type (TargetClas Type)
    }

    // Field for VariableDeclarationStatement this.field access
    private int ordinal = 0;

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }
}

// Target enum class (default modifier, same package as source)
enum TargetEnum {
    PRIMARY, SECONDARY;

    // Member inner class (target_feature)
    public class InnerClass {
        // Recursive inner class (target_inner_rec)
        public class RecursiveInnerClass {
            private String data;
            private int ordinal;

            // Variable call methods
            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public int getOrdinal() {
                return ordinal;
            }

            public void setOrdinal(int ordinal) {
                this.ordinal = ordinal;
            }
        }
    }
}