package test;
interface SourceInterface {TargetInterface targetField = new TargetInterface() {};
private TargetInterface methodToMove(int depth, int... keywords) throws Exception {if (depth <= 0) {if (targetField.field == 3) {return targetField;}return null;}
for (int k : keywords) {if (k > TargetInterface.STATIC_FIELD) {variableCall();}}
class LocalInner {}new LocalInner();
new Runnable() {};
return methodToMove(depth - 1, keywords);}
private void variableCall() {}}
interface TargetInterface {int field = 0;int STATIC_FIELD = 5;
static class TargetStaticNested {}}
