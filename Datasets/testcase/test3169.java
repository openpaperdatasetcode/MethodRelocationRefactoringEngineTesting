package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
class ParentClass {}
final class TargetClass extends ParentClass {int targetField;static class TargetStaticNested {}}
class SourceClass {static class SourceStaticNested {}class MemberInner {List<String> innerMethod() {return new ArrayList<>();}}
/**
Method Javadoc
Processes TargetClass instance and returns base type
@param target TargetClass instance
@return int base type result
@throws IOException if IO error occurs*/protected int methodToMove(TargetClass target) throws IOException { // requires_try_catch// Variable callint var = target.targetField;
// Expression statementtarget.targetField = var + 5;
// Raw typeList rawList = new ArrayList();
// Uses outer thisSourceClass outer = SourceClass.this;SourceStaticNested nested = new SourceStaticNested();
// Depends on inner classMemberInner inner = new MemberInner();
// Try statementtry {rawList.add(target.targetField);} catch (Exception e) {throw new IOException("Processing failed", e);}
// Call_method in ternary operatorsList<String> result = (var > 0) ? new MemberInner().innerMethod() : inner.this.innerMethod();
return target.targetField;}}
