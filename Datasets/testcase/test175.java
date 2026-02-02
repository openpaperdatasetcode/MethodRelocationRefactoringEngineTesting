```java
package pkg;

public interface SourceInterface {
    class Inner1 {
        private Target.field1 field1;
        private Target.field2 field2;
    }
    
    class Inner2 {
        // Second member inner class
    }
}

interface Target<T> {
    class field1 {}
    class field2 {}
    
    default Object movedMethod() {
        // VariableDeclarationStatement with private modifier
        private final java.util.ArrayList list = new java.util.ArrayList(); // raw type
        
        // For statement
        for (int i = 0; i < 2; i++) {
            // Access instance field (outer interface fields)
            if (field1 != null) break;
        }
        
        // Switch case
        switch (System.getProperty("test")) {
            case "reflection":
                // Used by reflection
                try {
                    Class<?> clazz = Class.forName("pkg.Target");
                    clazz.getMethod("toString").invoke(this); // variable call
                } catch (Exception e) {
                    // no_new_exception
                }
                break;
        }
        
        // Uses outer this (interface instance)
        return this.toString() + list.size();
    }
}
```