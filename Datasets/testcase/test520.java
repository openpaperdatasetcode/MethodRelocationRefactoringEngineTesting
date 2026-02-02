import java.util.Arrays;
import java.util.List;

// Parent class for source class extends feature
class ParentSourceClass {
    public int getBaseValue() {
        return 1; // Matches "1" in method_feature
    }
}

// Source normal class (default modifier, same package as target, extends ParentSourceClass)
class SourceClass extends ParentSourceClass {
    // Member inner class (source_class feature)
    class FirstInnerClass {
        // Recursive inner class (source_inner_rec position)
        class SecondInnerClass {
            // Method to be moved (normal, void return, final access, source_inner_rec)
            final void processTarget(TargetClass.InnerClass.RecursiveInnerClass targetParam) {
                // Method types parameter is:none (only target parameter, no type params)
                // Requires try-catch block
                try {
                    // Instance method with specified features (pos: expression, return base type)
                    int instanceMethodResult = new SourceClass().getBaseValue(); // new ClassName().method()
                    
                    // 3 public Assignment expressions
                    int var1 = 0;
                    var1 = targetParam.getCounter(); // 1st Assignment
                    String var2 = "";
                    var2 = targetParam.getData();    // 2nd Assignment
                    boolean var3 = false;
                    var3 = (var1 > 5);               // 3rd Assignment

                    // Variable call
                    List<String> dataList = targetParam.getDataList();
                    
                    // Enhanced for statement
                    for (String data : dataList) {
                        // If statement
                        if (data.isEmpty()) {
                            continue;
                        }
                        // Variable call
                        targetParam.setData(data);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
}

// Target normal class (public modifier, same package as source)
public class TargetClass {
    // Inner class (for target_inner_rec)
    class InnerClass {
        // Recursive inner class (target_inner_rec)
        class RecursiveInnerClass {
            private int counter;
            private String data;
            private List<String> dataList = Arrays.asList("a", "b", "c");

            // Variable call methods
            public int getCounter() {
                return counter;
            }

            public void setCounter(int counter) {
                this.counter = counter;
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
        }
    }

    // Local inner class (target_feature)
    public void methodWithLocalInner() {
        class TargetLocalInnerClass {
            void printTargetInfo() {
                System.out.println(TargetClass.this.toString());
            }
        }
        new TargetLocalInnerClass().printTargetInfo();
    }
}