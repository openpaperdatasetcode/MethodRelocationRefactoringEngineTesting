package test;
public class SourceClass {static class SourceStaticNested1 {}static class SourceStaticNested2 {}static String sourceStaticField = "staticFieldVal";
void instanceMethod(TargetClass target) {TargetClass.TargetInner targetInner = target.new TargetInner();TargetClass newTarget = new TargetClass();
for (int i = 0; i < 3; i++) {String varCall = target.targetField;targetInner.setInnerField(varCall + sourceStaticField);}
int infixResult = 10 + Integer.parseInt(target.targetField);switch (infixResult) {case 10:return;case 20:target.targetField = "updated";return;default:return;}}}
private class TargetClass {String targetField = "10";
class TargetInner {private String innerField;
public void setInnerField(String field) {this.innerField = field;}
public String getInnerField() {return this.innerField;}}}
class OthersClass {private int callChainedMethods() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();source.instanceMethod(target);return target.new TargetInner().getInnerField().length();}
static {OthersClass others = new OthersClass();int result = others.callChainedMethods();}}
