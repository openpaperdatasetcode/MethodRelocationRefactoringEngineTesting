package test.refactoring;

interface ParentInterface {
    private int parentMethod() {
        return 42;
    }
}

interface SourceInterface extends ParentInterface {
    class SourceInner {
        @Override
        public void overridingMethod(TargetInterface.TargetInnerRec param) {
            do {
                if (param == null) {
                    throw new IllegalArgumentException();
                }
                
                GenericClass<String> generic = new GenericClass<>();
                generic.process("test");
                this.overridingMethod(param);
                
                for (int i = 0; i < ParentInterface.super.parentMethod(); i++) {
                    param = new TargetInterface.TargetInnerRec(i);
                    TargetInterface.TargetInnerRec.method(param);
                }
            } while (param.count < 5);
        }
    }

    // First anonymous inner class
    Runnable anon1 = new Runnable() {
        public void run() {
            new SourceInner().overridingMethod(new TargetInterface.TargetInnerRec(0));
        }
    };

    // Second anonymous inner class
    Runnable anon2 = new Runnable() {
        public void run() {
            anon1.run();
        }
    };
}

strictfp interface TargetInterface {
    /**
     * Javadoc for TargetInnerRec class
     * This is a member inner class in TargetInterface
     */
    class TargetInnerRec {
        int count;

        public TargetInnerRec(int count) {
            this.count = count;
        }

        static void method(TargetInnerRec obj) {
            obj.count++;
        }
    }
}

class GenericClass<T> {
    default void process(T value) {
        System.out.println(value);
    }
}
    