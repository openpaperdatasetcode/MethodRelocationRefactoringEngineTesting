package com.refactoring.test;

// Source class (protected modifier, same package as target)
protected class SourceClass {
    // Static nested class in source
    static class SourceStaticNested {
        int value;
    }

    // Member inner class in source
    class SourceMemberInner {
        String data;
    }

    // Method to be refactored (instance, default access, returns TargetClass type, has TargetClass parameter)
    TargetClass moveCandidateMethod(TargetClass targetParam) {
        int count = 0;
        TargetClass result = new TargetClass();
        
        while (count < 10) {
            count++;
            // Variable call
            result.value = targetParam.value;
            
            // Continue statement
            if (count % 2 == 0) {
                continue;
            }
        }
        
        return result;
    }
}

// Target class (default modifier, same package as source)
class TargetClass {
    int value;

    // Static nested class (target_inner_rec as specified)
    static class target_inner_rec {
        String id;
    }
}