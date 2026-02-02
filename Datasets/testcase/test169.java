```java
package pkg29647;

interface SomeInterface {
    void interfaceMethod();
}

class BaseClass {
    public Object baseMethod() {
        return null;
    }
}

class TargetClass implements SomeInterface {
    /**
     * Javadoc comment
     */
    public void interfaceMethod() {}

    static class NestedStaticClass {
        Object nestedMethod() {
            return null;
        }
    }

    class TargetInner {
        Object targetMethod() {
            return null;
        }
    }
}

class Container {
    protected class SourceClass extends BaseClass {
        TargetClass targetField = new TargetClass();

        class MemberInner extends BaseClass {
            @Override
            public Object baseMethod() {
                Object result = null;
                int counter = 0;
                
                processBlock:
                {
                    if (counter > 0) {
                        break processBlock;
                    }
                    
                    assert counter == 0 : "Assertion failed";
                    
                    while (counter < 1) {
                        counter++;
                        break processBlock;
                    }
                    
                    throw new RuntimeException("Should not reach");
                }
                
                return result;
            }
            
            void overloadedMethod() {}
            void overloadedMethod(int param) {}
        }
        
        void createAnonymous() {
            new BaseClass() {
                void anonymousMethod() {
                    System.out.println("Anonymous");
                }
            };
        }
    }
}

class CallerClass {
    protected Object executeCall() {
        Container container = new Container();
        Container.SourceClass source = container.new SourceClass();
        Container.SourceClass.MemberInner inner = source.new MemberInner();
        
        Object val = null;
        while (val == null) {
            val = inner.baseMethod();
        }
        return val;
    }
}
```