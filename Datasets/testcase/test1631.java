package test;
import java.util.ArrayList;import java.util.List;
class SourceClass {public class MemberInner1 {public class MemberInner2 {// Method in source_inner with default access modifierint process(TargetClass target) {// Transient VariableDeclarationStatement with 2 obj.field accessestransient String field1 = target.inner.data;transient String field2 = target.inner.data + "_suffix";
// Expression statementtarget.inner.counter++;
// Protected NullLiteral (2 occurrences)protected Object nullVal1 = null;protected Object nullVal2 = null;if (target.inner.data == nullVal1) {target.inner.data = "default";}
// Variable call - access target's fieldint total = target.inner.counter;
// Raw type usageList rawList = new ArrayList();rawList.add(target.inner.data);
// Depends on inner classTargetClass.InnerProcessor processor = target.new InnerProcessor();total += processor.process(target.inner);
// Requires try-catchtry {if (target.inner.data.equals(nullVal2)) {throw new NullPointerException("Data is null");}} catch (NullPointerException e) {total = -1;}
return total;}}}}
class TargetClass {public Inner inner = new Inner();
public class Inner {public String data;public int counter;}
public class InnerProcessor {public int process(Inner inner) {return inner.data.length();}}}