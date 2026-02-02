package test;
import java.util.ArrayList;import java.util.List;
protected abstract class Target {protected abstract class MemberInner {abstract class InnerRec {private String recData;
public InnerRec(String data) {this.recData = data;}
public String getRecData() {return recData;}
public void updateData(String newData) {this.recData = newData;}}}
public abstract MemberInner createInner();}
sealed abstract class Source permits SourceImpl {class FirstMemberInner {// First member inner class featureString process(String input) {return input.toUpperCase();}}
class SecondMemberInner {// Second member inner class featureint countLength(String input) {return input.length();}}
private Object handle(Target.MemberInner.InnerRec targetRec) {List<Object> result = new ArrayList<>();
// Type declaration statementTarget.MemberInner targetInner = new Target() {@Overridepublic MemberInner createInner() {return new MemberInner() {@OverrideInnerRec createRec(String data) {return new InnerRec(data);}};}}.createInner();
// Expression statementtargetRec.updateData("updated_by_source");
// Variable callresult.add(targetRec.getRecData());
// Access instance methodFirstMemberInner firstInner = new FirstMemberInner();SecondMemberInner secondInner = new SecondMemberInner();result.add(firstInner.process(targetRec.getRecData()));result.add(secondInner.countLength(targetRec.getRecData()));
// No new exceptionreturn result;}}
final class SourceImpl extends Source {// Permitted subclass of sealed Source}