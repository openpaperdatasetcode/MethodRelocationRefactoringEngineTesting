package test;
import java.lang.reflect.Method;import java.util.List;
/**
Super record for extension
*/
record SuperRecord(int code) {}
/**
Source record with extensions and inner classes*/public record SourceRecord(String name) extends SuperRecord {protected String outerProtected = "protected_value";
class MemberInner {}
Object methodToMove(TargetRecord... targets) {class LocalInner {}LocalInner local = new LocalInner();
for (TargetRecord target : targets) {TargetRecord.MemberInner inner = target.new MemberInner();inner.variableCall();
// Access outer protected fieldString access = this.outerProtected;
// Switch caseswitch (inner.state) {case 1:inner.count++;break;default:inner.count--;}
// Ternary operator with generic method callint result = (inner.count > 0)? TargetRecord.MemberInner.genericMethod(inner, List.of("a")): 0;
// Reflection usagetry {Method method = TargetRecord.MemberInner.class.getMethod("variableCall");method.invoke(inner);} catch (Exception e) {}}
return null;}}
/**
Target record with Javadoc and member inner class
/
public record TargetRecord(String data) {
/*
Member inner class of TargetRecord*/class MemberInner {int state;int count;
void variableCall() {}
synchronized static <T> int genericMethod(MemberInner inner, List<T> list) {return inner.count + list.size();}}}