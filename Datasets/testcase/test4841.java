package test.refactoring;

interface SourceInterface {
    class StaticNestedClass {
        TargetInterface.TargetInner targetInnerField;
    }
    
    class MemberInnerClass {
        void useTarget(TargetInterface.TargetInner inner) {
            inner.process();
        }
    }
    
    protected void methodToMove(TargetInterface target) {
        private StaticNestedClass obj = new StaticNestedClass();
        obj.targetInnerField = target.new TargetInner();
        
        loopLabel: for (int i = 0; i < 5; i++) {
            new TargetInterface.TargetInner() {
                {
                    super();
                    this.process();
                }
            };
            
            if (i == 3) break loopLabel;
            new MemberInnerClass()::useTarget;
        }
        
        TargetInterface.TargetInner inner = new TargetInterface.TargetInner();
        inner.field = "value";
        inner.process();
    }
}

interface TargetInterface {
    class TargetInner {
        String field;
        
        public TargetInner() {}
        
        public TargetInner(String val) {
            this();
            this.field = val;
        }
        
        void process() {
            class LocalInnerClass {
                void print() {
                    System.out.println(field);
                }
            }
            new LocalInnerClass().print();
        }
    }
}
    