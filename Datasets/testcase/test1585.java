package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface OverrideAnnotation {}
abstract class ParentSource {// Parent method with protected access (violated by public override)protected abstract TargetClass process();}
protected class SourceClass extends ParentSource {public static class StaticNested<T> {T value;}
public class MemberInner {public TargetClass getTarget() {return new TargetClass();}}
@OverrideAnnotation@Overridepublic TargetClass process() { // Override violation (public > protected)MemberInner inner = new MemberInner();TargetClass target = inner.getTarget();
// Raw type usageStaticNested rawNested = new StaticNested();rawNested.value = "raw_data";
// Generic method in while loopint index = 0;while (index < 1) {StaticNested<TargetClass> genericNested = new StaticNested<>();genericNested.value = inner.getTarget();target = genericNested.value;index++;}
// Array access (2 occurrences)String[] dataArr = {"a", "b"};target.name = dataArr[0];String temp = dataArr[1];this.var = temp; // this.var = var
// Variable call - access target's fieldtarget.id = 100;
return target;}
private String var;}
class TargetClass {public int id;public String name;}