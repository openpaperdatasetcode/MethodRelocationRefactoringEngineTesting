package source;
import java.io.IOException;import java.util.ArrayList;import target.Target;
public class Source extends SuperClass {protected int outerProtectedField = 10;class MemberInner {}static class StaticNested {}
protected Source(Target targetParam) throws IOException {class LocalType {int localVal = targetParam.getTargetField();}new LocalType();
Target newTarget = new Target();if (targetParam == null) {throw new IllegalArgumentException("Target parameter cannot be null");}
Object var = targetParam;int accessOuter = Source.this.outerProtectedField;ArrayList rawList = new ArrayList();rawList.add(targetParam);}}
class SuperClass {}
package target;
public abstract class Target {private int targetField;
public int getTargetField() {return targetField;}}