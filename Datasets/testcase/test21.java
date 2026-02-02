```java
package pkg;

class SourceClass {
    private Runnable anon1 = new Runnable() {
        public void run() {}
    };
    private Runnable anon2 = new Runnable() {
        public void run() {}
    };

    private static int staticCounter = 0;

    Object moveMethodCandidate(target_inner_rec target) {
        @SuppressWarnings("unused")
        int annotatedVar = 0;
        Object obj = new Object();

        for (int i = 0; i < 5; i++) {
            if (i == 3) continue;
            ;
        }

        switch (staticCounter) {
            case 0: break;
            default: break;
        }

        class LocalClass {
            void innerMethod() {
                Object o = new Object();
                o.toString();
            }
        }

        try {
            staticCounter++;
            target.performAction();
        } catch (Exception e) {}

        return new target_inner_rec();
    }
}

class target_inner_rec {
    void methodWithLocalClass() {
        class LocalInner {}
    }

    void performAction() {}
}
```