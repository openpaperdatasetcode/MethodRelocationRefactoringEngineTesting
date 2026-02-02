package test;
protected class SourceClass {// Static nested class (source class feature)static class SourceStaticNested {}
// Anonymous inner class (source class feature){new Runnable() {@Overridepublic void run() {}};}
// Member inner class (for source_inner_rec method position)class SourceInner {/**
Method Javadoc (method feature: method javadoc)
@param targetParam parameter of target class (satisfies per_condition)*/protected void recursiveMethod(TargetClass targetParam, int depth) {// Base case for recursionif (depth <= 0) {return;}
// VariableDeclarationStatement (method feature)private int targetFieldVal = targetParam.staticNested.field;// Match target_feature: obj.field (targetParam.staticNested.field) & "3"if (targetFieldVal == 3) {variableCall();}
// For statement (method feature)for (int i = 0; i < depth; i++) {System.out.println("Recursion depth: " + (depth - i));}
// Recursive call (source_inner_rec method position)recursiveMethod(targetParam, depth - 1);}
// Variable call target (method feature)private void variableCall() {}}}
protected class TargetClass {// Static nested class (target class feature)static class TargetStaticNested {int field = 3; // Field for "obj.field" & "3" (target_feature)}
// Target class static nested instance (for method access)TargetStaticNested staticNested = new TargetStaticNested();}
