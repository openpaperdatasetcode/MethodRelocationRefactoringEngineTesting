package same.pkg;
import java.util.List;import java.util.ArrayList;
class Source {// Member inner classclass MemberInner {}
// Anonymous inner classRunnable anon = new Runnable() {@Overridepublic void run() {Source.staticMethod(new Target() {@OverrideTarget.Inner createInner() {return new Inner();}});}};
// Static method with required featurespublic static List<String> staticMethod(Target targetParam) {// Type declaration statementTarget.Inner targetInner;
// While statementint i = 0;while (i < targetParam.count) {i++;}
// Variable call (access target's inner class and its field)targetInner = targetParam.createInner();List<String> result = targetInner.items;
return result;}}
abstract class Target {int count = 5; // Field of target contained in source
// Member inner classclass Inner {List<String> items = new ArrayList<>();}
// Abstract method to create inner class instanceabstract Inner createInner();}