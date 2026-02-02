package source;
import target.OuterTarget;import java.util.List;import java.util.ArrayList;
public abstract class SourceClass {private class MemberInner {void innerMethod() {}}
public void sourceMethod() {class LocalInner {void localMethod() {}}}
/**
Javadoc for overloadedMethod
@param param integer parameter
@throws Exception when error occurs*/void overloadedMethod(int param) throws Exception {OuterTarget.TargetClass target = new OuterTarget.TargetClass();int var = 1;assert target.field == var;
synchronized (this) {this.overloadedMethod(new ArrayList<>());target.field = var;}}
/**
Javadoc for overloadedMethod
@param list list parameter with bounds
@throws Exception when error occurs*/void overloadedMethod(List<? extends Number> list) throws Exception {OuterTarget.TargetClass target = new OuterTarget.TargetClass();int var = 1;assert target.field == var;
synchronized (this) {this.overloadedMethod(1);target.field = var;}}}
package target;
public class OuterTarget {class TargetClass {int field;}}