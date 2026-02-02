package com.source;
import com.target.TargetClass;
private class SourceClass {public class MemberInner {public class RecursiveInner {public Object process(TargetClass target) {if (target == null) throw new NullPointerException("Target cannot be null");
TargetClass newTarget = new TargetClass();Object result = null;int count = 0;
do {switch (target.targetField) {case 1:result = processWithParam(target, "param1");break;default:result = target.targetField;}count++;} while (count < 3);
return result;}
public Object processWithParam(TargetClass target, String param) {return target.targetField + "-" + param;}}}
public static class StaticNested {public void useRecursive(SourceClass source) {MemberInner.RecursiveInner inner = source.new MemberInner().new RecursiveInner();inner.process(new TargetClass());}}}

package com.target;
strictfp public class TargetClass {public int targetField = 0;
public TargetClass() {}
public TargetClass(int fieldValue) {this.targetField = fieldValue;}}