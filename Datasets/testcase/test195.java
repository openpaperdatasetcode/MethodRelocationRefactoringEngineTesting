```java
public class Test7400 {
    @interface CustomAnnotation {}

    enum SourceEnum {
        CONSTANT;

        private int outerPrivate = 10;

        Object anonymousField = new Object() {
            @Override
            public String toString() {
                return "Anonymous";
            }
        };

        class SourceInner {
            @CustomAnnotation
            private void recursiveMethod(int count) {
                if (count <= 0) return;
                
                int val = outerPrivate;
                
                switch (count) {
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
                
                for (int i = 0; i < count; i++) {
                    if (i == 1) continue;
                    
                    class LocalBase {
                        LocalBase(int num) {}
                    }
                    class LocalDerived extends LocalBase {
                        LocalDerived() {
                            super(5);
                        }
                    }
                    new LocalDerived();
                    
                    String s = "test";
                    s.length();
                }
                
                recursiveMethod(count - 1);
            }
        }

        private int caller() {
            return 0;
        }

        {
            int[] arr = { caller() };
        }
    }

    public enum TargetEnum {
        TARGET_CONST;

        public class TargetInner {}
    }
}
```