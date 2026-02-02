package test;
import java.util.List;import java.util.ArrayList;
abstract class TargetClass {class TargetInner {class TargetInnerRec {int field;}}}
class SourceClass {private TargetClass targetField = new TargetClass() {};
static class SourceStaticNested {}
{new Object() {}; // Anonymous inner class}
TargetClass.TargetInner.TargetInnerRec methodToMove() {// Constructor in ternary operatorList<String> list = (1 > 0) ? new ArrayList ArrayList<>(super.hashCode()) : new ArrayList<>();
// Expression statementTargetClass.TargetInner inner = targetField.new TargetInner();TargetClass.TargetInner.TargetInnerRec innerRec = inner.new TargetInnerRec();
// Variable callint var = innerRec.field;
// Access instance fieldinnerRec.field = var + 1;
return innerRec;}}
class OthersClass {public Object callMethod() {SourceClass source = new SourceClass();return new SourceClass.SourceStaticNested().toString() +source.methodToMove().field;}}