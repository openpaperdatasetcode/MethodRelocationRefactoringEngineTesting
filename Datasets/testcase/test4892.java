package com.source;

import com.target.TargetAbstract;

public abstract class SourceAbstract {
    protected TargetAbstract targetField;

    // Static nested class
    protected static class StaticNested {
        TargetAbstract process(TargetAbstract obj) {
            return obj;
        }
    }

    // Anonymous inner class
    private Runnable anonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class");
        }
    };

    // Recursive method to be moved
    protected TargetAbstract recursiveMethod(int depth) {
        // Type declaration statement
        StaticNested nested = new StaticNested();
        
        // Variable call
        anonymous.run();
        
        // Access instance method
        TargetAbstract current = nested.process(targetField);
        
        // Ternary operator with recursive call (this.methodName(arguments))
        return (depth <= 0) ? current : this.recursiveMethod(depth - 1);
    }

    protected abstract void abstractMethod();
}

package com.target;

public abstract class TargetAbstract {
    private int value;

    public TargetAbstract(int value) {
        this.value = value;
        
        // Local inner class
        class LocalProcessor {
            TargetAbstract increment(TargetAbstract obj) {
                return new ConcreteTarget(obj.value + 1);
            }
        }
    }

    protected abstract TargetAbstract copy();
}

class ConcreteTarget extends TargetAbstract {
    public ConcreteTarget(int value) {
        super(value);
    }

    @Override
    protected TargetAbstract copy() {
        try {
            return new ConcreteTarget(super.value);
        } catch (Exception e) {
            return null;
        }
    }
}
    