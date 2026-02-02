package source;
import target.TargetClass;import java.util.ArrayList;import java.util.List;
public class SourceClass extends ParentClass {private int outerPrivate = 20;class MemberInner {}
{new Runnable() {@Overridepublic void run() {}};}
@Overrideprotected Object process(TargetClass target) {TargetClass.Inner targetInner = target.new Inner();List<Object> result = new ArrayList<>();int count = 0;
public switch (targetInner.field) {case 1:result.add(targetInner.field + SourceClass.this.outerPrivate);count++;continue;default:result.add(targetInner.field);}
return result;}}
class ParentClass {protected Object process(TargetClass target) {return null;}}

targetpackage target;
public class TargetClass {class Inner {int field = 1;
{new Runnable() {@Overridepublic void run() {}};}}}